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
 * 采购类：新增原物料补充协议--买卖合同 实体类
 *
 * @author 王策
 * @date : 2020-12-10 19:07:47
 */
@Getter
@Setter
@TableName("cgl_the_sales_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglTheSalesContract对象", description = "采购类：新增原物料补充协议--买卖合同")
public class CglTheSalesContractEntity extends BaseEntity {

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
	private String cglDomicileB;
	/**
	 * 鉴于，甲乙双方签署了一份关于采购“【？】
	 */
    @ApiModelProperty(value="鉴于，甲乙双方签署了一份关于采购“【？】")
	private String cglProcurement;
	/**
	 * 产品的《买卖合同》（合同编号：【？】，以下简称“原合同”）
	 */
    @ApiModelProperty(value="产品的《买卖合同》（合同编号：【？】，以下简称“原合同”）")
	private String cglContractNo;
	/**
	 * 现经甲方与乙方协商一致，就于原合同基础上新增采购“【？】产品”的买卖事宜达成以下一致补充约定
	 */
    @ApiModelProperty(value="现经甲方与乙方协商一致，就于原合同基础上新增采购“【？】产品”的买卖事宜达成以下一致补充约定")
	private String cglProduct;
	/**
	 * 新增标的交货数量，双方确认为【？】
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="新增标的交货数量，双方确认为【？】")
	private Integer cglNumberOfDelivery;
	/**
	 * 采购数量为【？】KG
	 */
    @ApiModelProperty(value="采购数量为【？】KG")
	private Double cglPurchaseQuantity;
	/**
	 * 新增标的结算，双方约定采用：双方第【？】种方式付款
	 */
    @ApiModelProperty(value="新增标的结算，双方约定采用：双方第【？】种方式付款")
	private String cglTermsOfPayment;
	/**
	 * 本协议经双方盖章后自【？】年【？】月【？】日起生效
	 */
    @ApiModelProperty(value="本协议经双方盖章后自【？】年【？】月【？】日起生效")
	private String cglEffectOfTime;
	/**
	 * 终止时间双方约定采用如下第【？】种方式确认
	 */
    @ApiModelProperty(value="终止时间双方约定采用如下第【？】种方式确认")
	private String cglConvention;

}
