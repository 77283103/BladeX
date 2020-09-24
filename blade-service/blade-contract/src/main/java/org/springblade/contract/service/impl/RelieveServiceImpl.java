package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.RelieveEntity;
import org.springblade.contract.mapper.RelieveMapper;
import org.springblade.contract.service.IRelieveService;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author szw
 * @date : 2020-09-23 20:10:29
 */
@Service
public class RelieveServiceImpl extends BaseServiceImpl<RelieveMapper, RelieveEntity> implements IRelieveService {

	@Override
	public IPage<RelieveEntity> pageList(IPage<RelieveEntity> page, RelieveEntity relieve) {
		return baseMapper.pageList(page, relieve);
	}
}
