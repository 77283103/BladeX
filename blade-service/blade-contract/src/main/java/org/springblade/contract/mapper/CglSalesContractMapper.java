package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglSalesContractEntity;
import org.springblade.contract.vo.CglSalesContractRequestVO;

/**
 * 采购类：买卖合同（国内设备购买） Mapper 接口
 *
 * @author 王策
 * @date : 2020-12-18 15:36:09
 */
public interface CglSalesContractMapper extends BaseMapper<CglSalesContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglSalesContract
	 * @return
	 */
	IPage<CglSalesContractEntity> pageList(IPage<CglSalesContractEntity> page, CglSalesContractRequestVO cglSalesContract);

}
