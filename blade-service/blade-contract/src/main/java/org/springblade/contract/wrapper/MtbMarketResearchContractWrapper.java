package org.springblade.contract.wrapper;

import org.springblade.contract.entity.MtbMarketResearchContractEntity;
import org.springblade.contract.vo.MtbMarketResearchContractRequestVO;
import org.springblade.contract.vo.MtbMarketResearchContractResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 媒体类：市调合同（定性+定量) 包装类,返回视图层所需的字段
 *
 * @author 王策
 * @date : 2020-12-10 19:37:20
 */
@Component
public class MtbMarketResearchContractWrapper implements IEntityWrapper<MtbMarketResearchContractEntity, MtbMarketResearchContractRequestVO, MtbMarketResearchContractResponseVO> {

	public static MtbMarketResearchContractWrapper build() {
		return new MtbMarketResearchContractWrapper();
 	}

    @Override
	public MtbMarketResearchContractEntity createEntity() {
		return new MtbMarketResearchContractEntity();
	}

	@Override
	public MtbMarketResearchContractRequestVO createQV() {
		return new MtbMarketResearchContractRequestVO();
	}

	@Override
	public MtbMarketResearchContractResponseVO createPV() {
		return new MtbMarketResearchContractResponseVO();
	}

    @Override
    public void selectUserName(MtbMarketResearchContractResponseVO responseVO) {
	}
}
