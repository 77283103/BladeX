package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;


/**
 *  实体类
 *
 * @author szw
 * @date : 2020-09-23 20:10:29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_relieve")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Relieve对象", description = "")
public class ContractRelieveEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 解除原因
	 */
	@ApiModelProperty(value = "解除原因")
	private String relieveCategory;
	/**
	 * 违约金额
	 */
	@ApiModelProperty(value = "违约金额")
	private BigDecimal breachAmount;
	/**
	 * 违约金流向
	 */
	@ApiModelProperty(value = "违约金流向")
	private String breachCategory;
	/**
	 * 赔偿金额
	 */
	@ApiModelProperty(value = "赔偿金额")
	private BigDecimal compensationAmount;
	/**
	 * 赔偿款流向
	 */
	@ApiModelProperty(value = "赔偿款流向")
	private String compensationCategory;
	/**
	 * 解除说明
	 */
	@ApiModelProperty(value = "解除说明")
	private String relieveRemark;
	/**
	 * 关联合同id
	 */
	@ApiModelProperty(value = "关联合同id")
	private Long refContractId;

}
