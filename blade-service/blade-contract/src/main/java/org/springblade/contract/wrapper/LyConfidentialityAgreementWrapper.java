package org.springblade.contract.wrapper;

import org.springblade.contract.vo.LyConfidentialityAgreementRequestVO;
import org.springblade.contract.vo.LyConfidentialityAgreementResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.LyConfidentialityAgreementEntity;
import org.springframework.stereotype.Component;


/**
 * 梁艳-保密协议 包装类,返回视图层所需的字段
 *
 * @author wd
 * @date : 2021-01-15 14:57:39
 */
@Component
public class LyConfidentialityAgreementWrapper implements IEntityWrapper<LyConfidentialityAgreementEntity, LyConfidentialityAgreementRequestVO, LyConfidentialityAgreementResponseVO> {

	public static LyConfidentialityAgreementWrapper build() {
		return new LyConfidentialityAgreementWrapper();
 	}

    @Override
	public LyConfidentialityAgreementEntity createEntity() {
		return new LyConfidentialityAgreementEntity();
	}

	@Override
	public LyConfidentialityAgreementRequestVO createQV() {
		return new LyConfidentialityAgreementRequestVO();
	}

	@Override
	public LyConfidentialityAgreementResponseVO createPV() {
		return new LyConfidentialityAgreementResponseVO();
	}

	@Override
	public void selectUserName(LyConfidentialityAgreementResponseVO responseVO) {

	}
}
