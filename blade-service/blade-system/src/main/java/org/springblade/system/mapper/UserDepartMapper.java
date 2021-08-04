package org.springblade.system.mapper;

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
	 * 修改
	 *
	 * @param newDeptId
	 * @param oldDeptId
	 * @return
	 */
	int updateByDept(Long newDeptId, Long oldDeptId);

	/**
	 * 根据lunid查询dept
	 *
	 * @param associationId 接口唯一标识
	 * @return
	 */
	UserDepartEntity getDeptIdByAssociationId(Long associationId);
	/**
	 * 根据lunid查询dept
	 *
	 * @param associationId 接口唯一标识
	 * @return
	 */
	UserDepartEntity getByUserIdDeptUser(Long associationId);

	/**
	 *
	 *批量新增
	 * @param userDepartList
	 * @return
	 */
	boolean saveBatchUserDepart(@Param("userDepartList") List<UserDepartEntity> userDepartList);


	/**
	 *
	 *批量新增
	 * @param userDepartList
	 * @return
	 */
	boolean saveUserDepartList(@Param("userDepartList") List<UserDepartEntity> userDepartList);

}
