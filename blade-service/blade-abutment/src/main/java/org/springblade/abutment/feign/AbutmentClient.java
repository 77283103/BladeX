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
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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
@AllArgsConstructor
@RestController
public class AbutmentClient implements IAbutmentClient {

	private IEkpService ekpService;
	private IDocService docService;
	private IESealService eSealService;

	@Value("${api.ekp.fdTemplateId}")
	private String fdTemplateId;

	@Override
	@GetMapping(EKP_SEND_FORM)
	public R<EkpVo> sendEkpForm(EkpEntity entity) {
		EkpVo ekpVo = null;
		try {
			PushEkpEntity pushEkpEntity = new PushEkpEntity();
			pushEkpEntity.setFdTemplateId(fdTemplateId);
			if(entity != null) {
				if(StrUtil.isNotEmpty(entity.getEmplno()) && StrUtil.isNotEmpty(entity.getDocSubject())) {
					DocCreatorEntity docCreatorEntity = new DocCreatorEntity();
					docCreatorEntity.setEmplno(entity.getEmplno());
					pushEkpEntity.setDocCreator(docCreatorEntity);

					FormValuesEntity formValuesEntity = new FormValuesEntity();
					formValuesEntity.setFd_parent_id(entity.getFd_parent_id());
					formValuesEntity.setFd_file_id(entity.getFd_file_id());
					formValuesEntity.setFd_name(entity.getFd_name());
					formValuesEntity.setFd_totle(entity.getFd_totle());
					formValuesEntity.setFd_cont_scop(entity.getFd_cont_scop());
					formValuesEntity.setFd_paydate(entity.getFd_paydate());
					formValuesEntity.setFd_starttime(entity.getFd_starttime());
					formValuesEntity.setFd_lasttime(entity.getFd_lasttime());
					formValuesEntity.setFd_dollar(entity.getFd_dollar());
					formValuesEntity.setFd_biaodi(entity.getFd_biaodi());
					formValuesEntity.setFd_tiaokuan(entity.getFd_tiaokuan());
					formValuesEntity.setFd_days(entity.getFd_days());
					formValuesEntity.setFd_percent1(entity.getFd_percent1());
					formValuesEntity.setFd_billday1(entity.getFd_billday1());
					formValuesEntity.setFd_percent2(entity.getFd_percent2());
					formValuesEntity.setFd_percent3(entity.getFd_percent3());
					formValuesEntity.setFd_billday2(entity.getFd_billday2());
					formValuesEntity.setFd_percent4(entity.getFd_percent4());
					formValuesEntity.setFd_percent5(entity.getFd_percent5());
					formValuesEntity.setFd_billday3(entity.getFd_billday3());
					formValuesEntity.setFd_percent6(entity.getFd_percent6());
					formValuesEntity.setFd_billday4(entity.getFd_billday4());
					formValuesEntity.setFd_percent7(entity.getFd_percent7());
					formValuesEntity.setFd_billday5(entity.getFd_billday5());
					formValuesEntity.setFd_percent8(entity.getFd_percent8());
					formValuesEntity.setFd_billday6(entity.getFd_billday6());
					formValuesEntity.setFd_pay(entity.getFd_pay());
					formValuesEntity.setFd_shouktk(entity.getFd_shouktk());
					formValuesEntity.setFd_shoukts(entity.getFd_shoukts());
					formValuesEntity.setFd_duty(entity.getFd_duty());
					pushEkpEntity.setFormValues(formValuesEntity);
					pushEkpEntity.setDocSubject(entity.getDocSubject());
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
	@GetMapping(E_SEAL_SINGLE_SIGN)
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
