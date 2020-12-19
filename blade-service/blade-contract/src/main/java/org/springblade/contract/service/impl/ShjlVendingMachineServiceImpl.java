package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ShjlVendingMachineEntity;
import org.springblade.contract.mapper.ShjlVendingMachineMapper;
import org.springblade.contract.service.IShjlVendingMachineService;
import org.springblade.contract.vo.ShjlVendingMachineRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1) 服务实现类
 *
 * @author 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1)
 * @date : 2020-12-18 16:14:40
 */
@Service
public class ShjlVendingMachineServiceImpl extends BaseServiceImpl<ShjlVendingMachineMapper, ShjlVendingMachineEntity> implements IShjlVendingMachineService {

	@Override
	public IPage<ShjlVendingMachineEntity> pageList(IPage<ShjlVendingMachineEntity> page, ShjlVendingMachineRequestVO shjlVendingMachine) {
		return baseMapper.pageList(page, shjlVendingMachine);
	}
}
