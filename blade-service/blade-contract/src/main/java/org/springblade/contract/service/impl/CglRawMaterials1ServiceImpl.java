package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglTheSalesContract1Entity;
import org.springblade.contract.vo.CglRawMaterials1RequestVO;
import org.springblade.contract.vo.CglRawMaterials1ResponseVO;
import org.springblade.contract.vo.CglTheSalesContract1ResponseVO;
import org.springblade.contract.wrapper.CglRawMaterials1Wrapper;
import org.springblade.contract.wrapper.CglTheSalesContract1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.CglRawMaterials1Entity;
import org.springblade.contract.mapper.CglRawMaterials1Mapper;
import org.springblade.contract.service.ICglRawMaterials1Service;
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
public class CglRawMaterials1ServiceImpl extends BaseServiceImpl<CglRawMaterials1Mapper, CglRawMaterials1Entity> implements ICglRawMaterials1Service, ITableRef<CglRawMaterials1Entity> {

	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<CglRawMaterials1Entity> pageList(IPage<CglRawMaterials1Entity> page, CglRawMaterials1RequestVO cglRawMaterials1) {
		return baseMapper.pageList(page, cglRawMaterials1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<CglRawMaterials1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, CglRawMaterials1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<CglRawMaterials1ResponseVO> selectRefList(Long refId) {
		List<CglRawMaterials1Entity> casesAgentEntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return CglRawMaterials1Wrapper.build().entityPVList(casesAgentEntityList);
	}

	@Override
	public void setRefId(Long refId, CglRawMaterials1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
