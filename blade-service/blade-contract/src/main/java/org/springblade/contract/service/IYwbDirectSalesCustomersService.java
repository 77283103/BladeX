package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwbDirectSalesCustomersEntity;
import org.springblade.contract.vo.YwbDirectSalesCustomersRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 业务类：22.直营客户促销执行通知函 服务类
 *
 * @author 业务类：22.直营客户促销执行通知函
 * @date : 2020-12-18 16:06:13
 */
public interface IYwbDirectSalesCustomersService extends BaseService<YwbDirectSalesCustomersEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywbDirectSalesCustomers
	 * @return
	 */
	IPage<YwbDirectSalesCustomersEntity> pageList(IPage<YwbDirectSalesCustomersEntity> page, YwbDirectSalesCustomersRequestVO ywbDirectSalesCustomers);
}
