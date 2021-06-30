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
package org.springblade.contract.excel.importbatchdraft;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.core.tool.utils.Func;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *合同信息
 */
@Data
public class ContractImportBatchDraftExcel implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("合同标识号码（非重复数字）")
	private String contractNumber;

	@ExcelProperty("合同所属大类")
	private String contractBigCategory;

	@ExcelProperty("合同所属小类")
	private String contractSmallCategory;

	@ExcelProperty("合同名称")
	private String contractName;

	@ExcelProperty("合同负责人(员工编号)")
	private String personCodeContract;

	@ExcelProperty("我方身份")
	private String contractRoles;

	@ExcelProperty("申请用章单位")
	private String sealName;

	@ExcelProperty("合同形式")
	private String contractForm;

	@ExcelProperty("相对方联系人")
	private String counterpartPerson;

	@ExcelProperty("联系人电话")
	private String telephonePerson;

	@ExcelProperty("联系人邮箱")
	private String emailPerson;

	@ExcelProperty("联系人地址")
	private String addressPerson;

	@ExcelProperty("合同份数")
	private String share;

	@ExcelProperty("合同起始时间")
	private Date startingTime;

	@ExcelProperty("合同结束时间")
	private Date endTime;

	@ExcelProperty("合同期限")
	private String contractPeriod;

	@ExcelProperty("币种")
	private String currencyCategory;

	@ExcelProperty("收付款")
	private String colPayType;

	@ExcelProperty("收付款条件")
	private String colPayTerm;

	@ExcelProperty("天数")
	private String days;

	@ExcelProperty("合同未税金额")
	private BigDecimal contractAmount;

	@ExcelProperty("税率")
	private Double contactTaxRate;

	@ExcelProperty("合同含税金额")
	private BigDecimal contractTaxAmount;

	@ExcelProperty("自动延展条款")
	private String extension;


	/* 合同关联信息 */

	@ApiModelProperty(value = "相对方")
	private List<ContractCounterpartImportBatchDraftExcel> contractCounterpartImportBatchDraftExcels;

	@ApiModelProperty(value = "签呈依据")
	private List<ContractAccordingImportBatchDraftExcel> contractAccordingImportBatchDraftExcels;

	@ApiModelProperty(value = "履约-保证金")
	private List<ContractBondImportBatchDraftExcel> contractBondImportBatchDraftExcels;

	@ApiModelProperty(value = "履约-计划收付款")
	private List<PerCollectPayImportBatchDraftExcel> perCollectPayImportBatchDraftExcels;


	/**
	 * 关联相对方集合
	 */
	public void relationCounterpartList(List<ContractCounterpartImportBatchDraftExcel> contractCounterpartImportBatchDraftExcels){
		if(Func.isEmpty(this.contractCounterpartImportBatchDraftExcels)){
			this.contractCounterpartImportBatchDraftExcels = new ArrayList<>();
		}
		if(Func.isNotEmpty(contractCounterpartImportBatchDraftExcels)){
			contractCounterpartImportBatchDraftExcels.forEach(contractCounterpartImportBatchDraftExcel -> {
				if(contractCounterpartImportBatchDraftExcel.getContractNumber().equals(this.contractNumber)){
					this.contractCounterpartImportBatchDraftExcels.add(contractCounterpartImportBatchDraftExcel);
				}
			});
		}
	}

	/**
	 * 关联签呈依据集合
	 */
	public void relationAccordingList(List<ContractAccordingImportBatchDraftExcel> contractAccordingImportBatchDraftExcels){
		if(Func.isEmpty(this.contractAccordingImportBatchDraftExcels)){
			this.contractAccordingImportBatchDraftExcels = new ArrayList<>();
		}
		if(Func.isNotEmpty(contractAccordingImportBatchDraftExcels)){
			contractAccordingImportBatchDraftExcels.forEach(contractAccordingImportBatchDraftExcel -> {
				if(contractAccordingImportBatchDraftExcel.getContractNumber().equals(this.contractNumber)){
					this.contractAccordingImportBatchDraftExcels.add(contractAccordingImportBatchDraftExcel);
				}
			});
		}
	}


	/**
	 * 关联保证金集合
	 * @param contractBondImportBatchDraftExcels
	 */
	public void relationBondList(List<ContractBondImportBatchDraftExcel> contractBondImportBatchDraftExcels){
		if(Func.isEmpty(this.contractBondImportBatchDraftExcels)){
			this.contractBondImportBatchDraftExcels = new ArrayList<>();
		}
		if(Func.isNotEmpty(contractBondImportBatchDraftExcels)){
			contractBondImportBatchDraftExcels.forEach(contractBondImportBatchDraftExcel -> {
				if(contractBondImportBatchDraftExcel.getContractNumber().equals(this.contractNumber)){
					this.contractBondImportBatchDraftExcels.add(contractBondImportBatchDraftExcel);
				}
			});
		}
	}

	/**
	 * 关联履约计划收付款集合
	 */
	public void relationPerCollectPayList(List<PerCollectPayImportBatchDraftExcel> perCollectPayImportBatchDraftExcels){
		if(Func.isEmpty(this.perCollectPayImportBatchDraftExcels)){
			this.perCollectPayImportBatchDraftExcels = new ArrayList<>();
		}
		if(Func.isNotEmpty(perCollectPayImportBatchDraftExcels)){
			perCollectPayImportBatchDraftExcels.forEach(perCollectPayImportBatchDraftExcel -> {
				if(perCollectPayImportBatchDraftExcel.getContractNumber().equals(this.contractNumber)){
					this.perCollectPayImportBatchDraftExcels.add(perCollectPayImportBatchDraftExcel);
				}
			});
		}
	}




}
