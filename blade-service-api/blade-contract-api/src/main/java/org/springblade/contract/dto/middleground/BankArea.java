package org.springblade.contract.dto.middleground;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * （统一中台）合同银行信息
 */
@Data
public class BankArea implements Serializable {

	@ApiModelProperty(value = "联行号")
	private String bank_code;

	@ApiModelProperty(value = "帐户户号")
	private String bank_account_code;

	@ApiModelProperty(value = "银行帐户名称")
	private String bank_account_name;

}
