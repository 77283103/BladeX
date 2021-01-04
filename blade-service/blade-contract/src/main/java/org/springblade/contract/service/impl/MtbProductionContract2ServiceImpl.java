package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtbProductionContract2Entity;
import org.springblade.contract.mapper.MtbProductionContract2Mapper;
import org.springblade.contract.service.IMtbProductionContract2Service;
import org.springblade.contract.vo.MtbProductionContract2RequestVO;
import org.springblade.contract.vo.MtbProductionContract2ResponseVO;
import org.springblade.contract.wrapper.MtbProductionContract2Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体类：平面广告拍摄制作合同（关联表2） 服务实现类
 *
 * @author 张文武
 * @date : 2021-01-04 11:24:01
 */
@Service
public class MtbProductionContract2ServiceImpl extends BaseServiceImpl<MtbProductionContract2Mapper, MtbProductionContract2Entity> implements IMtbProductionContract2Service, ITableRef<MtbProductionContract2Entity> {
	/**
	 * 合同id字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<MtbProductionContract2Entity> pageList(IPage<MtbProductionContract2Entity> page, MtbProductionContract2RequestVO mtbProductionContract2) {
		return baseMapper.pageList(page, mtbProductionContract2);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<MtbProductionContract2ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, MtbProductionContract2Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<MtbProductionContract2ResponseVO> selectRefList(Long refId) {
		List<MtbProductionContract2Entity> mtbProductionContract2EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return MtbProductionContract2Wrapper.build().entityPVList(mtbProductionContract2EntityList);
	}

	@Override
	public void setRefId(Long refId, MtbProductionContract2Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
