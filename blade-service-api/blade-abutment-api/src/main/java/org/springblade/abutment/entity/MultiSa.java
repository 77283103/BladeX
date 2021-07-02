package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xhbbo
 */
@Data
@ApiModel(value = "子公司以及签章单位信息列表")
public class MultiSa {
	@ApiModelProperty(value = "单据内容:单位名称")
	private String fd_seal_factName;
	@ApiModelProperty(value = "单据内容:企业编号")
	private String fd_seal_factNo;
	@ApiModelProperty(value = "单据内容:单位编号")
	private String fd_role_taxNo;

}
