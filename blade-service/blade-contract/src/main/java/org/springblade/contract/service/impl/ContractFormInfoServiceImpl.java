package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *  服务实现类
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:38
 */
@Service
public class ContractFormInfoServiceImpl extends BaseServiceImpl<ContractFormInfoMapper, ContractFormInfoEntity> implements IContractFormInfoService {

	@Resource
	private ContractFormInfoMapper contractFormInfoMapper;

	@Override
	public IPage<ContractFormInfoEntity> pageList(IPage<ContractFormInfoEntity> page, ContractFormInfoEntity contractFormInfo) {
		return baseMapper.pageList(page, contractFormInfo);
	}

	@Override
	public boolean updateExportStatus(String contractStatus,Long id) {
		return contractFormInfoMapper.updateExportStatus(contractStatus,id);
	}

	@Override
	public IPage<ContractFormInfoEntity> pageListSealInfo(IPage<ContractFormInfoRequestVO> page, ContractFormInfoRequestVO contractFormInfoRequestVO) {
		return baseMapper.pageListSealInfo(page, contractFormInfoRequestVO);
	}
}
