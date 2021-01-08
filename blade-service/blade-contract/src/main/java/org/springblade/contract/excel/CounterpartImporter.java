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

import lombok.RequiredArgsConstructor;
import org.springblade.contract.service.IContractCounterpartService;
import org.springblade.core.excel.support.ExcelImporter;

import java.util.List;

/**
 * 相对方数据导入类
 *
 * @author Chill
 */
@RequiredArgsConstructor
public class CounterpartImporter implements ExcelImporter<ContractCounterpartExcel> {

	private final IContractCounterpartService service;
	private final Boolean isCovered;

	@Override
	public void save(List<ContractCounterpartExcel> data) {
		service.importCounterpart(data, isCovered);
	}
}
