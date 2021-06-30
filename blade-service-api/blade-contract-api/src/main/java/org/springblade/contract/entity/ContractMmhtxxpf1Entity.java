package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDateTime;


/**
 * 行销品买卖合同子表 实体类
 *
 * @author kx
 * @date : 2021-05-10 13:36:57
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_mmhtxxpf1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractMmhtxxpf1对象", description = "行销品买卖合同子表")
public class ContractMmhtxxpf1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 品名
	 */
	@ApiModelProperty(value = "品名")
	private String contractName;
	/**
	 * 材质/规格
	 */
	@ApiModelProperty(value = "材质/规格")
	private String contractType;
	/**
	 * 未税单价未税单价（元/  ）
	 */
	@ApiModelProperty(value = "未税单价未税单价（元/  ） ")
	private BigDecimal contractPrice;
	/**
	 * 合作期限
	 */
	@ApiModelProperty(value = "合作期限")
	private String contractTime;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String contractElse;

	/**
	 * 合同ID
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同ID")
	private Long contractId;

}
