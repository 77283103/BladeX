package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclServiceEntity;
import org.springblade.contract.mapper.SclServiceMapper;
import org.springblade.contract.service.ISclServiceService;
import org.springblade.contract.vo.SclServiceRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 生产类：物流服务合同（二段仓储+配送） 服务实现类
 *
 * @author kx
 * @date : 2020-12-18 17:08:07
 */
@Service
public class SclServiceServiceImpl extends BaseServiceImpl<SclServiceMapper, SclServiceEntity> implements ISclServiceService {

	@Override
	public IPage<SclServiceEntity> pageList(IPage<SclServiceEntity> page, SclServiceRequestVO sclService) {
		return baseMapper.pageList(page, sclService);
	}
}
