package org.springblade.contract.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springblade.core.tool.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 合同解除 实体类
 *
 * @author 合同解除
 * @date : 2020-11-05 09:24:00
 */
@Getter
@Setter
@TableName("contract_relieve")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractRelieve对象", description = "合同解除")
public class ContractRelieveEntity extends BaseEntity {

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
	@ApiModelProperty(value="承办人")
    private Long signingBasis;

}
