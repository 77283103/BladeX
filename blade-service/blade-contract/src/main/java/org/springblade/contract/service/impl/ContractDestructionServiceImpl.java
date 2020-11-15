package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.vo.ContractDestructionResponseVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractDestructionEntity;
import org.springblade.contract.mapper.ContractDestructionMapper;
import org.springblade.contract.service.IContractDestructionService;
import org.springframework.stereotype.Service;
import org.springblade.contract.vo.ContractDestructionRequestVO;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 合同销毁 服务实现类
 *
 * @author szw
 * @date : 2020-11-11 16:37:02
 */
@Service
public class ContractDestructionServiceImpl extends BaseServiceImpl<ContractDestructionMapper, ContractDestructionEntity> implements IContractDestructionService {
	@Resource
	IContractFormInfoService contractFormInfoService;
	@Override
	public IPage<ContractDestructionEntity> pageList(IPage<ContractDestructionEntity> page, ContractDestructionRequestVO contractDestruction) {
		return baseMapper.pageList(page, contractDestruction);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean add(ContractDestructionResponseVO contractDestructionResponseVO) {
		contractFormInfoService.updateExportStatus("120",contractDestructionResponseVO.getContractId());
		return super.save(contractDestructionResponseVO);
	}
}
