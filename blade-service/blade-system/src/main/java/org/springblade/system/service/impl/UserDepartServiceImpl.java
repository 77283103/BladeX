package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.Dept;
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
@AllArgsConstructor
public class UserDepartServiceImpl extends ServiceImpl<UserDepartMapper, UserDepartEntity> implements IUserDepartService {

	private UserDepartMapper userDepartMapper;

	@Override
	public boolean updateByDept(Long newDeptId, Long oldDeptId) {
		userDepartMapper.updateByDept(newDeptId,oldDeptId);
		return true;
	}

	@Override
	public Long getUserDepartIdByAssociationId(Long associationId) {
		UserDepartEntity userDepart = userDepartMapper.getDeptIdByAssociationId(associationId);
		if (Func.isNotEmpty(userDepart)) {
			return userDepart.getId();
		}
		return null;
	}


}
