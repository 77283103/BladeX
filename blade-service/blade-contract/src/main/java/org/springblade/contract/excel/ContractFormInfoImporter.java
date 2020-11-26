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
package org.springblade.contract.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *合同信息
 */
@Data
public class ContractFormInfoImporter implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("合同小类")private String contractTemplateId;
	@ExcelProperty("申请用章全称")private String sealName;
	@ExcelProperty("相对方全称")private String counterpartName;
	@ExcelProperty("交易类型")private String dictValue;
	@ExcelProperty("合同负责人")private String personContract;
	@ExcelProperty("份数")private String sealNumber;
	@ExcelProperty("合同期限")private String contractPeriod;
	@ExcelProperty("合同时间-起")private String startingTime;
	@ExcelProperty("合同时间-止")private String endTime;
	@ExcelProperty("收付款")private String colPayType;
	@ExcelProperty("收付款条件")private String colPayTerm;
	@ExcelProperty("金额")private String contractAmount;
	@ExcelProperty("是否有自动延期条款")private String extension;
	@ExcelProperty("合同形式")private String contractForm;
	@ExcelProperty("相对方联系人")private String counterpartPerson;
	@ExcelProperty("相对方联系电话")private String telephonePerson;
	@ExcelProperty("相对方邮箱 ")private String emailPerson;
	@ExcelProperty("相对方联系地址")private String addressPerson;
	@ExcelProperty("起订量及说明")private String ywlMinimum;
	@ExcelProperty("甲方指定邮箱")private String ywlMailbox;
	@ExcelProperty("质量其他约定")private String ywlAgreements;
	@ExcelProperty("包装其他约定")private String ywlPacking;
	@ExcelProperty("交货方式")private String ywlMode;
	@ExcelProperty("验收其他约定")private String ywlAcceptance;
	@ExcelProperty("付款方式")private String ywlPaymentMethod;
	@ExcelProperty("相对方开户行名称")private String ywlNameBank;
	@ExcelProperty("相对方账号")private String ywlAccountNumber;
	@ExcelProperty("年度未按约定交货次数(可解约)")private String ywlDeliveryTimes;
	@ExcelProperty("其他违约行为违约金比例")private String ywlDamages;
	@ExcelProperty("违约责任其他约定")private String ywlBreachOfContract;

	@ExcelProperty("押金有无")private String isNotBond;
	@ExcelProperty("押金金额")private String planPayAmount;
	@ExcelProperty("缴交时间")private String planPayTime;
	@ExcelProperty("退回时间")private String planReturnTime;
	//@ExcelProperty("链接对应押金情况 ")private String aa;



	private String ywlFullName;
	private String ywlNameOfOpposite;
	private String ywlSerialNumber;
	private String ywlItemNo;
	private String ywlProductName;
	private String ywlSpecifications;
	private String ywlCompany;
	private String ywlPrice;
	private String ywlRemarks;










}
