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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springblade.contract.constant.ContractFormInfoTemplateContract;
import org.springblade.contract.entity.*;
import org.springblade.contract.util.DataFormatUtils;
import org.springblade.contract.vo.*;
import org.springblade.system.entity.TemplateFieldJsonEntity;
import org.springblade.system.vo.TemplateRequestVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 用户类型枚举
 *
 * @author Chill
 */
@Getter
@AllArgsConstructor
public enum TemplateExporterEnum {

	//新陈列协议书
	CLXY_42("CLXY_42"){
		@Override
		public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
			Map dataModel = new HashMap();
			List<Map<String, Object>> list=new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				if (ContractFormInfoTemplateContract.CONTRACT_YWLANEWDISPLAY1.equals(templateField.getRelationCode())) {
					JSONArray jsonArr = JSON.parseArray(templateField.getTableData());
					for(int i=0;i<jsonArr.size();i++) {
						Map<String, Object> map=new HashMap();
						map.put("ywlNumber",i+1);
						map.put("ywlDisplayProducts",jsonArr.getJSONObject(i).get("ywlDisplayProducts"));
						map.put("ywlDisplayMode",jsonArr.getJSONObject(i).get("ywlDisplayMode"));
						map.put("ywlMerchandisingStandards",jsonArr.getJSONObject(i).get("ywlMerchandisingStandards"));
						list.add(map);
					}
				}
			}
			dataModel.put("ywlCooperationContent",j.get("ywlCooperationContent"));
			dataModel.put("ywlTheStartTime",DataFormatUtils.systemTimeFormat(j.get("ywlTheStartTime").toString()));
			dataModel.put("ywlEndOfTime",DataFormatUtils.systemTimeFormat(j.get("ywlEndOfTime").toString()));
			dataModel.put("ywlDisplayFee",j.get("ywlDisplayFee"));
			dataModel.put("ywlDisplayDfee",j.get("ywlDisplayDfee"));
			if("1".equals(j.get("ywlDisplayType"))){
				dataModel.put("ywlDisplayType","货物");
			}else{
				dataModel.put("ywlDisplayType","款项");
			}
			dataModel.put("ywlOther",j.get("ywlOther"));
			dataModel.put("list",list);
			return dataModel;
		}
	},
	//店招合同
	DZHT_35("DZHT_35"){
		@Override
		public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
			Map dataModel = new HashMap();
				dataModel.put("ywlPatyA",j.get("ywlPatyA"));
				dataModel.put("ywlPatyB",j.get("ywlPatyB"));
				dataModel.put("ywlLocation",j.get("ywlLocation"));
				dataModel.put("ywlSuspensionStart",DataFormatUtils.systemTimeFormat(String.valueOf(j.get("ywlSuspensionStart"))));
				dataModel.put("ywlSuspensionEnd",DataFormatUtils.systemTimeFormat(String.valueOf(j.get("ywlSuspensionEnd").toString())));
				dataModel.put("ywlAgreementPeriodStart",DataFormatUtils.systemTimeFormat(String.valueOf(j.get("ywlAgreementPeriodStart").toString())));
				dataModel.put("ywlAgreementPeriodEnd",DataFormatUtils.systemTimeFormat(String.valueOf(j.get("ywlAgreementPeriodEnd").toString())));
				dataModel.put("ywlProductionCosts",j.get("ywlProductionCosts"));
				dataModel.put("ywlAmountOf",j.get("ywlAmountOf"));
				dataModel.put("ywlOtherConventions",j.get("ywlOtherConventions"));
			return dataModel;
		}
	},
	//媒体类：视频广告改编合同
	GBHT_14("GBHT_14"){
		@Override
		public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
			Map dataModel = new HashMap();
			List<Map<String, Object>> list=new ArrayList();
			List<Map<String, Object>> list1=new ArrayList();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
			for (TemplateFieldJsonEntity templateField : templateFieldList) {
				//*视频广告改编合同关联表
				if (ContractFormInfoTemplateContract.CONTRACT_MTADAPTATIONCONTRACT1.equals(templateField.getRelationCode())) {
					JSONArray jsonArr = JSON.parseArray(templateField.getTableData());
					for(int i=0;i<jsonArr.size();i++) {
						Map<String, Object> map=new HashMap();
						map.put("formDelivery",jsonArr.getJSONObject(i).get("formDelivery"));
						map.put("number",jsonArr.getJSONObject(i).get("number"));
						map.put("contentTheme",jsonArr.getJSONObject(i).get("contentTheme"));
						map.put("requirements",jsonArr.getJSONObject(i).get("requirements"));
						map.put("expenses",jsonArr.getJSONObject(i).get("expenses"));
						list.add(map);
					}
				}
				if (ContractFormInfoTemplateContract.CONTRACT_MTADAPTATIONCONTRACT2.equals(templateField.getRelationCode())) {
					JSONArray jsonArr = JSON.parseArray(templateField.getTableData());
					for(int i=0;i<jsonArr.size();i++) {
						Map<String, Object> map=new HashMap();
						map.put("intellectualProperty",jsonArr.getJSONObject(i).get("intellectualProperty"));
						map.put("sample",jsonArr.getJSONObject(i).get("sample"));
						map.put("serviceLife",jsonArr.getJSONObject(i).get("serviceLife"));
						map.put("useArea",jsonArr.getJSONObject(i).get("useArea"));
						list1.add(map);
					}
				}
			}
			dataModel.put("mtlPatyA",j.get("mtlPatyA"));
			dataModel.put("mtlPatyAEmail",j.get("mtlPatyAEmail"));
			dataModel.put("mtlContactEmail",j.get("mtlContactEmail"));
			dataModel.put("mtlPatyB",j.get("mtlPatyB"));
			dataModel.put("mtlPatyBEmail",j.get("mtlPatyBEmail"));
			dataModel.put("mtlPatyBHome",j.get("mtlPatyBHome"));
			dataModel.put("mtlAdaptationIssues",j.get("mtlAdaptationIssues"));
			dataModel.put("mtlNameOfAdvertising",j.get("mtlNameOfAdvertising"));
			dataModel.put("mtlBasedOnTheContent",j.get("mtlBasedOnTheContent"));
			dataModel.put("mtlProductionStartTime",DataFormatUtils.systemTimeFormat(j.get("mtlProductionStartTime").toString()));
			dataModel.put("mtlProductionCompletionTime",DataFormatUtils.systemTimeFormat(j.get("mtlProductionCompletionTime").toString()));
			dataModel.put("mtlHaveHasNot",j.get("mtlHaveHasNot"));
			dataModel.put("mtlUnpaidTaxRmb",j.get("mtlUnpaidTaxRmb"));
			dataModel.put("mtlRate",j.get("mtlRate"));
			dataModel.put("mtlTaxAmountIsRmb",j.get("mtlTaxAmountIsRmb"));
			dataModel.put("mtlCompanyName",j.get("mtlCompanyName"));
			dataModel.put("mtlWhereItIs",j.get("mtlWhereItIs"));
			dataModel.put("mtlAccount",j.get("mtlAccount"));
			dataModel.put("list",list);
			dataModel.put("list1",list1);
			return dataModel;
		}
	},

    //加工承揽合同（代工合同）
    CLHT_19("CLHT_19"){
        @Override
        public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
            Map dataModel = new HashMap();
            List<Map<String, Object>> list=new ArrayList();
            List<Map<String, Object>> list1=new ArrayList();
            List<Map<String, Object>> list2=new ArrayList();
            List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
            for (TemplateFieldJsonEntity templateField : templateFieldList) {
                //加工承揽合同（代工合同）关联表1
                if (ContractFormInfoTemplateContract.CONTRACT_SCLCONSTRUCTIONPROJECT1.equals(templateField.getRelationCode())) {
                    List<SclConstructionProject1ResponseVO> sclConstructionProject1List = JSON.parseArray(templateField.getTableData(), SclConstructionProject1ResponseVO.class);
                    for (int i=0;i<sclConstructionProject1List.size();i++) {
                        JSONObject sclConstructionProject1= JSON.parseObject(JSON.toJSONString(sclConstructionProject1List.get(i),filter, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));
                        Map<String, Object> map=new HashMap();
                        map.put("varieties",sclConstructionProject1.get("varieties"));
                        map.put("specifications",sclConstructionProject1.get("specifications"));
                        map.put("processingCost",sclConstructionProject1.get("processingCost"));
                        list.add(map);
                    }
                }
                //加工承揽合同（代工合同）关联表2
                if (ContractFormInfoTemplateContract.CONTRACT_SCLCONSTRUCTIONPROJECT2.equals(templateField.getRelationCode())) {
                    List<SclConstructionProject2ResponseVO> sclConstructionProject2List = JSON.parseArray(templateField.getTableData(), SclConstructionProject2ResponseVO.class);
                    for (int i=0;i<sclConstructionProject2List.size();i++) {
                        JSONObject sclConstructionProject2= JSON.parseObject(JSON.toJSONString(sclConstructionProject2List.get(i),filter, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));
                        Map<String, Object> map=new HashMap();
                        map.put("serialNumber",sclConstructionProject2.get("serialNumber"));
                        map.put("rawMaterial",sclConstructionProject2.get("rawMaterial"));
                        map.put("nameMaterial",sclConstructionProject2.get("nameMaterial"));
                        map.put("lossRate",sclConstructionProject2.get("lossRate"));
                        list1.add(map);
                    }
                }
                //加工承揽合同（代工合同）关联表3
                if (ContractFormInfoTemplateContract.CONTRACT_SCLCONSTRUCTIONPROJECT3.equals(templateField.getRelationCode())) {
                    List<SclConstructionProject3ResponseVO> sclConstructionProject3List = JSON.parseArray(templateField.getTableData(), SclConstructionProject3ResponseVO.class);
                    for (int i=0;i<sclConstructionProject3List.size();i++) {
                        JSONObject sclConstructionProject3= JSON.parseObject(JSON.toJSONString(sclConstructionProject3List.get(i),filter, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));
                        Map<String, Object> map=new HashMap();
                        map.put("productName",sclConstructionProject3.get("productName"));
                        map.put("productSpecifications",sclConstructionProject3.get("productSpecifications"));
                        map.put("supplier",sclConstructionProject3.get("supplier"));
                        map.put("withoutTax",sclConstructionProject3.get("withoutTax"));
                        map.put("remarks",sclConstructionProject3.get("remarks"));
                        list2.add(map);
                    }
                }
            }
            //主表
            SclConstructionProjectEntity sclConstructionProject = JSONObject.toJavaObject(j, SclConstructionProjectEntity.class);
            dataModel.put("sclOrderingParty",j.get("sclOrderingParty"));
            dataModel.put("sclPartyAddress",j.get("sclPartyAddress"));
            dataModel.put("sclContractor",j.get("sclContractor"));
            dataModel.put("sclAddressOfContractor",j.get("sclAddressOfContractor"));
            dataModel.put("sclStartTime",j.get("sclStartTime"));
            dataModel.put("sclCompletionTime",j.get("sclCompletionTime"));
            dataModel.put("sclExpenseBearingParty",j.get("sclExpenseBearingParty"));
            dataModel.put("sclExpenseBearingParty1",j.get("sclExpenseBearingParty1"));
            dataModel.put("sclProcessingWay",j.get("sclProcessingWay"));
            dataModel.put("sclRiskOfLoss",j.get("sclRiskOfLoss"));
            dataModel.put("sclDateOfRequest",j.get("sclDateOfRequest"));
            dataModel.put("sclNameOfBank",j.get("sclNameOfBank"));
            dataModel.put("sclAccountName",j.get("sclAccountName"));
            dataModel.put("sclBankNumber",j.get("sclBankNumber"));
            dataModel.put("sclDeposit",j.get("sclDeposit"));
            dataModel.put("sclDeposits",j.get("sclDeposits"));
            dataModel.put("sclPenalties",j.get("sclPenalties"));
            dataModel.put("sclBreachOfContract",j.get("sclBreachOfContract"));
            dataModel.put("sclDatatime",j.get("sclDatatime"));
            dataModel.put("sclProcessingFee1",j.get("sclProcessingFee1"));
            dataModel.put("sclBreachOfContract78",j.get("sclBreachOfContract78"));
            dataModel.put("sclBreachOfContract711",j.get("sclBreachOfContract711"));
            dataModel.put("sclBreachOfContract712",j.get("sclBreachOfContract712"));
 ;          dataModel.put("sclBreachOfContract7151",j.get("sclBreachOfContract7151"));
            dataModel.put("sclBreachOfContract7152",j.get("sclBreachOfContract7152"));
            dataModel.put("sclPaid",j.get("sclPaid"));
            dataModel.put("sclOrderingParty",j.get("sclOrderingParty"));
            dataModel.put("sclContractor",j.get("sclContractor"));
            dataModel.put("sclPartyAddress",j.get("sclPartyAddress"));
            dataModel.put("sclAddressOfContractor",j.get("sclAddressOfContractor"));
            dataModel.put("list",list);
            dataModel.put("list1",list1);
            dataModel.put("list2",list2);
            return dataModel;
        }
    },
    //音频制作合同
    ZZHT_18("ZZHT_18"){
        @Override
        public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
            Map dataModel = new HashMap();
            List<Map<String, Object>> list=new ArrayList();
            List<Map<String, Object>> list1=new ArrayList();
            List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
            for (TemplateFieldJsonEntity templateField : templateFieldList) {
                //音频制作合同 关联表1
                if (ContractFormInfoTemplateContract.CONTRACT_MTLAUDIOPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
                    List<MtlAudioProductionContract1ResponseVO> mtlAudioProductionContract1List = JSON.parseArray(templateField.getTableData(), MtlAudioProductionContract1ResponseVO.class);
                    for (int i=0;i<mtlAudioProductionContract1List.size();i++) {
                        JSONObject mtlAudioProductionContract1= JSON.parseObject(JSON.toJSONString(mtlAudioProductionContract1List.get(i),filter, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));
                        Map<String, Object> map=new HashMap();
                        map.put("formDelivery",mtlAudioProductionContract1.get("formDelivery"));
                        map.put("number",mtlAudioProductionContract1.get("number"));
                        map.put("contentTheme",mtlAudioProductionContract1.get("contentTheme"));
                        map.put("requirements",mtlAudioProductionContract1.get("requirements"));
                        map.put("expenses",mtlAudioProductionContract1.get("expenses"));
                        list.add(map);
                    }
                }
                //音频制作合同 关联表2
                if (ContractFormInfoTemplateContract.CONTRACT_MTLAUDIOPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
                    List<MtlAudioProductionContract2ResponseVO> mtlAudioProductionContract2List = JSON.parseArray(templateField.getTableData(), MtlAudioProductionContract2ResponseVO.class);
                    for (int i=0;i<mtlAudioProductionContract2List.size();i++) {
                        JSONObject mtlAudioProductionContract2= JSON.parseObject(JSON.toJSONString(mtlAudioProductionContract2List.get(i),filter, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));
                        Map<String, Object> map=new HashMap();
                        map.put("intellectualProperty",mtlAudioProductionContract2.get("intellectualProperty"));
                        map.put("serviceLife",mtlAudioProductionContract2.get("serviceLife"));
                        map.put("useArea",mtlAudioProductionContract2.get("useArea"));
                        list1.add(map);
                    }
                }
            }
            //主表
            MtlAudioProductionContractEntity mtlAudioProductionContract = JSONObject.toJavaObject(j, MtlAudioProductionContractEntity.class);
            dataModel.put("mtlPatyA",j.get("mtlPatyA"));
            dataModel.put("mtlPatyAEmail",j.get("mtlPatyAEmail"));
            dataModel.put("mtlContactEmail",j.get("mtlContactEmail"));
            dataModel.put("mtlPatyB",j.get("mtlPatyB"));
            dataModel.put("mtlPatyBEmail",j.get("mtlPatyBEmail"));
            dataModel.put("mtlPatyBHome",j.get("mtlPatyBHome"));
            dataModel.put("mtlAdaptationIssues",j.get("mtlAdaptationIssues"));
            dataModel.put("mtlNameOfTheAudio",j.get("mtlNameOfTheAudio"));
            dataModel.put("mtlAudioContent",j.get("mtlAudioContent"));
            dataModel.put("mtlProductionStartTime",j.get("mtlProductionStartTime"));
            dataModel.put("mtlProductionCompletionTime",j.get("mtlProductionCompletionTime"));
            dataModel.put("mtlAcceptance",j.get("mtlAcceptance"));
            dataModel.put("mtlUnpaidTaxRmb",j.get("mtlUnpaidTaxRmb"));
            dataModel.put("mtlRate",j.get("mtlRate"));
            dataModel.put("mtlTaxAmountIsRmb",j.get("mtlTaxAmountIsRmb"));
            dataModel.put("mtlCompanyName",j.get("mtlCompanyName"));
            dataModel.put("mtlWhereItIs",j.get("mtlWhereItIs"));
            dataModel.put("mtlAccount",j.get("mtlAccount"));
            dataModel.put("list",list);
            dataModel.put("list1",list1);
            return dataModel;
        }
    },
    //修图合同
    XTHT_17("XTHT_17"){
        @Override
        public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
            Map dataModel = new HashMap();
            List<Map<String, Object>> list=new ArrayList();
            List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
            for (TemplateFieldJsonEntity templateField : templateFieldList) {
                //音频制作合同 关联表1
                if (ContractFormInfoTemplateContract.CONTRACT_MTLEDITEDTHECONTRACT1.equals(templateField.getRelationCode())) {
                    List<MtlEditedTheContract1ResponseVO> mtlEditedTheContract1List = JSON.parseArray(templateField.getTableData(), MtlEditedTheContract1ResponseVO.class);
                    for (int i = 0; i < mtlEditedTheContract1List.size(); i++) {
                        JSONObject mtlEditedTheContract1 = JSON.parseObject(JSON.toJSONString(mtlEditedTheContract1List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
                        Map<String, Object> map = new HashMap();
                        map.put("pictureSample",mtlEditedTheContract1.get("pictureSample"));
                        map.put("modificationRequirements",mtlEditedTheContract1.get("modificationRequirements"));
                        map.put("deliveryTime",mtlEditedTheContract1.get("deliveryTime"));
                        map.put("modeDelivery",mtlEditedTheContract1.get("modeDelivery"));
                        list.add(map);
                    }
                }
            }
            //主表
            MtlEditedTheContractEntity mtlEditedTheContract = JSONObject.toJavaObject(j, MtlEditedTheContractEntity.class);
            dataModel.put("mtlPatyA",j.get("mtlPatyA"));
            dataModel.put("mtlPatyAEmail",j.get("mtlPatyAEmail"));
            dataModel.put("mtlContactEmail",j.get("mtlContactEmail"));
            dataModel.put("mtlPatyB",j.get("mtlPatyB"));
            dataModel.put("mtlPatyBEmail",j.get("mtlPatyBEmail"));
            dataModel.put("mtlPatyBHome",j.get("mtlPatyBHome"));
            dataModel.put("mtlUnpaidTaxRmb",j.get("mtlUnpaidTaxRmb"));
            dataModel.put("mtlRate",j.get("mtlRate"));
            dataModel.put("mtlTaxAmountIsRmb",j.get("mtlTaxAmountIsRmb"));
            dataModel.put("mtlCompanyName",j.get("mtlCompanyName"));
            dataModel.put("mtlWhereItIs",j.get("mtlWhereItIs"));
            dataModel.put("mtlAccount",j.get("mtlAccount"));
            dataModel.put("list",list);
            return dataModel;
        }
    },
    //视频制作合同
    ZZHT_16("ZZHT_16"){
        @Override
        public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
            Map dataModel = new HashMap();
            List<Map<String, Object>> list=new ArrayList();
            List<Map<String, Object>> list1=new ArrayList();
            List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
            for (TemplateFieldJsonEntity templateField : templateFieldList) {
                //视频制作合同 关联表1
                if (ContractFormInfoTemplateContract.CONTRACT_MTLVIDEOPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
                    List<MtlVideoProductionContract1ResponseVO> mtlVideoProductionContract1List = JSON.parseArray(templateField.getTableData(), MtlVideoProductionContract1ResponseVO.class);
                    for (int i=0;i<mtlVideoProductionContract1List.size();i++) {
                        JSONObject mtlVideoProductionContract1= JSON.parseObject(JSON.toJSONString(mtlVideoProductionContract1List.get(i),filter, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));
                        Map<String, Object> map=new HashMap();
                        map.put("formDelivery",mtlVideoProductionContract1.get("formDelivery"));
                        map.put("number",mtlVideoProductionContract1.get("number"));
                        map.put("contentTheme",mtlVideoProductionContract1.get("contentTheme"));
                        map.put("requirements",mtlVideoProductionContract1.get("requirements"));
                        map.put("expenses",mtlVideoProductionContract1.get("expenses"));
                        list.add(map);
                    }
                }
                //视频制作合同 关联表2
                if (ContractFormInfoTemplateContract.CONTRACT_MTLVIDEOPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
                    List<MtlVideoProductionContract2ResponseVO> mtlVideoProductionContract2List = JSON.parseArray(templateField.getTableData(), MtlVideoProductionContract2ResponseVO.class);
                    for (int i=0;i<mtlVideoProductionContract2List.size();i++) {
                        JSONObject mtlVideoProductionContract2= JSON.parseObject(JSON.toJSONString(mtlVideoProductionContract2List.get(i),filter, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));
                        Map<String, Object> map=new HashMap();
                        map.put("intellectualProperty",mtlVideoProductionContract2.get("intellectualProperty"));
                        map.put("sample",mtlVideoProductionContract2.get("sample"));
                        map.put("serviceLife",mtlVideoProductionContract2.get("serviceLife"));
                        map.put("useArea",mtlVideoProductionContract2.get("useArea"));
                        list1.add(map);
                    }
                }
            }
            //主表
            MtlVideoProductionContractEntity mtlVideoProductionContract = JSONObject.toJavaObject(j, MtlVideoProductionContractEntity.class);
            dataModel.put("mtlPatyA",j.get("mtlPatyA"));
            dataModel.put("mtlPatyAEmail",j.get("mtlPatyAEmail"));
            dataModel.put("mtlContactEmail",j.get("mtlContactEmail"));
            dataModel.put("mtlPatyB",j.get("mtlPatyB"));
            dataModel.put("mtlPatyBEmail",j.get("mtlPatyBEmail"));
            dataModel.put("mtlPatyBHome",j.get("mtlPatyBHome"));
            dataModel.put("mtlAdaptationIssues",j.get("mtlAdaptationIssues"));
            dataModel.put("mtlNameOfTheVideo",j.get("mtlNameOfTheVideo"));
            dataModel.put("mtlVideoContent",j.get("mtlVideoContent"));
            dataModel.put("mtlProductionStartTime",j.get("mtlProductionStartTime"));
            dataModel.put("mtlProductionCompletionTime",j.get("mtlProductionCompletionTime"));
            dataModel.put("mtlAcceptance",j.get("mtlAcceptance"));
            dataModel.put("mtlUnpaidTaxRmb",j.get("mtlUnpaidTaxRmb"));
            dataModel.put("mtlRate",j.get("mtlRate"));
            dataModel.put("mtlTaxAmountIsRmb",j.get("mtlTaxAmountIsRmb"));
            dataModel.put("mtlCompanyName",j.get("mtlCompanyName"));
            dataModel.put("mtlWhereItIs",j.get("mtlWhereItIs"));
            dataModel.put("mtlAccount",j.get("mtlAccount"));
            dataModel.put("list",list);
            dataModel.put("list1",list1);
            return dataModel;
        }
    },
    //视频广告拍摄制作合同
    ZZHT_15("ZZHT_15"){
        @Override
        public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
            Map dataModel = new HashMap();
            List<Map<String, Object>> list=new ArrayList();
            List<Map<String, Object>> list1=new ArrayList();
            List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
            for (TemplateFieldJsonEntity templateField : templateFieldList) {
                //视频广告拍摄制作合同 关联表1
                if (ContractFormInfoTemplateContract.CONTRACT_MTLSHOOTINGANDPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
                    List<MtlShootingAndProductionContract1ResponseVO> mtlShootingAndProductionContract1List = JSON.parseArray(templateField.getTableData(), MtlShootingAndProductionContract1ResponseVO.class);
                    for (int i=0;i<mtlShootingAndProductionContract1List.size();i++) {
                        JSONObject mtlShootingAndProductionContract1= JSON.parseObject(JSON.toJSONString(mtlShootingAndProductionContract1List.get(i),filter, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));
                        Map<String, Object> map=new HashMap();
                        map.put("formDelivery",mtlShootingAndProductionContract1.get("formDelivery"));
                        map.put("number",mtlShootingAndProductionContract1.get("number"));
                        map.put("contentTheme",mtlShootingAndProductionContract1.get("contentTheme"));
                        map.put("requirements",mtlShootingAndProductionContract1.get("requirements"));
                        map.put("expenses",mtlShootingAndProductionContract1.get("expenses"));
                        list.add(map);
                    }
                }
                //视频广告拍摄制作合同 关联表2
                if (ContractFormInfoTemplateContract.CONTRACT_MTLSHOOTINGANDPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
                    List<MtlShootingAndProductionContract2ResponseVO> mtlShootingAndProductionContract2List = JSON.parseArray(templateField.getTableData(), MtlShootingAndProductionContract2ResponseVO.class);
                    for (int i=0;i<mtlShootingAndProductionContract2List.size();i++) {
                        JSONObject mtlShootingAndProductionContract2= JSON.parseObject(JSON.toJSONString(mtlShootingAndProductionContract2List.get(i),filter, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));
                        Map<String, Object> map=new HashMap();
                        map.put("intellectualProperty",mtlShootingAndProductionContract2.get("intellectualProperty"));
                        map.put("sample",mtlShootingAndProductionContract2.get("sample"));
                        map.put("serviceLife",mtlShootingAndProductionContract2.get("serviceLife"));
                        map.put("useArea",mtlShootingAndProductionContract2.get("useArea"));
                        list1.add(map);
                    }
                }
            }
            //主表
            MtlShootingAndProductionContractEntity mtlShootingAndProductionContract = JSONObject.toJavaObject(j, MtlShootingAndProductionContractEntity.class);
            dataModel.put("mtlPatyA",j.get("mtlPatyA"));
            dataModel.put("mtlPatyAEmail",j.get("mtlPatyAEmail"));
            dataModel.put("mtlContactEmail",j.get("mtlContactEmail"));
            dataModel.put("mtlPatyB",j.get("mtlPatyB"));
            dataModel.put("mtlPatyBEmail",j.get("mtlPatyBEmail"));
            dataModel.put("mtlPatyBHome",j.get("mtlPatyBHome"));
            dataModel.put("mtlAdaptationIssues",j.get("mtlAdaptationIssues"));
            dataModel.put("mtlNameOfAdvertising",j.get("mtlNameOfAdvertising"));
            dataModel.put("mtlContentsOfAdvertisements",j.get("mtlContentsOfAdvertisements"));
            dataModel.put("mtlProductionStartTime",j.get("mtlProductionStartTime"));
            dataModel.put("mtlProductionCompletionTime",j.get("mtlProductionCompletionTime"));
            dataModel.put("mtlAcceptance",j.get("mtlAcceptance"));
            dataModel.put("mtlCompanyName",j.get("mtlCompanyName"));
            dataModel.put("mtlWhereItIs",j.get("mtlWhereItIs"));
            dataModel.put("mtlAccount",j.get("mtlAccount"));
            dataModel.put("list",list);
            dataModel.put("list1",list1);
            return dataModel;
        }
    },
    //活动执行合同
    HDZX_05("HDZX_05"){
        @Override
        public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
            Map dataModel = new HashMap();
            CglActivityExecutionContractEntity cglActivityExecutionContract = JSONObject.toJavaObject(j, CglActivityExecutionContractEntity.class);
            dataModel.put("cglPartyA",j.get("cglPartyA"));
            dataModel.put("cglPartyB",j.get("cglPartyB"));
            dataModel.put("cglActivity",j.get("cglActivity"));
            dataModel.put("cglArea",j.get("cglArea"));
            dataModel.put("cglByTheTime",j.get("cglByTheTime"));
            dataModel.put("cglAsOfTime",j.get("cglAsOfTime"));
            dataModel.put("cglAttachmentTwo",j.get("cglAttachmentTwo"));
            dataModel.put("cglAttachmentThree",j.get("cglAttachmentThree"));
            dataModel.put("cglAttachmentOne",j.get("cglAttachmentOne"));
            dataModel.put("cglTotalCost",j.get("cglTotalCost"));
            dataModel.put("cglAmoutActivity",j.get("cglAmoutActivity"));
            dataModel.put("cglSession",j.get("cglSession"));
            dataModel.put("cglPayment",j.get("cglPayment"));
            dataModel.put("cglNumberOfDays",j.get("cglNumberOfDays"));
            dataModel.put("cglTAmount",j.get("cglTAmount"));
            dataModel.put("cglAmountWords",j.get("cglAmountWords"));
            dataModel.put("cglProportion",j.get("cglProportion"));
            dataModel.put("cglLumpSum",j.get("cglLumpSum"));
            dataModel.put("cglCapitalize",j.get("cglCapitalize"));
            dataModel.put("cglOtherWay",j.get("cglOtherWay"));
            dataModel.put("cglBankOf",j.get("cglBankOf"));
            dataModel.put("cglAccountName",j.get("cglAccountName"));
            dataModel.put("cglAccount",j.get("cglAccount"));
            dataModel.put("cglInvoice",j.get("cglInvoice"));
            dataModel.put("cglBreachOfContract",j.get("cglBreachOfContract"));
            dataModel.put("cglPlanningScheme",j.get("cglPlanningScheme"));
            dataModel.put("cglScheduling",j.get("cglScheduling"));
            dataModel.put("cglThePrice",j.get("cglThePrice"));
            dataModel.put("cglInspectionStandard",j.get("cglInspectionStandard"));
            return dataModel;
        }
    },
    //物流服务合同（二段配送）
    FWHT_24("FWHT_24"){
        @Override
        public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
            Map dataModel = new HashMap();
            SclLogisticsServiceEntity sclLogisticsService = JSONObject.toJavaObject(j, SclLogisticsServiceEntity.class);
            dataModel.put("sclPartyA",j.get("sclPartyA"));
            dataModel.put("sclPartyB",j.get("sclPartyB"));
            dataModel.put("sclDateOfSigning",j.get("sclDateOfSigning"));
            dataModel.put("sclSite",j.get("sclSite"));
            dataModel.put("sclStorage",j.get("sclStorage"));
            dataModel.put("sclArea",j.get("sclArea"));
            dataModel.put("sclDDay",j.get("sclDDay"));
            dataModel.put("sclNo",j.get("sclNo"));
            dataModel.put("sclStorageee",j.get("sclStorageee"));
            dataModel.put("sclConditionsa",j.get("sclConditionsa"));
            dataModel.put("sclNumber",j.get("sclNumber"));
            dataModel.put("sclServices",j.get("sclServices"));
            dataModel.put("sclFood",j.get("sclFood"));
            dataModel.put("sclDrinks",j.get("sclDrinks"));
            dataModel.put("sclDairy",j.get("sclDairy"));
            dataModel.put("sclWater",j.get("sclWater"));
            dataModel.put("sclRequirementsp",j.get("sclRequirementsp"));
            dataModel.put("sclRange",j.get("sclRange"));
            dataModel.put("sclAreae",j.get("sclAreae"));
            dataModel.put("sclRequirementse",j.get("sclRequirementse"));
            dataModel.put("sclContractd",j.get("sclContract"));
            dataModel.put("sclSecond",j.get("sclSecond"));
            dataModel.put("sclBreach",j.get("sclBreach"));
            dataModel.put("sclProvide",j.get("sclProvide"));
            dataModel.put("sclHours",j.get("sclHours"));
            dataModel.put("sclMorning",j.get("sclMorning"));
            dataModel.put("sclManifest",j.get("sclManifest"));
            dataModel.put("sclAfternoon",j.get("sclAfternoon"));
            dataModel.put("sclAdvance",j.get("sclAdvance"));
            dataModel.put("sclSeason",j.get("sclSeason"));
            dataModel.put("sclReturn",j.get("sclReturn"));
            dataModel.put("sclRequesta",j.get("sclRequesta"));
            dataModel.put("sclItems",j.get("sclItems"));
            dataModel.put("sclDate",j.get("sclDate"));
            dataModel.put("sclRequirementsddd",j.get("sclRequirementsddd"));
            dataModel.put("sclThird",j.get("sclThird"));
            dataModel.put("sclRequirementsf",j.get("sclRequirementsf"));
            dataModel.put("sclTransfer",j.get("sclTransfer"));
            dataModel.put("sclDateOfs",j.get("sclDateOfs"));
            dataModel.put("sclRequirementsss",j.get("sclRequirementsss"));
            dataModel.put("sclStandard",j.get("sclStandard"));
            dataModel.put("sclAread",j.get("sclAread"));
            return dataModel;
        }
    },
    //媒体类：平面广告拍摄制作合同
    ZZHT_12("ZZHT_12"){
        @Override
        public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
            Map dataModel = new HashMap();
            List<Map<String, Object>> list=new ArrayList();
            List<Map<String, Object>> list1=new ArrayList();
            List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
            for (TemplateFieldJsonEntity templateField : templateFieldList) {
                //视频广告拍摄制作合同 关联表1
                if (ContractFormInfoTemplateContract.CONTRACT_MTBPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
                    List<MtbProductionContract1ResponseVO> mtbProductionContract1List = JSON.parseArray(templateField.getTableData(), MtbProductionContract1ResponseVO.class);
                    for (int i=0;i<mtbProductionContract1List.size();i++) {
                        JSONObject  mtbProductionContract1= JSON.parseObject(JSON.toJSONString(mtbProductionContract1List.get(i),filter, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));
                        Map<String, Object> map=new HashMap();
                        map.put("formDelivery", mtbProductionContract1.get("formDelivery"));
                        map.put("number", mtbProductionContract1.get("number"));
                        map.put("contentTheme", mtbProductionContract1.get("contentTheme"));
                        map.put("requirements", mtbProductionContract1.get("requirements"));
                        list.add(map);
                    }
                 }
                //视频广告拍摄制作合同 关联表2
                if (ContractFormInfoTemplateContract.CONTRACT_MTBPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
                    List<MtbProductionContract2ResponseVO> mtbProductionContract2List = JSON.parseArray(templateField.getTableData(), MtbProductionContract2ResponseVO.class);
                    for (int i=0;i<mtbProductionContract2List.size();i++) {
                        JSONObject mtbProductionContract2= JSON.parseObject(JSON.toJSONString(mtbProductionContract2List.get(i),filter, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));
                        Map<String, Object> map=new HashMap();
                        map.put("intellectualProperty",mtbProductionContract2.get("intellectualProperty"));
                        map.put("smallKind",mtbProductionContract2.get("smallKind"));
                        map.put("serviceLife",mtbProductionContract2.get("serviceLife"));
                        map.put("useArea",mtbProductionContract2.get("useArea"));
                        list1.add(map);
                    }
                }
            }
            MtbProductionContractEntity mtbProductionContract = JSONObject.toJavaObject(j, MtbProductionContractEntity.class);
            dataModel.put("mtbPatyA",j.get("mtbPatyA"));
            dataModel.put("mtbContactEmail",j.get("mtbContactEmail"));
            dataModel.put("mtbAddress",j.get("mtbAddress"));
            dataModel.put("mtbPatyB",j.get("mtbPatyB"));
            dataModel.put("mtbPatyBEmail",j.get("mtbPatyBEmail"));
            dataModel.put("mtbPatyBAddress",j.get("mtbPatyBAddress"));
            dataModel.put("mtbMakeMatters",j.get("mtbMakeMatters"));
            dataModel.put("mtbNameOfAdvertising",j.get("mtbNameOfAdvertising"));
            dataModel.put("mtbContentsOfAdvertisements",j.get("mtbContentsOfAdvertisements"));
            dataModel.put("mtbHaveHasNot",j.get("mtbHaveHasNot"));
            dataModel.put("mtbShootingStartTime",j.get("mtbShootingStartTime"));
            dataModel.put("mtbShootingCompletionTime",j.get("mtbShootingCompletionTime"));
            dataModel.put("mtbAcceptancePersonnel",j.get("mtbAcceptancePersonnel"));
            dataModel.put("mtbUnpaidTaxRmb",j.get("mtbUnpaidTaxRmb"));
            dataModel.put("mtbRate",j.get("mtbRate"));
            dataModel.put("mtbTaxInclusiveInRmb",j.get("mtbTaxInclusiveInRmb"));
            dataModel.put("mtbManyItems",j.get("mtbManyItems"));
            dataModel.put("mtbLegalTaxInvoice",j.get("mtbLegalTaxInvoice"));
            dataModel.put("mtbPayRmb",j.get("mtbPayRmb"));
            dataModel.put("mtbAllRemainingMoney",j.get("mtbAllRemainingMoney"));
            dataModel.put("mtbCompanyName",j.get("mtbCompanyName"));
            dataModel.put("mtbBankOfPartyB",j.get("mtbBankOfPartyB"));
            dataModel.put("mtbPartyAccount",j.get("mtbPartyAccount"));
            dataModel.put("mtbPortrait",j.get("mtbPortrait"));
            dataModel.put("list",list);
            dataModel.put("list1",list1);
            return dataModel;
        }
    },

    //生产类：设备维修保养合同
    //TODO 缺少关联表数据
    BYHT_21("BYHT_21"){
        @Override
        public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
            Map dataModel = new HashMap();
            List<Map<String, Object>> list=new ArrayList();
            List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
            for (TemplateFieldJsonEntity templateField : templateFieldList) {
                //设备维修保养合同 关联表1
                if (ContractFormInfoTemplateContract.CONTRACT_SCLEQUIOMENTMAINTENANCE1.equals(templateField.getRelationCode())) {
                    List<SclEquipmentMaintenance1ResponseVO> slEquipmentMaintenance1List = JSON.parseArray(templateField.getTableData(), SclEquipmentMaintenance1ResponseVO.class);
                    for (int i = 0; i < slEquipmentMaintenance1List.size(); i++) {
                        JSONObject slEquipmentMaintenance1 = JSON.parseObject(JSON.toJSONString(slEquipmentMaintenance1List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
                        Map<String, Object> map = new HashMap();
                        map.put("sclDeviceName",slEquipmentMaintenance1.get("sclDeviceName"));
                        map.put("sclBrand",j.get("sclBrand"));
                        map.put("sclSpecification",slEquipmentMaintenance1.get("sclSpecification"));
                        map.put("sclPrice",slEquipmentMaintenance1.get("sclPrice"));
                        map.put("sclNumbers",slEquipmentMaintenance1.get("sclNumbers"));
                        map.put("sclOther",slEquipmentMaintenance1.get("sclOther"));
                        list.add(map);
                    }
                }
            }
            SclEquipmentMaintenanceEntity sclEquipmentMaintenance = JSONObject.toJavaObject(j, SclEquipmentMaintenanceEntity.class);
            dataModel.put("sclPatyA",j.get("sclPatyA"));
            dataModel.put("sclPatyBs",j.get("sclPatyBs"));
            dataModel.put("sclPatyB",j.get("sclPatyB"));
            dataModel.put("sclAddress",j.get("sclAddress"));
            dataModel.put("sclProjectName",j.get("sclProjectName"));
            dataModel.put("sclHome",j.get("sclHome"));
            dataModel.put("sclTotalRmb",j.get("sclTotalRmb"));
            dataModel.put("sclCapitalRmb",j.get("sclCapitalRmb"));
            dataModel.put("sclNote",j.get("sclNote"));
            dataModel.put("sclEquipment",j.get("sclEquipment"));
            dataModel.put("sclMaintenancessss",j.get("sclMaintenancessss"));
            dataModel.put("sclGuaranteePeriod",j.get("sclGuaranteePeriod"));
            dataModel.put("sclQuality",j.get("sclQuality"));
            dataModel.put("sclRoutineMaintenancess",j.get("sclRoutineMaintenancess"));
            dataModel.put("sclMonth",j.get("sclMonth"));
            dataModel.put("sclMaintenance",j.get("sclMaintenance"));
            dataModel.put("sclPeriod",j.get("sclPeriod"));
            dataModel.put("sclMaintenances",j.get("sclMaintenances"));
            dataModel.put("sclRoutine",j.get("sclRoutine"));
            dataModel.put("sclCumulative",j.get("sclCumulative"));
            dataModel.put("sclSpecificallyAgreed",j.get("sclSpecificallyAgreed"));
            dataModel.put("sclContract",j.get("sclContract"));
            dataModel.put("sclPartyAs",j.get("sclPartyAs"));
            dataModel.put("sclServiceTelephone",j.get("sclServiceTelephone"));
            dataModel.put("sclShouldBeIn",j.get("sclShouldBeIn"));
            dataModel.put("sclMaintenanceq",j.get("sclMaintenanceq"));
            dataModel.put("sclSides",j.get("sclSides"));
            dataModel.put("sclPayment",j.get("sclPayment"));
            dataModel.put("sclOtherWay",j.get("sclOtherWay"));
            dataModel.put("sclBank",j.get("sclBank"));
            dataModel.put("sclName",j.get("sclName"));
            dataModel.put("sclAccount",j.get("sclAccount"));
            dataModel.put("sclContracts",j.get("sclContracts"));
            dataModel.put("sclLimit",j.get("sclLimit"));
            dataModel.put("sclGold",j.get("sclGold"));
            dataModel.put("sclThan",j.get("sclThan"));
            dataModel.put("sclBou",j.get("sclBou"));
            dataModel.put("sclStart",j.get("sclStart"));
            dataModel.put("sclLaste",j.get("sclLaste"));
            dataModel.put("sclCompany",j.get("sclCompany"));
            dataModel.put("list",list);
            return dataModel;
        }
    },
    //物流服务合同（二段配送）
    MMHT_26("MMHT_26"){
        @Override
        public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
            Map dataModel = new HashMap();
            SclContractTemplateEntity sclLogisticsService = JSONObject.toJavaObject(j, SclContractTemplateEntity.class);
            dataModel.put("sclPartyA",j.get("sclPartyA"));
            dataModel.put("sclPartyB",j.get("sclPartyB"));
            dataModel.put("sclPatyB",j.get("sclPatyB"));
            dataModel.put("sclMonth",j.get("sclMonth"));
            dataModel.put("sclDay",j.get("sclDay"));
            dataModel.put("sclSite",j.get("sclSite"));
            dataModel.put("sclStorage",j.get("sclStorage"));
            dataModel.put("sclArea",j.get("sclArea"));
            dataModel.put("sclNo",j.get("sclNo"));
            dataModel.put("sclStorageee",j.get("sclStorageee"));
            dataModel.put("sclConditionsa",j.get("sclConditionsa"));
            dataModel.put("sclNumber",j.get("sclNumber"));
            dataModel.put("sclServices",j.get("sclServices"));
            dataModel.put("sclFood",j.get("sclFood"));
            dataModel.put("sclDrinks",j.get("sclDrinks"));
            dataModel.put("sclDairy",j.get("sclDairy"));
            dataModel.put("sclWater",j.get("sclWater"));
            dataModel.put("sclRequirementsp",j.get("sclRequirementsp"));
            dataModel.put("sclRange",j.get("sclRange"));
            dataModel.put("sclAreae",j.get("sclAreae"));
            dataModel.put("sclRequirementse",j.get("sclRequirementse"));
            dataModel.put("sclContractd",j.get("sclContract"));
            dataModel.put("sclProvide",j.get("sclProvide"));
            dataModel.put("sclSecond",j.get("sclSecond"));
            dataModel.put("sclHours",j.get("sclHours"));
            dataModel.put("sclBreach",j.get("sclBreach"));
            dataModel.put("sclMorning",j.get("sclMorning"));
            dataModel.put("sclAfternoon",j.get("sclAfternoon"));
            dataModel.put("sclAdvance",j.get("sclAdvance"));
            dataModel.put("sclSeason",j.get("sclSeason"));
            dataModel.put("sclReturn",j.get("sclReturn"));
            dataModel.put("sclLiquidatedDamages",j.get("sclLiquidatedDamages"));
            dataModel.put("sclSenDamages",j.get("sclSenDamages"));
            dataModel.put("sclEngDamages",j.get("sclEngDamages"));
            dataModel.put("sclStartTimes",j.get("sclStartTimes"));
            dataModel.put("sclEndTimes",j.get("sclEndTimes"));
            dataModel.put("sclRequesta",j.get("sclRequesta"));
            dataModel.put("sclItems",j.get("sclItems"));
            dataModel.put("sclDate",j.get("sclDate"));
            return dataModel;
        }
    };

    public abstract Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j);

    @Getter
    public String type;

    /**通过轮询来获得相应的方法*/
    public static TemplateExporterEnum fromValue(String type) {
        return Stream.of(TemplateExporterEnum.values()).filter(fileType ->
                StringUtils.equals(fileType.getType(), type)
        ).findFirst().get();
    }

	/**处理json串中为null的过滤方法*/
	private static ValueFilter filter =new ValueFilter() {
        @Override
        public Object process(Object o, String s, Object o1) {
            if(o1 == null) {
                return "";
            }
            return o1;
        }
    };

}
