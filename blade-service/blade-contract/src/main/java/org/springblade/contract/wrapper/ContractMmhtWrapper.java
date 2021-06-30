package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ContractMmhtEntity;
import org.springblade.contract.vo.ContractMmhtRequestVO;
import org.springblade.contract.vo.ContractMmhtResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 国内设备买卖合同 包装类,返回视图层所需的字段
 *
 * @author kx
 * @date : 2021-05-10 13:39:32
 */
@Component
public class ContractMmhtWrapper implements IEntityWrapper<ContractMmhtEntity, ContractMmhtRequestVO, ContractMmhtResponseVO> {

	public static ContractMmhtWrapper build() {
		return new ContractMmhtWrapper();
 	}

    @Override
	public ContractMmhtEntity createEntity() {
		return new ContractMmhtEntity();
	}

	@Override
	public ContractMmhtRequestVO createQV() {
		return new ContractMmhtRequestVO();
	}

	@Override
	public ContractMmhtResponseVO createPV() {
		return new ContractMmhtResponseVO();
	}

	@Override
	public void selectUserName(ContractMmhtResponseVO responseVO) {

	}
}
