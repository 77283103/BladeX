package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ContractWbht1Entity;
import org.springblade.contract.vo.ContractWbht1RequestVO;
import org.springblade.contract.vo.ContractWbht1ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 消防-维保合同子表 包装类,返回视图层所需的字段
 *
 * @author kx
 * @date : 2021-05-10 13:40:21
 */
@Component
public class ContractWbht1Wrapper implements IEntityWrapper<ContractWbht1Entity, ContractWbht1RequestVO, ContractWbht1ResponseVO> {

	public static ContractWbht1Wrapper build() {
		return new ContractWbht1Wrapper();
 	}

    @Override
	public ContractWbht1Entity createEntity() {
		return new ContractWbht1Entity();
	}

	@Override
	public ContractWbht1RequestVO createQV() {
		return new ContractWbht1RequestVO();
	}

	@Override
	public ContractWbht1ResponseVO createPV() {
		return new ContractWbht1ResponseVO();
	}

	@Override
	public void selectUserName(ContractWbht1ResponseVO responseVO) {

	}
}
