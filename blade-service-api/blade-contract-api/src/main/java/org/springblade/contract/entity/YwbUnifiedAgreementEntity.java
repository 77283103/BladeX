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
 * 2021年统一e商城平台入驻服务协议（统一经销商） 实体类
 *
 * @author 2021年统一e商城平台入驻服务协议（统一经销商）
 * @date : 2020-12-18 16:03:27
 */
@Getter
@Setter
@TableName("ywb_unified_agreement")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "YwbUnifiedAgreement对象", description = "2021年统一e商城平台入驻服务协议（统一经销商）")
public class YwbUnifiedAgreementEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
    @ApiModelProperty(value="甲方")
	private String firstParty;
	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String partyB;
	/**
	 * 乙方地址
	 */
    @ApiModelProperty(value="乙方地址")
	private String addressB;
	/**
	 * 公司名称
	 */
    @ApiModelProperty(value="公司名称")
	private String corporateName;
	/**
	 * 企业社会信息码
	 */
    @ApiModelProperty(value="企业社会信息码")
	private String enterpriseCode;
	/**
	 *  地址及电话
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value=" 地址及电话")
	private Integer addressTelephone;
	/**
	 * 开户行及账号
	 */
    @ApiModelProperty(value="开户行及账号")
	private String bank number;
	/**
	 * 发票类型
	 */
    @ApiModelProperty(value="发票类型")
	private String invoiceType;
	/**
	 * 发票传递方式
	 */
    @ApiModelProperty(value="发票传递方式")
	private String deliveryMethod;
	/**
	 * 手机推送
	 */
    @ApiModelProperty(value="手机推送")
	private String mobilePush;
	/**
	 * 店铺经营类目
	 */
    @ApiModelProperty(value="店铺经营类目")
	private String storeOperation;
	/**
	 * 系统服务费率
	 */
    @ApiModelProperty(value="系统服务费率")
	private String serviceRate;
	/**
	 * 乙方店铺服务期限
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="乙方店铺服务期限")
	private Date periodShop;
	/**
	 * 开始时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="开始时间")
	private Date startTime;
	/**
	 * 结束时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="结束时间")
	private Date endTime;
	/**
	 * 备注
	 */
    @ApiModelProperty(value="备注")
	private String remarks;

}
