package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglLowCostHardwareRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.CglLowCostHardwareEntity;
import org.springblade.contract.mapper.CglLowCostHardwareMapper;
import org.springblade.contract.service.ICglLowCostHardwareService;
import org.springframework.stereotype.Service;

/**
 * 采购类：买卖合同（五金低耗类） 服务实现类
 *
 * @author 王策
 * @date : 2020-12-10 19:01:57
 */
@Service
public class CglLowCostHardwareServiceImpl extends BaseServiceImpl<CglLowCostHardwareMapper, CglLowCostHardwareEntity> implements ICglLowCostHardwareService {

	@Override
	public IPage<CglLowCostHardwareEntity> pageList(IPage<CglLowCostHardwareEntity> page, CglLowCostHardwareRequestVO cglLowCostHardware) {
		return baseMapper.pageList(page, cglLowCostHardware);
	}
}
