package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglCategorySalesContracts1Entity;
import org.springblade.contract.vo.CglTheSalesContract1RequestVO;
import org.springblade.contract.vo.CglTheSalesContract1ResponseVO;
import org.springblade.contract.wrapper.CglCategorySalesContracts1Wrapper;
import org.springblade.contract.wrapper.CglTheSalesContract1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.CglTheSalesContract1Entity;
import org.springblade.contract.mapper.CglTheSalesContract1Mapper;
import org.springblade.contract.service.ICglTheSalesContract1Service;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 采购类：新增原物料补充协议--买卖合同 服务实现类
 *
 * @author 采购类：新增原物料补充协议--买卖合同
 * @date : 2020-12-10 18:50:22
 */
@Service
public class CglTheSalesContract1ServiceImpl extends BaseServiceImpl<CglTheSalesContract1Mapper, CglTheSalesContract1Entity> implements ICglTheSalesContract1Service, ITableRef<CglTheSalesContract1Entity> {

	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<CglTheSalesContract1Entity> pageList(IPage<CglTheSalesContract1Entity> page, CglTheSalesContract1RequestVO cglTheSalesContract1) {
		return baseMapper.pageList(page, cglTheSalesContract1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<CglTheSalesContract1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, CglTheSalesContract1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<CglTheSalesContract1ResponseVO> selectRefList(Long refId) {
		List<CglTheSalesContract1Entity> casesAgentEntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return CglTheSalesContract1Wrapper.build().entityPVList(casesAgentEntityList);
	}

	@Override
	public void setRefId(Long refId, CglTheSalesContract1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
