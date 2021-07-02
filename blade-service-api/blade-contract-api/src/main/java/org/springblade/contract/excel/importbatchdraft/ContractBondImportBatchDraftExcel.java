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
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ContractBondImportBatchDraftExcel implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("合同标识号码（非重复数字）")
	private String contractNumber;

	@ExcelProperty("相对方统一社会信用代码")
	private String unifiedSocialCreditCode;

	@ExcelProperty("保证金类别")
	private String type;

	@ExcelProperty("计划缴纳时间")
	private Date planPayTime;

	@ExcelProperty("计划缴纳金额")
	private BigDecimal planPayAmount;

	@ExcelProperty("计划退回时间")
	private Date planReturnTime;

	@ExcelProperty("计划退回金额")
	private BigDecimal planReturnAmount;

	@ExcelProperty("退回条件")
	private String returnCondition;
}
