package org.springblade.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.core.mp.base.BaseService;
import org.springblade.system.entity.UserDepartEntity;

import java.util.List;

/**
 *  服务类
 *
 * @author Chill
 */
public interface IUserDepartService extends IService<UserDepartEntity> {

	/**
	 * 根据当前登录用户查询身份信息
	 * @param userId
	 * @return
	 */
	List<UserDepartEntity> currentUserDepart(Long userId);
}
