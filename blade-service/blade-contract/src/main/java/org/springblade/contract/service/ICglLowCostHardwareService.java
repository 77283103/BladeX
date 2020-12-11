package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglLowCostHardwareRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.CglLowCostHardwareEntity;

/**
 * 采购类：买卖合同（五金低耗类） 服务类
 *
 * @author 王策
 * @date : 2020-12-10 19:01:56
 */
public interface ICglLowCostHardwareService extends BaseService<CglLowCostHardwareEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglLowCostHardware
	 * @return
	 */
	IPage<CglLowCostHardwareEntity> pageList(IPage<CglLowCostHardwareEntity> page, CglLowCostHardwareRequestVO cglLowCostHardware);
}
