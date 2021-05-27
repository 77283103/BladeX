package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractWbht1Entity;
import org.springblade.contract.mapper.ContractWbht1Mapper;
import org.springblade.contract.vo.ContractWbht1ResponseVO;
import org.springblade.contract.wrapper.ContractWbht1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消防-维保合同子表 服务实现类
 *
 * @author kx
 * @date : 2021-05-10 13:40:21
 */
@Service
public class ContractWbht1ServiceImpl extends BaseServiceImpl<ContractWbht1Mapper, ContractWbht1Entity> implements IContractWbht1Service, ITableRef<ContractWbht1Entity> {
	/**
	 * 合同id字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<ContractWbht1Entity> pageList(IPage<ContractWbht1Entity> page, ContractWbht1Entity contractWbht1) {
		return baseMapper.pageList(page, contractWbht1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<ContractWbht1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, ContractWbht1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<ContractWbht1ResponseVO> selectRefList(Long refId) {
		List<ContractWbht1Entity> contractWbht1EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return ContractWbht1Wrapper.build().entityPVList(contractWbht1EntityList);
	}

	@Override
	public void setRefId(Long refId, ContractWbht1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
