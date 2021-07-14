package org.springblade.contract.vo;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import org.springblade.core.mp.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;


/**
 * 合同解除 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 合同解除
 * @date : 2020-11-05 09:24:00
 */
@Getter
@Setter
@ToString
@ApiModel(description = "合同解除请求对象")
public class ContractRelieveRequestVO extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 关联合同ID
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="关联合同ID")
	private Long refContractId;
	/**
	 * 解除日期
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="解除日期")
	private Date rescissionDate;
	/**
	 * 解除原因（字典）
	 */
	@ApiModelProperty(value="解除原因（字典）")
	private String relieveCategory;
	/**
	 * 违约金额
	 */
	@ApiModelProperty(value="违约金额")
	private BigDecimal breachAmount;
	/**
	 * 违约金流向(字典)
	 */
	@ApiModelProperty(value="违约金流向(字典)")
	private String breachCategory;
	/**
	 * 赔偿金额
	 */
	@ApiModelProperty(value="赔偿金额")
	private BigDecimal compensationAmount;
	/**
	 * 赔偿款流向（字典）
	 */
	@ApiModelProperty(value="赔偿款流向（字典）")
	private String compensationCategory;
	/**
	 * 解除说明
	 */
	@ApiModelProperty(value="解除说明")
	private String relieveRemark;
	/**
	 * 解除协议（文件）
	 */
	@ApiModelProperty(value="解除协议（文件）")
	private String termAgreement;
	/**
	 * 承办单位
	 */
	@ApiModelProperty(value="承办单位")
	private String manageUnit;
	/**
	 * 承办部门
	 */
	@ApiModelProperty(value="承办部门")
	private String manageDept;
	/**
	 * 承办人
	 */
	@ApiModelProperty(value="承办人")
	private String manager;
	/**
	 * 承办时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="承办时间")
	private Date manageDate;

	/**
	 * 对应签呈依据
	 */
	@ApiModelProperty(value="对应签呈依据")
	private Long signingBasis;

}
