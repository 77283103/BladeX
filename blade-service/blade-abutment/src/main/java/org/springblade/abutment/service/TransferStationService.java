package org.springblade.abutment.service;

import org.springblade.contract.dto.middleground.Contract;
import org.springblade.core.tool.api.R;

public interface TransferStationService {

	/**
	 * 推送合同数据至中台
	 * @param contract
	 * @return
	 */
	R pushContractData(Contract contract);

	/**
	 * 获取中台令牌
	 * @return
	 */
	String getToken();


}
