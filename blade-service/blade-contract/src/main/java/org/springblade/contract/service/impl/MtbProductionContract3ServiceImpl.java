package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtbProductionContract2Entity;
import org.springblade.contract.vo.MtbProductionContract2ResponseVO;
import org.springblade.contract.vo.MtbProductionContract3ResponseVO;
import org.springblade.contract.wrapper.MtbProductionContract2Wrapper;
import org.springblade.contract.wrapper.MtbProductionContract3Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.MtbProductionContract3Entity;
import org.springblade.contract.mapper.MtbProductionContract3Mapper;
import org.springblade.contract.service.IMtbProductionContract3Service;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体类：平面广告拍摄制作合同（关联表3） 服务实现类
 *
 * @author 韩杨
 * @date : 2021-01-21 11:27:01
 */
@Service
public class MtbProductionContract3ServiceImpl extends BaseServiceImpl<MtbProductionContract3Mapper, MtbProductionContract3Entity> implements IMtbProductionContract3Service, ITableRef<MtbProductionContract3Entity> {
	/**
	 * 合同id字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<MtbProductionContract3Entity> pageList(IPage<MtbProductionContract3Entity> page, MtbProductionContract3Entity mtbProductionContract3) {
		return baseMapper.pageList(page, mtbProductionContract3);
	}
	@Override
	public void saveBatchByRefId(Long refId, List<MtbProductionContract3ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, MtbProductionContract3Wrapper.build().PVEntityList(responseVOList),this);
	}
	@Override
	public List<MtbProductionContract3ResponseVO> selectRefList(Long refId) {
		List<MtbProductionContract3Entity> mtbProductionContract3EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return MtbProductionContract3Wrapper.build().entityPVList(mtbProductionContract3EntityList);
	}
	@Override
	public void setRefId(Long refId, MtbProductionContract3Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
