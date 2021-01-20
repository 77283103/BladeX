package org.springblade.contract.wrapper;

import org.springblade.contract.entity.BusServiceContractEntity;
import org.springblade.contract.vo.BusServiceContractRequestVO;
import org.springblade.contract.vo.BusServiceContractResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 班车服务合同 包装类,返回视图层所需的字段
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:25:18
 */
@Component
public class BusServiceContractWrapper implements IEntityWrapper<BusServiceContractEntity, BusServiceContractRequestVO, BusServiceContractResponseVO> {

	public static BusServiceContractWrapper build() {
		return new BusServiceContractWrapper();
 	}

    @Override
	public BusServiceContractEntity createEntity() {
		return new BusServiceContractEntity();
	}

	@Override
	public BusServiceContractRequestVO createQV() {
		return new BusServiceContractRequestVO();
	}

	@Override
	public BusServiceContractResponseVO createPV() {
		return new BusServiceContractResponseVO();
	}

	@Override
	public void selectUserName(BusServiceContractResponseVO responseVO) {

	}
}
