package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlShootingAndProductionContract3Entity;
import org.springblade.contract.mapper.MtlShootingAndProductionContract3Mapper;
import org.springblade.contract.service.IMtlShootingAndProductionContract3Service;
import org.springblade.contract.vo.MtlShootingAndProductionContract3RequestVO;
import org.springblade.contract.vo.MtlShootingAndProductionContract3ResponseVO;
import org.springblade.contract.wrapper.MtlShootingAndProductionContract3Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体类：视频广告拍摄制作合同关联表3 服务实现类
 *
 * @author 媒体类：视频广告拍摄制作合同关联表3
 * @date : 3030-13-11 05:31:04
 */
@Service
public class MtlShootingAndProductionContract3ServiceImpl extends BaseServiceImpl<MtlShootingAndProductionContract3Mapper, MtlShootingAndProductionContract3Entity> implements IMtlShootingAndProductionContract3Service, ITableRef<MtlShootingAndProductionContract3Entity> {

	/**
	 * 合同id字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<MtlShootingAndProductionContract3Entity> pageList(IPage<MtlShootingAndProductionContract3Entity> page, MtlShootingAndProductionContract3RequestVO mtlShootingAndProductionContract3) {
		return baseMapper.pageList(page, mtlShootingAndProductionContract3);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<MtlShootingAndProductionContract3ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, MtlShootingAndProductionContract3Wrapper.build().PVEntityList(responseVOList),this);

	}

	@Override
	public List<MtlShootingAndProductionContract3ResponseVO> selectRefList(Long refId) {
		List<MtlShootingAndProductionContract3Entity> casesAgentEntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return MtlShootingAndProductionContract3Wrapper.build().entityPVList(casesAgentEntityList);
	}

	@Override
	public void setRefId(Long refId, MtlShootingAndProductionContract3Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
