package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.CollectionEntity;


/**
 * 合同评估表 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 合同评估表
 * @date : 2020-11-05 09:37:39
 */
@Getter
@Setter
@ToString
@ApiModel(description = "合同收款明细")
public class CollectionRequestVO extends CollectionEntity {

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
}
