package org.springblade.contract.wrapper;

import org.springblade.contract.entity.DistServiceContractEntity;
import org.springblade.contract.vo.DistServiceContractResponseVO;
import org.springblade.contract.vo.DistributionServiceContractRequestVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 配送服务合同 包装类,返回视图层所需的字段
 *
 * @author 王策
 * @date : 2021-01-18 17:24:28
 */
@Component
public class DistributionServiceContractWrapper implements IEntityWrapper<DistServiceContractEntity, DistributionServiceContractRequestVO, DistServiceContractResponseVO> {

	public static DistributionServiceContractWrapper build() {
		return new DistributionServiceContractWrapper();
 	}

    @Override
	public DistServiceContractEntity createEntity() {
		return new DistServiceContractEntity();
	}

	@Override
	public DistributionServiceContractRequestVO createQV() {
		return new DistributionServiceContractRequestVO();
	}

	@Override
	public DistServiceContractResponseVO createPV() {
		return new DistServiceContractResponseVO();
	}

	@Override
	public void selectUserName(DistServiceContractResponseVO responseVO) {

	}
}
