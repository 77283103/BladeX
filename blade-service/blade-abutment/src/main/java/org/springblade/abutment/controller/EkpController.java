package org.springblade.abutment.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.common.annotation.AutoLog;
import org.springblade.abutment.entity.ContractSubmit;
import org.springblade.abutment.entity.NotArchive;
import org.springblade.abutment.service.IESealService;
import org.springblade.abutment.vo.ContractVo;
import org.springblade.contract.feign.IContractClient;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.log.logger.BladeLogger;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * ekp API控制器
 * </p>
 *
 * @Author gym
 * @since 2020-11-23
 */
@Slf4j
@RestController
@RequestMapping("/ekp")
@Api(value = "ekp API控制器")
@AllArgsConstructor
public class EkpController {
	IContractClient contractClient;
	IESealService eSealService;
	IFileClient fileClient;
	BladeLogger logger;

	/**
	 * epk通过后返回给合同平台合同状态
	 *
	 * @return
	 */
	@PostMapping("/submit")
	@AutoLog
	@ApiOperation(value = "epk通过后返回给合同平台合同状态接口")
	public R queryOrganization(@RequestBody ContractSubmit contractSubmit) {
		String status = contractSubmit.getSubmitStatus();
		Long id = Long.valueOf(contractSubmit.getContractId());
		String ekp_number = contractSubmit.getEkp_number();
		log.info("epk通过后返回给合同平台合同状态接口[返回的合同状态为]" + id + "执行时间为" + new Date());
		return R.data(contractClient.saveContractFormInfo(id, status,ekp_number));
	}

	/**
	 * epk返回给合同平台未归档信息
	 *
	 * @return
	 */
	@PostMapping("/notArchive")
	@AutoLog
	@ApiOperation(value = "epk通过后返回给合同平台合同状态接口")
	public R saverArchiveNot(@RequestBody NotArchive notArchive) {
		String notArchiveReason = notArchive.getNotArchiveReason();
		Long id = Long.valueOf(notArchive.getContractId());
		Date estimateArchiveDate = notArchive.getEstimateArchiveDate();
		log.info("epk通过后返回给合同平台合同状态接口[返回的合同状态为]" + id + "执行时间为" + new Date());
		return R.data(contractClient.saverArchiveNot(id, estimateArchiveDate, notArchiveReason));
	}

	/**
	 * 获取token
	 */
	@GetMapping("/eToken")
	@ApiOperationSupport(order = 12)
	@ApiOperation(value = "获取token", notes = "")
	public R<String> eToken() {
		String token = null;
		try {
			token = eSealService.getToken();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(token);
	}

	/**
	 * EKP获取合同审批后状态以及相关归档文件链接信息
	 *合同抛转OA，流程通过后，三种用印形式发起的合同需回写合同归档的状态
	 * 三种用印形式：1）电子合同-对方平台   2）实体合同-我司用电子印 3）实体合同-我司不用电子印回写合同归档状态：
	 * 1）未用印
	 * 2）已用印未归档，需附上“用印时间“
	 * 3）已归档，需附上“归档时间+扫描归档合同的链接”
	 * @return
	 */
	@PostMapping("/getArchiveInfo")
	@AutoLog
	@ApiOperation(value = "epk通过后返回给合同平台合同状态接口")
	@ApiLog("EKP获取合同审批后状态以及相关归档文件链接信息")
	public R<ContractVo> queryArchiveInfo(@RequestBody ContractSubmit contractSubmit) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
		Long id = Long.valueOf(contractSubmit.getContractId());
		log.info("epk获取合同信息传到合同平台的[合同ID为]" + id + "执行时间为" + new Date());
		ContractFormInfoResponseVO vo = contractClient.getById(id).getData();
		ContractVo cv = new ContractVo();
		if (Func.isEmpty(vo) || Func.isNull(vo.getId())) {
			cv.setCode("0");
			return R.data(400, cv, "未查到相关合同信息！");
		}
		if ("1".equals(vo.getContractForm())) {
			cv.setCode("1");
			return R.data(200, cv, "该合同为电子合同-我司用印，不予以返回相关信息！");
		}
		//赋值合同用印相关
		if (Func.isNotEmpty(vo.getSealInfoEntity())) {
			if (Func.isNotEmpty(vo.getSealInfoEntity().getSignTime())) {
				cv.setSigningDate(simpleDateFormat.format(vo.getSealInfoEntity().getSignTime()));
			}
		}
		//赋值合同归档相关
		if (Func.isNotEmpty(vo.getSigningEntity())) {
			if (Func.isNotEmpty(vo.getSigningEntity().getSignDate())) {
				cv.setArchiveDate(simpleDateFormat.format(vo.getSigningEntity().getSignDate()));
			}
			if (Func.isNoneBlank(vo.getSigningEntity().getTextFiles())) {
				/**只传文件ID**/
//				cv.setArchiveFileLink(vo.getSigningEntity().getTextFiles());
				/**只传文件链接**/
				R<List<FileVO>> result = fileClient.getByIds(vo.getSigningEntity().getTextFiles());
				if (result.isSuccess()) {
					StringBuilder name = new StringBuilder();
					result.getData().forEach(fileVO -> {
						name.append("http://10.93.1.43:8107/contractFormInfo/downloadFiles?&id="+fileVO.getId());
						name.append(",");
					});
					name.substring(0, name.length());
					cv.setArchiveFileLink(name.substring(0, name.length() - 1));
				}
			}
		}
		cv.setCode("1");
		//赋值合同状态
		cv.setSubmitStatus(vo.getContractStatus());
		logger.info("queryArchiveInfo", JsonUtil.toJson(cv));
		return R.data(200, cv, "获取数据成功！");
	}
}
