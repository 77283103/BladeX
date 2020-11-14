package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractRelieveEntity;
import org.springblade.contract.mapper.ContractRelieveMapper;
import org.springblade.contract.service.IContractRelieveService;
import org.springframework.stereotype.Service;
import org.springblade.contract.vo.ContractRelieveRequestVO;

/**
 * 合同解除 服务实现类
 *
 * @author 合同解除
 * @date : 2020-11-05 09:24:01
 */
@Service
public class ContractRelieveServiceImpl extends BaseServiceImpl<ContractRelieveMapper, ContractRelieveEntity> implements IContractRelieveService {

	@Override
	public IPage<ContractRelieveEntity> pageList(IPage<ContractRelieveEntity> page, ContractRelieveRequestVO contractRelieve) {
		return baseMapper.pageList(page, contractRelieve);
	}
}
