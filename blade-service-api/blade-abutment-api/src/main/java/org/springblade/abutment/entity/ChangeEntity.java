package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xhbbo
 */
@Data
public class ChangeEntity {

	@ApiModelProperty(value = "变更协议名称")
	private String Fd_change_Agreement_Name;

	@ApiModelProperty(value = "变更原因")
	private String Fd_change_reason;

	@ApiModelProperty(value = "变更内容")
	private String Fd_change_content;

	@ApiModelProperty(value = "变更主导方")
	private String Fd_change_party;

	@ApiModelProperty(value = "变更主导方")
	private String Fd_change_type;

	@ApiModelProperty(value = "变更说明")
	private String Fd_change_explain;

	@ApiModelProperty(value = "补充协议")
	private String Fd_change_text_file;
}
