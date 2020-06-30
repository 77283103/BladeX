package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.Condition;
import org.springblade.system.entity.UserDepartEntity;
import org.springblade.system.mapper.UserDepartMapper;
import org.springblade.system.service.IUserDepartService;
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
	public List<UserDepartEntity> currentUserDepart(Long userId) {
		UserDepartEntity userDepart = new UserDepartEntity();
		userDepart.setUserId(userId);
		QueryWrapper<UserDepartEntity> queryWrapper = Condition.getQueryWrapper(userDepart);
		List<UserDepartEntity> userDepartEntities = baseMapper.selectList(queryWrapper);
		return userDepartEntities;
	}
}
