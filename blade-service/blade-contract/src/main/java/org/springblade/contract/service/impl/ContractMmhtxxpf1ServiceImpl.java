package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglTheSalesContract1Entity;
import org.springblade.contract.entity.ContractMmhtxxpf1Entity;
import org.springblade.contract.mapper.ContractMmhtxxpf1Mapper;
import org.springblade.contract.service.IContractMmhtxxpf1Service;
import org.springblade.contract.vo.CglTheSalesContract1ResponseVO;
import org.springblade.contract.vo.ContractMmhtxxpf1ResponseVO;
import org.springblade.contract.wrapper.CglTheSalesContract1Wrapper;
import org.springblade.contract.wrapper.ContractMmhtxxpf1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行销品买卖合同子表 服务实现类
 *
 * @author kx
 * @date : 2021-05-10 13:37:00
 */
@Service
public class ContractMmhtxxpf1ServiceImpl extends BaseServiceImpl<ContractMmhtxxpf1Mapper, ContractMmhtxxpf1Entity> implements IContractMmhtxxpf1Service, ITableRef<ContractMmhtxxpf1Entity> {
	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<ContractMmhtxxpf1Entity> pageList(IPage<ContractMmhtxxpf1Entity> page, ContractMmhtxxpf1Entity contractMmhtxxpf1) {
		return baseMapper.pageList(page, contractMmhtxxpf1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<ContractMmhtxxpf1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, ContractMmhtxxpf1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<ContractMmhtxxpf1ResponseVO> selectRefList(Long refId) {
		List<ContractMmhtxxpf1Entity> casesAgentEntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return ContractMmhtxxpf1Wrapper.build().entityPVList(casesAgentEntityList);
	}
	@Override
	public void setRefId(Long refId, ContractMmhtxxpf1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
