package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.BusServiceContract1Entity;
import org.springblade.contract.mapper.BusServiceContract1Mapper;
import org.springblade.contract.service.IBusServiceContract1Service;
import org.springblade.contract.vo.BusServiceContract1ResponseVO;
import org.springblade.contract.wrapper.BusServiceContract1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 班车服务合同子表1 服务实现类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:29:13
 */
@Service
public class BusServiceContract1ServiceImpl extends BaseServiceImpl<BusServiceContract1Mapper, BusServiceContract1Entity> implements IBusServiceContract1Service, ITableRef<BusServiceContract1Entity> {

	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<BusServiceContract1Entity> pageList(IPage<BusServiceContract1Entity> page, BusServiceContract1Entity busServiceContract1) {
		return baseMapper.pageList(page, busServiceContract1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<BusServiceContract1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, BusServiceContract1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<BusServiceContract1ResponseVO> selectRefList(Long refId) {
		List<BusServiceContract1Entity> casesAgentEntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return BusServiceContract1Wrapper.build().entityPVList(casesAgentEntityList);
	}

	@Override
	public void setRefId(Long refId, BusServiceContract1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
