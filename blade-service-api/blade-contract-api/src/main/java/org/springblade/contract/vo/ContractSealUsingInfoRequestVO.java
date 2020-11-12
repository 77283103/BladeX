package org.springblade.contract.vo;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import org.springblade.core.mp.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;


/**
 * 合同用印 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 合同用印
 * @date : 2020-11-05 09:29:26
 */
@Getter
@Setter
@ToString
@ApiModel(description = "合同用印请求对象")
public class ContractSealUsingInfoRequestVO extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="关联合同ID")
	private Long refContractId;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="用印时间")
	private Date signTime;

	@ApiModelProperty(value="用印人")
	private String  signPerson;

	@ApiModelProperty(value="承办单位")
	private String manageUnit;

	@ApiModelProperty(value="承办部门")
	private String manageDept;

	@ApiModelProperty(value="承办人")
	private String manager;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="承办时间")
	private Date manageDate;

	@JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value="实际用印份数")
	private Integer actualPrintingCount;

	@ApiModelProperty(value="实际用印类型（字典）")
	private String actualPrintingType;

	@ApiModelProperty(value="合同类型（字典）")
	private String contractType;

	@ApiModelProperty(value="用印说明")
	private String signRemark;
}
