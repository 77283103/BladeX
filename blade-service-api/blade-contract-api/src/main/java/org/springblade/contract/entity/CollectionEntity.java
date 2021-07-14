package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

/**
 * @author xhbbo
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_form_collection")
@ApiModel(value = "CollectionEntity", description = "合同收款明细表")
public class CollectionEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 天数
	 */
	@ApiModelProperty(value = "天数")
	private Integer days;
	/**
	 * 总期数
	 */
	@ApiModelProperty(value = "总期数")
	private String periodNum;
	/**
	 * 付款信息-第几期
	 */
	@ApiModelProperty(value = "付款信息-第几期")
	private String periodIdx;
	/**
	 * 付款信息-(条件+帐期天数)条件
	 */
	@ApiModelProperty(value = "付款信息-(条件+帐期天数)条件")
	private String payCondition;
	/**
	 * 付款信息-是否开票
	 */
	@ApiModelProperty(value = "付款信息-是否开票")
	private String isReceipt;
	/**
	 * 付款信息-比例
	 */
	@ApiModelProperty(value = "付款信息-比例")
	private Double payPer;

	/**
	 * 付款信息-未税金额
	 */
	@ApiModelProperty(value = "付款信息-未税金额")
	private Double payAmount;
	/**
	 * 合同关联ID
	 */
	@ApiModelProperty(value = "合同关联ID")
	private String  refContractId;
	/**
	 * 收款关联ID
	 */
	@ApiModelProperty(value = "收款关联ID")
	private String refPaymenId;
}
