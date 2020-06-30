package org.springblade.system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.UserDepartEntity;

/**
 *  模型DTO
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDepartDTO extends UserDepartEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色别名
	 */
	private String roleAlias;
	/**
	 * 角色名
	 */
	private String roleName;
	/**
	 * 岗位名
	 */
	private String postName;
	/**
	 * 部门名
	 */
	private String deptName;
}
