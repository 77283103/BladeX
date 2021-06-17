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
import org.springblade.contract.feign.IContractClient;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

	/**
	 * epk通过后返回给合同平台合同状态
	 * @return
	 */
	@PostMapping("/submit")
	@AutoLog
	@ApiOperation(value = "epk通过后返回给合同平台合同状态接口")
	public R queryOrganization(@RequestBody ContractSubmit contractSubmit) {
		String status=contractSubmit.getSubmitStatus();
		Long id=Long.valueOf(contractSubmit.getContractId());
		log.info("epk通过后返回给合同平台合同状态接口[返回的合同状态为]"+id+"执行时间为"+new Date());
		return R.data(contractClient.saveContractFormInfo(id,status));
	}
	/**
	 * epk返回给合同平台未归档信息
	 * @return
	 */
	@PostMapping("/notArchive")
	@AutoLog
	@ApiOperation(value = "epk通过后返回给合同平台合同状态接口")
	public R saverArchiveNot(@RequestBody NotArchive notArchive) {
		String notArchiveReason=notArchive.getNotArchiveReason();
		Long id=Long.valueOf(notArchive.getContractId());
		Date estimateArchiveDate=notArchive.getEstimateArchiveDate();
		log.info("epk通过后返回给合同平台合同状态接口[返回的合同状态为]"+id+"执行时间为"+new Date());
		return R.data(contractClient.saverArchiveNot(id,estimateArchiveDate,notArchiveReason));
	}

	/**
	 * 获取token
	 */
	@GetMapping("/eToken")
	@ApiOperationSupport(order = 12)
	@ApiOperation(value = "获取token", notes = "")
	public R<String> eToken() {
		String token=null;
		try {
			token = eSealService.getToken();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(token);
	}

}
