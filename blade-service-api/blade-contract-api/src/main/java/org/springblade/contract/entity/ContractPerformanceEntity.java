package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springblade.core.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDateTime;


/**
 * 合同履约计划 实体类
 *
 * @author liyj
 * @date : 2020-09-23 19:26:28
 */
@Data
@TableName("contract_performance")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractPerformance对象", description = "合同履约计划")
public class ContractPerformanceEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 履约开始时间
	 */
	@ApiModelProperty(value = "履约开始时间")
	private LocalDateTime performanceStartDate;
	/**
	 * 履约结束时间
	 */
	@ApiModelProperty(value = "履约结束时间")
	private LocalDateTime performanceEndDate;
	/**
	 * 金额
	 */
	@ApiModelProperty(value = "金额")
	private BigDecimal amount;
	/**
	 * 合同id
	 */
	@ApiModelProperty(value = "合同id")
	private Long contractId;

}
