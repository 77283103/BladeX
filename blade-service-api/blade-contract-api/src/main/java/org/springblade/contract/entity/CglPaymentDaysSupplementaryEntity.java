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
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 采购类：账期补充协议--买卖合同 实体类
 *
 * @author 王策
 * @date : 2020-12-10 19:21:44
 */
@Getter
@Setter
@TableName("cgl_payment_days_supplementary")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglPaymentDaysSupplementary对象", description = "采购类：账期补充协议--买卖合同")
public class CglPaymentDaysSupplementaryEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
    @ApiModelProperty(value="甲方")
	private String cglPartyA;
	/**
	 * 甲方住所
	 */
    @ApiModelProperty(value="甲方住所")
	private String cglDomicile;
	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String cglPartyB;
	/**
	 * 乙方住所
	 */
    @ApiModelProperty(value="乙方住所")
	private String cglAboutPurchasing;
	/**
	 * 甲乙双方签署了一份关于采购“【？】”
	 */
    @ApiModelProperty(value="甲乙双方签署了一份关于采购“【？】”")
	private String cglProcurement;
	/**
	 * 产品的《买卖合同》（合同编号：【？】，以下简称“原合同”）
	 */
    @ApiModelProperty(value="产品的《买卖合同》（合同编号：【？】，以下简称“原合同”）")
	private String cglContractNo;
	/**
	 * 将原合同付款方式自【？】年【？】月【？】日发出的订购单开始变更
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="将原合同付款方式自【？】年【？】月【？】日发出的订购单开始变更")
	private Date cglSidesAgreed;
	/**
	 * 以下第【？】种方式
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="以下第【？】种方式")
	private Integer cglWay;

}
