package org.springblade.abutment.feign;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.entity.*;
import org.springblade.abutment.service.IDocService;
import org.springblade.abutment.service.IESealService;
import org.springblade.abutment.service.IEkpService;
import org.springblade.abutment.vo.*;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 对接Feign实现类
 *
 * @author cc
 */
@ApiIgnore
@Slf4j
@RestController
public class AbutmentClient implements IAbutmentClient {

	@Autowired
	private IEkpService ekpService;
	@Autowired
	private IDocService docService;
	@Autowired
	private IESealService eSealService;

	@Value("${api.ekp.fdTemplateId}")
	private String fdTemplateId;

	@Override
	@PostMapping(EKP_SEND_FORM_POST)
	public R<EkpVo> sendEkpFormPost(ContractFormInfoEntity entity) {

		EkpVo ekpVo = null;
		try {
			PushEkpEntity pushEkpEntity = new PushEkpEntity();
			pushEkpEntity.setFdTemplateId(fdTemplateId);
			if(entity != null) {
				//17090089
				if(StrUtil.isNotEmpty("17090089") && StrUtil.isNotEmpty(entity.getAccording().get(0).getFileId())) {
					DocCreatorEntity docCreatorEntity = new DocCreatorEntity();
					//人员编号
					docCreatorEntity.setEmplno("17090089");
					pushEkpEntity.setDocCreator(docCreatorEntity);

					FormValuesEntity formValuesEntity = new FormValuesEntity();
					formValuesEntity.setFd_accord_id(entity.getAccording().get(0).getFileId());
					formValuesEntity.setFd_contract_id(entity.getId().toString());
					//pdf的id
					formValuesEntity.setFd_attachment_id(entity.getTextFilePdf());
					//合同起草流程类型
					if("10".equals(entity.getContractSoure())){
						formValuesEntity.setFd_contract_type("10");
					}else if("30".equals(entity.getContractSoure())||StrUtil.isNotEmpty(entity.getOtherInformation())){
						formValuesEntity.setFd_contract_type("30");
					}else{
						formValuesEntity.setFd_contract_type("20");
					}
					//合同主旨
					formValuesEntity.setFd_main(entity.getContractName());
					//合同大类
					formValuesEntity.setFd_broad(entity.getContractName());
					//合同主旨
					formValuesEntity.setFd_main(entity.getContractName());
					//申请用公章全称
					formValuesEntity.setFd_offical_seal(entity.getSealName());
					//相对方名称
					formValuesEntity.setFd_full_name(entity.getCounterpart().get(0).getName());
					//合同负责人
					formValuesEntity.setFd_emplno("17090089");
					//合同份数
					formValuesEntity.setFd_copies(entity.getShare());
					//合同期限
					//合同期限
					switch (entity.getContractPeriod()) {
						case "xysn":
							formValuesEntity.setFd_contract_period("sx");
							break;
						case "dysn":
							formValuesEntity.setFd_contract_period("dx");
							break;
						case "wzzqx":
							formValuesEntity.setFd_contract_period("wx");
							break;
					}
					//合同主旨
					formValuesEntity.setFd_main(entity.getContractName());
					pushEkpEntity.setFormValues(formValuesEntity);
					//依据id
					pushEkpEntity.setDocSubject(entity.getAccording().get(0).getFileId());
					pushEkpEntity.setToken(ekpService.getToken());
					if (StrUtil.isNotEmpty(pushEkpEntity.getToken())) {
						ekpVo = ekpService.pushData(pushEkpEntity);
					}
				}
			}
		} catch(Exception exception) {
			log.error(exception.getMessage());
		}
		return R.data(ekpVo);
	}

	@Override
	@GetMapping(DOC_QUERY_INFO)
	public R<List<DocVo>> queryDocInfo(DocEntity entity) {
		List<DocVo> docVo = null;
		try {
			entity.setToken(docService.getToken());
			if(StrUtil.isNotEmpty(entity.getToken())) {
				docVo = docService.getDocInfo(entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(docVo);
	}

	@Override
	@GetMapping(E_SEAL_UPLOAD_FILE)
	public R<List<UploadFileVo>> uploadFiles(UploadFileEntity entity) {
		List<UploadFileVo> uploadFileVo = null;
		try {
			String token = eSealService.getToken();
			if(StrUtil.isNotEmpty(token)) {
				if(StrUtil.isNotEmpty(entity.getIsMerge())) {
					uploadFileVo = eSealService.uploadFiles(token, entity);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(uploadFileVo);
	}

	@Override
	@PostMapping(E_SEAL_SINGLE_SIGN)
	public R<SingleSignVo> singleSign(SingleSignEntity entity) {
		SingleSignVo singleSignVo = null;
		try {
			String token = eSealService.getToken();
			if(StrUtil.isNotEmpty(token)) {
				singleSignVo = eSealService.singleSign(token, entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(singleSignVo);
	}

	@Override
	@PostMapping(E_SEAL_SINGLE_SIGN_POST)
	public R<SingleSignVo> singleSignPost(SingleSignEntity entity) {
		SingleSignVo singleSignVo = null;
		try {
			String token = eSealService.getToken();
			if(StrUtil.isNotEmpty(token)) {
				singleSignVo = eSealService.singleSign(token, entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(singleSignVo);
	}

	@Override
	@GetMapping(E_SEAL_SEND_SMS)
	public R sendSms(SendSmsEntity entity) {
		try {
			String token = eSealService.getToken();
			if(StrUtil.isNotEmpty(token)) {
				eSealService.sendSms(token, entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	@GetMapping(E_SEAL_SINGLE_SIGN_MULTI)
	public R<MultiSignVo> multiSign(MultiSignEntity entity) {
		MultiSignVo multiSignVo = null;
		try {
			String token = eSealService.getToken();
			if(StrUtil.isNotEmpty(token)) {
				multiSignVo = eSealService.multiSign(token, entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(multiSignVo);
	}

	@Override
	@GetMapping(E_SEAL_READ_SIGNED)
	public R<String> readSigned(ReadSignedEntity entity) {
		String url = null;
		try {
			String token = eSealService.getToken();
			if(StrUtil.isNotEmpty(token)) {
				url = eSealService.readSigned(token, entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(url);
	}

	@Override
	@GetMapping(E_SEAL_COMPANY_INFO)
	public R<CompanyInfoVo> queryCompanyInfo(CompanyInfoEntity entity) {
		CompanyInfoVo companyInfoVo = null;
		try {
			String token = eSealService.getToken();
			if(StrUtil.isNotEmpty(token)) {
				companyInfoVo = eSealService.getCompanyInfo(token, entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(companyInfoVo);
	}
}
