package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwbDirectSalesCustomersEntity;
import org.springblade.contract.vo.YwbDirectSalesCustomersRequestVO;

/**
 * 业务类：22.直营客户促销执行通知函 Mapper 接口
 *
 * @author 业务类：22.直营客户促销执行通知函
 * @date : 2020-12-18 16:06:12
 */
public interface YwbDirectSalesCustomersMapper extends BaseMapper<YwbDirectSalesCustomersEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywbDirectSalesCustomers
	 * @return
	 */
	IPage<YwbDirectSalesCustomersEntity> pageList(IPage<YwbDirectSalesCustomersEntity> page, YwbDirectSalesCustomersRequestVO ywbDirectSalesCustomers);

}
