package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglSalesContractEntity;
import org.springblade.contract.mapper.CglSalesContractMapper;
import org.springblade.contract.service.ICglSalesContractService;
import org.springblade.contract.vo.CglSalesContractRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 采购类：买卖合同（国内设备购买） 服务实现类
 *
 * @author 王策
 * @date : 2020-12-18 15:36:10
 */
@Service
public class CglSalesContractServiceImpl extends BaseServiceImpl<CglSalesContractMapper, CglSalesContractEntity> implements ICglSalesContractService {

	@Override
	public IPage<CglSalesContractEntity> pageList(IPage<CglSalesContractEntity> page, CglSalesContractRequestVO cglSalesContract) {
		return baseMapper.pageList(page, cglSalesContract);
	}
}
