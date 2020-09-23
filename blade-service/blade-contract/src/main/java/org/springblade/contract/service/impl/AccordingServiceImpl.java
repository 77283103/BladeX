package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.AccordingEntity;
import org.springblade.contract.mapper.AccordingMapper;
import org.springblade.contract.service.IAccordingService;
import org.springframework.stereotype.Service;

/**
 * 合同依据管理 服务实现类
 *
 * @author XHB
 * @date : 2020-09-23 18:40:20
 */
@Service
public class AccordingServiceImpl extends BaseServiceImpl<AccordingMapper, AccordingEntity> implements IAccordingService {

	@Override
	public IPage<AccordingEntity> pageList(IPage<AccordingEntity> page, AccordingEntity according) {
		return baseMapper.pageList(page, according);
	}
}
