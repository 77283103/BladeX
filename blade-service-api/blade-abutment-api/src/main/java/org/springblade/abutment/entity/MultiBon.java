package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xhbbo
 */
@Data
@ApiModel(value = "多方保证金信息列表")
public class MultiBon {
	@ApiModelProperty(value = "单据内容:子公司名称（）")
	private String fd_bond_factnm;
	@ApiModelProperty(value = "单据内容:相对方名称（）")
	private String fd_bond_oppnm;
	@ApiModelProperty(value = "有无押金（关联表）")
	private String fd_bon_pledge;
	@ApiModelProperty(value = "押金（关联表）")
	private String fd_amount;
	@ApiModelProperty(value = "单据内容:缴交时间（关联表）")
	private String fd_bon_paytime;
	@ApiModelProperty(value = "单据内容:退回时间（关联表）")
	private String fd_bon_backtime;
	@ApiModelProperty(value = "单据内容:保证金类别（关联表）")
	private String fd_bond_type;
	@ApiModelProperty(value = "单据内容:退回条件（关联表）")
	private String fd_condition;
	@ApiModelProperty(value = "单据内容:保证金编号ID（关联表）")
	private String fd_bon_id;
}
