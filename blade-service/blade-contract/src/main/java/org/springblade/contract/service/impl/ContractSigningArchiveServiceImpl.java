package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractSigningArchiveEntity;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.mapper.ContractSigningArchiveMapper;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.service.IContractSigningArchiveService;
import org.springblade.contract.vo.ContractSigningArchiveRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合同签订表 服务实现类
 *
 * @author liyj
 * @date : 2020-09-23 19:27:05
 */
@Service
@AllArgsConstructor
public class ContractSigningArchiveServiceImpl extends BaseServiceImpl<ContractSigningArchiveMapper, ContractSigningArchiveEntity> implements IContractSigningArchiveService {

	private IContractFormInfoService contractFormInfoService;

	private ContractFormInfoMapper contractFormInfoMapper;




	@Override
	public IPage<ContractSigningArchiveEntity> pageList(IPage<ContractSigningArchiveEntity> page, ContractSigningArchiveRequestVO signing) {
		return baseMapper.pageList(page, signing);
	}

	@Override
	public List<ContractSigningArchiveEntity> getByContractId(Long id) {
		return baseMapper.selectBySigningArchiveId(id);
	}
}
