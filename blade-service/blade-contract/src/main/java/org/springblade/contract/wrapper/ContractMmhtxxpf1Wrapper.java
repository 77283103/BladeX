package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ContractMmhtxxpf1Entity;
import org.springblade.contract.vo.ContractMmhtxxpf1RequestVO;
import org.springblade.contract.vo.ContractMmhtxxpf1ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 行销品买卖合同子表 包装类,返回视图层所需的字段
 *
 * @author kx
 * @date : 2021-05-10 13:37:01
 */
@Component
public class ContractMmhtxxpf1Wrapper implements IEntityWrapper<ContractMmhtxxpf1Entity, ContractMmhtxxpf1RequestVO, ContractMmhtxxpf1ResponseVO> {

	public static ContractMmhtxxpf1Wrapper build() {
		return new ContractMmhtxxpf1Wrapper();
 	}

    @Override
	public ContractMmhtxxpf1Entity createEntity() {
		return new ContractMmhtxxpf1Entity();
	}

	@Override
	public ContractMmhtxxpf1RequestVO createQV() {
		return new ContractMmhtxxpf1RequestVO();
	}

	@Override
	public ContractMmhtxxpf1ResponseVO createPV() {
		return new ContractMmhtxxpf1ResponseVO();
	}

	@Override
	public void selectUserName(ContractMmhtxxpf1ResponseVO responseVO) {

	}
}
