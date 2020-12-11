package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglCategorySalesContracts1Entity;
import org.springblade.contract.vo.CglCategorySalesContracts1RequestVO;

/**
 * 采购类：买卖合同（行销品） Mapper 接口
 *
 * @author 采购类：买卖合同（行销品）
 * @date : 2020-12-10 18:58:22
 */
public interface CglCategorySalesContracts1Mapper extends BaseMapper<CglCategorySalesContracts1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglCategorySalesContracts1
	 * @return
	 */
	IPage<CglCategorySalesContracts1Entity> pageList(IPage<CglCategorySalesContracts1Entity> page, CglCategorySalesContracts1RequestVO cglCategorySalesContracts1);

}
