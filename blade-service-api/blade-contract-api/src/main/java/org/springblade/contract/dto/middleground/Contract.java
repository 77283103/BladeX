package org.springblade.contract.dto.middleground;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (统一中台) 合同
 */
@Data
public class Contract implements Serializable {

	@ApiModelProperty(value = "合同编码")
	private String no;

	@ApiModelProperty(value = "有效期起")
	private Date b_data;

	@ApiModelProperty(value = "有效期止")
	private Date e_data;

	@ApiModelProperty(value = "合同大类")
	private String contract_typr1;

	@ApiModelProperty(value = "合同中类")
	private String contract_typr2;

	@ApiModelProperty(value = "合同申请日期")
	private Date c_apple_date;

	@ApiModelProperty(value = "审核通过时间")
	private String c_audit_date;

	@ApiModelProperty(value = "用印归档时间")
	private String c_seal_date;

	@ApiModelProperty(value = "合同主体")
	private List<Affiliate> affiliates;

	@ApiModelProperty(value = "结算区")
	private PayCondition pay_condition;

	@ApiModelProperty(value = "合同体")
	private List<ContractBody> body;

	@ApiModelProperty(value = "银行信息")
	private BankArea bank_area;

}
