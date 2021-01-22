package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtbMarketResearchContract1Entity;
import org.springblade.contract.mapper.MtbMarketResearchContract1Mapper;
import org.springblade.contract.service.IMtbMarketResearchContract1Service;
import org.springblade.contract.vo.MtbMarketResearchContract1ResponseVO;
import org.springblade.contract.wrapper.MtbMarketResearchContract1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 市调合同 服务实现类
 *
 * @author 刘是罕
 * @date : 2021-01-21 11:07:50
 */
@Service
public class MtbMarketResearchContract1ServiceImpl extends BaseServiceImpl<MtbMarketResearchContract1Mapper, MtbMarketResearchContract1Entity> implements IMtbMarketResearchContract1Service, ITableRef<MtbMarketResearchContract1Entity> {
	/**
	 * 合同id字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<MtbMarketResearchContract1Entity> pageList(IPage<MtbMarketResearchContract1Entity> page, MtbMarketResearchContract1Entity mtbMarketResearchContract1) {
		return baseMapper.pageList(page, mtbMarketResearchContract1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<MtbMarketResearchContract1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, MtbMarketResearchContract1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<MtbMarketResearchContract1ResponseVO> selectRefList(Long refId) {
		List<MtbMarketResearchContract1Entity> mtbMarketResearchContract1EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return MtbMarketResearchContract1Wrapper.build().entityPVList(mtbMarketResearchContract1EntityList);
	}

	@Override
	public void setRefId(Long refId, MtbMarketResearchContract1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
