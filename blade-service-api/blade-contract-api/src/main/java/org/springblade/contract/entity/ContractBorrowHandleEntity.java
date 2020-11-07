package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 借阅处理 实体类
 *
 * @author xhb
 * @date : 2020-10-30 09:28:17
 */
@Getter
@Setter
@TableName("contract_borrow_handle")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractBorrowHandle对象", description = "借阅处理")
public class ContractBorrowHandleEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 借阅处理申请编号
	 */
	@ApiModelProperty(value = "借阅处理申请编号")
	private String handleId;
	/**
	 * 合同名称
	 */
	@ApiModelProperty(value = "合同名称")
	private String contractName;
	/**
	 * 合同编号
	 */
	@ApiModelProperty(value = "合同编号")
	private String contractNumber;
	/**
	 * 借阅页数
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value = "借阅页数")
	private Integer borrowPages;
	/**
	 * 快递公司
	 */
	@ApiModelProperty(value = "快递公司")
	private String courierCompany;
	/**
	 * 快递单号
	 */
	@ApiModelProperty(value = "快递单号")
	private String trackingNumber;
	/**
	 * 收件人
	 */
	@ApiModelProperty(value = "收件人")
	private String addressee;
	/**
	 * 收件地址
	 */
	@ApiModelProperty(value = "收件地址")
	private String recipientAddress;
	/**
	 * 收件人电话
	 */
	@ApiModelProperty(value = "收件人电话")
	private String recipientPhone;
	/**
	 * 借阅申请信息
	 * @TableField 不查询此对象属性
	 */

}
