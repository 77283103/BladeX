package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractFileDownloadLogEntity;
import org.springblade.contract.mapper.ContractFileDownloadLogMapper;
import org.springblade.contract.service.IContractFileDownloadLogService;
import org.springblade.contract.vo.ContractFileDownloadLogRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合同文件日志 服务实现类
 *
 * @author wpf
 * @date : 2021-06-23 10:30:38
 */
@Service
public class ContractFileDownloadLogServiceImpl extends BaseServiceImpl<ContractFileDownloadLogMapper, ContractFileDownloadLogEntity> implements IContractFileDownloadLogService {

	@Override
	public IPage<ContractFileDownloadLogEntity> pageList(IPage<ContractFileDownloadLogEntity> page, ContractFileDownloadLogRequestVO contractFileDownloadLog) {
		return baseMapper.pageList(page, contractFileDownloadLog);
	}

	@Override
	public List<ContractFileDownloadLogEntity> getList(Long contractId) {
		return baseMapper.selectList(contractId);
	}
}
