package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ChangeEntity;
import org.springblade.contract.mapper.ChangeMapper;
import org.springblade.contract.service.IChangeService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 合同变更 服务实现类
 *
 * @author szw
 * @date : 2020-09-23 19:24:50
 */
@Service
public class ChangeServiceImpl extends BaseServiceImpl<ChangeMapper, ChangeEntity> implements IChangeService {

	@Override
	public IPage<ChangeEntity> pageList(IPage<ChangeEntity> page, ChangeEntity change) {
		return baseMapper.pageList(page, change);
	}
}
