package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SealInfoEntity;
import org.springblade.contract.mapper.SealInfoMapper;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractSigningEntity;
import org.springblade.contract.mapper.ContractSigningMapper;
import org.springblade.contract.service.IContractSigningService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 合同签订表 服务实现类
 *
 * @author liyj
 * @date : 2020-09-23 19:27:05
 */
@Service
public class ContractSigningServiceImpl extends BaseServiceImpl<ContractSigningMapper, ContractSigningEntity> implements IContractSigningService {

	@Resource
	private ContractSigningMapper contractSigningMapper;
	@Resource
	private IContractFormInfoService contractFormInfoService;


	@Override
	public IPage<ContractSigningEntity> pageList(IPage<ContractSigningEntity> page, ContractSigningEntity signing) {
		return baseMapper.pageList(page, signing);
	}

	@Override
	public boolean save(String contractStatus, ContractSigningEntity entity) {
		contractFormInfoService.updateExportStatus(contractStatus,entity.getContractId());
		if(baseMapper.insert(entity) == 1) {
			return true;
		}
		return false;
	}

}
