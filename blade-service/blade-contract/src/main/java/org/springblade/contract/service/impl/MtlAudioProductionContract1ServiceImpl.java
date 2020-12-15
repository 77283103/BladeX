package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlAudioProductionContract1Entity;
import org.springblade.contract.mapper.MtlAudioProductionContract1Mapper;
import org.springblade.contract.service.IMtlAudioProductionContract1Service;
import org.springblade.contract.vo.MtlAudioProductionContract1RequestVO;
import org.springblade.contract.vo.MtlAudioProductionContract1ResponseVO;
import org.springblade.contract.wrapper.MtlAudioProductionContract1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体类：音频制作合同关联表 服务实现类
 *
 * @author 媒体类：音频制作合同关联表
 * @date : 2020-12-11 03:30:25
 */
@Service
public class MtlAudioProductionContract1ServiceImpl extends BaseServiceImpl<MtlAudioProductionContract1Mapper, MtlAudioProductionContract1Entity> implements IMtlAudioProductionContract1Service, ITableRef<MtlAudioProductionContract1Entity> {
	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<MtlAudioProductionContract1Entity> pageList(IPage<MtlAudioProductionContract1Entity> page, MtlAudioProductionContract1RequestVO mtlAudioProductionContract1) {
		return baseMapper.pageList(page, mtlAudioProductionContract1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<MtlAudioProductionContract1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, MtlAudioProductionContract1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<MtlAudioProductionContract1ResponseVO> selectRefList(Long refId) {
		List<MtlAudioProductionContract1Entity> MtlAudioProductionContract1EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return MtlAudioProductionContract1Wrapper.build().entityPVList(MtlAudioProductionContract1EntityList);
	}

	@Override
	public void setRefId(Long refId, MtlAudioProductionContract1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
