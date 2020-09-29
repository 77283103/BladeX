package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractSealUsingInfoEntity;
import org.springblade.contract.mapper.ContractSealUsingInfoMapper;
import org.springblade.contract.service.IContractSealUsingInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用印名称 服务实现类
 *
 * @author szw
 * @date : 2020-09-24 01:27:14
 */
@Service
public class ContractSealUsingInfoServiceImpl extends BaseServiceImpl<ContractSealUsingInfoMapper, ContractSealUsingInfoEntity> implements IContractSealUsingInfoService {

	@Resource
	private IContractFormInfoService contractFormInfoService;

	@Override
	public IPage<ContractSealUsingInfoEntity> pageList(IPage<ContractSealUsingInfoEntity> page, ContractSealUsingInfoEntity sealInfo) {
		return baseMapper.pageList(page, sealInfo);
	}

	@Override
	public boolean save(String contractStatus, ContractSealUsingInfoEntity entity) {
		contractFormInfoService.updateExportStatus(contractStatus,entity.getRefContractId());
		if(baseMapper.insert(entity) == 1) {
			return true;
		}
		return false;
	}

}
