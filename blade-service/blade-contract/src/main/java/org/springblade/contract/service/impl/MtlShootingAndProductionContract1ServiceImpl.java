package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclEquipmentMaintenance1Entity;
import org.springblade.contract.vo.MtlShootingAndProductionContract1RequestVO;
import org.springblade.contract.vo.MtlShootingAndProductionContract1ResponseVO;
import org.springblade.contract.wrapper.MtlShootingAndProductionContract1Wrapper;
import org.springblade.contract.wrapper.SclEquipmentMaintenance1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.MtlShootingAndProductionContract1Entity;
import org.springblade.contract.mapper.MtlShootingAndProductionContract1Mapper;
import org.springblade.contract.service.IMtlShootingAndProductionContract1Service;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体类：视频广告拍摄制作合同关联表 服务实现类
 *
 * @author 媒体类：视频广告拍摄制作合同关联表
 * @date : 2020-12-11 05:30:04
 */
@Service
public class MtlShootingAndProductionContract1ServiceImpl extends BaseServiceImpl<MtlShootingAndProductionContract1Mapper, MtlShootingAndProductionContract1Entity> implements IMtlShootingAndProductionContract1Service, ITableRef<MtlShootingAndProductionContract1Entity> {

	/**
	 * 合同id字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<MtlShootingAndProductionContract1Entity> pageList(IPage<MtlShootingAndProductionContract1Entity> page, MtlShootingAndProductionContract1RequestVO mtlShootingAndProductionContract1) {
		return baseMapper.pageList(page, mtlShootingAndProductionContract1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<MtlShootingAndProductionContract1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, MtlShootingAndProductionContract1Wrapper.build().PVEntityList(responseVOList),this);

	}

	@Override
	public List<MtlShootingAndProductionContract1ResponseVO> selectRefList(Long refId) {
		List<MtlShootingAndProductionContract1Entity> casesAgentEntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return MtlShootingAndProductionContract1Wrapper.build().entityPVList(casesAgentEntityList);
	}

	@Override
	public void setRefId(Long refId, MtlShootingAndProductionContract1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
