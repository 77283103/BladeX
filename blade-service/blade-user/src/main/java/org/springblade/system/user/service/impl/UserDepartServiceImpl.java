package org.springblade.system.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.system.entity.UserDepartEntity;
import org.springblade.system.user.mapper.UserDepartMapper;
import org.springblade.system.user.service.IUserDepartService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 *
 * @author Chill
 */
@Service
public class UserDepartServiceImpl extends ServiceImpl<UserDepartMapper, UserDepartEntity> implements IUserDepartService {

	@Override
	public void delByUserId(Long userId) {
		baseMapper.delByUserId(userId);
	}

	@Override
	public void delByUserIds(List<Long> userIds) {
		baseMapper.delByUserIds(userIds);
	}
}
