package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglProofingContract1Entity;
import org.springblade.contract.mapper.CglProofingContract1Mapper;
import org.springblade.contract.service.ICglProofingContract1Service;
import org.springblade.contract.vo.CglProofingContract1ResponseVO;
import org.springblade.contract.wrapper.CglProofingContract1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * cgl_proofing_contract1 服务实现类
 *
 * @author cglProofingContract1
 * @date : 2021-01-12 13:48:14
 */
@Service
public class CglProofingContract1ServiceImpl extends BaseServiceImpl<CglProofingContract1Mapper, CglProofingContract1Entity> implements ICglProofingContract1Service, ITableRef<CglProofingContract1Entity> {

	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<CglProofingContract1Entity> pageList(IPage<CglProofingContract1Entity> page, CglProofingContract1Entity cglProofingContract1) {
		return baseMapper.pageList(page, cglProofingContract1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<CglProofingContract1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, CglProofingContract1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<CglProofingContract1ResponseVO> selectRefList(Long refId) {
		List<CglProofingContract1Entity> cglProofingContract1EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return CglProofingContract1Wrapper.build().entityPVList(cglProofingContract1EntityList);
	}

	@Override
	public void setRefId(Long refId, CglProofingContract1Entity entity) {
		entity.setId(null);
		entity.setRefContractId(refId);
	}
}
