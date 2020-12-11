package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlVideoProductionContract1Entity;
import org.springblade.contract.mapper.MtlVideoProductionContract1Mapper;
import org.springblade.contract.service.IMtlVideoProductionContract1Service;
import org.springblade.contract.vo.MtlVideoProductionContract1RequestVO;
import org.springblade.contract.vo.MtlVideoProductionContract1ResponseVO;
import org.springblade.contract.wrapper.MtlVideoProductionContract1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体类：视频制作合同关联表 服务实现类
 *
 * @author 媒体类：视频制作合同关联表
 * @date : 2020-12-11 08:47:27
 */
@Service
public class MtlVideoProductionContract1ServiceImpl extends BaseServiceImpl<MtlVideoProductionContract1Mapper, MtlVideoProductionContract1Entity> implements IMtlVideoProductionContract1Service , ITableRef<MtlVideoProductionContract1Entity> {
	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<MtlVideoProductionContract1Entity> pageList(IPage<MtlVideoProductionContract1Entity> page, MtlVideoProductionContract1RequestVO mtlVideoProductionContract1) {
		return baseMapper.pageList(page, mtlVideoProductionContract1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<MtlVideoProductionContract1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, MtlVideoProductionContract1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<MtlVideoProductionContract1ResponseVO> selectRefList(Long refId) {
		List<MtlVideoProductionContract1Entity> MtlVideoProductionContract1EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return MtlVideoProductionContract1Wrapper.build().entityPVList(MtlVideoProductionContract1EntityList);
	}

	@Override
	public void setRefId(Long refId, MtlVideoProductionContract1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
