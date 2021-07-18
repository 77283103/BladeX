package org.springblade.abutment.feign;

import org.springblade.abutment.entity.*;
import org.springblade.abutment.vo.*;
import org.springblade.contract.entity.ContractBorrowApplicationEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.ContractTemplateEntity;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Feign接口类
 *
 * @author xhb
 */
@FeignClient(
        value = "blade-abutment",
	fallback = IAbutmentFallback.class
)
public interface IAbutmentClient {

    String API_PREFIX = "/client/abutment";
    String ORG="/org";
	String EKP = "/ekp";
	String DOC = "/doc";
	String E_SEAL = "/eSeal";
	String SEND_FILE_TO_FASTDFS= API_PREFIX + "sendFastDfs";
	String SEND_EKP=API_PREFIX+"sendEkp";
	String COUNTERPART_INSERT_OR_UPDATE=API_PREFIX+"getCounterpart";
	String ORGANIZATION_INFO_INCREMENT=API_PREFIX +ORG+"/getOrganization";
	String EKP_SEND_FORM = API_PREFIX + EKP + "/sendForm";
	String EKP_SEND_FORM_POST = API_PREFIX + EKP + "/sendFormPost";
	String EKP_SEND_MULTI_POST =API_PREFIX +EKP+"/sendMultiPost";
	String E_EKP_TOKEN =API_PREFIX +EKP +"/token";
	String EKP_NODE_FORM_POST = API_PREFIX + EKP + "/nodeFormPost";
	String EKP_SIG_FORM_POST=API_PREFIX+EKP+"pushNotSig";
	String DOC_QUERY_INFO = API_PREFIX + DOC + "/queryInfo";
	String SIGNATURE_QUERY_INFO=API_PREFIX +"/signatureQueryInfo";
	String E_SEAL_UPLOAD_FILE = API_PREFIX + E_SEAL + "/uploadFiles";
	String E_SEAL_SINGLE_SIGN = API_PREFIX + E_SEAL + "/singleSign";
	String E_SEAL_SINGLE_SIGN_POST= API_PREFIX + E_SEAL + "/singleSignPost";
	String E_SEAL_SEND_SMS = API_PREFIX + E_SEAL + "/sendSms";
	String E_SEAL_SINGLE_SIGN_MULTI = API_PREFIX + E_SEAL + "/multiSign";
	String E_SEAL_READ_SIGNED = API_PREFIX + E_SEAL + "/readSigned";
	String E_SEAL_COMPANY_INFO = API_PREFIX + E_SEAL + "/queryCompanyInfo";
	String E_SEAL_TOKEN = API_PREFIX + E_SEAL + "/token";
	String CONTRACT_BORROWING=API_PREFIX+"/appContract";
	String TEMPLATE_APP=API_PREFIX+"/templateApp";
	String EKP_SEND_BATCH_POST=API_PREFIX+"/sendEkpBatchPost";



	/**
	 * 模板审批
	 * 推送EKP信息
	 * @param entity
	 * @return
	 */
	@PostMapping(TEMPLATE_APP)
	R<EkpVo> temEkpFormPost(@RequestBody ContractTemplateEntity entity);
	/**
	 * 合同借阅
	 * 推送EKP信息
	 * @param entity
	 * @return
	 */
	@PostMapping(CONTRACT_BORROWING)
	R<EkpVo> borEkpFormPost(@RequestBody ContractBorrowApplicationEntity entity);
	/**
	 * 合同起草-(批量独立  批量范本）
	 * 推送EKP信息
	 * @param entity
	 * @return
	 */
	@PostMapping(EKP_SEND_BATCH_POST)
	R<EkpVo> sendEkpBatchPost(@RequestBody ContractFormInfoEntity entity);
	/**
	 * 合同起草-(独立、范本）
	 * 推送EKP信息
	 * @param entity
	 * @return
	 */
	@PostMapping(EKP_SEND_FORM_POST)
	R<EkpVo> sendEkpFormPost(@RequestBody ContractFormInfoEntity entity);
	/**
	 * 合同起草-多方
	 * 推送EKP信息
	 * @param entity
	 * @return
	 */
	@PostMapping(EKP_SEND_MULTI_POST)
	R<EkpVo> sendEkpMultiPost(@RequestBody ContractFormInfoEntity entity);
	/**
	 * 合同节点
	 * 推送EKP节点信息
	 * @param entity
	 * @return
	 */
	@PostMapping(EKP_NODE_FORM_POST)
	R<EkpVo> nodeEkpFormPost(@RequestBody ContractFormInfoResponseVO entity);

	/**
	 * 归档预警
	 * 推送EKP节点信息
	 * @return
	 */
	@PostMapping(EKP_SIG_FORM_POST)
	R<List<EkpVo>> pushNotSig(ContractFormInfoEntity entity);
	/**
	 * 获取依据信息
	 * @param entity
	 * @return
	 */
	@GetMapping(DOC_QUERY_INFO)
	R<List<DocVo>> queryDocInfo(DocEntity entity);
	/**
	 * 获取统一集团签章子公司信息
	 * @param entity
	 * @return
	 */
	@GetMapping(SIGNATURE_QUERY_INFO)
	R<AsDictVo> querySigatureInfo(AsDict entity);

	/**
	 * 上传合同
	 * @param entity
	 * @return
	 */
	@GetMapping(E_SEAL_UPLOAD_FILE)
	R<List<UploadFileVo>> uploadFiles(UploadFileEntity entity);

	/**
	 * 合同盖章 单个的
	 * @param entity
	 * @return
	 */
	@PostMapping(E_SEAL_SINGLE_SIGN)
	R<SingleSignVo> singleSign(@RequestBody SingleSignEntity entity);

	/**
	 * 合同盖章 单个的Post
	 * @param entity
	 * @return
	 */
	@PostMapping(E_SEAL_SINGLE_SIGN_POST)
	R<SingleSignVo> singleSignPost(@RequestBody SingleSignEntity entity);

	/**
	 * 发验证码短信
	 * @param entity
	 * @return
	 */
	@GetMapping(E_SEAL_SEND_SMS)
	R sendSms(SendSmsEntity entity);

	/**
	 * 合同盖章 批量的
	 * @param entity
	 * @return
	 */
	@GetMapping(E_SEAL_SINGLE_SIGN_MULTI)
	R<MultiSignVo> multiSign(MultiSignEntity entity);

	/**
	 * 获取合同地址
	 * @param entity
	 * @return
	 */
	@GetMapping(E_SEAL_READ_SIGNED)
	R<String> readSigned(ReadSignedEntity entity);

	/**
	 * 获取企业信息
	 * @param entity
	 * @return
	 */
    @GetMapping(E_SEAL_COMPANY_INFO)
	R<CompanyInfoVo> queryCompanyInfo(CompanyInfoEntity entity);

	/**
	 * 获取电子签章token
	 * @return
	 */
	@GetMapping(E_SEAL_TOKEN)
	R<String> token();

	/**
	 * 获取电子签章token
	 * @return
	 */
	@GetMapping(E_SEAL_TOKEN)
	R<String> tokenEkp();
	/**
	 * 推送数据
	 * @return
	 */
	@PostMapping(EKP_SEND_FORM)
	R<EkpVo> pushData(PushEkpEntity entity) throws Exception;
	/**
	 * 增量更新组织及人员信息数据
	 * @return
	 */
	@PostMapping(ORGANIZATION_INFO_INCREMENT)
	R<List<OrganizationVo>> getOrganizationInfoIncrement(@RequestBody OrgParme param);
	/**
	 * 获取相对方信息的数据
	 * @return
	 */
	@GetMapping(COUNTERPART_INSERT_OR_UPDATE)
	R<CounterpartVo> getCounterpart(CounterpartEntity entity);

	/**
	 * 向ekp推送数据
	 * @param pushEkpEntity
	 * @return R
	 */
	@PostMapping(SEND_EKP)
	R sendEkp(@RequestBody PushEkpEntity pushEkpEntity);


	@GetMapping(SEND_FILE_TO_FASTDFS)
	R sendFileToFastDfs(@RequestParam String filesIds);


}
