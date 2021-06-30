package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ContractMmhtxxpfEntity;
import org.springblade.contract.vo.ContractMmhtxxpfRequestVO;
import org.springblade.contract.vo.ContractMmhtxxpfResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 行销品买卖合同 包装类,返回视图层所需的字段
 *
 * @author kx
 * @date : 2021-05-10 13:37:45
 */
@Component
public class ContractMmhtxxpfWrapper implements IEntityWrapper<ContractMmhtxxpfEntity, ContractMmhtxxpfRequestVO, ContractMmhtxxpfResponseVO> {

	public static ContractMmhtxxpfWrapper build() {
		return new ContractMmhtxxpfWrapper();
 	}

    @Override
	public ContractMmhtxxpfEntity createEntity() {
		return new ContractMmhtxxpfEntity();
	}

	@Override
	public ContractMmhtxxpfRequestVO createQV() {
		return new ContractMmhtxxpfRequestVO();
	}

	@Override
	public ContractMmhtxxpfResponseVO createPV() {
		return new ContractMmhtxxpfResponseVO();
	}

	@Override
	public void selectUserName(ContractMmhtxxpfResponseVO responseVO) {

	}
}
