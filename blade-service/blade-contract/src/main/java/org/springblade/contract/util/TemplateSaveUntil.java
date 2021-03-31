package org.springblade.contract.util;

import com.alibaba.fastjson.JSONObject;
import org.springblade.contract.entity.*;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.service.*;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.feign.IDictBizClient;
import org.springblade.system.vo.TemplateRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TemplateSaveUntil {
    /*@Autowired
    private IDictBizClient bizClient;*/
	@Autowired
	private IContractFormInfoService contractFormInfoService;
	@Autowired
	private IYwlANewDisplayService ywlANewDisplayService;
	@Autowired
	private IYwiShopRecruitmentService ywlShopRecruitmentService;
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

	@Autowired
	private ISclProjectOutsourcingService sclProjectOutsourcingService;
	@Autowired
	private ISclConstructionProjectService sclConstructionProjectService;
	@Autowired
	private IMtlAudioProductionContractService mtlAudioProductionContractService;
	@Autowired
	private IShjlVendingMachineService shjlVendingMachineService;
	@Autowired
	private IActAppDatabasechangeloglockService actAppDatabasechangeloglockService;
	@Autowired
	private ISclServiceService sclServiceService;
	@Autowired
	private ISclLogisticsServiceService sclLogisticsServiceService;
	@Autowired
	private ISclProductionCategoryService sclProductionCategoryService;
	@Autowired
	private ISclOutsourcingAgreementService sclOutsourcingAgreementService;
	@Autowired
	private IOutsourcingAgreementService outsourcingAgreementService;
	@Autowired
	private IYwbEquipmentReleaseService ywbEquipmentReleaseService;
	@Autowired
	private IYwbSurveyProjectContractService ywbSurveyProjectContractService;
	@Autowired
	private IYwbDirectSalesCustomersService ywbDirectSalesCustomersService;
	@Autowired
	private IShjbPointOperationService shjbPointOperationService;
	@Autowired
	private IShjbOnlinePaymentOffsetService shjbOnlinePaymentOffsetService;
	@Autowired
	private IYwbUnifiedAgreementService ywbUnifiedAgreementService;
	@Autowired
	private IShjbGeneralVersionService shjbGeneralVersionService;
	@Autowired
	private IShjbNonSaleableOptionalService shjbNonSaleableOptionalService;
	@Autowired
	private ICglSalesContractService cglSalesContractService;
	@Autowired
	private ISclContractTemplateService sclContractTemplateService;
	@Autowired
	private ICglAdvertisementProductionService cglAdvertisementProductionService;
	@Autowired
	private ICglProofingContractService cglProofingContractService;
	@Autowired
	private IYwbBusContracteTemplateService ywbBusinessContractTemplateService;
	@Autowired
	private IMinferiorProductContractService inferiorProductContractService;
	@Autowired
	private ILyConfidentialityAgreementService lyConfidentialityAgreementService;
	@Autowired
	private IConfidentialityAgreementService confidentialityAgreementService;

	@Autowired
	private IDistServiceContractService distributionServiceContractService;
	@Autowired
	private IProductOutServiceContractService productOutServiceContractService;
	@Autowired
	private IDeviceLaunchUseContractService deviceLaunchUseContractService;
	@Autowired
	private IBusServiceContractService busServiceContractService;
	@Autowired
	private ILaborDispatchService laborDispatchService;
	@Autowired
	private ISclServiceContractRefrigerationService sclServiceContractRefrigerationService;
	//建一个静态的本类
	private static TemplateSaveUntil templateSaveUntil;

	//初始化
	@PostConstruct
	public void init() {
		templateSaveUntil = this;
	}

	public static Long templateSave(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO template, JSONObject j) {
		String infTimeStart="";
		String infTimeEnd="";
		Long id = null;
		if (Func.isEmpty(contractFormInfoEntity.getId())) {
			contractFormInfoEntity.setContractSoure("30");
			contractFormInfoEntity.setContractStatus("10");
			//新陈列协议书
			if ("CLXY_42".equals(template.getTemplateCode())) {
//				YwlANewDisplayEntity ywlANewDisplay = JSONObject.toJavaObject(j, YwlANewDisplayEntity.class);
//				templateSaveUntil.ywlANewDisplayService.save(ywlANewDisplay);
//				id = ywlANewDisplay.getId();
//
			} //店招合同
//			else if ("DZHT_35".equals(template.getTemplateCode())) {
//				YwlShopRecruitmentEntity ywlShopRecruitment = JSONObject.toJavaObject(j, YwlShopRecruitmentEntity.class);
//				templateSaveUntil.ywlShopRecruitmentService.save(ywlShopRecruitment);
//				id = ywlShopRecruitment.getId();
//			}
			//保密协议（二方）
			else if ("BMXY_01".equals(template.getTemplateCode())) {
				LyConfidentialityAgreementEntity lyConfidentialityAgreementEntity = JSONObject.toJavaObject(j, LyConfidentialityAgreementEntity.class);
				templateSaveUntil.lyConfidentialityAgreementService.save(lyConfidentialityAgreementEntity);
				id = lyConfidentialityAgreementEntity.getId();
			}
			//保密协议（三方）
			else if ("BMXY_02".equals(template.getTemplateCode())) {
				ConfidentialityAgreementEntity confidentialityAgreementEntity = JSONObject.toJavaObject(j, ConfidentialityAgreementEntity.class);
				templateSaveUntil.confidentialityAgreementService.save(confidentialityAgreementEntity);
				id = confidentialityAgreementEntity.getId();
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
				/*if(null!=bizClient.getValue("bond",mtlVideoProductionContract.getMtlHaveHasNot())){
					mtlVideoProductionContract.setMtlHaveHasNot(bizClient.getValue("bond",mtlVideoProductionContract.getMtlHaveHasNot()).getData());
				}
				if(null!=bizClient.getValue("mtl_term",mtlVideoProductionContract.getMtlPaymentMethod())){
					mtlVideoProductionContract.setMtlPaymentMethod(bizClient.getValue("mtl_term",mtlVideoProductionContract.getMtlHaveHasNot()).getData());
				}*/
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
			//生产项目外包服务合同
			else if ("FWHT_22".equals(template.getTemplateCode())) {
				SclProjectOutsourcingEntity sclProjectOutsourcingContract= JSONObject.toJavaObject(j, SclProjectOutsourcingEntity.class);
				templateSaveUntil.sclProjectOutsourcingService.save(sclProjectOutsourcingContract);
				id = sclProjectOutsourcingContract.getId();
			}
			//加工承揽合同（代工合同）
			else if ("CLHT_19".equals(template.getTemplateCode())) {
				SclConstructionProjectEntity sclConstructionProjectContract= JSONObject.toJavaObject(j, SclConstructionProjectEntity.class);
				templateSaveUntil.sclConstructionProjectService.save(sclConstructionProjectContract);
				id = sclConstructionProjectContract.getId();
			}
			//音频制作合同
			else if ("ZZHT_18".equals(template.getTemplateCode())) {
				MtlAudioProductionContractEntity mtlAudioProductionContract= JSONObject.toJavaObject(j, MtlAudioProductionContractEntity.class);
				templateSaveUntil.mtlAudioProductionContractService.save(mtlAudioProductionContract);
				id = mtlAudioProductionContract.getId();
			}
			//电子公章-（点位+运营）UP售货机合作合同（20191016）法务
			else if ("HZHT_49".equals(template.getTemplateCode())) {
				ShjlVendingMachineEntity shjlVendingMachine= JSONObject.toJavaObject(j, ShjlVendingMachineEntity.class);
				templateSaveUntil.shjlVendingMachineService.save(shjlVendingMachine);
				id = shjlVendingMachine.getId();
			}
			//2019.12.04修-售货机设备租赁合同—通用版（不可销售自选产品版本）补充协议（20191129）
			else if ("ZLHT_46".equals(template.getTemplateCode())) {
				ActAppDatabasechangeloglockEntity actAppDatabasechangeloglock= JSONObject.toJavaObject(j, ActAppDatabasechangeloglockEntity.class);
				templateSaveUntil.actAppDatabasechangeloglockService.save(actAppDatabasechangeloglock);
				id = actAppDatabasechangeloglock.getId();
			}
			//物流服务合同（二段仓储+配送）
			else if ("WLFW_23".equals(template.getTemplateCode())) {
				SclServiceEntity sclService= JSONObject.toJavaObject(j, SclServiceEntity.class);
				templateSaveUntil.sclServiceService.save(sclService);
				id = sclService.getId();
			}
			//物流服务合同（二段配送）
			else if ("FWHT_24".equals(template.getTemplateCode())) {
				SclLogisticsServiceEntity sclLogisticsService= JSONObject.toJavaObject(j, SclLogisticsServiceEntity.class);
				templateSaveUntil.sclLogisticsServiceService.save(sclLogisticsService);
				id = sclLogisticsService.getId();
			}
			//物流服务合同（一段+调拨运输）
			else if ("FWHT_25".equals(template.getTemplateCode())) {
				SclProductionCategoryEntity sclProductionCategory= JSONObject.toJavaObject(j, SclProductionCategoryEntity.class);
				templateSaveUntil.sclProductionCategoryService.save(sclProductionCategory);
				id = sclProductionCategory.getId();
			}
			//作业外包协议
			else if ("WBXY_27".equals(template.getTemplateCode())) {
				OutsourcingAgreementEntity outsourcingAgreement= JSONObject.toJavaObject(j, OutsourcingAgreementEntity.class);
				templateSaveUntil.outsourcingAgreementService.save(outsourcingAgreement);
				id = outsourcingAgreement.getId();
			}
			//设备投放使用协议
			else if ("SBTF_40".equals(template.getTemplateCode())) {
				YwbEquipmentReleaseEntity ywbEquipmentRelease= JSONObject.toJavaObject(j, YwbEquipmentReleaseEntity.class);
				templateSaveUntil.ywbEquipmentReleaseService.save(ywbEquipmentRelease);
				id = ywbEquipmentRelease.getId();
			}
			//售点普查项目合同
			else if ("XMHT_41".equals(template.getTemplateCode())) {
				YwbSurveyProjectContractEntity ywbSurveyProjectContract= JSONObject.toJavaObject(j, YwbSurveyProjectContractEntity.class);
				templateSaveUntil.ywbSurveyProjectContractService.save(ywbSurveyProjectContract);
				id = ywbSurveyProjectContract.getId();
			}
			//直营客户促销执行通知函
			else if ("ZXTZ_43".equals(template.getTemplateCode())) {
				YwbDirectSalesCustomersEntity ywbDirectSalesCustomers= JSONObject.toJavaObject(j, YwbDirectSalesCustomersEntity.class);
				templateSaveUntil.ywbDirectSalesCustomersService.save(ywbDirectSalesCustomers);
				id = ywbDirectSalesCustomers.getId();
			}
			//0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014）
			else if ("HZHT_44".equals(template.getTemplateCode())) {
				ShjbPointOperationEntity shjbPointOperation= JSONObject.toJavaObject(j, ShjbPointOperationEntity.class);
				templateSaveUntil.shjbPointOperationService.save(shjbPointOperation);
				id = shjbPointOperation.getId();
			}
			//0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014）
			else if ("FWHT_45".equals(template.getTemplateCode())) {
				ShjbOnlinePaymentOffsetEntity shjbOnlinePaymentOffset= JSONObject.toJavaObject(j, ShjbOnlinePaymentOffsetEntity.class);
				templateSaveUntil.shjbOnlinePaymentOffsetService.save(shjbOnlinePaymentOffset);
				id = shjbOnlinePaymentOffset.getId();
			}
			//2021年统一e商城平台入驻服务协议（统一经销商）
			else if ("FWXY_50".equals(template.getTemplateCode())) {
				YwbUnifiedAgreementEntity ywbUnifiedAgreement= JSONObject.toJavaObject(j, YwbUnifiedAgreementEntity.class);
				templateSaveUntil.ywbUnifiedAgreementService.save(ywbUnifiedAgreement);
				id = ywbUnifiedAgreement.getId();
			}
			//2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本））
			else if ("ZLHT_48".equals(template.getTemplateCode())) {
				ShjbGeneralVersionEntity shjbGeneralVersion= JSONObject.toJavaObject(j, ShjbGeneralVersionEntity.class);
				templateSaveUntil.shjbGeneralVersionService.save(shjbGeneralVersion);
				id = shjbGeneralVersion.getId();
			}
			//2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本）
			else if ("ZLHT_47".equals(template.getTemplateCode())) {
				ShjbNonSaleableOptionalEntity shjbNonSaleableOptional= JSONObject.toJavaObject(j, ShjbNonSaleableOptionalEntity.class);
				templateSaveUntil.shjbNonSaleableOptionalService.save(shjbNonSaleableOptional);
				id = shjbNonSaleableOptional.getId();
			}
			//买卖合同（国内设备购买）
			else if ("MMHT_06".equals(template.getTemplateCode())) {
				CglSalesContractEntity cglSalesContract= JSONObject.toJavaObject(j, CglSalesContractEntity.class);
				templateSaveUntil.cglSalesContractService.save(cglSalesContract);
				id = cglSalesContract.getId();
			}
			//下脚品买卖合同模版
			else if ("MMHT_26".equals(template.getTemplateCode())) {
				infTimeStart=j.get("infTimeStart").toString();
				infTimeEnd=j.get("infTimeStart").toString();
				j.put("infTimeStart",DataFormatUtils.SFFormatGLNZ(j.get("infTimeStart").toString()));
				j.put("infTimeEnd",DataFormatUtils.SFFormatGLNZ(j.get("infTimeEnd").toString()));
				InferiorProductContractEntity inferiorProductContract= JSONObject.toJavaObject(j, InferiorProductContractEntity.class);
				templateSaveUntil.inferiorProductContractService.save(inferiorProductContract);
				id = inferiorProductContract.getId();
				j.put("infTimeStart",infTimeStart);
				j.put("infTimeEnd",infTimeEnd);
			}
			//广告制作安装合同模板
			else if ("GGZZ_04".equals(template.getTemplateCode())) {
				CglAdvertisementProductionEntity cglAdvertisementProduction= JSONObject.toJavaObject(j, CglAdvertisementProductionEntity.class);
				templateSaveUntil.cglAdvertisementProductionService.save(cglAdvertisementProduction);
				id = cglAdvertisementProduction.getId();
			}
			//打样合同书
			else if ("DYHT_03".equals(template.getTemplateCode())) {
				CglProofingContractEntity cglProofingContractEntity= JSONObject.toJavaObject(j, CglProofingContractEntity.class);
				templateSaveUntil.cglProofingContractService.save(cglProofingContractEntity);
				id = cglProofingContractEntity.getId();
			}
			//房屋租赁合同模板
			else if ("FWZL_36".equals(template.getTemplateCode())) {
				YwbBusinessContractTemplateEntity ywbBusinessContractTemplateEntity= JSONObject.toJavaObject(j, YwbBusinessContractTemplateEntity.class);
				templateSaveUntil.ywbBusinessContractTemplateService.save(ywbBusinessContractTemplateEntity);
				id = ywbBusinessContractTemplateEntity.getId();
			}
			//配送服务合同
			else if ("FWHT_38".equals(template.getTemplateCode())) {
				DistServiceContractEntity distributionServiceContractEntity= JSONObject.toJavaObject(j, DistServiceContractEntity.class);
				templateSaveUntil.distributionServiceContractService.save(distributionServiceContractEntity);
				id = distributionServiceContractEntity.getId();
			}
			//生产项目外包服务合同
			else if ("FWHT_22".equals(template.getTemplateCode())) {
				ProductOutServiceContractEntity productOutServiceContractEntity= JSONObject.toJavaObject(j, ProductOutServiceContractEntity.class);
				templateSaveUntil.productOutServiceContractService.save(productOutServiceContractEntity);
				id = productOutServiceContractEntity.getId();
			}
			//设备投放使用协议
			else if ("SBTF_40".equals(template.getTemplateCode())) {
				DeviceLaunchUseContractEntity deviceLaunchUseContract= JSONObject.toJavaObject(j, DeviceLaunchUseContractEntity.class);
				templateSaveUntil.deviceLaunchUseContractService.save(deviceLaunchUseContract);
				id = deviceLaunchUseContract.getId();
			}
			//班车服务合同
			else if ("FWHT_51".equals(template.getTemplateCode())) {
				BusServiceContractEntity busServiceContractEntity= JSONObject.toJavaObject(j, BusServiceContractEntity.class);
				templateSaveUntil.busServiceContractService.save(busServiceContractEntity);
				id = busServiceContractEntity.getId();
			}
			//劳务派遣合同
			else if ("LWHT_52".equals(template.getTemplateCode())) {
				LaborDispatchEntity laborDispatchEntity= JSONObject.toJavaObject(j, LaborDispatchEntity.class);
				templateSaveUntil.laborDispatchService.save(laborDispatchEntity);
				id = laborDispatchEntity.getId();
			}
			//劳务派遣合同
			else if ("WLFW_59".equals(template.getTemplateCode())) {
				SclServiceContractRefrigerationEntity sclServiceContractRefrigeration= JSONObject.toJavaObject(j, SclServiceContractRefrigerationEntity.class);
				templateSaveUntil.sclServiceContractRefrigerationService.save(sclServiceContractRefrigeration);
				id = sclServiceContractRefrigeration.getId();
			}
			//ContractListId保存的是范本数据的id
			contractFormInfoEntity.setContractListId(id);
			templateSaveUntil.contractFormInfoService.save(contractFormInfoEntity);
		} else {
			templateSaveUntil.contractFormInfoService.updateById(contractFormInfoEntity);
			if ("CLXY_42".equals(template.getTemplateCode())) {
//				YwlANewDisplayEntity ywlANewDisplay = JSONObject.toJavaObject(j, YwlANewDisplayEntity.class);
//				ywlANewDisplay.setId(contractFormInfoEntity.getContractListId());
//				templateSaveUntil.ywlANewDisplayService.updateById(ywlANewDisplay);
//			} else if ("DZHT_35".equals(template.getTemplateCode())) {
//				YwlShopRecruitmentEntity ywlShopRecruitment = JSONObject.toJavaObject(j, YwlShopRecruitmentEntity.class);
//				ywlShopRecruitment.setId(contractFormInfoEntity.getContractListId());
//				templateSaveUntil.ywlShopRecruitmentService.updateById(ywlShopRecruitment);
			}
			//保密协议（二方）
			else if ("BMXY_01".equals(template.getTemplateCode())) {
				LyConfidentialityAgreementEntity lyConfidentialityAgreementEntity = JSONObject.toJavaObject(j, LyConfidentialityAgreementEntity.class);
				lyConfidentialityAgreementEntity.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.lyConfidentialityAgreementService.updateById(lyConfidentialityAgreementEntity);
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
				CglLowCostHardwareEntity cglLowCostHardware = JSONObject.toJavaObject(j, CglLowCostHardwareEntity.class);
				cglLowCostHardware.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.cglLowCostHardwareService.updateById(cglLowCostHardware);
			}
			//新增原物料补充协议--买卖合同
			else if ("BCXY_09".equals(template.getTemplateCode())) {
				CglTheSalesContractEntity cglTheSalesContract = JSONObject.toJavaObject(j, CglTheSalesContractEntity.class);
				cglTheSalesContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.cglTheSalesContractService.updateById(cglTheSalesContract);
			}
			//原物料-买卖合同
			else if ("MMHT_10".equals(template.getTemplateCode())) {
				CglRawMaterialsEntity cglRawMaterials = JSONObject.toJavaObject(j, CglRawMaterialsEntity.class);
				cglRawMaterials.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.cglRawMaterialsService.updateById(cglRawMaterials);
			}
			//账期补充协议--买卖合同
			else if ("MMHT_11".equals(template.getTemplateCode())) {
				CglPaymentDaysSupplementaryEntity cglPaymentDaysSupplementary = JSONObject.toJavaObject(j, CglPaymentDaysSupplementaryEntity.class);
				cglPaymentDaysSupplementary.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.cglPaymentDaysSupplementaryService.updateById(cglPaymentDaysSupplementary);
			}
			//平面广告拍摄制作合同
			else if ("ZZHT_12".equals(template.getTemplateCode())) {
				MtbProductionContractEntity mtbProductionContract = JSONObject.toJavaObject(j, MtbProductionContractEntity.class);
				mtbProductionContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.mtbProductionContractService.updateById(mtbProductionContract);
			}
			//市调合同（定性+定量）
			else if ("SDHT_13".equals(template.getTemplateCode())) {
				MtbMarketResearchContractEntity mtbMarketResearchContract = JSONObject.toJavaObject(j, MtbMarketResearchContractEntity.class);
				mtbMarketResearchContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.mtbMarketResearchContractService.updateById(mtbMarketResearchContract);
			}
			//视频广告改编合同
			else if ("GBHT_14".equals(template.getTemplateCode())) {
				MtlAdaptationContractEntity mtlAdaptationContract = JSONObject.toJavaObject(j, MtlAdaptationContractEntity.class);
				mtlAdaptationContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.mtlAdaptationContractService.updateById(mtlAdaptationContract);
			}
			//视频广告拍摄制作合同
			else if ("ZZHT_15".equals(template.getTemplateCode())) {
				MtlShootingAndProductionContractEntity mtlShootingAndProductionContract = JSONObject.toJavaObject(j, MtlShootingAndProductionContractEntity.class);
				mtlShootingAndProductionContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.mtlShootingAndProductionContractService.updateById(mtlShootingAndProductionContract);
			}
			//视频制作合同
			else if ("ZZHT_16".equals(template.getTemplateCode())) {
				MtlVideoProductionContractEntity mtlVideoProductionContract = JSONObject.toJavaObject(j, MtlVideoProductionContractEntity.class);
				mtlVideoProductionContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.mtlVideoProductionContractService.updateById(mtlVideoProductionContract);
			}
			//修图合同
			else if ("XTHT_17".equals(template.getTemplateCode())) {
				MtlEditedTheContractEntity mtlEditedTheContract = JSONObject.toJavaObject(j, MtlEditedTheContractEntity.class);
				mtlEditedTheContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.mtlEditedTheContractService.updateById(mtlEditedTheContract);
			}
			//设备维修保养合同
			else if ("BYHT_21".equals(template.getTemplateCode())) {
				SclEquipmentMaintenanceEntity sclEquipmentMaintenance = JSONObject.toJavaObject(j, SclEquipmentMaintenanceEntity.class);
				sclEquipmentMaintenance.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.sclEquipmentMaintenanceService.updateById(sclEquipmentMaintenance);
			}
			//生产项目外包服务合同
			else if ("FWHT_22".equals(template.getTemplateCode())) {
				SclProjectOutsourcingEntity sclProjectOutsourcingContract = JSONObject.toJavaObject(j, SclProjectOutsourcingEntity.class);
				sclProjectOutsourcingContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.sclProjectOutsourcingService.updateById(sclProjectOutsourcingContract);
			}
			//加工承揽合同（代工合同）
			else if ("CLHT_19".equals(template.getTemplateCode())) {
				SclConstructionProjectEntity sclConstructionProjectContract = JSONObject.toJavaObject(j, SclConstructionProjectEntity.class);
				sclConstructionProjectContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.sclConstructionProjectService.updateById(sclConstructionProjectContract);
			}
			//音频制作合同
			else if ("ZZHT_18".equals(template.getTemplateCode())) {
				MtlAudioProductionContractEntity mtlAudioProductionContract = JSONObject.toJavaObject(j, MtlAudioProductionContractEntity.class);
				mtlAudioProductionContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.mtlAudioProductionContractService.updateById(mtlAudioProductionContract);
			}
			//电子公章-（点位+运营）UP售货机合作合同（20191016）法务
			else if ("HZHT_49".equals(template.getTemplateCode())) {
				ShjlVendingMachineEntity shjlVendingMachine = JSONObject.toJavaObject(j, ShjlVendingMachineEntity.class);
				shjlVendingMachine.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.shjlVendingMachineService.updateById(shjlVendingMachine);
			}
			//2019.12.04修-售货机设备租赁合同—通用版（不可销售自选产品版本）补充协议（20191129）
			else if ("ZLHT_46".equals(template.getTemplateCode())) {
				ActAppDatabasechangeloglockEntity actAppDatabasechangeloglock = JSONObject.toJavaObject(j, ActAppDatabasechangeloglockEntity.class);
				actAppDatabasechangeloglock.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.actAppDatabasechangeloglockService.updateById(actAppDatabasechangeloglock);
			}
			//物流服务合同（二段仓储+配送）
			else if ("WLFW_23".equals(template.getTemplateCode())) {
				SclServiceEntity sclService = JSONObject.toJavaObject(j, SclServiceEntity.class);
				sclService.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.sclServiceService.updateById(sclService);
			}
			//物流服务合同（二段配送）
			else if ("FWHT_24".equals(template.getTemplateCode())) {
				SclLogisticsServiceEntity sclLogisticsService = JSONObject.toJavaObject(j, SclLogisticsServiceEntity.class);
				sclLogisticsService.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.sclLogisticsServiceService.updateById(sclLogisticsService);
			}
			//物流服务合同（一段+调拨运输）
			else if ("FWHT_25".equals(template.getTemplateCode())) {
				SclProductionCategoryEntity sclProductionCategory = JSONObject.toJavaObject(j, SclProductionCategoryEntity.class);
				sclProductionCategory.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.sclProductionCategoryService.updateById(sclProductionCategory);
			}
			//作业外包协议
			else if ("WBXY_27".equals(template.getTemplateCode())) {
				OutsourcingAgreementEntity outsourcingAgreement= JSONObject.toJavaObject(j, OutsourcingAgreementEntity.class);
				outsourcingAgreement.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.outsourcingAgreementService.updateById(outsourcingAgreement);
			}
			//设备投放使用协议
			else if ("SBTF_40".equals(template.getTemplateCode())) {
				YwbEquipmentReleaseEntity ywbEquipmentRelease= JSONObject.toJavaObject(j, YwbEquipmentReleaseEntity.class);
				ywbEquipmentRelease.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.ywbEquipmentReleaseService.updateById(ywbEquipmentRelease);
			}
			//售点普查项目合同
			else if ("XMHT_41".equals(template.getTemplateCode())) {
				YwbSurveyProjectContractEntity ywbSurveyProjectContract= JSONObject.toJavaObject(j, YwbSurveyProjectContractEntity.class);
				ywbSurveyProjectContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.ywbSurveyProjectContractService.updateById(ywbSurveyProjectContract);
			}
			//直营客户促销执行通知函
			else if ("ZXTZ_43".equals(template.getTemplateCode())) {
				YwbDirectSalesCustomersEntity ywbDirectSalesCustomers= JSONObject.toJavaObject(j, YwbDirectSalesCustomersEntity.class);
				ywbDirectSalesCustomers.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.ywbDirectSalesCustomersService.updateById(ywbDirectSalesCustomers);
			}
			//0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014）
			else if ("HZHT_44".equals(template.getTemplateCode())) {
				ShjbPointOperationEntity shjbPointOperation= JSONObject.toJavaObject(j, ShjbPointOperationEntity.class);
				shjbPointOperation.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.shjbPointOperationService.updateById(shjbPointOperation);
			}
			//0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014）
			else if ("FWHT_45".equals(template.getTemplateCode())) {
				ShjbOnlinePaymentOffsetEntity shjbOnlinePaymentOffset= JSONObject.toJavaObject(j, ShjbOnlinePaymentOffsetEntity.class);
				shjbOnlinePaymentOffset.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.shjbOnlinePaymentOffsetService.updateById(shjbOnlinePaymentOffset);
			}
			//2021年统一e商城平台入驻服务协议（统一经销商）
			else if ("FWXY_50".equals(template.getTemplateCode())) {
				YwbUnifiedAgreementEntity ywbUnifiedAgreement= JSONObject.toJavaObject(j, YwbUnifiedAgreementEntity.class);
				ywbUnifiedAgreement.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.ywbUnifiedAgreementService.updateById(ywbUnifiedAgreement);
			}
			//2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本））
			else if ("ZLHT_48".equals(template.getTemplateCode())) {
				ShjbGeneralVersionEntity shjbGeneralVersion= JSONObject.toJavaObject(j, ShjbGeneralVersionEntity.class);
				shjbGeneralVersion.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.shjbGeneralVersionService.updateById(shjbGeneralVersion);
			}
			//2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本）
			else if ("ZLHT_47".equals(template.getTemplateCode())) {
				ShjbNonSaleableOptionalEntity shjbNonSaleableOptional= JSONObject.toJavaObject(j, ShjbNonSaleableOptionalEntity.class);
				shjbNonSaleableOptional.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.shjbNonSaleableOptionalService.updateById(shjbNonSaleableOptional);
			}
			//买卖合同（国内设备购买）
			else if ("MMHT_06".equals(template.getTemplateCode())) {
				CglSalesContractEntity cglSalesContract= JSONObject.toJavaObject(j, CglSalesContractEntity.class);
				cglSalesContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.cglSalesContractService.updateById(cglSalesContract);
			}
			//下脚品买卖合同模版
			else if ("MMHT_26".equals(template.getTemplateCode())) {
				infTimeStart=j.get("infTimeStart").toString();
				infTimeEnd=j.get("infTimeStart").toString();
				j.put("infTimeStart",DataFormatUtils.SFFormatGLNZ(j.get("infTimeStart").toString()));
				j.put("infTimeEnd",DataFormatUtils.SFFormatGLNZ(j.get("infTimeEnd").toString()));
				InferiorProductContractEntity inferiorProductContract= JSONObject.toJavaObject(j, InferiorProductContractEntity.class);
				inferiorProductContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.inferiorProductContractService.updateById(inferiorProductContract);
				j.put("infTimeStart",infTimeStart);
				j.put("infTimeEnd",infTimeEnd);
			}
			//广告制作安装合同模板
			else if ("GGZZ_04".equals(template.getTemplateCode())) {
				CglAdvertisementProductionEntity cglAdvertisementProduction= JSONObject.toJavaObject(j, CglAdvertisementProductionEntity.class);
				cglAdvertisementProduction.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.cglAdvertisementProductionService.updateById(cglAdvertisementProduction);
			}
			//打样合同书
			else if ("DYHT_03".equals(template.getTemplateCode())) {
				CglProofingContractEntity cglProofingContractEntity= JSONObject.toJavaObject(j, CglProofingContractEntity.class);
				cglProofingContractEntity.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.cglProofingContractService.updateById(cglProofingContractEntity);
			}
			//房屋租赁合同模板
			else if ("FWZL_36".equals(template.getTemplateCode())) {
				YwbBusinessContractTemplateEntity ywbBusinessContractTemplateEntity= JSONObject.toJavaObject(j, YwbBusinessContractTemplateEntity.class);
				ywbBusinessContractTemplateEntity.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.ywbBusinessContractTemplateService.updateById(ywbBusinessContractTemplateEntity);
			}
			//配送服务合同
			else if ("FWHT_38".equals(template.getTemplateCode())) {
				DistServiceContractEntity distributionServiceContractEntity= JSONObject.toJavaObject(j, DistServiceContractEntity.class);
				distributionServiceContractEntity.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.distributionServiceContractService.updateById(distributionServiceContractEntity);
			}
			//生产项目外包服务合同
			else if ("FWHT_22".equals(template.getTemplateCode())) {
				ProductOutServiceContractEntity productOutServiceContractEntity= JSONObject.toJavaObject(j, ProductOutServiceContractEntity.class);
				productOutServiceContractEntity.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.productOutServiceContractService.updateById(productOutServiceContractEntity);
			}
			//设备投放使用协议
			else if ("SBTF_40".equals(template.getTemplateCode())) {
				DeviceLaunchUseContractEntity deviceLaunchUseContract= JSONObject.toJavaObject(j, DeviceLaunchUseContractEntity.class);
				deviceLaunchUseContract.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.deviceLaunchUseContractService.updateById(deviceLaunchUseContract);
			}
			//班车服务合同
			else if ("FWHT_51".equals(template.getTemplateCode())) {
				BusServiceContractEntity busServiceContractEntity= JSONObject.toJavaObject(j, BusServiceContractEntity.class);
				busServiceContractEntity.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.busServiceContractService.updateById(busServiceContractEntity);
			}
			//劳务派遣合同
			else if ("LWHT_52".equals(template.getTemplateCode())) {
				LaborDispatchEntity laborDispatchEntity= JSONObject.toJavaObject(j, LaborDispatchEntity.class);
				laborDispatchEntity.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.laborDispatchService.updateById(laborDispatchEntity);
			}
			//劳务派遣合同
			else if ("WLFW_59".equals(template.getTemplateCode())) {
				SclServiceContractRefrigerationEntity sclServiceContractRefrigeration= JSONObject.toJavaObject(j, SclServiceContractRefrigerationEntity.class);
				sclServiceContractRefrigeration.setId(contractFormInfoEntity.getContractListId());
				templateSaveUntil.sclServiceContractRefrigerationService.updateById(sclServiceContractRefrigeration);
			}
		}
		return contractFormInfoEntity.getId();
	}
}
