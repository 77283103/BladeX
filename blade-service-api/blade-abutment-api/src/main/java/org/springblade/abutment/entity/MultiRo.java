package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xhbbo
 */
@Data
@ApiModel(value = "多方相对方身份信息列表")
public class MultiRo {
	@ApiModelProperty(value = "单据内容:相对方身份（）")
	private String fd_roles;
	@ApiModelProperty(value = "单据内容:相对方名称（）")
	private String fd_linkman;
	@ApiModelProperty(value = "单据内容:相对方联系电话（）")
	private String fd_contact_number;
	@ApiModelProperty(value = "单据内容:相对方邮箱（）")
	private String fd_email;
	@ApiModelProperty(value = "单据内容:相对方联系地址（）")
	private String fd_address;
}
