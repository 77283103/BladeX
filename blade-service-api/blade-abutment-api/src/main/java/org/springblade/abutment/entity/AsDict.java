package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xhbbo
 */
@Data
public class AsDict {
	/**
	 * 序号
	 */
	@ApiModelProperty(value = "序号")
	private String fd_seq;
	/**
	 * 企业编号
	 */
	@ApiModelProperty(value = "企业编号")
	private String fd_factNo;
	/**
	 * 单位名称
	 */
	@ApiModelProperty(value = "单位名称")
	private String fd_factName;
	/**
	 * 单位编号
	 */
	@ApiModelProperty(value = "单位编号")
	private String fd_taxNo;
}
