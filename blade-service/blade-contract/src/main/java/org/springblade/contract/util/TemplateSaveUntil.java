package org.springblade.contract.util;

import com.alibaba.fastjson.JSONObject;
import org.springblade.contract.entity.*;
import org.springblade.contract.service.*;
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
	@Autowired
	private ICglActivityExecutionContractService cglActivityExecutionContractService;
	@Autowired
	private ICglCategorySalesContractsService cglCategorySalesContractsService;
	@Autowired
	private ICglLowCostHardwareService cglLowCostHardwareService;
	@Autowired
	private ICglTheSalesContractService cglTheSalesContractService;
	@Autowired
	private ICglRawMaterialsService cglRawMaterialsService;
	@Autowired
	private ICglPaymentDaysSupplementaryService cglPaymentDaysSupplementaryService;
	@Autowired
	private IMtbProductionContractService mtbProductionContractService;
	@Autowired
	private IMtbMarketResearchContractService mtbMarketResearchContractService;
	@Autowired
	private IMtlShootingAndProductionContractService mtlShootingAndProductionContractService;
	@Autowired
	private IMtlAdaptationContractService mtlAdaptationContractService;
	@Autowired
	private IMtlEditedTheContractService mtlEditedTheContractService;
	@Autowired
	private IMtlVideoProductionContractService mtlVideoProductionContractService;
	@Autowired
	private ISclEquipmentMaintenanceService sclEquipmentMaintenanceService;

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
			//变更用的
			if (Func.isNotEmpty(template.getChangeContractId())) {
				contractFormInfoEntity.setChangeContractId(template.getChangeContractId());
				contractFormInfoEntity.setChangeCategory("10");
			}
			//新陈列协议书
			if ("CLXY_42".equals(template.getTemplateCode())) {
				YwlANewDisplayEntity ywlANewDisplay = JSONObject.toJavaObject(j, YwlANewDisplayEntity.class);
				templateSaveUntil.ywlANewDisplayService.save(ywlANewDisplay);
				id = ywlANewDisplay.getId();

			} //店招合同
			else if ("DZHT_35".equals(template.getTemplateCode())) {
				YwlShopRecruitmentEntity ywlShopRecruitment = JSONObject.toJavaObject(j, YwlShopRecruitmentEntity.class);
				templateSaveUntil.ywlShopRecruitmentService.save(ywlShopRecruitment);
				id = ywlShopRecruitment.getId();
			}
			//活动执行合同
			else if ("HDZX_05".equals(template.getTemplateCode())) {
				CglActivityExecutionContractEntity cglActivityExecutionContract = JSONObject.toJavaObject(j, CglActivityExecutionContractEntity.class);
				templateSaveUntil.cglActivityExecutionContractService.save(cglActivityExecutionContract);
				id = cglActivityExecutionContract.getId();
			}
			//买卖合同（行销品）
			else if ("MMHT_07".equals(template.getTemplateCode())) {
				CglCategorySalesContractsEntity cglCategorySalesContracts = JSONObject.toJavaObject(j, CglCategorySalesContractsEntity.class);
				templateSaveUntil.cglCategorySalesContractsService.save(cglCategorySalesContracts);
				id = cglCategorySalesContracts.getId();
			}
			//买卖合同（五金低耗类）
			else if ("MMHT_08".equals(template.getTemplateCode())) {
				CglLowCostHardwareEntity cglLowCostHardware= JSONObject.toJavaObject(j, CglLowCostHardwareEntity.class);
				templateSaveUntil.cglLowCostHardwareService.save(cglLowCostHardware);
				id = cglLowCostHardware.getId();
			}
			//新增原物料补充协议--买卖合同
			else if ("BCXY_09".equals(template.getTemplateCode())) {
				CglTheSalesContractEntity cglTheSalesContract= JSONObject.toJavaObject(j, CglTheSalesContractEntity.class);
				templateSaveUntil.cglTheSalesContractService.save(cglTheSalesContract);
				id = cglTheSalesContract.getId();
			}
			//原物料-买卖合同
			else if ("MMHT_10".equals(template.getTemplateCode())) {
				CglRawMaterialsEntity cglRawMaterials= JSONObject.toJavaObject(j, CglRawMaterialsEntity.class);
				templateSaveUntil.cglRawMaterialsService.save(cglRawMaterials);
				id = cglRawMaterials.getId();
			}
			//账期补充协议--买卖合同
			else if ("MMHT_11".equals(template.getTemplateCode())) {
				CglPaymentDaysSupplementaryEntity cglPaymentDaysSupplementary= JSONObject.toJavaObject(j, CglPaymentDaysSupplementaryEntity.class);
				templateSaveUntil.cglPaymentDaysSupplementaryService.save(cglPaymentDaysSupplementary);
				id = cglPaymentDaysSupplementary.getId();
			}
			//平面广告拍摄制作合同
			else if ("ZZHT_12".equals(template.getTemplateCode())) {
				MtbProductionContractEntity mtbProductionContract= JSONObject.toJavaObject(j, MtbProductionContractEntity.class);
				templateSaveUntil.mtbProductionContractService.save(mtbProductionContract);
				id = mtbProductionContract.getId();
			}
			//市调合同（定性+定量）
			else if ("SDHT_13".equals(template.getTemplateCode())) {
				MtbMarketResearchContractEntity mtbMarketResearchContract= JSONObject.toJavaObject(j, MtbMarketResearchContractEntity.class);
				templateSaveUntil.mtbMarketResearchContractService.save(mtbMarketResearchContract);
				id = mtbMarketResearchContract.getId();
			}
			//视频广告改编合同
			else if ("GBHT_14".equals(template.getTemplateCode())) {
				MtlAdaptationContractEntity mtlAdaptationContract= JSONObject.toJavaObject(j, MtlAdaptationContractEntity.class);
				templateSaveUntil.mtlAdaptationContractService.save(mtlAdaptationContract);
				id = mtlAdaptationContract.getId();
			}
			//视频广告拍摄制作合同
			else if ("ZZHT_15".equals(template.getTemplateCode())) {
				MtlShootingAndProductionContractEntity mtlShootingAndProductionContract= JSONObject.toJavaObject(j, MtlShootingAndProductionContractEntity.class);
				templateSaveUntil.mtlShootingAndProductionContractService.save(mtlShootingAndProductionContract);
				id = mtlShootingAndProductionContract.getId();
			}
			//视频制作合同
			else if ("ZZHT_16".equals(template.getTemplateCode())) {
				MtlVideoProductionContractEntity mtlVideoProductionContract= JSONObject.toJavaObject(j, MtlVideoProductionContractEntity.class);
				templateSaveUntil.mtlVideoProductionContractService.save(mtlVideoProductionContract);
				id = mtlVideoProductionContract.getId();
			}
			//修图合同
			else if ("XTHT_17".equals(template.getTemplateCode())) {
				MtlEditedTheContractEntity mtlEditedTheContract= JSONObject.toJavaObject(j, MtlEditedTheContractEntity.class);
				templateSaveUntil.mtlEditedTheContractService.save(mtlEditedTheContract);
				id = mtlEditedTheContract.getId();
			}
			//设备维修保养合同
			else if ("BYHT_21".equals(template.getTemplateCode())) {
				SclEquipmentMaintenanceEntity sclEquipmentMaintenance= JSONObject.toJavaObject(j, SclEquipmentMaintenanceEntity.class);
				templateSaveUntil.sclEquipmentMaintenanceService.save(sclEquipmentMaintenance);
				id = sclEquipmentMaintenance.getId();
			}
			contractFormInfoEntity.setContractListId(id);
			templateSaveUntil.contractFormInfoService.save(contractFormInfoEntity);
		} else {
			//变更用的
			if ("130".equals(template.getOriginalContractStatus())) {
				contractFormInfoEntity.setSubmitStatus("10");
				contractFormInfoEntity.setContractStatus("20");
				templateSaveUntil.contractFormInfoService.updateExportStatus(template.getOriginalContractStatus(), template.getChangeContractId());
			}
			templateSaveUntil.contractFormInfoService.updateById(contractFormInfoEntity);
			if("CLXY_42".equals(template.getTemplateCode())){
				YwlANewDisplayEntity ywlANewDisplay = JSONObject.toJavaObject(j, YwlANewDisplayEntity.class);
				ywlANewDisplay.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.ywlANewDisplayService.updateById(ywlANewDisplay);
			}else if("DZHT_35".equals(template.getTemplateCode())){
				YwlShopRecruitmentEntity ywlShopRecruitment = JSONObject.toJavaObject(j, YwlShopRecruitmentEntity.class);
				ywlShopRecruitment.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.ywlShopRecruitmentService.updateById(ywlShopRecruitment);
			}
			//活动执行合同
			else if ("HDZX_05".equals(template.getTemplateCode())) {
				CglActivityExecutionContractEntity cglActivityExecutionContract = JSONObject.toJavaObject(j, CglActivityExecutionContractEntity.class);
				cglActivityExecutionContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.cglActivityExecutionContractService.updateById(cglActivityExecutionContract);
			}
			//买卖合同（行销品）
			else if ("MMHT_07".equals(template.getTemplateCode())) {
				CglCategorySalesContractsEntity cglCategorySalesContracts = JSONObject.toJavaObject(j, CglCategorySalesContractsEntity.class);
				cglCategorySalesContracts.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.cglCategorySalesContractsService.updateById(cglCategorySalesContracts);
			}
			//买卖合同（五金低耗类）
			else if ("MMHT_08".equals(template.getTemplateCode())) {
				CglLowCostHardwareEntity cglLowCostHardware= JSONObject.toJavaObject(j, CglLowCostHardwareEntity.class);
				cglLowCostHardware.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.cglLowCostHardwareService.updateById(cglLowCostHardware);
			}
			//新增原物料补充协议--买卖合同
			else if ("BCXY_09".equals(template.getTemplateCode())) {
				CglTheSalesContractEntity cglTheSalesContract= JSONObject.toJavaObject(j, CglTheSalesContractEntity.class);
				cglTheSalesContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.cglTheSalesContractService.updateById(cglTheSalesContract);
			}
			//原物料-买卖合同
			else if ("MMHT_10".equals(template.getTemplateCode())) {
				CglRawMaterialsEntity cglRawMaterials= JSONObject.toJavaObject(j, CglRawMaterialsEntity.class);
				cglRawMaterials.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.cglRawMaterialsService.updateById(cglRawMaterials);
			}
			//账期补充协议--买卖合同
			else if ("MMHT_11".equals(template.getTemplateCode())) {
				CglPaymentDaysSupplementaryEntity cglPaymentDaysSupplementary= JSONObject.toJavaObject(j, CglPaymentDaysSupplementaryEntity.class);
				cglPaymentDaysSupplementary.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.cglPaymentDaysSupplementaryService.updateById(cglPaymentDaysSupplementary);
			}
			//平面广告拍摄制作合同
			else if ("ZZHT_12".equals(template.getTemplateCode())) {
				MtbProductionContractEntity mtbProductionContract= JSONObject.toJavaObject(j, MtbProductionContractEntity.class);
				mtbProductionContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.mtbProductionContractService.updateById(mtbProductionContract);
			}
			//市调合同（定性+定量）
			else if ("SDHT_13".equals(template.getTemplateCode())) {
				MtbMarketResearchContractEntity mtbMarketResearchContract= JSONObject.toJavaObject(j, MtbMarketResearchContractEntity.class);
				mtbMarketResearchContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.mtbMarketResearchContractService.updateById(mtbMarketResearchContract);
			}
			//视频广告改编合同
			else if ("GBHT_14".equals(template.getTemplateCode())) {
				MtlAdaptationContractEntity mtlAdaptationContract= JSONObject.toJavaObject(j, MtlAdaptationContractEntity.class);
				mtlAdaptationContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.mtlAdaptationContractService.updateById(mtlAdaptationContract);
			}
			//视频广告拍摄制作合同
			else if ("ZZHT_15".equals(template.getTemplateCode())) {
				MtlShootingAndProductionContractEntity mtlShootingAndProductionContract= JSONObject.toJavaObject(j, MtlShootingAndProductionContractEntity.class);
				mtlShootingAndProductionContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.mtlShootingAndProductionContractService.updateById(mtlShootingAndProductionContract);
			}
			//视频制作合同
			else if ("ZZHT_16".equals(template.getTemplateCode())) {
				MtlVideoProductionContractEntity mtlVideoProductionContract= JSONObject.toJavaObject(j, MtlVideoProductionContractEntity.class);
				mtlVideoProductionContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.mtlVideoProductionContractService.updateById(mtlVideoProductionContract);
			}
			//修图合同
			else if ("XTHT_17".equals(template.getTemplateCode())) {
				MtlEditedTheContractEntity mtlEditedTheContract= JSONObject.toJavaObject(j, MtlEditedTheContractEntity.class);
				mtlEditedTheContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.mtlEditedTheContractService.updateById(mtlEditedTheContract);
			}
			//设备维修保养合同
			else if ("BYHT_21".equals(template.getTemplateCode())) {
				SclEquipmentMaintenanceEntity sclEquipmentMaintenance= JSONObject.toJavaObject(j, SclEquipmentMaintenanceEntity.class);
				sclEquipmentMaintenance.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.sclEquipmentMaintenanceService.updateById(sclEquipmentMaintenance);
			}
		}
		return "templateSave";
	}
}
