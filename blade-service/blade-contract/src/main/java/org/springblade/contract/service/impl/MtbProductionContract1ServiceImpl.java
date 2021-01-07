package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtbProductionContract1Entity;
import org.springblade.contract.mapper.MtbProductionContract1Mapper;
import org.springblade.contract.service.IMtbProductionContract1Service;
import org.springblade.contract.vo.MtbProductionContract1RequestVO;
import org.springblade.contract.vo.MtbProductionContract1ResponseVO;
import org.springblade.contract.wrapper.MtbProductionContract1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体类：平面广告拍摄制作合同（关联表1） 服务实现类
 *
 * @author 张文武
 * @date : 2021-01-04 11:27:47
 */
@Service
public class MtbProductionContract1ServiceImpl extends BaseServiceImpl<MtbProductionContract1Mapper, MtbProductionContract1Entity> implements IMtbProductionContract1Service, ITableRef<MtbProductionContract1Entity> {
	/**
	 * 合同id字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<MtbProductionContract1Entity> pageList(IPage<MtbProductionContract1Entity> page, MtbProductionContract1RequestVO mtbProductionContract1) {
		return baseMapper.pageList(page, mtbProductionContract1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<MtbProductionContract1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, MtbProductionContract1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<MtbProductionContract1ResponseVO> selectRefList(Long refId) {
		List<MtbProductionContract1Entity> mtbProductionContract1EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return MtbProductionContract1Wrapper.build().entityPVList(mtbProductionContract1EntityList);
	}

	@Override
	public void setRefId(Long refId, MtbProductionContract1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
