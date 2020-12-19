package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglSalesContractEntity;
import org.springblade.contract.vo.CglSalesContractRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 采购类：买卖合同（国内设备购买） 服务类
 *
 * @author 王策
 * @date : 2020-12-18 15:36:10
 */
public interface ICglSalesContractService extends BaseService<CglSalesContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglSalesContract
	 * @return
	 */
	IPage<CglSalesContractEntity> pageList(IPage<CglSalesContractEntity> page, CglSalesContractRequestVO cglSalesContract);
}
