package org.springblade.system.entity;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * DataSealAuthority 实体类
 *
 * @author xhb
 * @date : 2021-04-12 16:50:59
 */
@Getter
@Setter
@TableName("data_seal_authority")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DataSealAuthority对象", description = "DataSealAuthority")
public class DataSealAuthorityEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户名")
	private String userName;
	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "角色名")
	private String roleName;
	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID")
	private String userId;
	/**
	 * 所属角色ID
	 */
	@ApiModelProperty(value = "所属角色ID")
	private String roleId;
	/**
	 * 申请单位编号集合（去重）
	 */
	@ApiModelProperty(value = "申请单位编号集合（去重）")
	private String code;
	/**
	 * 权限字段
	 */
	@ApiModelProperty(value = "权限字段")
	private String authField;
	/**
	 * 管理签章申请单位集合
	 */
	@ApiModelProperty(value = "管理签章申请单位集合")
	private String seal;

}
