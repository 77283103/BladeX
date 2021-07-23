package org.springblade.system.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.system.entity.UserDepartEntity;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author Chill
 */
public interface UserDepartMapper extends BaseMapper<UserDepartEntity> {

	/**
	 * 根据UserId删除身份信息
	 * @param userId
	 */
	void delByUserId(@Param("userId") Long userId);

	/**
	 * 根据UserIds删除身份信息
	 * @param userIds
	 */
	void delByUserIds(@Param("userIds") List<Long> userIds);
}
