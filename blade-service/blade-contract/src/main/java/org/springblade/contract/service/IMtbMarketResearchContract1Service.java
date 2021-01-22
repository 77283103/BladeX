package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtbMarketResearchContract1Entity;
import org.springblade.contract.vo.MtbMarketResearchContract1ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 市调合同 服务类
 *
 * @author 刘是罕
 * @date : 2021-01-21 11:07:50
 */
public interface IMtbMarketResearchContract1Service extends BaseService<MtbMarketResearchContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtbMarketResearchContract1
	 * @return
	 */
	IPage<MtbMarketResearchContract1Entity> pageList(IPage<MtbMarketResearchContract1Entity> page, MtbMarketResearchContract1Entity mtbMarketResearchContract1);
	void saveBatchByRefId(Long refId, List<MtbMarketResearchContract1ResponseVO> responseVOList);

	List<MtbMarketResearchContract1ResponseVO> selectRefList(Long refId);
}
