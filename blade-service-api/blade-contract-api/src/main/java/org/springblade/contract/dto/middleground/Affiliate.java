package org.springblade.contract.dto.middleground;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * (统一中台) 合同主体
 */
@Data
public class Affiliate implements Serializable {

	@ApiModelProperty(value = "组织机构代码")
	private String vend_orgcode;

	@ApiModelProperty(value = "名称")
	private String vend_name;

	@ApiModelProperty(value = "公司内部代号")
	private String vend_no;

	@ApiModelProperty(value = "主体类别")
	private String kind;

}
