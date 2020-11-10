package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractSealUsingInfoEntity;
import org.springblade.contract.mapper.ContractSealUsingInfoMapper;
import org.springblade.contract.service.IContractSealUsingInfoService;
import org.springframework.stereotype.Service;
import org.springblade.contract.vo.ContractSealUsingInfoRequestVO;

/**
 * 合同用印 服务实现类
 *
 * @author 合同用印
 * @date : 2020-11-05 09:29:26
 */
@Service
public class ContractSealUsingInfoServiceImpl extends BaseServiceImpl<ContractSealUsingInfoMapper, ContractSealUsingInfoEntity> implements IContractSealUsingInfoService {

	@Override
	public IPage<ContractSealUsingInfoEntity> pageList(IPage<ContractSealUsingInfoEntity> page, ContractSealUsingInfoRequestVO contractSealUsingInfo) {
		return baseMapper.pageList(page, contractSealUsingInfo);
	}
}
