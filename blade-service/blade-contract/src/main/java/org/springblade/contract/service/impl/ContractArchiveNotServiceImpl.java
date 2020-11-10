package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractArchiveNotEntity;
import org.springblade.contract.mapper.ContractArchiveNotMapper;
import org.springblade.contract.service.IContractArchiveNotService;
import org.springframework.stereotype.Service;
import org.springblade.contract.vo.ContractArchiveNotRequestVO;

/**
 * 未归档原因 服务实现类
 *
 * @author 未归档原因
 * @date : 2020-11-09 15:19:18
 */
@Service
public class ContractArchiveNotServiceImpl extends BaseServiceImpl<ContractArchiveNotMapper, ContractArchiveNotEntity> implements IContractArchiveNotService {

	@Override
	public IPage<ContractArchiveNotEntity> pageList(IPage<ContractArchiveNotEntity> page, ContractArchiveNotRequestVO contractArchiveNot) {
		return baseMapper.pageList(page, contractArchiveNot);
	}
}
