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
 * 原物料1v多 实体类
 *
 * @author szw
 * @date : 2020-11-22 16:42:02
 */
@Getter
@Setter
@TableName("contract_raw_materials")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractRawMaterials对象", description = "原物料1v多")
public class ContractRawMaterialsEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 申请用章全称
	 */
    @ApiModelProperty(value="申请用章全称")
	private String ywlFullName;
	/**
	 * 相对方名称
	 */
    @ApiModelProperty(value="相对方名称")
	private String ywlNameOfOpposite;
	/**
	 * 序号
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="序号")
	private Integer ywlSerialNumber;
	/**
	 * 料号
	 */
    @ApiModelProperty(value="料号")
	private String ywlItemNo;
	/**
	 * 品名
	 */
    @ApiModelProperty(value="品名")
	private String ywlProductName;
	/**
	 * 规格
	 */
    @ApiModelProperty(value="规格")
	private String ywlSpecifications;
	/**
	 * 单位
	 */
    @ApiModelProperty(value="单位")
	private String ywlCompany;
	/**
	 * 未税单价（元/单位)
	 */
    @ApiModelProperty(value="未税单价（元/单位)")
	private BigDecimal ywlPrice;
	/**
	 * 备注
	 */
    @ApiModelProperty(value="备注")
	private String ywlRemarks;
	/**
	 * 关联合同id
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="关联合同id")
	private Long contractId;

}
