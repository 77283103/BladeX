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
	 * 修改
	 *
	 * @param newDeptId
	 * @param oldDeptId
	 * @return
	 */
	boolean updateByDept(Long newDeptId, Long oldDeptId);


	/**
	 * 根据lunid获取id
	 *
	 * @param associationId 接口唯一标识
	 * @return 主键
	 */
	Long getUserDepartIdByAssociationId(Long associationId);

}
