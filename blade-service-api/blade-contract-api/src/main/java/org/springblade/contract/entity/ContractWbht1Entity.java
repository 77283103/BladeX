package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;


/**
 * 消防-维保合同子表 实体类
 *
 * @author kx
 * @date : 2021-05-10 13:40:18
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_wbht1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractWbht1对象", description = "消防-维保合同子表")
public class ContractWbht1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@ApiModelProperty(value = "序号")
	private String contractNum;
	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
	private String contractName;
	/**
	 * 身份证号
	 */
	@ApiModelProperty(value = "身份证号")
	private String contractNumber;
	/**
	 * 工种
	 */
	@ApiModelProperty(value = "工种")
	private String contractBus;
	/**
	 * 施工资质证编号
	 */
	@ApiModelProperty(value = "施工资质证编号")
	private String contractBusNum;

	/**
	 * 合同ID
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同ID")
	private Long contractId;

}
