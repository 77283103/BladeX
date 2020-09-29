package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.service.IContractAccordingService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.mapper.ContractAccordingMapper;
import org.springframework.stereotype.Service;

/**
 * 合同依据管理 服务实现类
 *
 * @author XHB
 * @date : 2020-09-24 14:20:32
 */
@Service
public class ContractAccordingServiceImpl extends BaseServiceImpl<ContractAccordingMapper, ContractAccordingEntity> implements IContractAccordingService {

	@Override
	public IPage<ContractAccordingEntity> pageList(IPage<ContractAccordingEntity> page, ContractAccordingEntity according) {
		return baseMapper.pageList(page, according);
	}
}
