package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.util.List;


/**
 * 多方相对方收付款 实体类
 *
 * @author xhb
 * @date : 2021-04-23 17:30:31
 */
@Getter
@Setter
@TableName("contract_mult_paymen")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractMultPaymen对象", description = "多方相对方收付款")
public class ContractMultPaymenEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 身份
	 */
	@ApiModelProperty(value = "身份")
	private String counterpartIdentity;
	/**
	 * 合同ID
	 */
	@ApiModelProperty(value = "合同ID")
	private String contractId;
	/**
	 * 币种
	 */
	@ApiModelProperty(value = "币种")
	private String currencyCategory;
	/**
	 * 合同税前金额
	 */
	@ApiModelProperty(value = "合同税前金额")
	private BigDecimal contractAmount;
	/**
	 * 合同税后金额
	 */
	@ApiModelProperty(value = "合同税后金额")
	private BigDecimal contractTaxAmount;
	/**
	 * 汇率
	 */
	@ApiModelProperty(value = "汇率")
	private Double contractTaxRate;
	/**
	 * 收付款
	 */
	@ApiModelProperty(value = "收付款")
	private String colPayType;
	/**
	 * 首付款期限
	 */
	@ApiModelProperty(value = "首付款期限")
	private String colPayTerm;
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
	 * 相对方ID
	 */
	@ApiModelProperty(value = "相对方ID")
	private String counterpartId;
	/**
	 * 相对方名称
	 */
	@ApiModelProperty(value = "相对方名称")
	private String counterpartName;
	/**
	 * 子公司名称
	 */
	@ApiModelProperty(value = "子公司名称")
	private String subsidiaryPerson;
	/**
	 * 收款明细列表
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "合同收款明细")
	private List<CollectionEntity> collection;

}
