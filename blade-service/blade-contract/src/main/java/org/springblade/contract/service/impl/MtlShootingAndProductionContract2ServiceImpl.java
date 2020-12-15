package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlShootingAndProductionContract1Entity;
import org.springblade.contract.vo.MtlShootingAndProductionContract1ResponseVO;
import org.springblade.contract.vo.MtlShootingAndProductionContract2RequestVO;
import org.springblade.contract.vo.MtlShootingAndProductionContract2ResponseVO;
import org.springblade.contract.wrapper.MtlShootingAndProductionContract1Wrapper;
import org.springblade.contract.wrapper.MtlShootingAndProductionContract2Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.MtlShootingAndProductionContract2Entity;
import org.springblade.contract.mapper.MtlShootingAndProductionContract2Mapper;
import org.springblade.contract.service.IMtlShootingAndProductionContract2Service;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体类：视频广告拍摄制作合同关联表2 服务实现类
 *
 * @author 媒体类：视频广告拍摄制作合同关联表2
 * @date : 2020-12-11 05:31:04
 */
@Service
public class MtlShootingAndProductionContract2ServiceImpl extends BaseServiceImpl<MtlShootingAndProductionContract2Mapper, MtlShootingAndProductionContract2Entity> implements IMtlShootingAndProductionContract2Service, ITableRef<MtlShootingAndProductionContract2Entity> {

	/**
	 * 合同id字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<MtlShootingAndProductionContract2Entity> pageList(IPage<MtlShootingAndProductionContract2Entity> page, MtlShootingAndProductionContract2RequestVO mtlShootingAndProductionContract2) {
		return baseMapper.pageList(page, mtlShootingAndProductionContract2);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<MtlShootingAndProductionContract2ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, MtlShootingAndProductionContract2Wrapper.build().PVEntityList(responseVOList),this);

	}

	@Override
	public List<MtlShootingAndProductionContract2ResponseVO> selectRefList(Long refId) {
		List<MtlShootingAndProductionContract2Entity> casesAgentEntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return MtlShootingAndProductionContract2Wrapper.build().entityPVList(casesAgentEntityList);
	}

	@Override
	public void setRefId(Long refId, MtlShootingAndProductionContract2Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
