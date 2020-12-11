package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclConstructionProject2Entity;
import org.springblade.contract.mapper.SclConstructionProject2Mapper;
import org.springblade.contract.service.ISclConstructionProject2Service;
import org.springblade.contract.vo.SclConstructionProject2RequestVO;
import org.springblade.contract.vo.SclConstructionProject2ResponseVO;
import org.springblade.contract.wrapper.SclConstructionProject2Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生产类：加工承揽合同（代工合同）关联表2 服务实现类
 *
 * @author 生产类：加工承揽合同（代工合同）关联表2
 * @date : 2020-12-11 10:22:09
 */
@Service
public class SclConstructionProject2ServiceImpl extends BaseServiceImpl<SclConstructionProject2Mapper, SclConstructionProject2Entity> implements ISclConstructionProject2Service, ITableRef<SclConstructionProject2Entity> {
	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<SclConstructionProject2Entity> pageList(IPage<SclConstructionProject2Entity> page, SclConstructionProject2RequestVO sclConstructionProject2) {
		return baseMapper.pageList(page, sclConstructionProject2);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<SclConstructionProject2ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, SclConstructionProject2Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<SclConstructionProject2ResponseVO> selectRefList(Long refId) {
		List<SclConstructionProject2Entity> SclConstructionProject2EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return SclConstructionProject2Wrapper.build().entityPVList(SclConstructionProject2EntityList);
	}

	@Override
	public void setRefId(Long refId, SclConstructionProject2Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
