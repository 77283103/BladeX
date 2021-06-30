package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ContractWbhtEntity;
import org.springblade.contract.vo.ContractWbhtRequestVO;
import org.springblade.contract.vo.ContractWbhtResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 消防-维保合同 包装类,返回视图层所需的字段
 *
 * @author kx
 * @date : 2021-05-10 13:41:09
 */
@Component
public class ContractWbhtWrapper implements IEntityWrapper<ContractWbhtEntity, ContractWbhtRequestVO, ContractWbhtResponseVO> {

	public static ContractWbhtWrapper build() {
		return new ContractWbhtWrapper();
 	}

    @Override
	public ContractWbhtEntity createEntity() {
		return new ContractWbhtEntity();
	}

	@Override
	public ContractWbhtRequestVO createQV() {
		return new ContractWbhtRequestVO();
	}

	@Override
	public ContractWbhtResponseVO createPV() {
		return new ContractWbhtResponseVO();
	}

	@Override
	public void selectUserName(ContractWbhtResponseVO responseVO) {

	}
}
