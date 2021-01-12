package org.springblade.contract.wrapper;

import org.springblade.contract.entity.CglProofingContractEntity;
import org.springblade.contract.vo.CglProofingContractRequestVO;
import org.springblade.contract.vo.CglProofingContractResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 采购类_打样合同书 包装类,返回视图层所需的字段
 *
 * @author 采购类_打样合同书
 * @date : 2021-01-12 13:24:36
 */
@Component
public class CglProofingContractWrapper implements IEntityWrapper<CglProofingContractEntity, CglProofingContractRequestVO, CglProofingContractResponseVO> {

	public static CglProofingContractWrapper build() {
		return new CglProofingContractWrapper();
 	}

    @Override
	public CglProofingContractEntity createEntity() {
		return new CglProofingContractEntity();
	}

	@Override
	public CglProofingContractRequestVO createQV() {
		return new CglProofingContractRequestVO();
	}

	@Override
	public CglProofingContractResponseVO createPV() {
		return new CglProofingContractResponseVO();
	}

	@Override
	public void selectUserName(CglProofingContractResponseVO responseVO) {

	}
}
