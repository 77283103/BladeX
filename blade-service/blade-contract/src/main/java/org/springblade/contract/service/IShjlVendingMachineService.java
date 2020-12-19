package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ShjlVendingMachineEntity;
import org.springblade.contract.vo.ShjlVendingMachineRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1) 服务类
 *
 * @author 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1)
 * @date : 2020-12-18 16:14:40
 */
public interface IShjlVendingMachineService extends BaseService<ShjlVendingMachineEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param shjlVendingMachine
	 * @return
	 */
	IPage<ShjlVendingMachineEntity> pageList(IPage<ShjlVendingMachineEntity> page, ShjlVendingMachineRequestVO shjlVendingMachine);
}
