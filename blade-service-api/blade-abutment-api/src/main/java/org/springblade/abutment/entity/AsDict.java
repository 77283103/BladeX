package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xhbbo
 */
@Data
public class AsDict {
	/**
	 * 单位名称
	 */
	@ApiModelProperty(value = "单位名称")
	private String dict_value;
	/**
	 * 单位编号
	 */
	@ApiModelProperty(value = "单位编号")
	private String app_n;
}
