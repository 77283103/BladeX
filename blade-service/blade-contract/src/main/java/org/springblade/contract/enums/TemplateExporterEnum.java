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
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springblade.contract.constant.ContractFormInfoTemplateContract;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.YwlANewDisplayEntity;
import org.springblade.contract.entity.YwlShopRecruitmentEntity;
import org.springblade.contract.vo.YwlANewDisplay1ResponseVO;
import org.springblade.core.tool.utils.Func;
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
				//*新陈列协议书关联表
				if (ContractFormInfoTemplateContract.CONTRACT_YWLANEWDISPLAY1.equals(templateField.getRelationCode())) {
					List<YwlANewDisplay1ResponseVO> ywlShopRecruitment1List = JSON.parseArray(templateField.getTableData(), YwlANewDisplay1ResponseVO.class);
					for (int i=0;i<ywlShopRecruitment1List.size();i++) {
						Map<String, Object> map=new HashMap();
						map.put("ywlNumber",i+1);
						map.put("ywlDisplayProducts",ywlShopRecruitment1List.get(i).getYwlDisplayProducts());
						map.put("ywlDisplayMode",ywlShopRecruitment1List.get(i).getYwlDisplayMode());
						map.put("ywlMerchandisingStandards",ywlShopRecruitment1List.get(i).getYwlMerchandisingStandards());
						list.add(map);
					}
				}
			}
			YwlANewDisplayEntity ywlANewDisplay = JSONObject.toJavaObject(j, YwlANewDisplayEntity.class);
			dataModel.put("ywlCooperationContent",ywlANewDisplay.getYwlCooperationContent());
			dataModel.put("ywlTheStartTime",ywlANewDisplay.getYwlTheStartTime());
			dataModel.put("ywlEndOfTime",ywlANewDisplay.getYwlEndOfTime());
			dataModel.put("ywlDisplayFee",ywlANewDisplay.getYwlDisplayFee());
			dataModel.put("ywlDisplayDfee",ywlANewDisplay.getYwlDisplayFee());
			dataModel.put("ywlOther",ywlANewDisplay.getYwlOther());
			dataModel.put("list",list);
			return dataModel;
		}
	},
	//店招合同
	DZHT_35("DZHT_35"){
		@Override
		public Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j) {
			Map dataModel = new HashMap();
			YwlShopRecruitmentEntity ywlShopRecruitment = JSONObject.toJavaObject(j, YwlShopRecruitmentEntity.class);
			if (!Func.isEmpty(ywlShopRecruitment.getYwlPatyA())){
				dataModel.put("ywlPatyA",ywlShopRecruitment.getYwlPatyA());
			}else{
				dataModel.put("ywlPatyA","");
			}
			if (!Func.isEmpty(ywlShopRecruitment.getYwlPatyB())){
				dataModel.put("ywlPatyB",ywlShopRecruitment.getYwlPatyB());
			}else{
				dataModel.put("ywlPatyB","");
			}
			if (!Func.isEmpty(ywlShopRecruitment.getYwlLocation())){
				dataModel.put("ywlLocation",ywlShopRecruitment.getYwlLocation());
			}else{
				dataModel.put("ywlLocation","");
			}
			if (!Func.isEmpty(ywlShopRecruitment.getYwlProductionCosts())){
				dataModel.put("ywlProductionCosts",ywlShopRecruitment.getYwlProductionCosts());
			}else{
				dataModel.put("ywlProductionCosts","");
			}
			if (!Func.isEmpty(ywlShopRecruitment.getYwlAmountOf())){
				dataModel.put("ywlAmountOf",ywlShopRecruitment.getYwlAmountOf());
			}else{
				dataModel.put("ywlAmountOf","");
			}
			if (!Func.isEmpty(ywlShopRecruitment.getYwlProductionCosts())){
				dataModel.put("ywlOtherConventions",ywlShopRecruitment.getYwlProductionCosts());
			}else{
				dataModel.put("ywlOtherConventions","");
			}
			return dataModel;
		}
	};




	public abstract Map setScheduler(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json,JSONObject j);

	@Getter
	public String type;

	//通过轮询来获得相应的方法
	public static TemplateExporterEnum fromValue(String type) {
		return Stream.of(TemplateExporterEnum.values()).filter(fileType ->
			StringUtils.equals(fileType.getType(), type)
		).findFirst().get();
	}

}
