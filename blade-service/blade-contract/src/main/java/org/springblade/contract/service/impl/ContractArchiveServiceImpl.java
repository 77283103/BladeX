package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractArchiveEntity;
import org.springblade.contract.mapper.ContractArchiveMapper;
import org.springblade.contract.service.IContractArchiveService;
import org.springframework.stereotype.Service;
import org.springblade.contract.vo.ContractArchiveRequestVO;

/**
 * 合同归档 服务实现类
 *
 * @author 合同归档
 * @date : 2020-11-05 09:41:39
 */
@Service
public class ContractArchiveServiceImpl extends BaseServiceImpl<ContractArchiveMapper, ContractArchiveEntity> implements IContractArchiveService {

	@Override
	public IPage<ContractArchiveEntity> pageList(IPage<ContractArchiveEntity> page, ContractArchiveRequestVO contractArchive) {
		return baseMapper.pageList(page, contractArchive);
	}

	@Override
	public void exportTargetDataResult() {

	}
}
