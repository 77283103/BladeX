package org.springblade.system.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.system.entity.UserDepartEntity;

import java.util.List;

/**
 *  服务类
 *
 * @author Chill
 */
public interface IUserDepartService extends IService<UserDepartEntity> {

	/**
	 * 根据UserId删除身份信息
	 * @param id
	 */
	void delByUserId(Long id);

	/**
	 * 根据userIds删除身份信息
	 * @param userIds
	 */
	void delByUserIds(List<Long> userIds);
}
