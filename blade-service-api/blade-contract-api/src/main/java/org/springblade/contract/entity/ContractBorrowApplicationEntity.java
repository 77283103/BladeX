package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
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

import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 借阅申请 实体类
 *
 * @author xhb
 * @date : 2020-10-30 09:27:01
 */
@Getter
@Setter
@TableName("contract_borrow_application")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractBorrowApplication对象", description = "借阅申请")
public class ContractBorrowApplicationEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 申请编号
	 */
	@ApiModelProperty(value = "申请编号")
	private String applicationId;
	/**
	 * 申请人
	 */
	@ApiModelProperty(value = "申请人")
	private String applicant;
	/**
	 * 申请人编号
	 */
	@ApiModelProperty(value = "申请人编号")
	private String applicantCode;
	/**
	 * 申请部门
	 */
	@ApiModelProperty(value = "申请部门")
	private String applicationDepartment;
	/**
	 * 资料类型
	 */
	@ApiModelProperty(value = "资料类型")
	private String dataType;
	/**
	 * 借阅周期（起始时间）
	 */
	@ApiModelProperty(value = "借阅周期（起始时间）")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date borrowCycleStart;
	/**
	 * 借阅周期（结束时间）
	 */
	@ApiModelProperty(value = "借阅周期（结束时间）")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date borrowCycleEnd;
	/**
	 * 借阅方式
	 */
	@ApiModelProperty(value = "借阅方式")
	private String borrowMode;
	/**
	 * 资料名称
	 */
	@ApiModelProperty(value = "资料名称")
	private String dataName;
	/**
	 * 事由说明
	 */
	@ApiModelProperty(value = "事由说明")
	private String explanation;
	/**
	 * 进度
	 */
	@ApiModelProperty(value = "进度")
	private String borrowSchedule;
	/**
	 * 借阅状态
	 */
	@ApiModelProperty(value = "借阅状态")
	private String borrowStatus;

	/**
	 * 借阅处理信息
	 * @TableField 不查询此对象属性
	 */
	@ApiModelProperty(value = "借阅处理信息")
	@TableField(exist = false)
	private ContractBorrowHandleEntity handleEntity;

	/**
	 * 借阅归还信息
	 * @TableField 不查询此对象属性
	 */
	@ApiModelProperty(value = "借阅归还信息")
	@TableField(exist = false)
	private ContractBorrowReturnEntity returnEntity;
}
