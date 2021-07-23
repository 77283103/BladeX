package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractMultPaymenEntity;

/**
 * 多方相对方收付款 服务类
 *
 * @author xhb
 * @date : 2021-04-23 17:30:31
 */
public interface IContractMultPaymenService extends BaseService<ContractMultPaymenEntity> {

	/**
	 * 分页查询
	 *
	 * @param page
	 * @param contractMultPaymen
	 * @return
	 */
	IPage<ContractMultPaymenEntity> pageList(IPage<ContractMultPaymenEntity> page, ContractMultPaymenEntity contractMultPaymen);

	/**
	 * 删除合同相对方关联表
	 *
	 * @param id 合同id
	 */
	void deleteMult(Long id);

	/**
	 * @param paymenEntity 保存
	 * @author jitwxs
	 * @date 2021/7/23 11:57
	 */
	ContractMultPaymenEntity saveMultPay(ContractMultPaymenEntity paymenEntity);
}
