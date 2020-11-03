package org.springblade.cases.entity;

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
 * 案件结案 实体类
 *
 * @author xhb
 * @date : 2020-10-30 10:03:17
 */
@Getter
@Setter
@TableName("contract_case_closed")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractCaseClosed对象", description = "案件结案")
public class ContractCaseClosedEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 结案案件编号
	 */
	@ApiModelProperty(value = "结案案件编号")
	private String closeCaseId;
	/**
	 * 结案时间
	 */
	@ApiModelProperty(value = "结案时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date closeCaseDate;
	/**
	 * 是否已归档
	 */
	@ApiModelProperty(value = "是否已归档")
	private String isArchive;
	/**
	 * 结案说明
	 */
	@ApiModelProperty(value = "结案说明")
	private String closeCaseDescription;
	/**
	 * 结案文书
	 */
	@ApiModelProperty(value = "结案文书")
	private String closeCaseDocument;

}
