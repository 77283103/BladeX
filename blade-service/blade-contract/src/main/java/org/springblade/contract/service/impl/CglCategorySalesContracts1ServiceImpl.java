package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglCategorySalesContracts1RequestVO;
import org.springblade.contract.vo.CglCategorySalesContracts1ResponseVO;
import org.springblade.contract.wrapper.CglCategorySalesContracts1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.CglCategorySalesContracts1Entity;
import org.springblade.contract.mapper.CglCategorySalesContracts1Mapper;
import org.springblade.contract.service.ICglCategorySalesContracts1Service;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 采购类：买卖合同（行销品） 服务实现类
 *
 * @author 采购类：买卖合同（行销品）
 * @date : 2020-12-10 18:58:22
 */
@Service
public class CglCategorySalesContracts1ServiceImpl extends BaseServiceImpl<CglCategorySalesContracts1Mapper, CglCategorySalesContracts1Entity> implements ICglCategorySalesContracts1Service, ITableRef<CglCategorySalesContracts1Entity> {

	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<CglCategorySalesContracts1Entity> pageList(IPage<CglCategorySalesContracts1Entity> page, CglCategorySalesContracts1RequestVO cglCategorySalesContracts1) {
		return baseMapper.pageList(page, cglCategorySalesContracts1);
	}
	@Override
	public void saveBatchByRefId(Long refId, List<CglCategorySalesContracts1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, CglCategorySalesContracts1Wrapper.build().PVEntityList(responseVOList),this);
	}
	@Override
	public List<CglCategorySalesContracts1ResponseVO> selectRefList(Long refId) {
		List<CglCategorySalesContracts1Entity> casesAgentEntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return CglCategorySalesContracts1Wrapper.build().entityPVList(casesAgentEntityList);
	}

	@Override
	public void setRefId(Long refId, CglCategorySalesContracts1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
