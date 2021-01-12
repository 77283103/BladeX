package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.CglProofingContractEntity;
import org.springblade.contract.mapper.CglProofingContractMapper;
import org.springblade.contract.service.ICglProofingContractService;
import org.springframework.stereotype.Service;

/**
 * 采购类_打样合同书 服务实现类
 *
 * @author 采购类_打样合同书
 * @date : 2021-01-12 13:24:36
 */
@Service
public class CglProofingContractServiceImpl extends BaseServiceImpl<CglProofingContractMapper, CglProofingContractEntity> implements ICglProofingContractService {

	@Override
	public IPage<CglProofingContractEntity> pageList(IPage<CglProofingContractEntity> page, CglProofingContractEntity cglProofingContract) {
		return baseMapper.pageList(page, cglProofingContract);
	}
}
