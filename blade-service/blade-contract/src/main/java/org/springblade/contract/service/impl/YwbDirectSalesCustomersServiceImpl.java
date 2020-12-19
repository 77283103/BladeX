package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwbDirectSalesCustomersEntity;
import org.springblade.contract.mapper.YwbDirectSalesCustomersMapper;
import org.springblade.contract.service.IYwbDirectSalesCustomersService;
import org.springblade.contract.vo.YwbDirectSalesCustomersRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 业务类：22.直营客户促销执行通知函 服务实现类
 *
 * @author 业务类：22.直营客户促销执行通知函
 * @date : 2020-12-18 16:06:13
 */
@Service
public class YwbDirectSalesCustomersServiceImpl extends BaseServiceImpl<YwbDirectSalesCustomersMapper, YwbDirectSalesCustomersEntity> implements IYwbDirectSalesCustomersService {

	@Override
	public IPage<YwbDirectSalesCustomersEntity> pageList(IPage<YwbDirectSalesCustomersEntity> page, YwbDirectSalesCustomersRequestVO ywbDirectSalesCustomers) {
		return baseMapper.pageList(page, ywbDirectSalesCustomers);
	}
}
