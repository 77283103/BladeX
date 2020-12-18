package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglSalesContract1Entity;
import org.springblade.contract.vo.CglSalesContract1RequestVO;

/**
 * 采购类：买卖合同（国内设备购买）附表 Mapper 接口
 *
 * @author 王策
 * @date : 2020-12-18 16:12:24
 */
public interface CglSalesContract1Mapper extends BaseMapper<CglSalesContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglSalesContract1
	 * @return
	 */
	IPage<CglSalesContract1Entity> pageList(IPage<CglSalesContract1Entity> page, CglSalesContract1RequestVO cglSalesContract1);

}
