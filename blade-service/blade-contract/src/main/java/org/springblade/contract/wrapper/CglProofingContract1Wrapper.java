package org.springblade.contract.wrapper;

import org.springblade.contract.entity.CglProofingContract1Entity;
import org.springblade.contract.vo.CglProofingContract1RequestVO;
import org.springblade.contract.vo.CglProofingContract1ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * cgl_proofing_contract1 包装类,返回视图层所需的字段
 *
 * @author cglProofingContract1
 * @date : 2021-01-12 13:48:15
 */
@Component
public class CglProofingContract1Wrapper implements IEntityWrapper<CglProofingContract1Entity, CglProofingContract1RequestVO, CglProofingContract1ResponseVO> {

	public static CglProofingContract1Wrapper build() {
		return new CglProofingContract1Wrapper();
 	}

    @Override
	public CglProofingContract1Entity createEntity() {
		return new CglProofingContract1Entity();
	}

	@Override
	public CglProofingContract1RequestVO createQV() {
		return new CglProofingContract1RequestVO();
	}

	@Override
	public CglProofingContract1ResponseVO createPV() {
		return new CglProofingContract1ResponseVO();
	}

	@Override
	public void selectUserName(CglProofingContract1ResponseVO responseVO) {

	}
}
