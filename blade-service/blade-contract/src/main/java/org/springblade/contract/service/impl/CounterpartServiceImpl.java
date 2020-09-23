package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.CounterpartEntity;
import org.springblade.contract.mapper.CounterpartMapper;
import org.springblade.contract.service.ICounterpartService;
import org.springframework.stereotype.Service;

/**
 * 相对方管理 服务实现类
 *
 * @author XHB
 * @date : 2020-09-23 19:35:06
 */
@Service
public class CounterpartServiceImpl extends BaseServiceImpl<CounterpartMapper, CounterpartEntity> implements ICounterpartService {

	@Override
	public IPage<CounterpartEntity> pageList(IPage<CounterpartEntity> page, CounterpartEntity counterpart) {
		return baseMapper.pageList(page, counterpart);
	}
}
