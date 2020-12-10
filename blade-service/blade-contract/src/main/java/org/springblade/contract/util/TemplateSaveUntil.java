package org.springblade.contract.util;

import com.alibaba.fastjson.JSONObject;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.YwlANewDisplayEntity;
import org.springblade.contract.entity.YwlShopRecruitmentEntity;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.service.IYwlANewDisplayService;
import org.springblade.contract.service.IYwlShopRecruitmentService;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.vo.TemplateRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TemplateSaveUntil {
	@Autowired
	private IContractFormInfoService contractFormInfoService;
	@Autowired
	private IYwlANewDisplayService ywlANewDisplayService;
	@Autowired
	private IYwlShopRecruitmentService ywlShopRecruitmentService;
	//建一个静态的本类
	private static TemplateSaveUntil templateSaveUntil;

	//初始化
	@PostConstruct
	public void init() {
		templateSaveUntil = this;
	}

	public String templateSave(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO template, JSONObject j) {
		Long id = null;
		if (Func.isEmpty(contractFormInfoEntity.getId())) {
			contractFormInfoEntity.setContractSoure("30");
			contractFormInfoEntity.setContractStatus("10");
			if ("CLXY_42".equals(template.getTemplateCode())) {
				YwlANewDisplayEntity ywlANewDisplay = JSONObject.toJavaObject(j, YwlANewDisplayEntity.class);
				templateSaveUntil.ywlANewDisplayService.save(ywlANewDisplay);
				id = ywlANewDisplay.getId();
			} else if ("DZHT_35".equals(template.getTemplateCode())) {
				YwlShopRecruitmentEntity ywlShopRecruitment = JSONObject.toJavaObject(j, YwlShopRecruitmentEntity.class);
				templateSaveUntil.ywlShopRecruitmentService.save(ywlShopRecruitment);
				id = ywlShopRecruitment.getId();
				if (Func.isNotEmpty(template.getChangeContractId())) {
					contractFormInfoEntity.setChangeContractId(template.getChangeContractId());
					contractFormInfoEntity.setChangeCategory("10");
				}
				if ("DZHT_35".equals(template.getTemplateCode())) {
					YwlShopRecruitmentEntity ywlShopRecruitmentEntity = JSONObject.toJavaObject(j, YwlShopRecruitmentEntity.class);
					templateSaveUntil.ywlShopRecruitmentService.save(ywlShopRecruitmentEntity);
					id = ywlShopRecruitmentEntity.getId();
				}
				contractFormInfoEntity.setContractListId(id);
				templateSaveUntil.contractFormInfoService.save(contractFormInfoEntity);
			} else {
				if ("130".equals(template.getOriginalContractStatus())) {
					contractFormInfoEntity.setSubmitStatus("10");
					contractFormInfoEntity.setContractStatus("20");

					templateSaveUntil.contractFormInfoService.updateExportStatus(template.getOriginalContractStatus(), template.getChangeContractId());
				}
				templateSaveUntil.contractFormInfoService.updateById(contractFormInfoEntity);
				if ("CLXY_42".equals(template.getTemplateCode())) {
					YwlANewDisplayEntity ywlANewDisplay = JSONObject.toJavaObject(j, YwlANewDisplayEntity.class);
					ywlANewDisplay.setId(contractFormInfoEntity.getContractListId());
					templateSaveUntil.ywlANewDisplayService.updateById(ywlANewDisplay);
				} else if ("DZHT_35".equals(template.getTemplateCode())) {
					YwlShopRecruitmentEntity ywlShopRecruitment = JSONObject.toJavaObject(j, YwlShopRecruitmentEntity.class);
					ywlShopRecruitment.setId(contractFormInfoEntity.getContractListId());
					templateSaveUntil.ywlShopRecruitmentService.updateById(ywlShopRecruitment);
				}
			}
		}
		return "templateSave";
	}
}
