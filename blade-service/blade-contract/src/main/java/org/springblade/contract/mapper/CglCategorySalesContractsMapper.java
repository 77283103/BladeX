package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglCategorySalesContractsEntity;
import org.springblade.contract.vo.CglCategorySalesContractsRequestVO;

/**
 * 采购类：买卖合同（行销品） Mapper 接口
 *
 * @author 王策
 * @date : 2020-12-10 18:52:45
 */
public interface CglCategorySalesContractsMapper extends BaseMapper<CglCategorySalesContractsEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglCategorySalesContracts
	 * @return
	 */
	IPage<CglCategorySalesContractsEntity> pageList(IPage<CglCategorySalesContractsEntity> page, CglCategorySalesContractsRequestVO cglCategorySalesContracts);

}
