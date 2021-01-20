package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.BusServiceContractEntity;

/**
 * 班车服务合同 服务类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:25:17
 */
public interface IBusServiceContractService extends BaseService<BusServiceContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param busServiceContract
	 * @return
	 */
	IPage<BusServiceContractEntity> pageList(IPage<BusServiceContractEntity> page, BusServiceContractEntity busServiceContract);
}
