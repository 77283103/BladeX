package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclConstructionProject3Entity;
import org.springblade.contract.mapper.SclConstructionProject3Mapper;
import org.springblade.contract.service.ISclConstructionProject3Service;
import org.springblade.contract.vo.SclConstructionProject3RequestVO;
import org.springblade.contract.vo.SclConstructionProject3ResponseVO;
import org.springblade.contract.wrapper.SclConstructionProject3Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生产类：加工承揽合同（代工合同）关联表3 服务实现类
 *
 * @author 生产类：加工承揽合同（代工合同）关联表3
 * @date : 2020-12-11 10:36:43
 */
@Service
public class SclConstructionProject3ServiceImpl extends BaseServiceImpl<SclConstructionProject3Mapper, SclConstructionProject3Entity> implements ISclConstructionProject3Service, ITableRef<SclConstructionProject3Entity> {
	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<SclConstructionProject3Entity> pageList(IPage<SclConstructionProject3Entity> page, SclConstructionProject3RequestVO sclConstructionProject3) {
		return baseMapper.pageList(page, sclConstructionProject3);
	}


	@Override
	public void saveBatchByRefId(Long refId, List<SclConstructionProject3ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, SclConstructionProject3Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<SclConstructionProject3ResponseVO> selectRefList(Long refId) {
		List<SclConstructionProject3Entity> SclConstructionProject3EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return SclConstructionProject3Wrapper.build().entityPVList(SclConstructionProject3EntityList);
	}

	@Override
	public void setRefId(Long refId, SclConstructionProject3Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
