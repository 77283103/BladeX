package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclConstructionProject1Entity;
import org.springblade.contract.mapper.SclConstructionProject1Mapper;
import org.springblade.contract.service.ISclConstructionProject1Service;
import org.springblade.contract.vo.SclConstructionProject1RequestVO;
import org.springblade.contract.vo.SclConstructionProject1ResponseVO;
import org.springblade.contract.wrapper.SclConstructionProject1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生产类：加工承揽合同（代工合同）关联表 服务实现类
 *
 * @author 生产类：加工承揽合同（代工合同）关联表
 * @date : 2020-12-11 10:10:14
 */
@Service
public class SclConstructionProject1ServiceImpl extends BaseServiceImpl<SclConstructionProject1Mapper, SclConstructionProject1Entity> implements ISclConstructionProject1Service, ITableRef<SclConstructionProject1Entity> {
	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<SclConstructionProject1Entity> pageList(IPage<SclConstructionProject1Entity> page, SclConstructionProject1RequestVO sclConstructionProject1) {
		return baseMapper.pageList(page, sclConstructionProject1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<SclConstructionProject1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, SclConstructionProject1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<SclConstructionProject1ResponseVO> selectRefList(Long refId) {
		List<SclConstructionProject1Entity> SclConstructionProject1EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return SclConstructionProject1Wrapper.build().entityPVList(SclConstructionProject1EntityList);
	}

	@Override
	public void setRefId(Long refId, SclConstructionProject1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
