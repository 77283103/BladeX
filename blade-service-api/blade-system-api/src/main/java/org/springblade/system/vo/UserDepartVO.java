package org.springblade.system.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.UserDepartEntity;

import java.io.Serializable;

/**
 *  模型VO
 *
 * @author Chill
 */
@Data
public class UserDepartVO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	/**
	 * 用户id
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long userId;
	/**
	 * 角色id
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long roleId;
	/**
	 * 部门id
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long deptId;
	/**
	 * 岗位id
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long postId;
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
