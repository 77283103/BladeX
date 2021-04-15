package org.springblade.system.vo;

import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DataSealAuthority 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author xhb
 * @date : 2021-04-12 16:51:00
 */
@Getter
@Setter
@ApiModel(description = "DataSealAuthority请求对象")
public class DataSealAuthorityRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户名",required = true)
	private String userName;
	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "角色名",required = true)
	private String roleName;

    @NotBlank(message = "用户ID不能为空")
    @ApiModelProperty(value="用户ID",required = true)
	private String userId;

    @NotBlank(message = "所属角色ID不能为空")
    @ApiModelProperty(value="所属角色ID",required = true)
	private String roleId;

    @NotBlank(message = "申请单位编号集合（去重）不能为空")
    @ApiModelProperty(value="申请单位编号集合（去重）",required = true)
	private String code;

    @NotBlank(message = "权限字段不能为空")
    @ApiModelProperty(value="权限字段",required = true)
	private String authField;

    @NotBlank(message = "管理签章申请单位集合不能为空")
    @ApiModelProperty(value="管理签章申请单位集合",required = true)
	private String seal;

}
