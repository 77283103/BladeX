package org.springblade.contract.feign;

import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.ContractTemplateEntity;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.vo.ContractTemplateResponseVO;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.TemplateEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Feign接口类
 *
 * @author xhb
 */
@FeignClient(
	value = AppConstant.APPLICATION_CONTRACT_NAME,
	fallback = IContractFallback.class
)
public interface IContractClient {

	String API_PREFIX = "/client";
	String SAVEBATCH=API_PREFIX+"/savebatch";
	String SAVEORUPDATE=API_PREFIX+"/save_or_update";
	String UPDATEBYID=API_PREFIX+"/updatebyid";
	String SELECTBYNAME=API_PREFIX+"/selectbyname";
	String CONTRACT = API_PREFIX + "/contractFormInfo";
	String STATUS=API_PREFIX +"/signingNot";
	String TEMPLATE_UPDATE = API_PREFIX + "/template_update";
	String CHOOSE=API_PREFIX + "/getChooseList";
	String CONTRACT_SAVE = API_PREFIX + "/contractSave";
	String TEMPLATE_GET_ID = API_PREFIX + "/template_getId";
	String UTILS_FILE=API_PREFIX +"/utils_file";
	String NOT_ARCHIVE_SAVE=API_PREFIX+"/not_archive_save";

	/**
	 * 保存相对方
	 * @param listInsert
	 * @return
	 */
	@PostMapping(SAVEBATCH)
	R< List<ContractCounterpartEntity>> saveBatch(@RequestBody List<ContractCounterpartEntity> listInsert);
	/**
	 * 保存或修改相对方
	 * @param list
	 * @return
	 */
	@PostMapping(SAVEORUPDATE)
	R<List<ContractCounterpartEntity>> saveOrUpdate(@RequestBody List<ContractCounterpartEntity> list);
	/**
	 * 修改相对方
	 * @param updateCounterpart
	 * @return
	 */
	@PostMapping(UPDATEBYID)
	R<Boolean> updateById(@RequestBody ContractCounterpartEntity updateCounterpart);

	/**
	 * 根据一统一代码查询
	 * @param unifiedSocialCreditCode
	 * @return
	 */
	@GetMapping(SELECTBYNAME)
	R<List<ContractCounterpartEntity>> selectByName(@RequestParam("unifiedSocialCreditCode") String  unifiedSocialCreditCode);
	/**
	 * 获取合同信息
	 * @param id
	 * @return
	 */
	@GetMapping(CONTRACT)
	R<ContractFormInfoResponseVO> getById(@RequestParam("id") Long id);
	/**
	 * 获取合同节点信息
	 * @param status
	 * @return
	 */
	@GetMapping(STATUS)
	R<List<ContractFormInfoEntity>> getByStatus(@RequestParam("status") String  status);
	/**
	 * 動態返回合同選擇的下拉選
	 * @return list
	 */
	@GetMapping(CHOOSE)
	R<List<ContractFormInfoEntity>> getChooseList();
	/**
	 * 更新模板里json
	 * @para @return
	 */
	@PostMapping(TEMPLATE_UPDATE)
	R<ContractTemplateResponseVO> templateUpdate(@RequestBody TemplateEntity entity);

	/**
	 * epk通过后返回给合同平台合同状态
	 * @para @return
	 * @return
	 */
	@GetMapping(CONTRACT_SAVE)
	R saveContractFormInfo(@RequestParam("id") Long id, @RequestParam("status") String status);

	/**
	 * 根据模板查询id
	 * @para @return
	 * @return
	 */
	@GetMapping(TEMPLATE_GET_ID)
	R<ContractTemplateEntity> getByTemplateId(@RequestParam("id") Long id);
	/**
	 * epk通过后返回给合同平台未归档信息
	 * @author jitwxs
	 * @date 2021/6/16 11:20
	 * @param id 合同id
	 * @param estimateArchiveDate 计划归档时间
	 * @param notArchiveReason  未归档原因
	 * @return org.springblade.core.tool.api.R
	 */
	@GetMapping(NOT_ARCHIVE_SAVE)
	R saverArchiveNot(@RequestParam("id") Long id, @RequestParam("estimateArchiveDate") Date estimateArchiveDate, @RequestParam("notArchiveReason") String notArchiveReason);

}
