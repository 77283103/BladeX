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
	@ApiModelProperty(value = "单据内容:身份（）")
	private String fd_role_identity;
	@ApiModelProperty(value = "单据内容:子公司名称（）")
	private String fd_role_real;
	@ApiModelProperty(value = "单据内容:相对方名称（）")
	private String fd_role_name;
	@ApiModelProperty(value = "单据内容:相对方联系电话（）")
	private String fd_role_number;
	@ApiModelProperty(value = "单据内容:相对方邮箱（）")
	private String fd_role_email;
	@ApiModelProperty(value = "单据内容:相对方联系地址（）")
	private String fd_role_address;
	@ApiModelProperty(value = "单据内容:银行信息-联行号")
	private String fd_role_bank_code;
	@ApiModelProperty(value = "单据内容:银行信息-银行账号")
	private String fd_role_account_code;
	@ApiModelProperty(value = "单据内容:银行信息-银行帐户名称")
	private String fd_role_account_name;
	@ApiModelProperty(value = "单据内容:银行信息-开户行名称")
	private String fd_role_deposit_name;
}
