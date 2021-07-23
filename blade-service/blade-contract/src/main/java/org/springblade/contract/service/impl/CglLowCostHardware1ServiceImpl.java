package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglLowCostHardware1Entity;
import org.springblade.contract.mapper.CglLowCostHardware1Mapper;
import org.springblade.contract.service.ICglLowCostHardware1Service;
import org.springblade.contract.vo.CglLowCostHardware1RequestVO;
import org.springblade.contract.vo.CglLowCostHardware1ResponseVO;
import org.springblade.contract.wrapper.CglLowCostHardware1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 采购类：原物料-买卖合同 服务实现类
 *
 * @author 采购类：原物料-买卖合同
 * @date : 2020-12-10 18:54:35
 */
@Service
public class CglLowCostHardware1ServiceImpl extends BaseServiceImpl<CglLowCostHardware1Mapper, CglLowCostHardware1Entity> implements ICglLowCostHardware1Service, ITableRef<CglLowCostHardware1Entity> {

	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<CglLowCostHardware1Entity> pageList(IPage<CglLowCostHardware1Entity> page, CglLowCostHardware1RequestVO cglRawMaterials1) {
		return baseMapper.pageList(page, cglRawMaterials1);
	}

	/**
	 * 批量保存
	 * @param refId
	 * @param responseVOList
	 */
	@Override
	public void saveBatchByRefId(Long refId, List<CglLowCostHardware1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, CglLowCostHardware1Wrapper.build().PVEntityList(responseVOList),this);
	}

	/**
	 *
	 * @param refId
	 * @return
	 */
	@Override
	public List<CglLowCostHardware1ResponseVO> selectRefList(Long refId) {
		List<CglLowCostHardware1Entity> casesAgentEntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return CglLowCostHardware1Wrapper.build().entityPVList(casesAgentEntityList);
	}

	@Override
	public void setRefId(Long refId, CglLowCostHardware1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
