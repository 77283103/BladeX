package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.CglAdvertisementProductionEntity;
import org.springblade.contract.mapper.CglAdvertisementProductionMapper;
import org.springblade.contract.service.ICglAdvertisementProductionService;
import org.springframework.stereotype.Service;

/**
 * 采购类：广告制作安装合同模板 服务实现类
 *
 * @author 采购类：广告制作安装合同模板
 * @date : 2021-01-11 15:05:51
 */
@Service
public class CglAdvertisementProductionServiceImpl extends BaseServiceImpl<CglAdvertisementProductionMapper, CglAdvertisementProductionEntity> implements ICglAdvertisementProductionService {

	@Override
	public IPage<CglAdvertisementProductionEntity> pageList(IPage<CglAdvertisementProductionEntity> page, CglAdvertisementProductionEntity cglAdvertisementProduction) {
		return baseMapper.pageList(page, cglAdvertisementProduction);
	}
}
