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
 * 生产类：设备维修保养合同(关联表） 实体类
 *
 * @author kx
 * @date : 2020-12-11 10:59:49
 */
@Getter
@Setter
@TableName("scl_equipment_maintenance_1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclEquipmentMaintenance1对象", description = "生产类：设备维修保养合同(关联表）")
public class SclEquipmentMaintenance1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="序号")
	private Integer sclNumber;
	/**
	 * 设备名称
	 */
    @ApiModelProperty(value="设备名称")
	private String sclDeviceName;
	/**
	 * 品牌
	 */
    @ApiModelProperty(value="品牌")
	private String sclBrand;
	/**
	 * 型号、规格
	 */
    @ApiModelProperty(value="型号、规格")
	private String sclSpecification;
	/**
	 * 服务单价（未税）
	 */
    @ApiModelProperty(value="服务单价（未税）")
	private BigDecimal sclPrice;
	/**
	 * 数量
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="数量")
	private Integer sclNumbers;
	/**
	 * 其他
	 */
    @ApiModelProperty(value="其他")
	private String sclOther;
	/**
	 * 合同ID
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="合同ID")
	private Long contractId;

}
