package org.springblade.contract.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springblade.contract.constant.ContractFormInfoTemplateContract;
import org.springblade.contract.entity.*;
import org.springblade.contract.excel.ContractFormInfoImporter;
import org.springblade.contract.excel.ContractFormInfoImporterEx;
import org.springblade.contract.service.*;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.system.entity.TemplateFieldJsonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class ExcelSaveUntil {
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

	@Autowired
	private ISclProjectOutsourcingService sclProjectOutsourcingService;
	@Autowired
	private ISclConstructionProjectService sclConstructionProjectService;
	@Autowired
	private IMtlAudioProductionContractService mtlAudioProductionContractService;
	@Autowired
	private ICglRawMaterials1Service cglRawMaterials1Service;
	//建一个静态的本类
	private static ExcelSaveUntil templateSaveUntil;

	//初始化
	@PostConstruct
	public void init() {
		templateSaveUntil = this;
	}

	public Long excelSave(ContractFormInfoEntity contractFormInfoEntity, ContractFormInfoImporter contractFormInfoExcel, ContractTemplateEntity template, MultipartFile file,String json) {
		Long id = null;
		JSONObject j = new JSONObject();
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
			CglRawMaterialsEntity cglRawMaterials= new CglRawMaterialsEntity();
			cglRawMaterials.setCglBNumber(contractFormInfoExcel.getYwlAccountNumber());
			templateSaveUntil.cglRawMaterialsService.save(cglRawMaterials);
			id = cglRawMaterials.getId();
			List<ContractFormInfoImporterEx> read2 = ExcelUtil.read(file, 1, 1, ContractFormInfoImporterEx.class);
			List<CglRawMaterials1Entity> contractRawMaterialsList = new ArrayList<CglRawMaterials1Entity>();
			for (ContractFormInfoImporterEx read2Ex : read2) {
				if (contractFormInfoExcel.getSealName().equals(read2Ex.getYwlFullName()) && contractFormInfoExcel.getCounterpartName().equals(read2Ex.getYwlNameOfOpposite())) {
					CglRawMaterials1Entity cglRawMaterials1 = new CglRawMaterials1Entity();
					if (!"无".equals(read2Ex.getCglMaterial()) && !"".equals(read2Ex.getCglMaterial()) && read2Ex.getCglMaterial() != null) {
						cglRawMaterials1.setCglMaterial(Integer.parseInt(read2Ex.getCglMaterial()));
					}
					if (!"无".equals(read2Ex.getCglOfTheGoods()) && !"".equals(read2Ex.getCglOfTheGoods()) && read2Ex.getCglOfTheGoods() != null) {
						cglRawMaterials1.setCglOfTheGoods(read2Ex.getCglOfTheGoods());
					}
					if (!"无".equals(read2Ex.getCglSpecifications()) && !"".equals(read2Ex.getCglSpecifications()) && read2Ex.getCglSpecifications() != null) {
						cglRawMaterials1.setCglSpecifications(read2Ex.getCglSpecifications());
					}
					if (!"无".equals(read2Ex.getCglUnitPrice()) && !"".equals(read2Ex.getCglUnitPrice()) && read2Ex.getCglUnitPrice() != null) {
						cglRawMaterials1.setCglUnitPrice(new BigDecimal(read2Ex.getCglUnitPrice()));
					}
					if (!"无".equals(contractFormInfoExcel.getYwlItemNo()) && !"".equals(contractFormInfoExcel.getYwlItemNo()) && contractFormInfoExcel.getYwlItemNo() != null) {
						cglRawMaterials1.setCglPaymentDays(contractFormInfoExcel.getYwlItemNo());
					}
					if (!"无".equals(read2Ex.getCglNote()) && !"".equals(read2Ex.getCglNote()) && read2Ex.getCglNote() != null) {
						cglRawMaterials1.setCglNote(read2Ex.getCglNote());
					}
					cglRawMaterials1.setContractId(contractFormInfoEntity.getId());
					templateSaveUntil.cglRawMaterials1Service.save(cglRawMaterials1);
					contractRawMaterialsList.add(cglRawMaterials1);
				}
			}
			cglRawMaterials.setCglRawMaterials1List(contractRawMaterialsList);
			JSONObject dbz = (JSONObject) JSONObject.toJSON(cglRawMaterials);
			String jsonEx = this.getjson(json,contractFormInfoEntity,dbz,template);
			contractFormInfoEntity.setJson(jsonEx);
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
		contractFormInfoEntity.setContractListId(id);
		templateSaveUntil.contractFormInfoService.saveOrUpdate(contractFormInfoEntity);
		return id;
	}

	//json数据拼接
	public String getjson(String json, ContractFormInfoEntity contractFormInfo, JSONObject dbz,ContractTemplateEntity contractTemplate) {
		JSONArray objects = new JSONArray();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
		try {
			objects = JSONArray.parseArray(json);
			JSONObject db = (JSONObject) JSONObject.toJSON(contractFormInfo);
			for (int i = 0; i < objects.size(); i++) {
				JSONObject temp = objects.getJSONObject(i);
				String fieldName = temp.getString("fieldName");
				String componentType = temp.getString("componentType");
				if ("datePicker".equals(componentType)) {
					if (fieldName != null) {
						String dbColum = db.getString(fieldName);
						if (dbColum != null) {
							Date d = sdf.parse(dbColum);
							temp.put("fieldValue", sd.format(d));
						}
					}
				} else if ("relationList".equals(componentType)) {
					System.out.println("是的");
				} else {
					if (fieldName != null) {
						String dbColum = db.getString(fieldName);
						if (dbColum != null) {
							temp.put("fieldValue", dbColum);
						}
					}
				}
				objects.set(i, temp);
			}
			for (int j = 0; j < objects.size(); j++) {
				JSONObject temp = objects.getJSONObject(j);
				String fieldName = temp.getString("fieldName");
				String componentType = temp.getString("componentType");
				if ("datePicker".equals(componentType)) {
					if (fieldName != null) {
						String dbColum = dbz.getString(fieldName);
						if (dbColum != null) {
							Date d = sdf.parse(dbColum);
							temp.put("fieldValue", sd.format(d));
						}
					}
				} else if ("relationList".equals(componentType)) {
					System.out.println("是的");
				} else {
					if (fieldName != null) {
						String dbColum = dbz.getString(fieldName);
						if (dbColum != null) {
							temp.put("fieldValue", dbColum);
						}
					}
				}
				objects.set(j, temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		json = objects.toJSONString();
		List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
		//ContractTemplateEntity contractTemplate = contractTemplateMapper.selectById(contractFormInfo.getContractTemplateId());
		for (TemplateFieldJsonEntity templateField : templateFieldList) {
			if (ContractFormInfoTemplateContract.CONTRACT_ID.equals(templateField.getComponentType())) {
				templateField.setFieldValue(contractFormInfo.getId().toString());
			}
			//合同分类
			if (ContractFormInfoTemplateContract.CONTRACT_BIG_CATEGORY.equals(templateField.getRelationCode())) {
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				if (null != jsonObj) {
					jsonObj.put("template", contractTemplate);
					jsonObj.put("first", contractFormInfo.getContractBigCategory());
					jsonObj.put("second", contractFormInfo.getContractSmallCategory());
					templateField.setSecondSelectDataObject(jsonObj);
				}
			}
			//合同分类
			if (ContractFormInfoTemplateContract.CONTRACT_COL_PAY.equals(templateField.getRelationCode())) {
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				if (null != jsonObj) {
					jsonObj.put("first", contractFormInfo.getColPayType());
					jsonObj.put("second", contractFormInfo.getColPayTerm());
					jsonObj.put("days", contractFormInfo.getDays());
					templateField.setSecondSelectDataObject(jsonObj);
				}
			}
			//处理有字段验证规则数据
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE_REQUORED.equals(templateField.getRequired())) {
				List<Object> objectList = JSON.parseArray(templateField.getRequiredData(), Object.class);
				if (CollectionUtil.isNotEmpty(objectList)) {
					templateField.setRequiredDataList(objectList);
				}
			}
			//处理小类型组件数据
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE_SMALL.contains(templateField.getComponentType())) {
				List<Object> objectList = JSON.parseArray(templateField.getDicData(), Object.class);
				if (CollectionUtil.isNotEmpty(objectList)) {
					templateField.setDicDataList(objectList);
				}
			}
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE.contains(templateField.getComponentType())) {
				if (ContractFormInfoTemplateContract.CONTRACT_ACCORDING.equals(templateField.getRelationCode())) {
					/*保存依据信息*/
					if (CollectionUtil.isNotEmpty(contractFormInfo.getAccording())) {
						templateField.setTableData(JSONObject.toJSONString(contractFormInfo.getAccording()));
						templateField.setTableDataList(contractFormInfo.getAccording());
					}
				}
				//是关联列表的组件都在这里处理
				if (ContractFormInfoTemplateContract.CONTRACT_COUNTERPART.equals(templateField.getRelationCode())) {
					//字符串对象转成JSONObject
					JSONObject obj = JSON.parseObject(templateField.getTableDataObject());
					//判断相对方是否为空
					if (CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())) {
						obj.put(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_COUNTERPART, contractFormInfo.getCounterpart());
					}
					if (CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())) {
						obj.put(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_COUNTERPART, contractFormInfo.getContractBond());
					}
					templateField.setTableDataObject(JSONObject.toJSONString(obj));
					templateField.setTableDataObjectList(obj);
				}
				//*保存履约信息
				if (ContractFormInfoTemplateContract.CONTRACT_PERFORMANCE.equals(templateField.getRelationCode())) {
					if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())) {
						templateField.setTableData(JSONObject.toJSONString(contractFormInfo.getPerformanceList()));
						templateField.setTableDataList(contractFormInfo.getPerformanceList());
					}
				}
				//*保存履约计划收付款
				if (ContractFormInfoTemplateContract.CONTRACT_PERFORMANCE_COLPAY.equals(templateField.getRelationCode())) {
					if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())) {
						templateField.setTableData(JSONObject.toJSONString(contractFormInfo.getPerformanceColPayList()));
						templateField.setTableDataList(contractFormInfo.getPerformanceColPayList());
					}
				}
				//*保存履约计划收付款
				if (ContractFormInfoTemplateContract.CONTRACT_RAW_MATERIALS.equals(templateField.getRelationCode())) {
					if (CollectionUtil.isNotEmpty(contractFormInfo.getRawMaterialsList())) {
						templateField.setTableData(JSONObject.toJSONString(contractFormInfo.getRawMaterialsList()));
						templateField.setTableDataList(contractFormInfo.getRawMaterialsList());
					}
				}
			}
		}
		return toJSONString(templateFieldList);
	}

	//替换所有组件里的数据
	public String toJSONString(List<TemplateFieldJsonEntity> templateFieldList) {
		//把数组在转回json字符串
		String json = JSON.toJSONString(templateFieldList);
		//再转成json数组替换里面的数据
		com.alibaba.fastjson.JSONArray jsonArr = JSON.parseArray(json);
		JSONArray jsonArray = new JSONArray();
		JSONArray array = new JSONArray();
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jsonObject2 = jsonArr.getJSONObject(i);
			if (null != (jsonObject2.get("secondSelectDataObject"))) {
				jsonObject2.put("secondSelectData", jsonObject2.get("secondSelectDataObject"));
				jsonObject2.remove("secondSelectDataObject");
			}
			if (null != (jsonObject2.get("tableDataList"))) {
				jsonObject2.put("tableData", jsonObject2.get("tableDataList"));
				jsonObject2.remove("tableDataList");
			}
			if (null != (jsonObject2.get("requiredDataList"))) {
				jsonObject2.put("requiredData", jsonObject2.get("requiredDataList"));
				jsonObject2.remove("requiredDataList");
			}
			if (null != (jsonObject2.get("dicDataList"))) {
				jsonObject2.put("dicData", jsonObject2.get("dicDataList"));
				jsonObject2.remove("dicDataList");
			}
			if (null != (jsonObject2.get("tableDataObjectList"))) {
				jsonObject2.put("tableDataObject", jsonObject2.get("tableDataObjectList"));
				jsonObject2.remove("tableDataObjectList");
			}
			if ("[]".equals(jsonObject2.get("tableData"))) {
				jsonObject2.put("tableData", array);
			}
			jsonArray.add(jsonObject2);
		}
		json = jsonArray.toJSONString();
		return json;
	}
}
