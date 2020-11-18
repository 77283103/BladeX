package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.vo.ContractBondRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractBondEntity;
import org.springblade.contract.mapper.ContractBondMapper;
import org.springblade.contract.service.IContractBondService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 保证金 服务实现类
 *
 * @author szw
 * @date : 2020-11-04 18:28:11
 */
@Service
@AllArgsConstructor

public class ContractBondServiceImpl extends BaseServiceImpl<ContractBondMapper, ContractBondEntity> implements IContractBondService {

	@Override
	public IPage<ContractBondEntity> pageList(IPage<ContractBondEntity> page, ContractBondRequestVO contractBond) {
		page=baseMapper.pageList(page, contractBond);
		return page;
	}

	@Override
	public IPage<ContractBondEntity> pageListSerious(IPage<ContractBondEntity> page, ContractBondRequestVO contractBond) {
		page=baseMapper.pageListSerious(page, contractBond);
		return page;
	}

	@Override
	public IPage<ContractBondEntity> pageListLong(IPage<ContractBondEntity> page, ContractBondRequestVO contractBond) {
		page = baseMapper.pageListLong(page, contractBond);
		return page;
	}

	@Override
	public void saveBond(List<Long> ids, Long id) {
		baseMapper.deleteBond(id);
		baseMapper.saveBond(ids,id);
	}
}
