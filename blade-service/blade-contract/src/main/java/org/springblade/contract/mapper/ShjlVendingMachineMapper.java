package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ShjlVendingMachineEntity;
import org.springblade.contract.vo.ShjlVendingMachineRequestVO;

/**
 * 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1) Mapper 接口
 *
 * @author 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1)
 * @date : 2020-12-18 16:14:39
 */
public interface ShjlVendingMachineMapper extends BaseMapper<ShjlVendingMachineEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param shjlVendingMachine
	 * @return
	 */
	IPage<ShjlVendingMachineEntity> pageList(IPage<ShjlVendingMachineEntity> page, ShjlVendingMachineRequestVO shjlVendingMachine);

}
