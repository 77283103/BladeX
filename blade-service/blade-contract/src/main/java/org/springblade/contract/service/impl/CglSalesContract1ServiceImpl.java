package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglSalesContract1Entity;
import org.springblade.contract.mapper.CglSalesContract1Mapper;
import org.springblade.contract.service.ICglSalesContract1Service;
import org.springblade.contract.vo.CglSalesContract1RequestVO;
import org.springblade.contract.vo.CglSalesContract1ResponseVO;
import org.springblade.contract.wrapper.CglSalesContract1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 采购类：买卖合同（国内设备购买）附表 服务实现类
 *
 * @author 王策
 * @date : 2020-12-18 16:12:25
 */
@Service
public class CglSalesContract1ServiceImpl extends BaseServiceImpl<CglSalesContract1Mapper, CglSalesContract1Entity> implements ICglSalesContract1Service, ITableRef<CglSalesContract1Entity> {
	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "ref_contract_id";
	@Override
	public IPage<CglSalesContract1Entity> pageList(IPage<CglSalesContract1Entity> page, CglSalesContract1RequestVO cglSalesContract1) {
		return baseMapper.pageList(page, cglSalesContract1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<CglSalesContract1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, CglSalesContract1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<CglSalesContract1ResponseVO> selectRefList(Long refId) {
		List<CglSalesContract1Entity> salesContract1EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return CglSalesContract1Wrapper.build().entityPVList(salesContract1EntityList);
	}

	@Override
	public void setRefId(Long refId, CglSalesContract1Entity entity) {
		entity.setId(null);
		entity.setRefContractId(refId);
	}
}
