package org.springblade.system.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springblade.core.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDateTime;


/**
 *  实体类
 *
 * @author Feng
 */
@Data
@TableName("blade_user_depart")
@ApiModel(value = "UserDepart对象", description = "UserDepart对象")
public class UserDepart implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "主键")
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;
	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	private Long userId;
	/**
	 * 角色id
	 */
	@ApiModelProperty(value = "角色id")
	private Long roleId;
	/**
	 * 部门id
	 */
	@ApiModelProperty(value = "部门id")
	private Long deptId;
	/**
	 * 岗位id
	 */
	@ApiModelProperty(value = "岗位id")
	private Long postId;

}
