package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.DistServiceContractEntity;
import org.springblade.core.mp.base.BaseService;

/**
 * 配送服务合同 服务类
 *
 * @author 王策
 * @date : 2021-01-18 17:24:27
 */
public interface IDistServiceContractService extends BaseService<DistServiceContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param distributionServiceContract
	 * @return
	 */
	IPage<DistServiceContractEntity> pageList(IPage<DistServiceContractEntity> page, DistServiceContractEntity distributionServiceContract);
}
