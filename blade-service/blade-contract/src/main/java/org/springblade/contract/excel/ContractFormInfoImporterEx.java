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

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
 *原物料一对多
 */
@Data
public class ContractFormInfoImporterEx implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("申请用章全称")private String ywlFullName;
	@ExcelProperty("相对方全称")private String ywlNameOfOpposite;
	@ExcelProperty("序号")private String ywlSerialNumber;
	@ExcelProperty("料号")private String ywlItemNo;
	@ExcelProperty("品名")private String ywlProductName;
	@ExcelProperty("规格")private String ywlSpecifications;
	@ExcelProperty("单位")private String ywlCompany;
	@ExcelProperty("未税单价（元/单位)")private String ywlPrice;
	@ExcelProperty("备注")private String ywlRemarks;










}
