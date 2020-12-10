package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglActivityExecutionContractRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.CglActivityExecutionContractEntity;
import org.springblade.contract.mapper.CglActivityExecutionContractMapper;
import org.springblade.contract.service.ICglActivityExecutionContractService;
import org.springframework.stereotype.Service;

/**
 * 采购类：活动执行合同 服务实现类
 *
 * @author 采购类：活动执行合同
 * @date : 2020-12-10 18:29:51
 */
@Service
public class CglActivityExecutionContractServiceImpl extends BaseServiceImpl<CglActivityExecutionContractMapper, CglActivityExecutionContractEntity> implements ICglActivityExecutionContractService {

	@Override
	public IPage<CglActivityExecutionContractEntity> pageList(IPage<CglActivityExecutionContractEntity> page, CglActivityExecutionContractRequestVO cglActivityExecutionContract) {
		return baseMapper.pageList(page, cglActivityExecutionContract);
	}
}
