package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtbMarketResearchContract1Entity;

/**
 * 市调合同 Mapper 接口
 *
 * @author 刘是罕
 * @date : 2021-01-21 11:07:49
 */
public interface MtbMarketResearchContract1Mapper extends BaseMapper<MtbMarketResearchContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtbMarketResearchContract1
	 * @return
	 */
	IPage<MtbMarketResearchContract1Entity> pageList(IPage<MtbMarketResearchContract1Entity> page, MtbMarketResearchContract1Entity mtbMarketResearchContract1);

}
