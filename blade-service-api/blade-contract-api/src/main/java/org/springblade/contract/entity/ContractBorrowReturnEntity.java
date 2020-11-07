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
 * 借阅归还 实体类
 *
 * @author xhb
 * @date : 2020-10-30 09:28:54
 */
@Getter
@Setter
@TableName("contract_borrow_return")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractBorrowReturn对象", description = "借阅归还")
public class ContractBorrowReturnEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 借阅归还申请编号
	 */
	@ApiModelProperty(value = "借阅归还申请编号")
	private String returnId;
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
	 * 归还人
	 */
	@ApiModelProperty(value = "归还人")
	private String returnee;
	/**
	 * 归还部门
	 */
	@ApiModelProperty(value = "归还部门")
	private String returnDepartment;
	/**
	 * 归还页数
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value = "归还页数")
	private Integer returnPages;
	/**
	 * 归还位置
	 */
	@ApiModelProperty(value = "归还位置")
	private String returnLocation;
	/**
	 * 经办人
	 */
	@ApiModelProperty(value = "经办人")
	private String manager;
	/**
	 * 经办单位
	 */
	@ApiModelProperty(value = "经办单位")
	private String manageUnit;
	/**
	 * 经办时间
	 */
	@ApiModelProperty(value = "经办时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date manageDate;
	/**
	 * 借阅处理信息
	 * @TableField 不查询此对象属性
	 */
//	@ApiModelProperty(value = "借阅处理信息")
//	@TableField(exist = false)
//	private ContractBorrowHandleEntity handleEntity;

}
