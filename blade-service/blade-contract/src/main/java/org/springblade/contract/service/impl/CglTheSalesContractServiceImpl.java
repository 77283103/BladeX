package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglTheSalesContractRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.CglTheSalesContractEntity;
import org.springblade.contract.mapper.CglTheSalesContractMapper;
import org.springblade.contract.service.ICglTheSalesContractService;
import org.springframework.stereotype.Service;

/**
 * 采购类：新增原物料补充协议--买卖合同 服务实现类
 *
 * @author 王策
 * @date : 2020-12-10 19:07:49
 */
@Service
public class CglTheSalesContractServiceImpl extends BaseServiceImpl<CglTheSalesContractMapper, CglTheSalesContractEntity> implements ICglTheSalesContractService {

	@Override
	public IPage<CglTheSalesContractEntity> pageList(IPage<CglTheSalesContractEntity> page, CglTheSalesContractRequestVO cglTheSalesContract) {
		return baseMapper.pageList(page, cglTheSalesContract);
	}
}
