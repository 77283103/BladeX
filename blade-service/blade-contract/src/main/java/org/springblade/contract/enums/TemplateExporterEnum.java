/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.contract.enums;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.PictureType;
import com.deepoove.poi.data.Pictures;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springblade.contract.constant.ContractFormInfoTemplateContract;
import org.springblade.contract.entity.*;
import org.springblade.contract.util.ConversionNuToCh;
import org.springblade.contract.util.DataFormatUtils;
import org.springblade.contract.util.MoneyToChiness;
import org.springblade.contract.vo.*;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.TemplateFieldJsonEntity;
import org.springblade.system.vo.TemplateRequestVO;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * 用户类型枚举
 *
 * @author Chill
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum TemplateExporterEnum {

	//物流服务合同（二段仓储+配送）
	WLFW_23("WLFW_23") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("sclPartya", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("sclPartyb", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("date", DataFormatUtils.systemTimeFormat(String.valueOf(j.get("date"))));
			dataModel.put("sclMorning", DataFormatUtils.systemTimeFormatH(j.get("sclMorning").toString()));
			dataModel.put("sclAfternoon", DataFormatUtils.systemTimeFormatH(j.get("sclAfternoon").toString()));
			//票据签收及货款回收要求-1
			dataModel.put("sclRequesta", DataFormatUtils.systemTimeFormatH(String.valueOf(j.get("sclRequesta"))));
			//物流服务项目及费用计算标准（未税价）：
			if ("1".equals(j.get("traffic"))) {
				dataModel.put("traffic", "☑");
				dataModel.put("area", "☐");
				dataModel.put("sclAread", "_");
			} else if ("2".equals(j.get("traffic"))) {
				dataModel.put("traffic", "☐");
				dataModel.put("area", "☑");
				dataModel.put("sclAread", j.get("sclAread"));
			} else {
				dataModel.put("traffic", "☐");
				dataModel.put("area", "☐");
				dataModel.put("sclAread", "_");
			}
			//、保证金条款
			dataModel.put("sclWhole", MoneyToChiness.tenThousand(j.get("sclYuan").toString()));

			//合同变更、解除和终止条款约定-1
			dataModel.put("startTimes", Func.isNull(contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.GLNZTimeFormat(contractFormInfoEntity.getStartingTime().toString()));
			dataModel.put("endTimes", Func.isNull(contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.GLNZTimeFormat(contractFormInfoEntity.getEndTime().toString()));
			//（以下无正文）
			//委托事项：（请在以下方框内打勾）
			dataModel.put("shippingSummons", j.get("shippingSummons").toString().contains("1") ? "☑" : "☐");
			dataModel.put("dispatching", j.get("shippingSummons").toString().contains("2") ? "☑" : "☐");
			dataModel.put("management", j.get("shippingSummons").toString().contains("3") ? "☑" : "☐");
			dataModel.put("other", j.get("shippingSummons").toString().contains("4") ? "☑" : "☐");
			dataModel.put("otherContent", j.get("shippingSummons").toString().contains("4") ? j.get("otherContent") : "");
//            if ("1".equals(j.get("shippingSummons"))) {
//                dataModel.put("shippingSummons", "☑");
//                dataModel.put("dispatching", "☐");
//                dataModel.put("management", "☐");
//                dataModel.put("other", "☐");
//                dataModel.put("otherContent", "");
//            } else if ("2".equals(j.get("shippingSummons"))) {
//                dataModel.put("shippingSummons", "☐");
//                dataModel.put("dispatching", "☑");
//                dataModel.put("management", "☐");
//                dataModel.put("other", "☐");
//                dataModel.put("otherContent", "");
//            } else if ("3".equals(j.get("shippingSummons"))) {
//                dataModel.put("shippingSummons", "☐");
//                dataModel.put("dispatching", "☐");
//                dataModel.put("management", "☑");
//                dataModel.put("other", "☐");
//                dataModel.put("otherContent", "");
//            } else if ("4".equals(j.get("shippingSummons"))) {
//                dataModel.put("shippingSummons", "☐");
//                dataModel.put("dispatching", "☐");
//                dataModel.put("management", "☐");
//                dataModel.put("other", "☑");
//                dataModel.put("otherContent", j.get("otherContent"));
//            } else {
//                dataModel.put("shippingSummons", "☐");
//                dataModel.put("dispatching", "☐");
//                dataModel.put("management", "☐");
//                dataModel.put("other", "☐");
//                dataModel.put("otherContent", "");
//            }
			dataModel.put("date3", DataFormatUtils.systemTimeFormat(String.valueOf(j.get("date3"))));
			dataModel.put("date4", DataFormatUtils.systemTimeFormat(String.valueOf(j.get("date4"))));
			//拼接附件
			dataModel.put("annex", j.get("annex"));
			//《出货传票》及《出货传票交接清单》样式
			dataModel.put("image", j.get("image"));
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	//作业外包协议
	WBXY_27("WBXY_27") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("employer", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("contractor", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			//承包期限
			dataModel.put("contractPeriodA", null == (contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.GLNZTimeFormat(contractFormInfoEntity.getStartingTime().toString()));
			dataModel.put("contractPeriodB", null == (contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.GLNZTimeFormat(contractFormInfoEntity.getEndTime().toString()));
			dataModel.put("compensationMuch", j.get("compensationMuch"));
			if (Func.isNotEmpty(j.get("compensationMuch")) && Func.isNotEmpty(j.get("damages")) ) {
				if (Integer.parseInt(j.get("compensationMuch").toString()) > Integer.parseInt(j.get("damages").toString())) {
					dataModel.put("damages", j.get("compensationMuch"));
				} else {
					dataModel.put("damages", j.get("damages"));
				}
			}
			dataModel.put("exceedterm", j.get("fewDays"));
			//履约保证金相关
			if (contractFormInfoEntity.getContractBond().size() <= 0) {
				dataModel.put("performance", "");
				dataModel.put("performanceA", "");
			} else if (contractFormInfoEntity.getContractBond().size() == 1) {
				if (contractFormInfoEntity.getContractBond().get(0).getIsNotBond().contains("1")) {
					dataModel.put("performance", "0");
					dataModel.put("performanceA", "零万元");
				} else if (contractFormInfoEntity.getContractBond().get(0).getIsNotBond().contains("0")) {
					String planPayAmount = String.valueOf(contractFormInfoEntity.getContractBond().get(0).getPlanPayAmount().divide(BigDecimal.valueOf(10000)));
					dataModel.put("performance", planPayAmount);
					dataModel.put("performanceA", MoneyToChiness.tenThousand(planPayAmount));
				}
			} else if (contractFormInfoEntity.getContractBond().size() >= 2) {
				AtomicReference<Double> valuesD = new AtomicReference<>(0D);
				contractFormInfoEntity.getContractBond().forEach(element -> {
					valuesD.updateAndGet(v -> v + Double.parseDouble(String.valueOf(element.getPlanPayAmount().divide(BigDecimal.valueOf(10000)))));
				});
				dataModel.put("performance", valuesD);
				dataModel.put("performanceA", MoneyToChiness.tenThousand(valuesD.toString()));
			}
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	//新陈列协议书
	CLXY_42("CLXY_42") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			List<YwlANewDisplay1ResponseVO> YwlANewDisplay1 = new ArrayList();
			//把json串转换成集合
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//给当前范本的合同关联表设置一个编号 等于编号时循环处理放到list中
				if (ContractFormInfoTemplateContract.CONTRACT_YWLANEWDISPLAY1.equals(templateField.getRelationCode())) {
					YwlANewDisplay1 = JSON.parseArray(templateField.getTableData(), YwlANewDisplay1ResponseVO.class);
					for (int i = 0; i < YwlANewDisplay1.size(); i++) {
						YwlANewDisplay1.get(i).setYwlNumber(String.valueOf(i + 1));
//						Map<String, Object> map = new HashMap();
//						map.put("ywlNumber", i + 1);
//						map.put("ywlDisplayProducts", jsonArr.getJSONObject(i).get("ywlDisplayProducts"));
//						map.put("ywlDisplayMode", jsonArr.getJSONObject(i).get("ywlDisplayMode"));
//						map.put("ywlMerchandisingStandards", jsonArr.getJSONObject(i).get("ywlMerchandisingStandards"));
//						list.add(map);
					}
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder().bind("YwlANewDisplay1", policy).build();
			dataModel.put("YwlANewDisplay1", YwlANewDisplay1);
			//这部分处理模板的变量字段
			dataModel.put("ywlPatya", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("ywlPatyb", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("ywlCooperationContent", j.get("ywlCooperationContent"));
			//日期格式的字段需要DataFormatUtils.systemTimeFormat处理一下
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dataModel.put("ywlTheStartTime", null == (contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getStartingTime())));
			dataModel.put("ywlEndOfTime", null == (contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getEndTime())));
			dataModel.put("ywlDisplayFee", j.get("ywlDisplayFee"));
			dataModel.put("ywlDisplayDfee", MoneyToChiness.moneyToChinese(j.get("ywlDisplayFee").toString()));
			//这里是处理下来选的字段的
			if ("1".equals(j.get("ywlDisplayType"))) {
				dataModel.put("ywlDisplayType", "☑");
				dataModel.put("ywlDisplayType1", "☐");
			} else if ("2".equals(j.get("ywlDisplayType"))) {
				dataModel.put("ywlDisplayType1", "☑");
				dataModel.put("ywlDisplayType", "☐");
			} else {
				dataModel.put("ywlDisplayType1", "☐");
				dataModel.put("ywlDisplayType", "☐");
			}
			dataModel.put("ywlOther", j.get("ywlOther"));
			modle.put("dataModel", setFile(filepaths, dataModel));
			modle.put("config", config);
			return modle;
		}
	},
	//店招合同
	DZHT_35("DZHT_35") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("ywlPatyA", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("ywlPatyB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dataModel.put("ywlAgreementPeriodStart", null == (contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getStartingTime())));
			dataModel.put("ywlAgreementPeriodEnd", null == (contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getEndTime())));
			dataModel.put("ywlAmountOf", MoneyToChiness.moneyToChinese(j.get("ywlProductionCosts").toString()));
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	//媒体类：视频广告改编合同
	GBHT_14("GBHT_14") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			List<MtlAdaptationContract1ResponseVO> MtlAdaptationContract1 = new ArrayList();
			List<MtlAdaptationContract2ResponseVO> MtlAdaptationContract2 = new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//*视频广告改编合同关联表
				if (ContractFormInfoTemplateContract.CONTRACT_MTADAPTATIONCONTRACT1.equals(templateField.getRelationCode())) {
					MtlAdaptationContract1 = JSON.parseArray(templateField.getTableData(), MtlAdaptationContract1ResponseVO.class);
//					for (int i = 0; i < jsonArr.size(); i++) {
//						Map<String, Object> map = new HashMap();
//						map.put("formDelivery", jsonArr.getJSONObject(i).get("formDelivery"));
//						map.put("number", jsonArr.getJSONObject(i).get("number"));
//						map.put("contentTheme", jsonArr.getJSONObject(i).get("contentTheme"));
//						map.put("requirements", jsonArr.getJSONObject(i).get("requirements"));
//						map.put("expenses", jsonArr.getJSONObject(i).get("expenses"));
//						list.add(map);
//					}
				}
				if (ContractFormInfoTemplateContract.CONTRACT_MTADAPTATIONCONTRACT2.equals(templateField.getRelationCode())) {
					MtlAdaptationContract2 = JSON.parseArray(templateField.getTableData(), MtlAdaptationContract2ResponseVO.class);
					if ("未".equals(j.get("mtlHaveHasNot"))) {
						MtlAdaptationContract2.clear();
						MtlAdaptationContract2ResponseVO mtlAdaptationContract2 = new MtlAdaptationContract2ResponseVO();
						mtlAdaptationContract2.setIntellectualProperty("//");
						mtlAdaptationContract2.setSample("//");
						mtlAdaptationContract2.setUseArea("//");
						mtlAdaptationContract2.setServiceLife("//");
						MtlAdaptationContract2.add(mtlAdaptationContract2);
					}
//					for (int i = 0; i < jsonArr.size(); i++) {
//						Map<String, Object> map = new HashMap();
//						map.put("intellectualProperty", jsonArr.getJSONObject(i).get("intellectualProperty"));
//						map.put("sample", jsonArr.getJSONObject(i).get("sample"));
//						map.put("serviceLife", jsonArr.getJSONObject(i).get("serviceLife"));
//						map.put("useArea", jsonArr.getJSONObject(i).get("useArea"));
//						list1.add(map);
//					}
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder()
				.bind("MtlAdaptationContract1", policy).bind("MtlAdaptationContract2", policy).build();
			dataModel.put("MtlAdaptationContract1", MtlAdaptationContract1);
			dataModel.put("MtlAdaptationContract2", MtlAdaptationContract2);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			//主表
			dataModel.put("mtlPatyA", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("mtlPatyB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("mtlProductionStartTime", Func.isNull(contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.GLNZTimeFormat(contractFormInfoEntity.getStartingTime().toString()));
			dataModel.put("mtlProductionCompletionTime", Func.isNull(contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.GLNZTimeFormat(contractFormInfoEntity.getEndTime().toString()));
			dataModel.put("mtlUnpaidTaxRmb", Func.isNull(contractFormInfoEntity.getContractAmount()) ? "" : contractFormInfoEntity.getContractAmount());
			dataModel.put("mtlRate", Func.isNull(contractFormInfoEntity.getContactTaxRate()) ? "" : contractFormInfoEntity.getContactTaxRate());
			dataModel.put("mtlTaxAmountIsRmb", Func.isNull(contractFormInfoEntity.getContractTaxAmount()) ? "" : contractFormInfoEntity.getContractTaxAmount());
			modle.put("dataModel", setFile(filepaths, dataModel));
			modle.put("config", config);
			return modle;
		}
	},

	//加工承揽合同（代工合同）
	CLHT_19("CLHT_19") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
			List<SclConstructionProject1ResponseVO> SclConstructionProject1 = new ArrayList();
			List<SclConstructionProject2ResponseVO> SclConstructionProject2 = new ArrayList();
			List<SclConstructionProject3ResponseVO> SclConstructionProject3 = new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//加工承揽合同（代工合同）关联表1
				if (ContractFormInfoTemplateContract.CONTRACT_SCLCONSTRUCTIONPROJECT1.equals(templateField.getRelationCode())) {
					SclConstructionProject1 = JSON.parseArray(templateField.getTableData(), SclConstructionProject1ResponseVO.class);
//					for (int i = 0; i < sclConstructionProject1List.size(); i++) {
//						JSONObject sclConstructionProject1 = JSON.parseObject(JSON.toJSONString(sclConstructionProject1List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//						Map<String, Object> map = new HashMap();
//						map.put("varieties", sclConstructionProject1.get("varieties"));
//						map.put("specifications", sclConstructionProject1.get("specifications"));
//						map.put("processingCost", sclConstructionProject1.get("processingCost"));
//						list.add(map);
//					}
				}
				//加工承揽合同（代工合同）关联表2
				if (ContractFormInfoTemplateContract.CONTRACT_SCLCONSTRUCTIONPROJECT2.equals(templateField.getRelationCode())) {
					SclConstructionProject2 = JSON.parseArray(templateField.getTableData(), SclConstructionProject2ResponseVO.class);
					for (int i = 0; i < SclConstructionProject2.size(); i++) {
						JSONObject sclConstructionProject2 = JSON.parseObject(JSON.toJSONString(SclConstructionProject2.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
						SclConstructionProject2.get(i).setSerialNumber(String.valueOf(i + 1));
//						Map<String, Object> map = new HashMap();
//						map.put("serialNumber", sclConstructionProject2.get("serialNumber"));
//						map.put("rawMaterial", sclConstructionProject2.get("rawMaterial"));
//						map.put("nameMaterial", sclConstructionProject2.get("nameMaterial"));
//						map.put("lossRate", sclConstructionProject2.get("lossRate"));
//						list1.add(map);
					}
				}
				//加工承揽合同（代工合同）关联表3
				if (ContractFormInfoTemplateContract.CONTRACT_SCLCONSTRUCTIONPROJECT3.equals(templateField.getRelationCode())) {
					SclConstructionProject3 = JSON.parseArray(templateField.getTableData(), SclConstructionProject3ResponseVO.class);
//					for (int i = 0; i < sclConstructionProject3List.size(); i++) {
//						JSONObject sclConstructionProject3 = JSON.parseObject(JSON.toJSONString(sclConstructionProject3List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//						Map<String, Object> map = new HashMap();
//						map.put("productName", sclConstructionProject3.get("productName"));
//						map.put("productSpecifications", sclConstructionProject3.get("productSpecifications"));
//						map.put("supplier", sclConstructionProject3.get("supplier"));
//						map.put("withoutTax", sclConstructionProject3.get("withoutTax"));
//						map.put("remarks", sclConstructionProject3.get("remarks"));
//						list2.add(map);
//					}
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder()
				.bind("SclConstructionProject1", policy).bind("SclConstructionProject2", policy).bind("SclConstructionProject3", policy).build();
			dataModel.put("SclConstructionProject1", SclConstructionProject1);
			dataModel.put("SclConstructionProject2", SclConstructionProject2);
			dataModel.put("SclConstructionProject3", SclConstructionProject3);
			//主表
			SclConstructionProjectEntity sclConstructionProject = JSONObject.toJavaObject(j, SclConstructionProjectEntity.class);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("sclOrderingParty", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("sclContractor", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("sclStartTime", null == (contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getStartingTime())));
			dataModel.put("sclCompletionTime", null == (contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getEndTime())));
			dataModel.put("annex", j.get("annex"));
			modle.put("dataModel", setFile(filepaths, dataModel));
			modle.put("config", config);
			return modle;
		}
	},
	//音频制作合同
	ZZHT_18("ZZHT_18") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			List<MtlAudioProductionContract1ResponseVO> MtlAudioProductionContract1 = new ArrayList();
			List<MtlAudioProductionContract2ResponseVO> MtlAudioProductionContract2 = new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//音频制作合同 关联表1
				if (ContractFormInfoTemplateContract.CONTRACT_MTLAUDIOPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
					MtlAudioProductionContract1 = JSON.parseArray(templateField.getTableData(), MtlAudioProductionContract1ResponseVO.class);
//                    for (int i = 0; i < mtlAudioProductionContract1List.size(); i++) {
//                        JSONObject mtlAudioProductionContract1 = JSON.parseObject(JSON.toJSONString(mtlAudioProductionContract1List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//                        Map<String, Object> map = new HashMap();
//                        map.put("formDelivery", mtlAudioProductionContract1.get("formDelivery"));
//                        map.put("number", mtlAudioProductionContract1.get("number"));
//                        map.put("contentTheme", mtlAudioProductionContract1.get("contentTheme"));
///                       map.put("requirements", mtlAudioProductionContract1.get("requirements"));
//                        map.put("expenses", mtlAudioProductionContract1.get("expenses"));
//                        list.add(map);
//                    }
				}
				//音频制作合同 关联表2
				if (ContractFormInfoTemplateContract.CONTRACT_MTLAUDIOPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
					MtlAudioProductionContract2 = JSON.parseArray(templateField.getTableData(), MtlAudioProductionContract2ResponseVO.class);
					if ("未".equals(j.get("mtlHaveHasNot"))) {
						MtlAudioProductionContract2.clear();
						MtlAudioProductionContract2ResponseVO mtlAdaptationContract2 = new MtlAudioProductionContract2ResponseVO();
						mtlAdaptationContract2.setIntellectualProperty("//");
						mtlAdaptationContract2.setUseArea("//");
						mtlAdaptationContract2.setServiceLife("//");
						MtlAudioProductionContract2.add(mtlAdaptationContract2);
					}
//                    for (int i = 0; i < mtlAudioProductionContract2List.size(); i++) {
//                        JSONObject mtlAudioProductionContract2 = JSON.parseObject(JSON.toJSONString(mtlAudioProductionContract2List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//                        Map<String, Object> map = new HashMap();
//                        map.put("intellectualProperty", mtlAudioProductionContract2.get("intellectualProperty"));
//                        map.put("serviceLife", mtlAudioProductionContract2.get("serviceLife"));
//                        map.put("useArea", mtlAudioProductionContract2.get("useArea"));
//                        list1.add(map);
//                    }
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder()
				.bind("MtlAudioProductionContract1", policy).bind("MtlAudioProductionContract2", policy).build();
			dataModel.put("MtlAudioProductionContract1", MtlAudioProductionContract1);
			dataModel.put("MtlAudioProductionContract2", MtlAudioProductionContract2);
			//主表
			MtlAudioProductionContractEntity mtlAudioProductionContract = JSONObject.toJavaObject(j, MtlAudioProductionContractEntity.class);
			dataModel.put("mtlPatyA", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("mtlPatyB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("mtlProductionStartTime", Func.isNull(contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.GLNZTimeFormat(contractFormInfoEntity.getStartingTime().toString()));
			dataModel.put("mtlProductionCompletionTime", Func.isNull(contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.GLNZTimeFormat(contractFormInfoEntity.getEndTime().toString()));
			//2.乙方制作的作品被甲方全部确认后，应同时提交以下内容（勾选）：
			StringBuilder mtlTerm = new StringBuilder();
			if (j.get("mtlSubmitContent").toString().contains("1")) {
				mtlTerm.append("(1)");
			}
			if (j.get("mtlSubmitContent").toString().contains("2")) {
				mtlTerm.append("(2)");
			}
			if (j.get("mtlSubmitContent").toString().contains("3")) {
				mtlTerm.append("(3)");
			}
			if (j.get("mtlSubmitContent").toString().contains("4")) {
				mtlTerm.append("(4)");
			}
			dataModel.put("mtlSubmitContent", mtlTerm.toString());
			dataModel.put("mtlUnpaidTaxRmb", Func.isNull(contractFormInfoEntity.getContractAmount()) ? "" : contractFormInfoEntity.getContractAmount());
			dataModel.put("mtlRate", Func.isNull(contractFormInfoEntity.getContactTaxRate()) ? "" : contractFormInfoEntity.getContactTaxRate());
			dataModel.put("mtlTaxAmountIsRmb", Func.isNull(contractFormInfoEntity.getContractTaxAmount()) ? "" : contractFormInfoEntity.getContractTaxAmount());
			if ("1".equals(j.getOrDefault("mtlTerm", ""))) {
				dataModel.put("mtlInvoiceDays1", j.get("mtlInvoiceDays1"));
				dataModel.put("mtlInvoiceDays2", " /");
				dataModel.put("mtlCapitalizationRmb2", " /");
				dataModel.put("mtlCapitalizationRmbSy2", " /");
			} else {
				dataModel.put("mtlInvoiceDays1", " /");
				dataModel.put("mtlInvoiceDays2", j.get("mtlInvoiceDays2"));
				dataModel.put("mtlCapitalizationRmb2", j.get("mtlCapitalizationRmb2"));
				dataModel.put("mtlCapitalizationRmbSy2", j.get("mtlCapitalizationRmbSy2"));
			}
			modle.put("dataModel", setFile(filepaths, dataModel));
			modle.put("config", config);
			return modle;
		}
	},
	//修图合同
	XTHT_17("XTHT_17") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map model = new HashMap();
			Map dataModel = new HashMap();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			List<MtlEditedTheContract1ResponseVO> MtlEditedTheContract1 = new ArrayList<>();
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//音频制作合同 关联表1
				if (ContractFormInfoTemplateContract.CONTRACT_MTLEDITEDTHECONTRACT1.equals(templateField.getRelationCode())) {
					MtlEditedTheContract1 = JSON.parseArray(templateField.getTableData(), MtlEditedTheContract1ResponseVO.class);
					for (int i = 0; i < MtlEditedTheContract1.size(); i++) {
						if (Func.isNotEmpty(MtlEditedTheContract1.get(i).getPictureSampleD()) && Func.notNull(MtlEditedTheContract1.get(i).getPictureSampleD())) {

							MtlEditedTheContract1.get(i).setPictureSampleD(Pictures.ofUrl(MtlEditedTheContract1.get(i).getPictureSample().substring(0, MtlEditedTheContract1.get(i).getPictureSample().length() - 1), PictureType.JPEG).size(50, 50).create());
//						JSONObject mtlEditedTheContract1 = JSON.parseObject(JSON.toJSONString(mtlEditedTheContract1List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//						MtlEditedTheContract1.get(i).setDeliveryTime(DataFormatUtils.systemTimeFormat(String.valueOf(mtlEditedTheContract1.get("deliveryTime"))));
//						Map<String, Object> map = new HashMap();
//						map.put("pictureSample", mtlEditedTheContract1.get("pictureSample"));
//						map.put("modificationRequirements", mtlEditedTheContract1.get("modificationRequirements"));
//						map.put("deliveryTime", DataFormatUtils.systemTimeFormat(String.valueOf(mtlEditedTheContract1.get("deliveryTime"))));
//						map.put("modeDelivery", mtlEditedTheContract1.get("modeDelivery"));
//						MtlEditedTheContract1.add(map);
						}
					}
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder()
				.bind("MtlEditedTheContract1", policy).build();
			dataModel.put("MtlEditedTheContract1", MtlEditedTheContract1);
			//主表
			MtlEditedTheContractEntity mtlEditedTheContract = JSONObject.toJavaObject(j, MtlEditedTheContractEntity.class);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("mtlPatyA", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("mtlPatyB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("mtlUnpaidTaxRmb", Func.isNull(contractFormInfoEntity.getContractAmount()) ? "" : contractFormInfoEntity.getContractAmount());
			dataModel.put("mtlRate", Func.isNull(contractFormInfoEntity.getContactTaxRate()) ? "" : contractFormInfoEntity.getContactTaxRate());
			dataModel.put("mtlTaxAmountIsRmb", Func.isNull(contractFormInfoEntity.getContractTaxAmount()) ? "" : contractFormInfoEntity.getContractTaxAmount());
			dataModel.put("period", Func.isNull(contractFormInfoEntity.getDays()) ? "" : contractFormInfoEntity.getDays());
			model.put("dataModel", setFile(filepaths, dataModel));
			model.put("config", config);
			return model;
		}
	},
	//视频制作合同
	ZZHT_16("ZZHT_16") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map model = new HashMap();
			Map dataModel = new HashMap();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			List<MtlVideoProductionContract1ResponseVO> MtlVideoProductionContract1 = new ArrayList<>();
			List<MtlVideoProductionContract2ResponseVO> MtlVideoProductionContract2 = new ArrayList<>();
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//视频制作合同 关联表1
				if (ContractFormInfoTemplateContract.CONTRACT_MTLVIDEOPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
					MtlVideoProductionContract1 = JSON.parseArray(templateField.getTableData(), MtlVideoProductionContract1ResponseVO.class);
				}
				//视频制作合同 关联表2
				if (ContractFormInfoTemplateContract.CONTRACT_MTLVIDEOPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
					MtlVideoProductionContract2 = JSON.parseArray(templateField.getTableData(), MtlVideoProductionContract2ResponseVO.class);
					if ("未".equals(j.get("mtlHaveHasNot"))) {
						MtlVideoProductionContract2.clear();
						MtlVideoProductionContract2ResponseVO mtlAdaptationContract2 = new MtlVideoProductionContract2ResponseVO();
						mtlAdaptationContract2.setIntellectualProperty("//");
						mtlAdaptationContract2.setSample("//");
						mtlAdaptationContract2.setUseArea("//");
						mtlAdaptationContract2.setServiceLife("//");
						MtlVideoProductionContract2.add(mtlAdaptationContract2);
					}
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder()
				.bind("MtlVideoProductionContract1", policy).bind("MtlVideoProductionContract2", policy).build();
			dataModel.put("MtlVideoProductionContract1", MtlVideoProductionContract1);
			dataModel.put("MtlVideoProductionContract2", MtlVideoProductionContract2);
			//主表
			MtlVideoProductionContractEntity mtlVideoProductionContract = JSONObject.toJavaObject(j, MtlVideoProductionContractEntity.class);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("mtlPatyA", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("mtlPatyB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("mtlProductionStartTime", Func.isNull(contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.GLNZTimeFormat(contractFormInfoEntity.getStartingTime().toString()));
			dataModel.put("mtlProductionCompletionTime", Func.isNull(contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.GLNZTimeFormat(contractFormInfoEntity.getEndTime().toString()));
			//2.乙方制作的作品被甲方全部确认后，应同时提交以下内容（勾选）：
			StringBuilder mtlTerm = new StringBuilder();
			if (j.get("mtlSubmitContent").toString().contains("1")) {
				mtlTerm.append("(1)");
			}
			if (j.get("mtlSubmitContent").toString().contains("2")) {
				mtlTerm.append("(2)");
			}
			if (j.get("mtlSubmitContent").toString().contains("3")) {
				mtlTerm.append("(3)");
			}
			if (j.get("mtlSubmitContent").toString().contains("4")) {
				mtlTerm.append("(4)");
			}
			dataModel.put("mtlSubmitContent", mtlTerm.toString());
			dataModel.put("mtlUnpaidTaxRmb", Func.isNull(contractFormInfoEntity.getContractAmount()) ? "" : contractFormInfoEntity.getContractAmount());
			dataModel.put("mtlRate", Func.isNull(contractFormInfoEntity.getContactTaxRate()) ? "" : contractFormInfoEntity.getContactTaxRate());
			dataModel.put("mtlTaxAmountIsRmb", Func.isNull(contractFormInfoEntity.getContractTaxAmount()) ? "" : contractFormInfoEntity.getContractTaxAmount());
			if ("1".equals(j.getOrDefault("mtlPaymentMethod", ""))) {
				dataModel.put("mtlInvoiceDays1", j.get("mtlInvoiceDays1"));
				dataModel.put("mtlInvoiceDays2", " /");
				dataModel.put("mtlCapitalizationRmb2", " /");
				dataModel.put("mtlCapitalizationRmbSy2", " /");
			} else {
				dataModel.put("mtlInvoiceDays1", " /");
				dataModel.put("mtlInvoiceDays2", j.get("mtlInvoiceDays2"));
				dataModel.put("mtlCapitalizationRmb2", j.get("mtlCapitalizationRmb2"));
				dataModel.put("mtlCapitalizationRmbSy2", j.get("mtlCapitalizationRmbSy2"));
			}
			model.put("dataModel", setFile(filepaths, dataModel));
			model.put("config", config);
			return model;
		}
	},
	//视频广告拍摄制作合同
	ZZHT_15("ZZHT_15") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			List<MtlShootingAndProductionContract1ResponseVO> MtlShootingAndProductionContract1 = new ArrayList();
			List<MtlShootingAndProductionContract2ResponseVO> MtlShootingAndProductionContract2 = new ArrayList();
			List<MtlShootingAndProductionContract3ResponseVO> MtlShootingAndProductionContract3 = new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//视频广告拍摄制作合同 关联表1
				if (ContractFormInfoTemplateContract.CONTRACT_MTLSHOOTINGANDPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
					MtlShootingAndProductionContract1 = JSON.parseArray(templateField.getTableData(), MtlShootingAndProductionContract1ResponseVO.class);
//					for (int i = 0; i < mtlShootingAndProductionContract1List.size(); i++) {
//						JSONObject mtlShootingAndProductionContract1 = JSON.parseObject(JSON.toJSONString(mtlShootingAndProductionContract1List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//						Map<String, Object> map = new HashMap();
//						map.put("formDelivery", mtlShootingAndProductionContract1.get("formDelivery"));
//						map.put("number", mtlShootingAndProductionContract1.get("number"));
//						map.put("contentTheme", mtlShootingAndProductionContract1.get("contentTheme"));
//						map.put("requirements", mtlShootingAndProductionContract1.get("requirements"));
//						map.put("expenses", mtlShootingAndProductionContract1.get("expenses"));
//						list.add(map);
//					}
				}
				//视频广告拍摄制作合同 关联表2
				if (ContractFormInfoTemplateContract.CONTRACT_MTLSHOOTINGANDPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
					MtlShootingAndProductionContract2 = JSON.parseArray(templateField.getTableData(), MtlShootingAndProductionContract2ResponseVO.class);
//					for (int i = 0; i < mtlShootingAndProductionContract2List.size(); i++) {
//						JSONObject mtlShootingAndProductionContract2 = JSON.parseObject(JSON.toJSONString(mtlShootingAndProductionContract2List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//						Map<String, Object> map = new HashMap();
//						map.put("intellectualProperty", mtlShootingAndProductionContract2.get("intellectualProperty"));
//						map.put("sample", mtlShootingAndProductionContract2.get("sample"));
//						map.put("serviceLife", mtlShootingAndProductionContract2.get("serviceLife"));
//						map.put("useArea", mtlShootingAndProductionContract2.get("useArea"));
//						list1.add(map);
//					}
				}
				//视频广告拍摄制作合同 关联表3
				if (ContractFormInfoTemplateContract.CONTRACT_MTLSHOOTINGANDPRODUCTIONCONTRACT3.equals(templateField.getRelationCode())) {
					MtlShootingAndProductionContract3 = JSON.parseArray(templateField.getTableData(), MtlShootingAndProductionContract3ResponseVO.class);
//					for (int i = 0; i < mtlShootingAndProductionContract3List.size(); i++) {
//						JSONObject mtlShootingAndProductionContract3 = JSON.parseObject(JSON.toJSONString(mtlShootingAndProductionContract3List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//						Map<String, Object> map = new HashMap();
//						map.put("file", mtlShootingAndProductionContract3.get("file"));
//						map.put("creationTime", DataFormatUtils.timeStamp2Date(String.valueOf(mtlShootingAndProductionContract3.get("creationTime"))));
//						map.put("completeplace", mtlShootingAndProductionContract3.get("completeplace"));
//						map.put("creator", mtlShootingAndProductionContract3.get("creator"));
//						map.put("employment", mtlShootingAndProductionContract3.get("employment"));
//						list2.add(map);
//					}
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder()
				.bind("MtlShootingAndProductionContract1", policy).bind("MtlShootingAndProductionContract2", policy).bind("MtlShootingAndProductionContract3", policy).build();
			dataModel.put("MtlShootingAndProductionContract1", MtlShootingAndProductionContract1);
			dataModel.put("MtlShootingAndProductionContract2", MtlShootingAndProductionContract2);
			dataModel.put("MtlShootingAndProductionContract3", MtlShootingAndProductionContract3);
			//主表
			MtlShootingAndProductionContractEntity mtlShootingAndProductionContract = JSONObject.toJavaObject(j, MtlShootingAndProductionContractEntity.class);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("mtlPatyA", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("mtlPatyB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("mtlProductionStartTime", Func.isNull(contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.GLNZTimeFormat(contractFormInfoEntity.getStartingTime().toString()));
			dataModel.put("mtlProductionCompletionTime", Func.isNull(contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.GLNZTimeFormat(contractFormInfoEntity.getEndTime().toString()));
			//2.乙方制作的作品被甲方全部确认后，应同时提交以下内容（勾选）：
			StringBuilder mtlTerm = new StringBuilder();
			if (j.get("mtlSubmitCheck").toString().contains("1")) {
				mtlTerm.append("(1),");
			}
			if (j.get("mtlSubmitCheck").toString().contains("2")) {
				mtlTerm.append("(2),");
			}
			if (j.get("mtlSubmitCheck").toString().contains("3")) {
				mtlTerm.append("(3),");
			}
			if (j.get("mtlSubmitCheck").toString().contains("4")) {
				mtlTerm.append("(4)");
			}
			dataModel.put("mtlSubmitCheck", mtlTerm.toString());
			dataModel.put("mtlUnpaidTaxRmb", Func.isNull(contractFormInfoEntity.getContractAmount()) ? "" : contractFormInfoEntity.getContractAmount());
			dataModel.put("mtlRate", Func.isNull(contractFormInfoEntity.getContactTaxRate()) ? "" : contractFormInfoEntity.getContactTaxRate());
			dataModel.put("mtlTaxAmountIsRmb", Func.isNull(contractFormInfoEntity.getContractTaxAmount()) ? "" : contractFormInfoEntity.getContractTaxAmount());
			dataModel.put("dateSigning", DataFormatUtils.systemTimeFormat(String.valueOf(j.get("dateSigning"))));
			dataModel.put("contractNo", Func.isNull(contractFormInfoEntity.getContractNumber()) ? "" : contractFormInfoEntity.getContractNumber());
			modle.put("dataModel", setFile(filepaths, dataModel));
			modle.put("config", config);
			return modle;
		}
	},
	//活动执行合同
	HDZX_05("HDZX_05") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("cglPartya", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("cglPartyb", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dataModel.put("cglByTime", null == (contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getStartingTime())));
			dataModel.put("cglAsTime", null == (contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getEndTime())));
			dataModel.put("cglTotal", contractFormInfoEntity.getContractAmount());
			dataModel.put("capitalization", MoneyToChiness.moneyToChinese(Func.isNull(contractFormInfoEntity.getContractTaxAmount()) ? "" : contractFormInfoEntity.getContractTaxAmount().toString()));
			if (j.get("cglPayment").toString().contains("2.1")) {
				dataModel.put("days", j.get("days"));
				dataModel.put("amount", j.get("amount"));
				dataModel.put("element", j.get("element"));
				dataModel.put("amountWords", MoneyToChiness.moneyToChinese(j.get("element").toString()));
				dataModel.put("cglProportion", j.get("cglProportion"));
				dataModel.put("cglLumpSum", j.get("cglLumpSum"));
				dataModel.put("cglCapitalize", MoneyToChiness.moneyToChinese(j.get("cglLumpSum").toString()));
				dataModel.put("other", "——");
			} else if (j.get("cglPayment").toString().contains("2.2")) {
				dataModel.put("days", "——");
				dataModel.put("amount", "——");
				dataModel.put("element", "——");
				dataModel.put("amountWords", "——");
				dataModel.put("cglProportion", "——");
				dataModel.put("cglLumpSum", "__");
				dataModel.put("cglCapitalize", "__");
				dataModel.put("other", j.get("other"));
			} else {
				dataModel.put("days", "__");
				dataModel.put("amount", "__");
				dataModel.put("element", "__");
				dataModel.put("amountWords", "__");
				dataModel.put("cglProportion", "__");
				dataModel.put("cglLumpSum", "__");
				dataModel.put("cglCapitalize", "__");
				dataModel.put("other", "__");
			}
			dataModel.put("cglInvoice", Func.isNull(contractFormInfoEntity.getContactTaxRate()) ? "" : contractFormInfoEntity.getContactTaxRate());
			dataModel.put("annex", j.getOrDefault("annex", ""));
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	//物流服务合同（二段配送）
	FWHT_24("FWHT_24") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			SclLogisticsServiceEntity sclLogisticsService = JSONObject.toJavaObject(j, SclLogisticsServiceEntity.class);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("partya", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("partyb", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("date", DataFormatUtils.systemTimeFormat(String.valueOf(j.get("date"))));
			dataModel.put("no", DataFormatUtils.systemTimeFormatH(String.valueOf(j.get("no"))));
			dataModel.put("storageee", DataFormatUtils.systemTimeFormatH(String.valueOf(j.get("storageee"))));
			dataModel.put("conditionsa", DataFormatUtils.systemTimeFormatH(String.valueOf(j.get("conditionsa"))));
			dataModel.put("contractd", DataFormatUtils.systemTimeFormatH(j.get("contractd").toString()));
			dataModel.put("breach", MoneyToChiness.tenThousand(j.get("second").toString()));
			dataModel.put("date2", Func.isNull(contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.GLNZTimeFormat(contractFormInfoEntity.getStartingTime().toString()));
			dataModel.put("requirementsss", Func.isNull(contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.GLNZTimeFormat(contractFormInfoEntity.getEndTime().toString()));
			dataModel.put("choice", j.get("choice").toString().contains("1") ? "☑" : "☐");
			dataModel.put("choice1", j.get("choice").toString().contains("2") ? "☑" : "☐");
			dataModel.put("choice2", j.get("choice").toString().contains("3") ? "☑" : "☐");
			dataModel.put("other", j.get("choice").toString().contains("4") ? "☑" : "☐");
			dataModel.put("otherContent", j.get("choice").toString().contains("4") ? j.get("otherContent") : "");
			//委托期间：
			dataModel.put("date5", DataFormatUtils.systemTimeFormat(String.valueOf(j.get("date5"))));
			dataModel.put("date6", DataFormatUtils.systemTimeFormat(String.valueOf(j.get("date6"))));
			dataModel.put("date7", DataFormatUtils.systemTimeFormat(String.valueOf(j.get("date7"))));
			dataModel.put("date8", DataFormatUtils.systemTimeFormat(String.valueOf(j.get("date8"))));
			//附件:8：厂商承诺书
			dataModel.put("company", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("company1", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	//媒体类：平面广告拍摄制作合同
	ZZHT_12("ZZHT_12") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			List<MtbProductionContract1ResponseVO> MtbProductionContract1 = new ArrayList();
			List<MtbProductionContract2ResponseVO> MtbProductionContract2 = new ArrayList();
			List<MtbProductionContract3ResponseVO> MtbProductionContract3 = new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//平面广告拍摄制作合同 关联表1
				if (ContractFormInfoTemplateContract.CONTRACT_MTBPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
					MtbProductionContract1 = JSON.parseArray(templateField.getTableData(), MtbProductionContract1ResponseVO.class);
//					for (int i = 0; i < mtbProductionContract1List.size(); i++) {
//						JSONObject mtbProductionContract1 = JSON.parseObject(JSON.toJSONString(mtbProductionContract1List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//						Map<String, Object> map = new HashMap();
//						map.put("formDelivery", mtbProductionContract1.get("formDelivery"));
//						map.put("number", mtbProductionContract1.get("number"));
//						map.put("requirements", mtbProductionContract1.get("requirements"));
//						map.put("expenses", mtbProductionContract1.get("expenses"));
//						list.add(map);
//					}
				}
				//平面广告拍摄制作合同 关联表2
				if (ContractFormInfoTemplateContract.CONTRACT_MTBPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
					MtbProductionContract2 = JSON.parseArray(templateField.getTableData(), MtbProductionContract2ResponseVO.class);
					if ("未".equals(j.get("mtbHaveHasNot"))) {
						MtbProductionContract2.clear();
						MtbProductionContract2ResponseVO mtlAdaptationContract2 = new MtbProductionContract2ResponseVO();
						mtlAdaptationContract2.setIntellectualProperty("//");
						mtlAdaptationContract2.setSmallKind("//");
						mtlAdaptationContract2.setUseArea("//");
						mtlAdaptationContract2.setServiceLife("//");
						MtbProductionContract2.add(mtlAdaptationContract2);
					}
//					for (int i = 0; i < mtbProductionContract2List.size(); i++) {
//						JSONObject mtbProductionContract2 = JSON.parseObject(JSON.toJSONString(mtbProductionContract2List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//						Map<String, Object> map = new HashMap();
//						map.put("intellectualProperty", mtbProductionContract2.get("intellectualProperty"));
//						map.put("smallKind", mtbProductionContract2.get("smallKind"));
//						map.put("serviceLife", mtbProductionContract2.get("serviceLife"));
//						map.put("useArea", mtbProductionContract2.get("useArea"));
//						list1.add(map);
//					}
				}
				//平面广告拍摄制作合同 关联表3
				if (ContractFormInfoTemplateContract.CONTRACT_MTBPRODUCTIONCONTRACT3.equals(templateField.getRelationCode())) {
					MtbProductionContract3 = JSON.parseArray(templateField.getTableData(), MtbProductionContract3ResponseVO.class);
//					for (int i = 0; i < mtbProductionContract3List.size(); i++) {
//						JSONObject mtbProductionContract3 = JSON.parseObject(JSON.toJSONString(mtbProductionContract3List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//						Map<String, Object> map = new HashMap();
//						map.put("wenJian", mtbProductionContract3.get("wenJian"));
//						map.put("shouChuang", DataFormatUtils.timeStamp2Date(String.valueOf(mtbProductionContract3.get("shouChuang"))));
//						map.put("wanCheng", mtbProductionContract3.get("wanCheng"));
//						map.put("createZhe", mtbProductionContract3.get("createZhe"));
//						map.put("zhiWuChuang", mtbProductionContract3.get("zhiWuChuang"));
//						list2.add(map);
//					}
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder()
				.bind("MtbProductionContract1", policy).bind("MtbProductionContract2", policy).bind("MtbProductionContract3", policy).build();
			dataModel.put("MtbProductionContract1", MtbProductionContract1);
			dataModel.put("MtbProductionContract2", MtbProductionContract2);
			dataModel.put("MtbProductionContract3", MtbProductionContract3);
			//主表
			MtbProductionContractEntity mtbProductionContract = JSONObject.toJavaObject(j, MtbProductionContractEntity.class);
			dataModel.put("mtbPatyA", contractFormInfoEntity.getSealName());
			dataModel.put("mtbPatyB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("mtbShootingStartTime", Func.isNull(contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.GLNZTimeFormat(String.valueOf(contractFormInfoEntity.getStartingTime())));
			dataModel.put("mtbShootingCompletionTime", Func.isNull(contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.GLNZTimeFormat(String.valueOf(contractFormInfoEntity.getEndTime())));
			//2.乙方制作的作品被甲方全部确认后，应同时提交以下内容（勾选）：
			StringBuilder mtlTerm = new StringBuilder();
			if (Func.isNull(j.get("mtbSubmitContent"))) {
				mtlTerm.append("/");
			} else {
				if (j.get("mtbSubmitContent").toString().contains("1")) {
					mtlTerm.append("(1)");
				}
				if (j.get("mtbSubmitContent").toString().contains("2")) {
					mtlTerm.append("(2)");
				}
			}
			dataModel.put("mtbSubmitContent", mtlTerm.toString());
			dataModel.put("mtbUnpaidTaxRmb", Func.isNull(contractFormInfoEntity.getContractAmount()) ? "" : contractFormInfoEntity.getContractAmount());
			dataModel.put("mtbRate", Func.isNull(contractFormInfoEntity.getContactTaxRate()) ? "" : contractFormInfoEntity.getContactTaxRate());
			dataModel.put("mtbTaxInclusiveInRmb", Func.isNull(contractFormInfoEntity.getContractTaxAmount()) ? "" : contractFormInfoEntity.getContractTaxAmount());
			dataModel.put("mtbTaxInclusiveInRmbD", MoneyToChiness.moneyToChinese(contractFormInfoEntity.getContractTaxAmount().toString()));
			dataModel.put("qiandingTime", DataFormatUtils.GLNZTimeFormat(String.valueOf(j.getOrDefault("qiandingTime", ""))));
			dataModel.put("weituo", j.getOrDefault("weituo", ""));
			modle.put("dataModel", setFile(filepaths, dataModel));
			modle.put("config", config);
			return modle;
		}
	},
	//生产类：设备维修保养合同
	BYHT_21("BYHT_21") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
			List<SclEquipmentMaintenance1ResponseVO> SclEquipmentMaintenance1 = new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//设备维修保养合同 关联表1
				if (ContractFormInfoTemplateContract.CONTRACT_SCLEQUIOMENTMAINTENANCE1.equals(templateField.getRelationCode())) {
					SclEquipmentMaintenance1 = JSON.parseArray(templateField.getTableData(), SclEquipmentMaintenance1ResponseVO.class);
					for (int i = 0; i < SclEquipmentMaintenance1.size(); i++) {
						SclEquipmentMaintenance1.get(i).setSclNumber(i + 1);
//						JSONObject slEquipmentMaintenance1 = JSON.parseObject(JSON.toJSONString(slEquipmentMaintenance1List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//						Map<String, Object> map = new HashMap();
//						map.put("sclNumber", i + 1);
//						map.put("sclDeviceName", slEquipmentMaintenance1.get("sclDeviceName"));
//						map.put("sclBrand", slEquipmentMaintenance1.get("sclBrand"));
//						map.put("sclSpecification", slEquipmentMaintenance1.get("sclSpecification"));
//						map.put("sclPrice", slEquipmentMaintenance1.get("sclPrice"));
//						map.put("sclNumbers", slEquipmentMaintenance1.get("sclNumbers"));
//						map.put("sclOther", slEquipmentMaintenance1.get("sclOther"));
//						list.add(map);
					}
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder()
				.bind("SclEquipmentMaintenance1", policy).build();
			dataModel.put("SclEquipmentMaintenance1", SclEquipmentMaintenance1);
			//主表
			SclEquipmentMaintenanceEntity sclEquipmentMaintenance = JSONObject.toJavaObject(j, SclEquipmentMaintenanceEntity.class);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("sclPatyA", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("sclPatyB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			AtomicReference<Double> valuesD = new AtomicReference<>(0D);
			SclEquipmentMaintenance1.forEach(element -> {
				valuesD.updateAndGet(v -> v + BigDecimal.valueOf(Double.parseDouble(element.getSclPrice().toString())).multiply(BigDecimal.valueOf(Double.parseDouble(element.getSclNumbers().toString()))).doubleValue());
			});
			dataModel.put("sclTotalRmb", valuesD);
			dataModel.put("sclCapitalRmb", MoneyToChiness.moneyToChinese(String.valueOf(valuesD)));
			//1、累计运行时间维修保养
			if (j.get("sclEquipment").toString().contains("1")) {
				dataModel.put("sclMaintenancessss", j.get("sclMaintenancessss"));
				dataModel.put("sclGuaranteePeriod", j.get("sclGuaranteePeriod"));
			} else {
				dataModel.put("sclMaintenancessss", "＿");
				dataModel.put("sclGuaranteePeriod", "＿");
			}
			//2、单次维修保养
			if (j.get("sclEquipment").toString().contains("2")) {
				dataModel.put("sclQuality", j.get("sclQuality"));
			} else {
				dataModel.put("sclQuality", "＿");
			}
			//3、月度维修保养
			if (j.get("sclEquipment").toString().contains("3")) {
				dataModel.put("sclRoutineMaintenancess", j.get("sclRoutineMaintenancess"));
				dataModel.put("sclMonth", j.get("sclMonth"));
			} else {
				dataModel.put("sclRoutineMaintenancess", "＿");
				dataModel.put("sclMonth", "＿");
			}
			//4、季度维修保养
			if (j.get("sclEquipment").toString().contains("4")) {
				dataModel.put("sclMaintenance", j.get("sclMaintenance"));
				dataModel.put("sclPeriod", j.get("sclPeriod"));
			} else {
				dataModel.put("sclMaintenance", "＿");
				dataModel.put("sclPeriod", "＿");
			}
			//5、年度维修保养
			if (j.get("sclEquipment").toString().contains("5")) {
				dataModel.put("sclMaintenances", j.get("sclMaintenances"));
				dataModel.put("sclRoutine", j.get("sclRoutine"));
			} else {
				dataModel.put("sclMaintenances", "＿");
				dataModel.put("sclRoutine", "＿");
			}
			if (j.get("sclSides").toString().contains("(3)")) {
				dataModel.put("sclOtherWay", j.get("sclOtherWay"));
			} else {
				dataModel.put("sclOtherWay", "＿");
			}
			dataModel.put("sclStart", null == (contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getStartingTime())));
			dataModel.put("sclLaste", null == (contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getEndTime())));
			dataModel.put("annex", j.get("annex"));
			modle.put("dataModel", setFile(filepaths, dataModel));
			modle.put("config", config);
			return modle;
		}
	},
	//物流服务合同（一段+调拨运输）
	FWHT_25("FWHT_25") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			SclProductionCategoryEntity sclProductionCategory = JSONObject.toJavaObject(j, SclProductionCategoryEntity.class);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("sclPartyA", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("sclPartyB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			//签订时间
			dataModel.put("sclDateOfSigning", DataFormatUtils.systemTimeFormat(String.valueOf(j.get("sclDateOfSigning"))));
			//签订地点
			dataModel.put("sclSite", new TextRenderData((String) j.get("sclSite"), Style.builder().buildBold().build()));
			//保证金履行方式一
			switch (j.get("bondChooseOne").toString()) {
				case "1":
					dataModel.put("bondChooseOne", "☑");
					dataModel.put("sclNo", j.get("sclNo"));
					dataModel.put("sclStorageee", MoneyToChiness.tenThousand(j.get("sclNo").toString()));
					dataModel.put("bondChooseTow", "☐");
					dataModel.put("sclBail", "/");
					dataModel.put("sclStorageee1", "/");
					dataModel.put("bondChooseThree", "☐");
					dataModel.put("sclAffiliatedEnterprise", "/");
					dataModel.put("sclContract", "/");
					dataModel.put("sclDeposit", "/");
					dataModel.put("sclStorageee2", "/");
					break;
				//保证金履行方式二
				case "2":
					dataModel.put("bondChooseOne", "☐");
					dataModel.put("sclNo", "/");
					dataModel.put("sclStorageee", "/");
					dataModel.put("bondChooseTow", "☑");
					dataModel.put("sclBail", j.get("sclBail"));
					dataModel.put("sclStorageee1", MoneyToChiness.tenThousand(j.get("sclBail").toString()));
					dataModel.put("bondChooseThree", "☐");
					dataModel.put("sclAffiliatedEnterprise", "/");
					dataModel.put("sclContract", "/");
					dataModel.put("sclDeposit", "/");
					dataModel.put("sclStorageee2", "/");
					break;
				case "3":
					dataModel.put("bondChooseOne", "☐");
					dataModel.put("sclNo", "/");
					dataModel.put("sclStorageee", "/");
					dataModel.put("bondChooseTow", "☐");
					dataModel.put("sclBail", "/");
					dataModel.put("sclStorageee1", "/");
					dataModel.put("bondChooseThree", "☑");
					dataModel.put("sclAffiliatedEnterprise", j.get("sclAffiliatedEnterprise"));
					dataModel.put("sclContract", j.get("sclContract"));
					dataModel.put("sclDeposit", j.get("sclDeposit"));
					dataModel.put("sclStorageee2", MoneyToChiness.tenThousand(j.get("sclDeposit").toString()));
					break;
				case "":
					dataModel.put("bondChooseOne", "☐");
					dataModel.put("sclNo", "/");
					dataModel.put("sclStorageee", "/");
					dataModel.put("bondChooseTow", "☐");
					dataModel.put("sclBail", "/");
					dataModel.put("sclStorageee1", "/");
					dataModel.put("bondChooseThree", "☐");
					dataModel.put("sclAffiliatedEnterprise", "/");
					dataModel.put("sclContract", "/");
					dataModel.put("sclDeposit", "/");
					dataModel.put("sclStorageee2", "/");
					break;
			}
			//这里是处理下拉选的字段的
			if ("1".equals(j.get("sclDrinks"))) {
				dataModel.put("sclDrinks", "☑中石化☐中石油");
			} else if ("2".equals(j.get("sclDrinks"))) {
				dataModel.put("sclDrinks", "☐中石化☑中石油");
			} else {
				dataModel.put("sclDrinks", "☐中石化☐中石油");
			}
			//本合同期限为
			dataModel.put("sclDateOfs", Func.isNull(contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.GLNZTimeFormat(String.valueOf(contractFormInfoEntity.getStartingTime())));
			dataModel.put("sclRequirementsss", Func.isNull(contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.GLNZTimeFormat(String.valueOf(contractFormInfoEntity.getEndTime())));
			dataModel.put("sclCompany", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},

	//采购类_打样合同书
	DYHT_03("DYHT_03") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
			List<CglProofingContract1ResponseVO> CglProofingContract1 = new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//采购类_打样合同书 关联表1
				if (ContractFormInfoTemplateContract.CONTRACT_CGLPROOFINGCONTRACT1.equals(templateField.getRelationCode())) {
					CglProofingContract1 = JSON.parseArray(templateField.getTableData(), CglProofingContract1ResponseVO.class);
//					for (int i = 0; i < cglProofingContract1List.size(); i++) {
//						JSONObject glProofingContract1 = JSON.parseObject(JSON.toJSONString(cglProofingContract1List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//						Map<String, Object> map = new HashMap();
//						map.put("proofingProducts", glProofingContract1.get("proofingProducts"));
//						map.put("proofingContent", glProofingContract1.get("proofingContent"));
//						map.put("textureMaterial", glProofingContract1.get("textureMaterial"));
//						map.put("unitPrice", glProofingContract1.get("unitPrice"));
//						map.put("number", glProofingContract1.get("number"));
//						map.put("totalAmount", glProofingContract1.get("totalAmount"));
//						list.add(map);
//					}
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder().bind("CglProofingContract1", policy).build();
			dataModel.put("CglProofingContract1", CglProofingContract1);
			//主表
			CglProofingContractEntity cglProofingContract = JSONObject.toJavaObject(j, CglProofingContractEntity.class);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("partyA", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("partyB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			AtomicReference<Double> valuesD = new AtomicReference<>(0D);
			CglProofingContract1.forEach(element -> {
				valuesD.updateAndGet(v -> v + Double.parseDouble(element.getTotalAmount().toString()));
			});
			dataModel.put("element", valuesD);
			dataModel.put("date", DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getStartingTime())));
			dataModel.put("element1", Func.isNull(contractFormInfoEntity.getContractAmount()) ? 0 : contractFormInfoEntity.getContractAmount());
			dataModel.put("taxRate", Func.isNull(contractFormInfoEntity.getContactTaxRate()) ? 0 : contractFormInfoEntity.getContactTaxRate());
			dataModel.put("element2", Func.isNull(contractFormInfoEntity.getContractTaxAmount()) ? 0 : contractFormInfoEntity.getContractTaxAmount());
			modle.put("dataModel", setFile(filepaths, dataModel));
			modle.put("config", config);
			return modle;
		}
	},
	//协议（二方）
	BMXY_01("BMXY_01") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			List<Map<String, Object>> list = new ArrayList();
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("contactPartyA", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("contactPartyB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("specificDate", DataFormatUtils.systemTimeFormat(String.valueOf(j.get("specificDate"))));
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	//保密协议（三方）
	BMXY_02("BMXY_02") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			List<Map<String, Object>> list = new ArrayList();
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("jiaFang", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("specificDate", DataFormatUtils.systemTimeFormat(String.valueOf(j.get("specificDate"))));
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	//房屋租赁合同模板
	FWZL_36("FWZL_36") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			YwbBusinessContractTemplateEntity ywbBusinessContractTemplateEntity = JSONObject.toJavaObject(j, YwbBusinessContractTemplateEntity.class);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("ywbLessors", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("ywbTenantry", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("ywbTermStart", Func.isNull(contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.GLNZTimeFormat(String.valueOf(contractFormInfoEntity.getStartingTime())));
			dataModel.put("ywbTermEnd", Func.isNull(contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.GLNZTimeFormat(String.valueOf(contractFormInfoEntity.getEndTime())));
			dataModel.put("ywbShall", Func.isNull(contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.GLNZTimeFormat(String.valueOf(contractFormInfoEntity.getStartingTime())));
			if (j.get("ywbPayment").toString().contains("1")) {
				BigDecimal sum = BigDecimal.valueOf(Double.parseDouble(String.valueOf(j.get("ywbStandard"))))
					.multiply(BigDecimal.valueOf(Double.parseDouble(j.get("ywbShuilv").toString()) / 100 + 1))
					.multiply(BigDecimal.valueOf(getMonthDiff(
						DataFormatUtils.GLNZTimeFormatBar(String.valueOf(contractFormInfoEntity.getStartingTime())),
						DataFormatUtils.GLNZTimeFormatBar(String.valueOf(contractFormInfoEntity.getEndTime())))));
				dataModel.put("ywbPayment", "☑月/☐季/☐半年/☐年");
				dataModel.put("ywbZujinjine", sum);
				//租金金额大写
				dataModel.put("ywbZujinjined", MoneyToChiness.moneyToChinese(sum.toString()));
			}
			if (j.get("ywbPayment").toString().contains("2")) {
				BigDecimal sum = BigDecimal.valueOf(Double.parseDouble(String.valueOf(j.get("ywbStandard"))))
					.multiply(BigDecimal.valueOf(Double.parseDouble(j.get("ywbShuilv").toString()) / 100 + 1))
					.multiply(BigDecimal.valueOf(getMonthDiff(
						DataFormatUtils.GLNZTimeFormatBar(String.valueOf(contractFormInfoEntity.getStartingTime())),
						DataFormatUtils.GLNZTimeFormatBar(String.valueOf(contractFormInfoEntity.getEndTime()))
					) / 3));
				dataModel.put("ywbPayment", "☐月/☑季/☐半年/☐年");
				dataModel.put("ywbZujinjine", sum);
				dataModel.put("ywbZujinjined", MoneyToChiness.moneyToChinese(sum.toString()));
			}
			if (j.get("ywbPayment").toString().contains("3")) {
				BigDecimal sum = BigDecimal.valueOf(Double.parseDouble(String.valueOf(j.get("ywbStandard"))))
					.multiply(BigDecimal.valueOf(Double.parseDouble(j.get("ywbShuilv").toString()) / 100 + 1))
					.multiply(BigDecimal.valueOf(getMonthDiff(
						DataFormatUtils.GLNZTimeFormatBar(String.valueOf(contractFormInfoEntity.getStartingTime())),
						DataFormatUtils.GLNZTimeFormatBar(String.valueOf(contractFormInfoEntity.getEndTime()))
					) / 6));
				dataModel.put("ywbPayment", "☐月/☐季/☑半年/☐年");
				dataModel.put("ywbZujinjine", sum);
				dataModel.put("ywbZujinjined", MoneyToChiness.moneyToChinese(sum.toString()));
			}
			if (j.get("ywbPayment").toString().contains("4")) {
				BigDecimal sum = BigDecimal.valueOf(Double.parseDouble(String.valueOf(j.get("ywbStandard"))))
					.multiply(BigDecimal.valueOf(Double.parseDouble(j.get("ywbShuilv").toString()) / 100 + 1))
					.multiply(BigDecimal.valueOf(getMonthDiff(
						DataFormatUtils.GLNZTimeFormatBar(String.valueOf(contractFormInfoEntity.getStartingTime())),
						DataFormatUtils.GLNZTimeFormatBar(String.valueOf(contractFormInfoEntity.getEndTime()))
					) / 12));
				dataModel.put("ywbPayment", "☐月/☐季/☐半年/☑年");
				dataModel.put("ywbZujinjine", sum);
				dataModel.put("ywbZujinjined", MoneyToChiness.moneyToChinese(sum.toString()));
			}
			if (j.get("ywbPayment").toString().equals("")) {
				dataModel.put("ywbPayment", "☐月/☐季/☐半年/☐年");
				dataModel.put("ywbZujinjine", " ");
				dataModel.put("ywbZujinjined", " ");
			}
			//租金支付方式：乙方以银行汇款的方式按（${ywbBankRemittance}）支付；
			if (j.get("ywbBankRemittance").toString().contains("1")) {
				dataModel.put("ywbBankRemittance", "☑月/☐季/☐半年/☐年");
			}
			if (j.get("ywbBankRemittance").toString().contains("2")) {
				dataModel.put("ywbBankRemittance", "☐月/☑季/☐半年/☐年");
			}
			if (j.get("ywbBankRemittance").toString().contains("3")) {
				dataModel.put("ywbBankRemittance", "☐月/☐季/☑半年/☐年");
			}
			if (j.get("ywbBankRemittance").toString().contains("4")) {
				dataModel.put("ywbBankRemittance", "☐月/☐季/☐半年/☑年");
			}
			if (j.get("ywbBankRemittance").equals("")) {
				dataModel.put("ywbBankRemittance", "☐月/☐季/☐半年/☐年");
			}
			dataModel.put("ywbShallCapitali", MoneyToChiness.moneyToChinese(j.get("ywbShallRmba").toString()));
			//5、租赁期内的因使用租赁物而产生的下列费用中 甲方承担：
			StringBuilder putEquipment = new StringBuilder();
			if (j.get("ywbTerminclude").toString().contains("1")) {
				putEquipment.append("(1) ");
			}
			if (j.get("ywbTerminclude").toString().contains("2")) {
				putEquipment.append("(2) ");
			}
			if (j.get("ywbTerminclude").toString().contains("3")) {
				putEquipment.append("(3) ");
			}
			if (j.get("ywbTerminclude").toString().contains("4")) {
				putEquipment.append("(4) ");
			}
			if (j.get("ywbTerminclude").toString().contains("5")) {
				putEquipment.append("(5) ");
			}
			if (j.get("ywbTerminclude").toString().contains("6")) {
				putEquipment.append("(6) ");
			}
			if (j.get("ywbTerminclude").equals("[]")) {
				putEquipment.append("————");
			}
			dataModel.put("ywbTerminclude", putEquipment.toString());
			//5、租赁期内的因使用租赁物而产生的下列费用中 乙方承担：
			StringBuilder ywbPeriod = new StringBuilder();
			if (j.get("ywbPeriod").toString().contains("1")) {
				ywbPeriod.append("(1) ");
			}
			if (j.get("ywbPeriod").toString().contains("2")) {
				ywbPeriod.append("(2) ");
			}
			if (j.get("ywbPeriod").toString().contains("3")) {
				ywbPeriod.append("(3) ");
			}
			if (j.get("ywbPeriod").toString().contains("4")) {
				ywbPeriod.append("(4) ");
			}
			if (j.get("ywbPeriod").toString().contains("5")) {
				ywbPeriod.append("(5) ");
			}
			if (j.get("ywbPeriod").toString().contains("6")) {
				ywbPeriod.append("(6) ");
			}
			if (j.get("ywbPeriod").equals("[]")) {
				ywbPeriod.append("————");
			}
			dataModel.put("ywbPeriod", ywbPeriod.toString());
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},




	//下脚品买卖合同模版
	MMHT_26("MMHT_26") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("infSaler", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("infBuyer", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			//时间选择
			dataModel.put("infTimeStart", DataFormatUtils.systemTimeFormatH(j.get("infTimeStart").toString()));
			dataModel.put("infTimeEnd", DataFormatUtils.systemTimeFormatH(j.get("infTimeEnd").toString()));
			//履约保证金相关
			if (contractFormInfoEntity.getContractBond().size() <= 0) {
				dataModel.put("infAppointAmount", "");
				dataModel.put("infCap", "");
//				dataModel.put("infBreachAmountFir", "");
//				dataModel.put("infBreachAmountSec", "");
			} else if (contractFormInfoEntity.getContractBond().size() >= 1) {
				if (contractFormInfoEntity.getContractBond().get(0).getIsNotBond().contains("1")) {
					dataModel.put("infAppointAmount", "_");
					dataModel.put("infCap", "_");
//					dataModel.put("infBreachAmountFir", "_");
//					dataModel.put("infBreachAmountSec", "_");
				} else if (contractFormInfoEntity.getContractBond().get(0).getIsNotBond().contains("0")) {
					String planPayAmount = String.valueOf(contractFormInfoEntity.getContractBond().get(0).getPlanPayAmount().divide(BigDecimal.valueOf(10000)));
					dataModel.put("infAppointAmount", planPayAmount);
					dataModel.put("infCap", MoneyToChiness.tenThousand(planPayAmount));
//					dataModel.put("infBreachAmountFir", planPayAmount);
//					dataModel.put("infBreachAmountSec", planPayAmount);
				}
			} else if (contractFormInfoEntity.getContractBond().size() >= 2) {
				AtomicReference<Double> valuesD = new AtomicReference<>(0D);
				contractFormInfoEntity.getContractBond().forEach(element -> {
					valuesD.updateAndGet(v -> v + Double.parseDouble(String.valueOf(element.getPlanPayAmount().divide(BigDecimal.valueOf(10000)))));
				});
				dataModel.put("infAppointAmount", valuesD);
				dataModel.put("infCap", MoneyToChiness.tenThousand(String.valueOf(valuesD)));
//				dataModel.put("infBreachAmountFir", valuesD);
//				dataModel.put("infBreachAmountSec", valuesD);
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dataModel.put("infContractStart", null == (contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getStartingTime())));
			dataModel.put("infContractEnd", null == (contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getEndTime())));
			dataModel.put("sigDate", DataFormatUtils.systemTimeFormat(j.get("sigDate").toString()));
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	//配送服务合同
	FWHT_38("FWHT_38") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			DistServiceContractEntity sclLogisticsService = JSONObject.toJavaObject(j, DistServiceContractEntity.class);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("clientA", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("trusteeB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("validityContractA", Func.isNull(contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.GLNZTimeFormat(String.valueOf(contractFormInfoEntity.getStartingTime())));
			dataModel.put("validityContractB", Func.isNull(contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.GLNZTimeFormat(String.valueOf(contractFormInfoEntity.getEndTime())));
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	//生产项目外包服务合同
	FWHT_22("FWHT_22") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
			List<ProductOutServiceContract1ResponseVO> ProductOutServiceContract1 = new ArrayList();
			List<ProductOutServiceContract2ResponseVO> ProductOutServiceContract2 = new ArrayList();
			List<ProductOutServiceContract3ResponseVO> ProductOutServiceContract3 = new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//生产项目外包服务合同关联表1
				if (ContractFormInfoTemplateContract.CONTRACT_PRODUCTOUTSERVICECONTRACT1.equals(templateField.getRelationCode())) {
					ProductOutServiceContract1 = JSON.parseArray(templateField.getTableData(), ProductOutServiceContract1ResponseVO.class);
					for (int i = 0; i < ProductOutServiceContract1.size(); i++) {
						ProductOutServiceContract1.get(i).setNum(i + 1);
//						JSONObject productOutServiceContract1 = JSON.parseObject(JSON.toJSONString(productOutServiceContract1ResponseVOList.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//						Map<String, Object> map = new HashMap();
//						map.put("Num", i + 1);
//						map.put("name", productOutServiceContract1.get("name"));
//						map.put("unit", productOutServiceContract1.get("unit"));
//						map.put("unitPrice", productOutServiceContract1.get("unitPrice"));
//						map.put("content", productOutServiceContract1.get("content"));
//						list.add(map);
					}
				}
				//生产项目外包服务合同关联表2
				if (ContractFormInfoTemplateContract.CONTRACT_PRODUCTOUTSERVICECONTRACT2.equals(templateField.getRelationCode())) {
					ProductOutServiceContract2 = JSON.parseArray(templateField.getTableData(), ProductOutServiceContract2ResponseVO.class);
					for (int i = 0; i < ProductOutServiceContract2.size(); i++) {
						ProductOutServiceContract2.get(i).setRewardNum(i + 1);
//						JSONObject productOutServiceContract2 = JSON.parseObject(JSON.toJSONString(productOutServiceContract2ResponseVOList.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//						Map<String, Object> map = new HashMap();
//						map.put("rewardNum", i + 1);
//						map.put("rewardContent", productOutServiceContract2.get("rewardContent"));
//						map.put("rewardAmount", productOutServiceContract2.get("rewardAmount"));
//						list1.add(map);
					}
				}
				//生产项目外包服务合同关联表3
				if (ContractFormInfoTemplateContract.CONTRACT_PRODUCTOUTSERVICECONTRACT3.equals(templateField.getRelationCode())) {
					ProductOutServiceContract3 = JSON.parseArray(templateField.getTableData(), ProductOutServiceContract3ResponseVO.class);
					for (int i = 0; i < ProductOutServiceContract3.size(); i++) {
						ProductOutServiceContract3.get(i).setPunishNum(i + 1);
//						JSONObject productOutServiceContract3 = JSON.parseObject(JSON.toJSONString(productOutServiceContract3ResponseVOList.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
//						Map<String, Object> map = new HashMap();
//						map.put("punishNum", i + 1);
//						map.put("punishContent", productOutServiceContract3.get("punishContent"));
//						map.put("punishAmount", productOutServiceContract3.get("punishAmount"));
//						list2.add(map);
					}
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder()
				.bind("ProductOutServiceContract1", policy).bind("ProductOutServiceContract2", policy).bind("ProductOutServiceContract3", policy).build();
			dataModel.put("ProductOutServiceContract1", ProductOutServiceContract1);
			dataModel.put("ProductOutServiceContract2", ProductOutServiceContract2);
			dataModel.put("ProductOutServiceContract3", ProductOutServiceContract3);
			//主表
			ProductOutServiceContractEntity productOutServiceContractEntity = JSONObject.toJavaObject(j, ProductOutServiceContractEntity.class);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("proTimeStart", null == (contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getStartingTime())));
			dataModel.put("proTimeEnd", null == (contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getEndTime())));
			modle.put("dataModel", setFile(filepaths, dataModel));
			modle.put("config", config);
			return modle;
		}
	},
	//设备投放使用协议
	SBTF_40("SBTF_40") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd");
			DeviceLaunchUseContractEntity ywbBusinessContractTemplateEntity = JSONObject.toJavaObject(j, DeviceLaunchUseContractEntity.class);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("devSaler", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("devBuyer", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("devBuyerNum", getCounterpart(contractFormInfoEntity).get("legalRepresentative").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("legalRepresentative").get(0));
			dataModel.put("devNumberInWord", ConversionNuToCh.cvt(Integer.parseInt(j.get("devNumber").toString()),true));
			//设备投放勾选
			StringBuilder putEquipment = new StringBuilder();
			if (j.get("putEquipment").toString().contains("1")) {
				putEquipment.append("☑冰箱,");
			} else {
				putEquipment.append("☐冰箱,");
			}
			if (j.get("putEquipment").toString().contains("2")) {
				putEquipment.append("☑水冷柜（卧柜）,");
			} else {
				putEquipment.append("☐水冷柜（卧柜）,");
			}
			if (j.get("putEquipment").toString().contains("3")) {
				putEquipment.append("☑冰桶,");
			} else {
				putEquipment.append("☐冰桶,");
			}
			if (j.get("putEquipment").toString().contains("4")) {
				putEquipment.append("☑风幕柜,");
			} else {
				putEquipment.append("☐风幕柜,");
			}
			if (j.get("putEquipment").toString().contains("5")) {
				putEquipment.append("☑热饮机,");
			} else {
				putEquipment.append("☐热饮机,");
			}
			if (j.get("putEquipment").toString().contains("6")) {
				putEquipment.append("☑冷热一体机");
			} else {
				putEquipment.append("☐冷热一体机");
			}
			dataModel.put("putEquipment", putEquipment.toString());
			//设备摆放位置勾选
			StringBuilder devPlace = new StringBuilder();
			if (j.get("devPlace").toString().contains("1")) {
				devPlace.append("☑第一层,");
			} else {
				devPlace.append("☐第一层,");
			}
			if (j.get("devPlace").toString().contains("2")) {
				devPlace.append("☑第二层,");
			} else {
				devPlace.append("☐第二层,");
			}
			if (j.get("devPlace").toString().contains("3")) {
				devPlace.append("☑第三层,");
			} else {
				devPlace.append("☐第三层,");
			}
			if (j.get("devPlace").toString().contains("4")) {
				devPlace.append("☑第四层,");
			} else {
				devPlace.append("☐第四层,");
			}
			if (j.get("devPlace").toString().contains("5")) {
				devPlace.append("☑第五层,");
			} else {
				devPlace.append("☐第五层,");
			}
			if (j.get("devPlace").toString().contains("6")) {
				devPlace.append("☑第六层,");
			} else {
				devPlace.append("☐第六层,");
			}
			dataModel.put("devPlace", devPlace.toString());
			//设备投放值
			StringBuilder devPlaceValue = new StringBuilder();
			for (int i = 1; i <= 6; i++) {
				if (j.get("devPlace").toString().contains(String.valueOf(i))) {
					devPlaceValue.append("第" + i + "层,");
				}
			}
			dataModel.put("devPlaceValue", Func.isBlank(devPlaceValue.toString()) ? "" : devPlaceValue.toString().substring(0, devPlaceValue.length() - 1));
			dataModel.put("devBorroStart", null == (contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getStartingTime())));
			dataModel.put("devBorroEnd", null == (contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getEndTime())));
			dataModel.put("devDepositInWord", MoneyToChiness.moneyToChinese(j.get("devDeposit").toString()));
			//押金收取状况勾选
			if ("新投放本次有收押".equals(j.get("newRelease").toString())) {
				dataModel.put("newRelease", "☑");
				dataModel.put("agreementRenewal", "☐");
			} else {
				dataModel.put("newRelease", "☐");
				dataModel.put("agreementRenewal", "☑");
			}
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	//班车服务合同
	FWHT_51("FWHT_51") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
			//主表
			BusServiceContractEntity productOutServiceContractEntity = JSONObject.toJavaObject(j, BusServiceContractEntity.class);
			dataModel.put("busSaler", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("busBuyer", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("busTimeA", DataFormatUtils.systemTimeFormatH(String.valueOf(j.get("busTimeA"))));
			dataModel.put("busTimeB", DataFormatUtils.systemTimeFormatH(String.valueOf(j.get("busTimeB"))));
			dataModel.put("busServiceTimeStart", null == (contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getStartingTime())));
			dataModel.put("busServiceTimeEnd", null == (contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getEndTime())));
			dataModel.put("annex", j.get("annex"));
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	//市调合同（定性+定量）
	SDHT_13("SDHT_13") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			List<MtbMarketResearchContract1ResponseVO> IMtbMarketResearchContract1 = new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//市调合同（定性+定量） 关联表1
				if (ContractFormInfoTemplateContract.CONTRAT_IMTBMARKETRESEARCHCONTRACT1.equals(templateField.getRelationCode())) {
					IMtbMarketResearchContract1 = JSON.parseArray(templateField.getTableData(), MtbMarketResearchContract1ResponseVO.class);
//					for (int i = 0; i < mtbMarketResearchContract1ResponseVOList.size(); i++) {
					//JSONObject mtbProductionContract= JSON.parseObject(JSON.toJSONString(mtbMarketResearchContract1ResponseVOList.get(i),filter, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));
//						Map<String, Object> map = new HashMap();
//						mtbMarketResearchContract1ResponseVOList.get(i).setMtbTime(null == mtbMarketResearchContract1ResponseVOList.get(i).getMtbTime() ? "" : DataFormatUtils.GLNZTimeFormat(String.valueOf(mtbMarketResearchContract1ResponseVOList.get(i).getMtbTime())));
//						mtbMarketResearchContract1ResponseVOList.get(i).setMtbMatter(null == mtbMarketResearchContract1ResponseVOList.get(i).getMtbMatter() ? "" : mtbMarketResearchContract1ResponseVOList.get(i).getMtbMatter());
//						list.add(map);
//					}
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder().bind("IMtbMarketResearchContract1", policy).build();
			dataModel.put("IMtbMarketResearchContract1", IMtbMarketResearchContract1);
			//主表
			MtbMarketResearchContractEntity mtbMarketResearchContractEntity = JSONObject.toJavaObject(j, MtbMarketResearchContractEntity.class);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("patya", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("patyb", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			//研究方法：单选
			if ("定性研究".equals(j.get("research").toString())) {
				dataModel.put("research", "☑");
				dataModel.put("methods", "☐");
				dataModel.put("element", j.get("element"));
				dataModel.put("element1", j.get("element1"));
				dataModel.put("element2", j.get("element2"));
				dataModel.put("element3", j.get("element3"));
				dataModel.put("element4", j.get("element6"));
				dataModel.put("element5", BigDecimal.valueOf(
					Double.parseDouble("".equals(j.get("element").toString()) ? "0.0" : j.get("element").toString())
						+ Double.parseDouble("".equals(j.get("element1").toString()) ? "0.0" : j.get("element1").toString())
						+ Double.parseDouble("".equals(j.get("element2").toString()) ? "0.0" : j.get("element2").toString())
						+ Double.parseDouble("".equals(j.get("element3").toString()) ? "0.0" : j.get("element3").toString())
						+ Double.parseDouble("".equals(j.get("element4").toString()) ? "0.0" : j.get("element4").toString())).multiply(
					BigDecimal.valueOf(null == contractFormInfoEntity.getContactTaxRate() ? 1 : contractFormInfoEntity.getContactTaxRate() + 1)
				));
				dataModel.put("element6", "");
				dataModel.put("element7", "");
				dataModel.put("element8", "");
				dataModel.put("element9", "");
				dataModel.put("element10", "");
				dataModel.put("element11", "");
				dataModel.put("element12", "");
			} else {
				dataModel.put("research", "☐");
				dataModel.put("methods", "☑");
				dataModel.put("element6", j.get("element"));
				dataModel.put("element7", j.get("element1"));
				dataModel.put("element8", j.get("element2"));
				dataModel.put("element9", j.get("element3"));
				dataModel.put("element10", j.get("element4"));
				dataModel.put("element11", j.get("element6"));
				dataModel.put("element12", BigDecimal.valueOf(
					Double.parseDouble("".equals(j.get("element6").toString()) ? "0.0" : j.get("element6").toString())
						+ Double.parseDouble("".equals(j.get("element7").toString()) ? "0.0" : j.get("element7").toString())
						+ Double.parseDouble("".equals(j.get("element8").toString()) ? "0.0" : j.get("element8").toString())
						+ Double.parseDouble("".equals(j.get("element9").toString()) ? "0.0" : j.get("element9").toString())
						+ Double.parseDouble("".equals(j.get("element11").toString()) ? "0.0" : j.get("element11").toString())
						+ Double.parseDouble("".equals(j.get("element10").toString()) ? "0.0" : j.get("element10").toString())).multiply(
					BigDecimal.valueOf(null == contractFormInfoEntity.getContactTaxRate() ? 1 : contractFormInfoEntity.getContactTaxRate() + 1)
				));
				dataModel.put("element", "");
				dataModel.put("element1", "");
				dataModel.put("element2", "");
				dataModel.put("element3", "");
				dataModel.put("element4", "");
				dataModel.put("element5", "");
			}
			//乙方需按照本合同约定的时间安排向甲方以书面的形式提交以下成果物：多选
			if (StringUtils.join(j.get("results"), "-").contains("调研简报")) {
				dataModel.put("results", "☑");
			} else {
				dataModel.put("results", "☐");
			}
			if (StringUtils.join(j.get("results"), "-").contains("调研报告(PPT中文报告)")) {
				dataModel.put("research1", "☑");
			} else {
				dataModel.put("research1", "☐");
			}
			if (StringUtils.join(j.get("results"), "-").contains("视频录像文件")) {
				dataModel.put("video", "☑");
			} else {
				dataModel.put("video", "☐");
			}
			if (StringUtils.join(j.get("results"), "-").contains("定性研究现场原始问答记录")) {
				dataModel.put("qualitative", "☑");
			} else {
				dataModel.put("qualitative", "☐");
			}
			if (StringUtils.join(j.get("results"), "-").contains("定量研究原始数据记录")) {
				dataModel.put("quantitative1", "☑");
			} else {
				dataModel.put("quantitative1", "☐");
			}
			if (StringUtils.join(j.get("results"), "-").contains("其他")) {
				dataModel.put("other", "☑");
				dataModel.put("other1", j.get("other1"));
			} else {
				dataModel.put("other", "☐");
				dataModel.put("other1", "——");
			}
			dataModel.put("totalCost", null == contractFormInfoEntity.getContractAmount() ? 0 : contractFormInfoEntity.getContractAmount());
			dataModel.put("rate", null == contractFormInfoEntity.getContactTaxRate() ? 0 : contractFormInfoEntity.getContactTaxRate());
			dataModel.put("amount", null == contractFormInfoEntity.getContractTaxAmount() ? 0 : contractFormInfoEntity.getContractTaxAmount());
			//双方确认：市调所需的除甲方产品以外其他物品购买费用由
			if ("乙方承担".equals(j.get("undertakesb").toString())) {
				dataModel.put("undertakesb", "☑");
				dataModel.put("undertakesa", "☐");
			} else {
				dataModel.put("undertakesb", "☐");
				dataModel.put("undertakesa", "☑");
			}
			modle.put("dataModel", setFile(filepaths, dataModel));
			modle.put("config", config);
			return modle;
		}
	},
	//劳务派遣合同
	LWHT_52("LWHT_52") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
			LaborDispatchEntity laborDispatchEntity = JSONObject.toJavaObject(j, LaborDispatchEntity.class);
			//甲方（用工单位）
			dataModel.put("ywbFirstParty", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			//乙方（派遣单位）
			dataModel.put("ywbPartyB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("specificDateStart", null == (contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getStartingTime())));
			dataModel.put("specificDateEnd", null == (contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getEndTime())));
			//拼接附件
			dataModel.put("annex", j.get("annex"));
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	/**
	 * TAG-21-05-00
	 * 物流服务合同（冷冻）
	 */
	WLFW_59("WLFW_59") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("sclPatyA", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("sclPatyB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("sclStartTime", null == (contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getStartingTime())));
			dataModel.put("sclEndOfTime", null == (contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getEndTime())));
			dataModel.put("sclSomeDay", DataFormatUtils.systemTimeFormat(String.valueOf(j.get("sclSomeDay"))));
			dataModel.put("sclSigningDate", DataFormatUtils.systemTimeFormat(String.valueOf(j.get("sclSigningDate"))));
			dataModel.put("sclMarginRmb2", MoneyToChiness.tenThousand(String.valueOf(j.get("sclMarginRmb"))));
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	/**
	 * TAG-21-05-01
	 */
	//统一e商城平台入驻服务协议（统一经销商）目前使用模板
	FWXY_29("FWXY_29") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	/**
	 * TAG-21-05-05
	 * 2021年统一e商城平台入驻服务协议（统一经销商）
	 */
	FWXY_50("FWXY_50") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	/**
	 * TAG-21-05-03
	 * //买卖合同（行销品）
	 */
	MMHT_07("MMHT_07") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			List<CglCategorySalesContracts1ResponseVO> CglCategorySalesContracts1 = new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//买卖合同（行销品）(关联表1)
				if (ContractFormInfoTemplateContract.CONTRACT_CGLCATEGORYSALESCONTRACTS1.equals(templateField.getRelationCode())) {
					CglCategorySalesContracts1 = JSON.parseArray(templateField.getTableData(), CglCategorySalesContracts1ResponseVO.class);
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder().bind("CglCategorySalesContracts1", policy).build();
			dataModel.put("CglCategorySalesContracts1", CglCategorySalesContracts1);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			if (j.get("cglPaymentDays").toString().contains("1")) {
				dataModel.put("cglSpecialPayment", "/");
			}
			if (j.get("cglPaymentDays").toString().contains("2")) {
				dataModel.put("cglSpecialPayment", j.get("cglSpecialPayment"));
			}
			modle.put("dataModel", setFile(filepaths, dataModel));
			modle.put("config", config);
			return modle;
		}
	},
	/**
	 * TAG-21-05-04
	 * 买卖合同（国内设备购买）
	 * MMHT_06
	 */
	MMHT_06("MMHT_06") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			List<CglSalesContract1ResponseVO> CglSalesContract1 = new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//买卖合同（国内设备购买）(关联表1)
				if (ContractFormInfoTemplateContract.CONTRACT_CGLSALESCONTRACT1ENTITY.equals(templateField.getRelationCode())) {
					CglSalesContract1 = JSON.parseArray(templateField.getTableData(), CglSalesContract1ResponseVO.class);
					for (int i = 0; i < CglSalesContract1.size(); i++) {
						CglSalesContract1.get(i).setCqlSNumber(i + 1);
					}
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder().bind("CglSalesContract1", policy).build();
			dataModel.put("CglSalesContract1", CglSalesContract1);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			AtomicReference<Double> valuesD = new AtomicReference<>(0D);
			CglSalesContract1.forEach(element -> {
				valuesD.updateAndGet(v -> v + Double.parseDouble(element.getCqlFreePrice().toString()));
			});
			dataModel.put("contactYuanSma", valuesD);
			dataModel.put("contactYuanBig", MoneyToChiness.moneyToChinese(valuesD.toString()));
			if (j.get("contactNext").toString().contains("A")) {
				dataModel.put("contactDayt", j.get("contactDayt"));
				dataModel.put("contactHour", j.get("contactHour"));
				dataModel.put("contactHourt", "/");
			}
			if (j.get("contactNext").toString().contains("B")) {
				dataModel.put("contactDayt", "/");
				dataModel.put("contactHour", "/");
				dataModel.put("contactHourt", j.get("contactHourt"));
			}
			if (j.get("contactSpecilf").toString().contains("A")) {
				dataModel.put("contactDayf", j.get("contactDayf"));
				dataModel.put("contactDays", j.get("contactDays"));
				dataModel.put("contactDayx", j.get("contactDayx"));
				dataModel.put("contactPercent", j.get("contactPercent"));
			}
			if (j.get("contactSpecilf").toString().contains("B")) {
				dataModel.put("contactDayf", "/");
				dataModel.put("contactDays", "/");
				dataModel.put("contactDayx", "/");
				dataModel.put("contactPercent", "/");
			}
			if (j.get("contactTypeg").toString().contains("A")) {
				dataModel.put("contactDayn", MapUtils.getString(j, "contactDayn", "/"));
				dataModel.put("contactPencenty", "/");
				dataModel.put("contactAcceptw", "/");
				dataModel.put("contactPencentr", "/");
				dataModel.put("contactAccept", "/");
				dataModel.put("contactPencente", "/");
				dataModel.put("contactDayp", "/");
				dataModel.put("contactPencentw", "/");
				dataModel.put("contactPencentq", "/");
				dataModel.put("contactLawtime", "/");
				dataModel.put("contactQuaty", "/");
				dataModel.put("contactElsytype", "/");
			}
			if (j.get("contactTypeg").toString().contains("B")) {
				dataModel.put("contactDayn", "/");
				dataModel.put("contactPencenty", j.get("contactPencenty"));
				dataModel.put("contactAcceptw", j.get("contactAcceptw"));
				dataModel.put("contactPencentr", j.get("contactPencentr"));
				dataModel.put("contactAccept", j.get("contactAccept"));
				dataModel.put("contactPencente", j.get("contactPencente"));
				dataModel.put("contactDayp", j.get("contactDayp"));
				dataModel.put("contactPencentw", j.get("contactPencentw"));
				dataModel.put("contactPencentq", j.get("contactPencentq"));
				dataModel.put("contactLawtime", j.get("contactLawtime"));
				dataModel.put("contactQuaty", j.get("contactQuaty"));
				dataModel.put("contactElsytype", "/");
			}
			if (j.get("contactTypeg").toString().contains("C")) {
				dataModel.put("contactDayn", "/");
				dataModel.put("contactPencenty", "/");
				dataModel.put("contactAcceptw", "/");
				dataModel.put("contactPencentr", "/");
				dataModel.put("contactAccept", "/");
				dataModel.put("contactPencente", "/");
				dataModel.put("contactDayp", "/");
				dataModel.put("contactPencentw", "/");
				dataModel.put("contactPencentq", "/");
				dataModel.put("contactLawtime", "/");
				dataModel.put("contactQuaty", "/");
				dataModel.put("contactElsytype", j.get("contactElsytype"));
			}
			modle.put("dataModel", setFile(filepaths, dataModel));
			modle.put("config", config);
			return modle;
		}
	},
	/**
	 * TAG-2021-05-02
	 * GGZZ_04
	 * 广告制作安装合同模板
	 */
	GGZZ_04("GGZZ_04") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
			CglAdvertisementProductionEntity cglAdvertisementProductionEntity = JSONObject.toJavaObject(j, CglAdvertisementProductionEntity.class);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("mtlPartyA", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("mtlPartyB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("mtlStartDate", null == (contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getStartingTime())));
			dataModel.put("mtlDateClosed", null == (contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getEndTime())));
			if ("1".equals(j.getOrDefault("mtlClearingFrom", ""))) {
				dataModel.put("mtlReceiveInvoice", j.getOrDefault("mtlReceiveInvoice", ""));
				dataModel.put("mtlReceiveDay", j.getOrDefault("mtlReceiveDay", ""));
				dataModel.put("mtlOtherMethods", " ");
			} else {
				dataModel.put("mtlReceiveInvoice", "  ");
				dataModel.put("mtlReceiveDay", "  ");
				dataModel.put("mtlOtherMethods", j.getOrDefault("mtlOtherMethods", ""));
			}
			modle.put("dataModel", setFile(filepaths, dataModel));
			return modle;
		}
	},
	/**
	 * XFWB_60
	 * TAG-21-05-24
	 * 消防设施维修保养服务合同
	 */
	XFWB_60("XFWB_60") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
			List<ContractWbht1ResponseVO> ContractWbht1 = new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//买卖合同（国内设备购买）(关联表1)
				if (ContractFormInfoTemplateContract.CONTRACT_CONTRACTWBHT1.equals(templateField.getRelationCode())) {
					ContractWbht1 = JSON.parseArray(templateField.getTableData(), ContractWbht1ResponseVO.class);
					for (int i = 0; i < ContractWbht1.size(); i++) {
						ContractWbht1.get(i).setContractNum(String.valueOf(i + 1));
					}
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder().bind("ContractWbht1", policy).build();
			dataModel.put("ContractWbht1", ContractWbht1);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				log.info("==key" + entry.getKey().toString());
				log.info("==value" + entry.getKey().toString());
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			if (j.get("contactTime").toString().contains("1")) {
				dataModel.put("contactTime", "每月");
			}
			if (j.get("contactTime").toString().contains("2")) {
				dataModel.put("contactTime", "每季度");
			}
			if (j.get("contactTime").toString().contains("3")) {
				dataModel.put("contactTime", "每半年一次");
			}
			dataModel.put("contactPartyA", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("contactPartyB", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("contactTimeStart", null == (contractFormInfoEntity.getStartingTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getStartingTime())));
			dataModel.put("contactTimeEnd", null == (contractFormInfoEntity.getEndTime()) ? "" : DataFormatUtils.systemTimeFormat(simpleDateFormat.format(contractFormInfoEntity.getEndTime())));
			dataModel.put("annex", j.get("annex"));
			modle.put("dataModel", setFile(filepaths, dataModel));
			modle.put("config", config);
			return modle;
		}
	},
	/**
	 * MMHT_10
	 * TAG-21-07.12
	 * 原物料-买卖合同
	 */
	MMHT_10("MMHT_10") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			List<CglRawMaterials1ResponseVO> CglRawMaterials1 = new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//买卖合同（国内设备购买）(关联表1)
				if (ContractFormInfoTemplateContract.CONTRACT_CGLRAWMATERIALS1.equals(templateField.getRelationCode())) {
					CglRawMaterials1 = JSON.parseArray(templateField.getTableData(), CglRawMaterials1ResponseVO.class);
					for (int i = 0; i < CglRawMaterials1.size(); i++) {
						CglRawMaterials1.get(i).setCglNumber(i + 1);
						if (Func.isNotEmpty(CglRawMaterials1.get(i).getCglStartingTime())){
							CglRawMaterials1.get(i).setCglStartingTime(DataFormatUtils.systemTimeFormat(CglRawMaterials1.get(i).getCglStartingTime()));
						}
						if (Func.isNotEmpty(CglRawMaterials1.get(i).getCglEndOfTime())){
							CglRawMaterials1.get(i).setCglEndOfTime(DataFormatUtils.systemTimeFormat(CglRawMaterials1.get(i).getCglEndOfTime()));
						}
					}
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder().bind("CglRawMaterials1", policy).build();
			dataModel.put("CglRawMaterials1", CglRawMaterials1);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("cglBuyer", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("cglSeller", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("cglAccount", Func.isNull(contractFormInfoEntity.getBankAccountCode()) ? "" : contractFormInfoEntity.getBankAccountCode());
			dataModel.put("cglBankNumber", Func.isNull(contractFormInfoEntity.getBankDepositName()) ? "" : contractFormInfoEntity.getBankDepositName());
			dataModel.put("cglNameOfTheAccount", Func.isNull(contractFormInfoEntity.getBankAccountName()) ? "" : contractFormInfoEntity.getBankAccountName());
			dataModel.put("annex", j.get("annex"));
			modle.put("dataModel", setFile(filepaths, dataModel));
			modle.put("config", config);
			return modle;
		}
	},
	/**
	 * MMHT_08
	 * 买卖合同-五金低耗类
	 */
	MMHT_08("MMHT_08") {
		@Override
		public Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
			Map modle = new HashMap();
			Map dataModel = new HashMap();
			List<CglLowCostHardware1ResponseVO> CglLowCostHardware1 = new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//买卖合同-五金低耗类
				if (ContractFormInfoTemplateContract.CONTRACT_CGLLOWCOSTHARDWARE1.equals(templateField.getRelationCode())) {
					CglLowCostHardware1 = JSON.parseArray(templateField.getTableData(), CglLowCostHardware1ResponseVO.class);
					for (int i = 0; i < CglLowCostHardware1.size(); i++) {
						CglLowCostHardware1.get(i).setCglNumber(i + 1);
						if (Func.isNotEmpty(CglLowCostHardware1.get(i).getCglStartingTime())){
							CglLowCostHardware1.get(i).setCglStartingTime(DataFormatUtils.systemTimeFormat(CglLowCostHardware1.get(i).getCglStartingTime()));
						}
						if (Func.isNotEmpty(CglLowCostHardware1.get(i).getCglEndOfTime())){
							CglLowCostHardware1.get(i).setCglEndOfTime(DataFormatUtils.systemTimeFormat(CglLowCostHardware1.get(i).getCglEndOfTime()));
						}
					}
				}
			}
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.builder().bind("CglLowCostHardware1", policy).build();
			dataModel.put("CglLowCostHardware1", CglLowCostHardware1);
			//迭代器遍历json对象
			Iterator iter = j.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (entry.getKey() == null) {
					continue;
				}
				dataModel.put(entry.getKey().toString(), entry.getValue().toString());
			}
			dataModel.put("cglTheBuyer", Func.isNull(contractFormInfoEntity.getSealName()) ? "" : contractFormInfoEntity.getSealName());
			dataModel.put("cglTheSeller", getCounterpart(contractFormInfoEntity).get("name").size() <= 0 ? "未选择相对方" : getCounterpart(contractFormInfoEntity).get("name").get(0));
			dataModel.put("cglAccount", Func.isNull(contractFormInfoEntity.getBankAccountCode()) ? "" : contractFormInfoEntity.getBankAccountCode());
			dataModel.put("cglBankNumber", Func.isNull(contractFormInfoEntity.getBankDepositName()) ? "" : contractFormInfoEntity.getBankDepositName());
			dataModel.put("cglAccounts", Func.isNull(contractFormInfoEntity.getBankAccountName()) ? "" : contractFormInfoEntity.getBankAccountName());
			dataModel.put("annex", j.get("annex"));
			modle.put("dataModel", setFile(filepaths, dataModel));
			modle.put("config", config);
			return modle;
		}
	};

	public abstract Map setScheduler(List<String> filepaths, ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j);

	@Getter
	public String type;

	/**
	 * 通过轮询来获得相应的方法
	 */
	public static TemplateExporterEnum fromValue(String type) {
		return Stream.of(TemplateExporterEnum.values()).filter(fileType ->
			StringUtils.equals(fileType.getType(), type)
		).findFirst().get();
	}

	/**
	 * 处理相对方名称
	 **/
	public static Map<String, List<String>> getCounterpart(ContractFormInfoEntity formInfo) {
		Map<String, List<String>> dataModel = new HashMap();
		List<String> list = new ArrayList<>();
		List<String> listLegalRepresentative = new ArrayList<>();
		formInfo.getCounterpart().forEach(counterpart -> {
			list.add(counterpart.getName());
		});
		formInfo.getCounterpart().forEach(counterpart -> {
			listLegalRepresentative.add(counterpart.getLegalRepresentative());
		});
		dataModel.put("name", list);
		dataModel.put("legalRepresentative", listLegalRepresentative);
		return dataModel;
	}

	/**
	 * 插入文件
	 **/
	public static Map setFile(List<String> filepaths, Map dataModel) {
		for (int i = 0; i < filepaths.size(); i++) {
			dataModel.put("docx_word" + i, new DocxRenderData(new File(filepaths.get(i))));
		}
		return dataModel;
	}


	/**
	 * 获取两个日期相差的月数
	 *
	 * @param d1 较大的日期
	 * @param d2 较小的日期
	 * @return 如果d1>d2返回 月数差 否则返回0
	 */
	@SneakyThrows
	public static int getMonthDiff(String d2, String d1) {
		if (d1.contains("__") || d2.contains("__")) {
			return 0;
		} else {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			//使用SimpleDateFormat的parse()方法生成Date
			Date date1 = sf.parse(d1);
			Date date2 = sf.parse(d2);
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(date1);
			c2.setTime(date2);
			if (c1.getTimeInMillis() < c2.getTimeInMillis()) {
				return 0;
			}
			int year1 = c1.get(Calendar.YEAR);
			int year2 = c2.get(Calendar.YEAR);
			int month1 = c1.get(Calendar.MONTH);
			int month2 = c2.get(Calendar.MONTH);
			int day1 = c1.get(Calendar.DAY_OF_MONTH);
			int day2 = c2.get(Calendar.DAY_OF_MONTH);
			// 获取年的差值 假设 d1 = 2015-8-16 d2 = 2011-9-30
			int yearInterval = year1 - year2;
			// 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
			if (month1 < month2 || month1 == month2 && day1 < day2) {
				yearInterval--;
			}
			// 获取月数差值
			int monthInterval = (month1 + 12) - month2;
			if (day1 < day2) {
				monthInterval--;
			}
			monthInterval %= 12;
			return yearInterval * 12 + monthInterval;
		}
	}

	/**
	 * 处理json串中为null的过滤方法
	 */
	private static ValueFilter filter = new ValueFilter() {
		@Override
		public Object process(Object o, String s, Object o1) {
			if (o1 == null) {
				return "";
			}
			return o1;
		}
	};
}
