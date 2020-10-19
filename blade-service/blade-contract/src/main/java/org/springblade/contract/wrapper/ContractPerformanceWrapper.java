package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springblade.contract.vo.ContractPerformanceResponseVO;

/**
 * 合同履约计划 包装类,返回视图层所需的字段
 *
 * @author liyj
 * @date : 2020-09-23 19:26:28
 */
public class ContractPerformanceWrapper extends BaseEntityWrapper<ContractPerformanceEntity, ContractPerformanceResponseVO>  {

	public static ContractPerformanceWrapper build() {
		return new ContractPerformanceWrapper();
 	}

	@Override
	public ContractPerformanceResponseVO entityVO(ContractPerformanceEntity performance) {
		ContractPerformanceResponseVO performanceResponseVO = BeanUtil.copy(performance, ContractPerformanceResponseVO.class);

		//User createUser = UserCache.getUser(performance.getCreateUser());
		//User updateUser = UserCache.getUser(performance.getUpdateUser());
		//performanceResponseVO.setCreateUserName(createUser.getName());
		//performanceResponseVO.setUpdateUserName(updateUser.getName());

		return performanceResponseVO;
	}

}
