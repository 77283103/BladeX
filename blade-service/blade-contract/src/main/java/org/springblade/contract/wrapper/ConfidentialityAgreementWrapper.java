package org.springblade.contract.wrapper;

import org.springblade.contract.vo.ConfidentialityAgreementRequestVO;
import org.springblade.contract.vo.ConfidentialityAgreementResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.ConfidentialityAgreementEntity;
import org.springframework.stereotype.Component;


/**
 * 梁艳-保密协议（三方） 包装类,返回视图层所需的字段
 *
 * @author 王策
 * @date : 2021-01-15 15:36:28
 */
@Component
public class ConfidentialityAgreementWrapper implements IEntityWrapper<ConfidentialityAgreementEntity, ConfidentialityAgreementRequestVO, ConfidentialityAgreementResponseVO> {

	public static ConfidentialityAgreementWrapper build() {
		return new ConfidentialityAgreementWrapper();
 	}

    @Override
	public ConfidentialityAgreementEntity createEntity() {
		return new ConfidentialityAgreementEntity();
	}

	@Override
	public ConfidentialityAgreementRequestVO createQV() {
		return new ConfidentialityAgreementRequestVO();
	}

	@Override
	public ConfidentialityAgreementResponseVO createPV() {
		return new ConfidentialityAgreementResponseVO();
	}

	@Override
	public void selectUserName(ConfidentialityAgreementResponseVO responseVO) {

	}
}
