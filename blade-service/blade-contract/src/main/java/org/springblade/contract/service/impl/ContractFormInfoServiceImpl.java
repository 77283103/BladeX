package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:38
 */
@Service
public class ContractFormInfoServiceImpl extends BaseServiceImpl<ContractFormInfoMapper, ContractFormInfoEntity> implements IContractFormInfoService {

	@Override
	public IPage<ContractFormInfoEntity> pageList(IPage<ContractFormInfoEntity> page, ContractFormInfoEntity contractFormInfo) {
		return baseMapper.pageList(page, contractFormInfo);
	}
}
