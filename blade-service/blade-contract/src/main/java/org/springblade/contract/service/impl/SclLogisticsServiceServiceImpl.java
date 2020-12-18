package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclLogisticsServiceEntity;
import org.springblade.contract.mapper.SclLogisticsServiceMapper;
import org.springblade.contract.service.ISclLogisticsServiceService;
import org.springblade.contract.vo.SclLogisticsServiceRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 生产类：物流服务合同（二段配送） 服务实现类
 *
 * @author kx
 * @date : 2020-12-18 17:17:43
 */
@Service
public class SclLogisticsServiceServiceImpl extends BaseServiceImpl<SclLogisticsServiceMapper, SclLogisticsServiceEntity> implements ISclLogisticsServiceService {

	@Override
	public IPage<SclLogisticsServiceEntity> pageList(IPage<SclLogisticsServiceEntity> page, SclLogisticsServiceRequestVO sclLogisticsService) {
		return baseMapper.pageList(page, sclLogisticsService);
	}
}
