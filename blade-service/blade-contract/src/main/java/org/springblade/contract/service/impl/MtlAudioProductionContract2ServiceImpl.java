package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlAudioProductionContract2Entity;
import org.springblade.contract.mapper.MtlAudioProductionContract2Mapper;
import org.springblade.contract.service.IMtlAudioProductionContract2Service;
import org.springblade.contract.vo.MtlAudioProductionContract2RequestVO;
import org.springblade.contract.vo.MtlAudioProductionContract2ResponseVO;
import org.springblade.contract.wrapper.MtlAudioProductionContract2Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体类：音频制作合同关联表2 服务实现类
 *
 * @author 媒体类：音频制作合同关联表2
 * @date : 2020-12-11 03:31:37
 */
@Service
public class MtlAudioProductionContract2ServiceImpl extends BaseServiceImpl<MtlAudioProductionContract2Mapper, MtlAudioProductionContract2Entity> implements IMtlAudioProductionContract2Service, ITableRef<MtlAudioProductionContract2Entity> {
	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<MtlAudioProductionContract2Entity> pageList(IPage<MtlAudioProductionContract2Entity> page, MtlAudioProductionContract2RequestVO mtlAudioProductionContract2) {
		return baseMapper.pageList(page, mtlAudioProductionContract2);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<MtlAudioProductionContract2ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, MtlAudioProductionContract2Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<MtlAudioProductionContract2ResponseVO> selectRefList(Long refId) {
		List<MtlAudioProductionContract2Entity> MtlAudioProductionContract2EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return MtlAudioProductionContract2Wrapper.build().entityPVList(MtlAudioProductionContract2EntityList);
	}

	@Override
	public void setRefId(Long refId, MtlAudioProductionContract2Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
