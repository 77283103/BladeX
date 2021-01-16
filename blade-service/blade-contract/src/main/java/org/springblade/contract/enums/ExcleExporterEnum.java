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
public enum ExcleExporterEnum {

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
			dataModel.put("ywlTheStartTime",j.get("ywlTheStartTime"));
			dataModel.put("ywlEndOfTime",j.get("ywlEndOfTime"));
			dataModel.put("ywlDisplayFee",j.get("ywlDisplayFee"));
			dataModel.put("ywlDisplayDfee","");
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
			dataModel.put("mtlProductionStartTime",j.get("mtlProductionStartTime"));
			dataModel.put("mtlProductionCompletionTime",j.get("mtlProductionCompletionTime"));
			dataModel.put("mtlUnpaidTaxRmb",j.get("mtlUnpaidTaxRmb"));
			dataModel.put("mtlRate",j.get("mtlRate"));
			dataModel.put("mtlTaxAmountIsRmb",j.get("mtlTaxAmountIsRmb"));
			dataModel.put("mtlCompanyName",j.get("mtlCompanyName"));
			dataModel.put("mtlWhereItIs",j.get("mtlWhereItIs"));
			dataModel.put("mtlAccount",j.get("mtlAccount"));
			dataModel.put("yearA",j.get("yearA"));
            dataModel.put("yearB",j.get("yearB"));
            dataModel.put("yearC",j.get("yearC"));
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
            dataModel.put("mtlmtlContactEmail",j.get("mtlmtlContactEmail"));
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
    HDZX_05("HDZX_05") {
        @Override
        public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
            Map dataModel = new HashMap();
            CglActivityExecutionContractEntity cglActivityExecutionContract = JSONObject.toJavaObject(j, CglActivityExecutionContractEntity.class);
            dataModel.put("cglPartyA", j.get("cglPartyA"));
            dataModel.put("cglPartyB", j.get("cglPartyB"));
            dataModel.put("cglActivity", j.get("cglActivity"));
            dataModel.put("cglArea", j.get("cglArea"));
            dataModel.put("cglByTheTime", j.get("cglByTheTime"));
            dataModel.put("cglAsOfTime", j.get("cglAsOfTime"));
            dataModel.put("cglAttachmentTwo", j.get("cglAttachmentTwo"));
            dataModel.put("cglAttachmentThree", j.get("cglAttachmentThree"));
            dataModel.put("cglAttachmentOne", j.get("cglAttachmentOne"));
            dataModel.put("cglTotalCost", j.get("cglTotalCost"));
            dataModel.put("cglAmoutActivity", j.get("cglAmoutActivity"));
            dataModel.put("cglSession", j.get("cglSession"));
            dataModel.put("cglPayment", j.get("cglPayment"));
            dataModel.put("cglNumberOfDays", j.get("cglNumberOfDays"));
            dataModel.put("cglTAmount", j.get("cglTAmount"));
            dataModel.put("cglAmountWords", j.get("cglAmountWords"));
            dataModel.put("cglProportion", j.get("cglProportion"));
            dataModel.put("cglLumpSum", j.get("cglLumpSum"));
            dataModel.put("cglCapitalize", j.get("cglCapitalize"));
            dataModel.put("cglOtherWay", j.get("cglOtherWay"));
            dataModel.put("cglBankOf", j.get("cglBankOf"));
            dataModel.put("cglAccountName", j.get("cglAccountName"));
            dataModel.put("cglAccount", j.get("cglAccount"));
            dataModel.put("cglInvoice", j.get("cglInvoice"));
            dataModel.put("cglBreachOfContract", j.get("cglBreachOfContract"));
            dataModel.put("cglPlanningScheme", j.get("cglPlanningScheme"));
            dataModel.put("cglScheduling", j.get("cglScheduling"));
            dataModel.put("cglThePrice", j.get("cglThePrice"));
            dataModel.put("cglInspectionStandard", j.get("cglInspectionStandard"));
            return dataModel;
        }
    },
    //广告制作安装合同模板
    GGZZ_04("GGZZ_04"){
        @Override
        public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
            Map dataModel = new HashMap();
            CglAdvertisementProductionEntity cglAdvertisementProductionEntity = JSONObject.toJavaObject(j, CglAdvertisementProductionEntity.class);
            dataModel.put("sclPartyA",j.get("sclPartyA"));
            dataModel.put("cglAddressA",j.get("cglAddressA"));
            dataModel.put("cglPartyB",j.get("cglPartyB"));
            dataModel.put("cglAddress",j.get("cglAddress"));
            dataModel.put("sclArea",j.get("sclArea"));
            dataModel.put("quotationSheet",j.get("quotationSheet"));
            dataModel.put("cglEmail",j.get("cglEmail"));
            dataModel.put("individual",j.get("individual"));
            dataModel.put("day",j.get("day"));
            dataModel.put("dayA",j.get("dayA"));
            dataModel.put("dayB",j.get("dayB"));
            dataModel.put("dayC",j.get("dayC"));
            dataModel.put("dayD",j.get("dayD"));
            dataModel.put("dayE",j.get("dayE"));
            dataModel.put("year",j.get("year"));
            dataModel.put("dayF",j.get("dayF"));
            dataModel.put("paymentMethod",j.get("paymentMethod"));
            dataModel.put("type",j.get("type"));
            dataModel.put("dayG",j.get("dayG"));
            dataModel.put("otherWays",j.get("otherWays"));
            dataModel.put("corporateName",j.get("corporateName"));
            dataModel.put("bankDeposit",j.get("bankDeposit"));
            dataModel.put("account",j.get("account"));
            dataModel.put("dayH",j.get("dayH"));
            dataModel.put("money",j.get("money"));
            dataModel.put("dayI",j.get("dayI"));
            dataModel.put("specialAgreement",j.get("specialAgreement"));
            dataModel.put("date",j.get("date"));
            dataModel.put("dateA",j.get("dateA"));
            dataModel.put("number",j.get("number"));
            dataModel.put("numberA",j.get("numberA"));
            dataModel.put("numberB",j.get("numberB"));
            dataModel.put("cglAttachment",j.get("cglAttachment"));
            dataModel.put("cglAttachmentA",j.get("cglAttachmentA"));
            return dataModel;
        }
    },
    //采购类_打样合同书
    DYHT_03("DYHT_03"){
        @Override
        public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
            Map dataModel = new HashMap();
            List<Map<String, Object>> list=new ArrayList();
            List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
            for (TemplateFieldJsonEntity templateField : templateFieldList) {
                //设备维修保养合同 关联表1
                if (ContractFormInfoTemplateContract.CONTRACT_CGLPROOFINGCONTRACT1.equals(templateField.getRelationCode())) {
                    List<CglProofingContract1ResponseVO> cglProofingContract1List = JSON.parseArray(templateField.getTableData(), CglProofingContract1ResponseVO.class);
                    for (int i = 0; i < cglProofingContract1List.size(); i++) {
                        JSONObject glProofingContract1 = JSON.parseObject(JSON.toJSONString(cglProofingContract1List.get(i), filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
                        Map<String, Object> map = new HashMap();
                        map.put("proofingProducts",glProofingContract1.get("proofingProducts"));
                        map.put("proofingContent",glProofingContract1.get("proofingContent"));
                        map.put("textureMaterial",glProofingContract1.get("textureMaterial"));
                        map.put("unitPrice",glProofingContract1.get("unitPrice"));
                        map.put("number",glProofingContract1.get("number"));
                        map.put("totalAmount",glProofingContract1.get("totalAmount"));
                        list.add(map);
                    }
                }
            }
            CglProofingContractEntity cglProofingContract = JSONObject.toJavaObject(j, CglProofingContractEntity.class);
            dataModel.put("partyA",j.get("partyA"));
            dataModel.put("partyB",j.get("partyB"));
            dataModel.put("element",j.get("element"));
            dataModel.put("date",j.get("date"));
            dataModel.put("element1",j.get("element1"));
            dataModel.put("taxRate",j.get("taxRate"));
            dataModel.put("element2",j.get("element2"));
            dataModel.put("accounName",j.get("accounName"));
            dataModel.put("accountNumber",j.get("accountNumber"));
            dataModel.put("bankDeposit",j.get("bankDeposit"));
            dataModel.put("list",list);
            return dataModel;
        }
    },
    //下脚品买卖合同模版
    MMHT_26("MMHT_26"){
        @Override
        public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
            Map dataModel = new HashMap();
            InferiorProductContractEntity inferiorProductContract = JSONObject.toJavaObject(j, InferiorProductContractEntity.class);
            dataModel.put("infSaler",j.get("infSaler"));
            dataModel.put("infSalerAddr",j.get("infSalerAddr"));
            dataModel.put("infBuyer",j.get("infBuyer"));
            dataModel.put("infBuyerAddr",j.get("infBuyerAddr"));
            dataModel.put("infTypeFir",j.get("infTypeFir"));
            dataModel.put("infWeekStart",j.get("infWeekStart"));
            dataModel.put("infWeekEnd",j.get("infWeekEnd"));
            dataModel.put("infTimeStart",j.get("infTimeStart"));
            dataModel.put("infTimeEnd",j.get("infTimeEnd"));
            dataModel.put("infSortAddr",j.get("infSortAddr"));
            dataModel.put("infLeastAmount",j.get("infLeastAmount"));
            dataModel.put("infTimeAmount",j.get("infTimeAmount"));
            dataModel.put("infLoadAddr",j.get("infLoadAddr"));
            dataModel.put("infAppointAmount",j.get("infAppointAmount"));
            dataModel.put("infCap",j.get("infCap"));
            dataModel.put("infTimeLeastFir",j.get("infTimeLeastFir"));
            dataModel.put("infTimeLeastSec",j.get("infTimeLeastSec"));
            dataModel.put("infSalerAccoutName",j.get("infSalerAccoutName"));
            dataModel.put("infBuyerAccoutName",j.get("infBuyerAccoutName"));
            dataModel.put("infSalerAccountId",j.get("infSalerAccountId"));
            dataModel.put("infBuyerAccountId",j.get("infBuyerAccountId"));
            dataModel.put("infSalerAccountBank",j.get("infSalerAccountBank"));
            dataModel.put("infBuyerAccountBank",j.get("infBuyerAccountBank"));
            dataModel.put("infTimeLeastThi",j.get("infTimeLeastThi"));
            dataModel.put("infSalerMail",j.get("infSalerMail"));
            dataModel.put("infBuyerMail",j.get("infBuyerMail"));
            dataModel.put("infBreachAmountFir",j.get("infBreachAmountFir"));
            dataModel.put("infBreachAmountSec",j.get("infBreachAmountSec"));
            dataModel.put("infBreachAmountThi",j.get("infBreachAmountThi"));
            dataModel.put("infBreachAmountFou",j.get("infBreachAmountFou"));
            dataModel.put("infContractStart", DataFormatUtils.systemTimeFormat(String.valueOf(j.get("infContractStart"))));
            dataModel.put("infContractEnd",DataFormatUtils.systemTimeFormat(String.valueOf(j.get("infContractEnd"))));
            dataModel.put("infContractNum",j.get("infContractNum"));
            dataModel.put("infSalerPhone",j.get("infSalerPhone"));
            dataModel.put("infBuyerPhone",j.get("infBuyerPhone"));
            dataModel.put("infSalerPerson",j.get("infSalerPerson"));
            dataModel.put("infBuyerPerson",j.get("infBuyerPerson"));
            dataModel.put("infSurf",j.get("infSurf"));
            dataModel.put("infOil",j.get("infOil"));
            dataModel.put("infTeaSurf",j.get("infTeaSurf"));
            return dataModel;
        }
    };


    public abstract Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j);

	@Getter
	public String type;

	/**通过轮询来获得相应的方法*/
	public static ExcleExporterEnum fromValue(String type) {
		return Stream.of(ExcleExporterEnum.values()).filter(fileType ->
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
