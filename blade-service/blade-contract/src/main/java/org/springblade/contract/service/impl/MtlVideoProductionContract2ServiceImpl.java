package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlVideoProductionContract2Entity;
import org.springblade.contract.mapper.MtlVideoProductionContract2Mapper;
import org.springblade.contract.service.IMtlVideoProductionContract2Service;
import org.springblade.contract.vo.MtlVideoProductionContract2RequestVO;
import org.springblade.contract.vo.MtlVideoProductionContract2ResponseVO;
import org.springblade.contract.wrapper.MtlVideoProductionContract2Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体类：视频制作合同关联表2 服务实现类
 *
 * @author 媒体类：视频制作合同关联表2
 * @date : 2020-12-11 08:48:37
 */
@Service
public class MtlVideoProductionContract2ServiceImpl extends BaseServiceImpl<MtlVideoProductionContract2Mapper, MtlVideoProductionContract2Entity> implements IMtlVideoProductionContract2Service, ITableRef<MtlVideoProductionContract2Entity> {
	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<MtlVideoProductionContract2Entity> pageList(IPage<MtlVideoProductionContract2Entity> page, MtlVideoProductionContract2RequestVO mtlVideoProductionContract2) {
		return baseMapper.pageList(page, mtlVideoProductionContract2);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<MtlVideoProductionContract2ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, MtlVideoProductionContract2Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<MtlVideoProductionContract2ResponseVO> selectRefList(Long refId) {
		List<MtlVideoProductionContract2Entity> MtlVideoProductionContract2EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return MtlVideoProductionContract2Wrapper.build().entityPVList(MtlVideoProductionContract2EntityList);
	}

	@Override
	public void setRefId(Long refId, MtlVideoProductionContract2Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
