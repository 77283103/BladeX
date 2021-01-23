package org.springblade.contract.wrapper;

import org.springblade.contract.entity.MtbMarketResearchContract1Entity;
import org.springblade.contract.vo.MtbMarketResearchContract1RequestVO;
import org.springblade.contract.vo.MtbMarketResearchContract1ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 市调合同 包装类,返回视图层所需的字段
 *
 * @author 刘是罕
 * @date : 2021-01-21 11:07:50
 */
@Component
public class MtbMarketResearchContract1Wrapper implements IEntityWrapper<MtbMarketResearchContract1Entity, MtbMarketResearchContract1RequestVO, MtbMarketResearchContract1ResponseVO> {

	public static MtbMarketResearchContract1Wrapper build() {
		return new MtbMarketResearchContract1Wrapper();
 	}

    @Override
	public MtbMarketResearchContract1Entity createEntity() {
		return new MtbMarketResearchContract1Entity();
	}

	@Override
	public MtbMarketResearchContract1RequestVO createQV() {
		return new MtbMarketResearchContract1RequestVO();
	}

	@Override
	public MtbMarketResearchContract1ResponseVO createPV() {
		return new MtbMarketResearchContract1ResponseVO();
	}

	@Override
	public void selectUserName(MtbMarketResearchContract1ResponseVO responseVO) {

	}
}
