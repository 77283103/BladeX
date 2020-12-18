package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclProductionCategoryEntity;
import org.springblade.contract.mapper.SclProductionCategoryMapper;
import org.springblade.contract.service.ISclProductionCategoryService;
import org.springblade.contract.vo.SclProductionCategoryRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 生产类：物流服务合同（一段+调拨运输） 服务实现类
 *
 * @author kx
 * @date : 2020-12-18 17:18:05
 */
@Service
public class SclProductionCategoryServiceImpl extends BaseServiceImpl<SclProductionCategoryMapper, SclProductionCategoryEntity> implements ISclProductionCategoryService {

	@Override
	public IPage<SclProductionCategoryEntity> pageList(IPage<SclProductionCategoryEntity> page, SclProductionCategoryRequestVO sclProductionCategory) {
		return baseMapper.pageList(page, sclProductionCategory);
	}
}
