package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.DistServiceContractEntity;
import org.springblade.contract.service.IDistServiceContractService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.mapper.DistServiceContractMapper;
import org.springframework.stereotype.Service;

/**
 * 相对方管理  服务实现类
 *
 * @author 王策
 * @date : 2021-01-18 17:24:27
 */
@Service
public class DistServiceContractServiceImpl extends BaseServiceImpl<DistServiceContractMapper, DistServiceContractEntity> implements IDistServiceContractService {

	@Override
	public IPage<DistServiceContractEntity> pageList(IPage<DistServiceContractEntity> page, DistServiceContractEntity distributionServiceContract) {
		return baseMapper.pageList(page, distributionServiceContract);
	}
}
