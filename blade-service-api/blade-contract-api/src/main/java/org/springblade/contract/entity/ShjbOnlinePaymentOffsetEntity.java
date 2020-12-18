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
 * 售货机类：0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014） 实体类
 *
 * @author 售货机类：0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014）
 * @date : 2020-12-18 16:04:41
 */
@Getter
@Setter
@TableName("shjb_online_payment_offset")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ShjbOnlinePaymentOffset对象", description = "售货机类：0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014）")
public class ShjbOnlinePaymentOffsetEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方联系地址
	 */
    @ApiModelProperty(value="甲方联系地址")
	private String contactPartyA;
	/**
	 * 乙方联系地址
	 */
    @ApiModelProperty(value="乙方联系地址")
	private String contactPartyB;
	/**
	 * 本合同约定中的双方合作售货机，甲方同意均开放【？】货道/台供乙方销售其自选产品使用
	 */
    @ApiModelProperty(value="本合同约定中的双方合作售货机，甲方同意均开放【？】货道/台供乙方销售其自选产品使用")
	private String channelPlatform;
	/**
	 * 名称
	 */
    @ApiModelProperty(value="名称")
	private String name;
	/**
	 * 税号
	 */
    @ApiModelProperty(value="税号")
	private String dutyParagraph;
	/**
	 * 地址及电话
	 */
    @ApiModelProperty(value="地址及电话")
	private String addressNumber;
	/**
	 * 开户行及账号
	 */
    @ApiModelProperty(value="开户行及账号")
	private String bankAccount;
	/**
	 * 户名
	 */
    @ApiModelProperty(value="户名")
	private String accountName;
	/**
	 * 账号
	 */
    @ApiModelProperty(value="账号")
	private String accountNumber;
	/**
	 * 开户行全称
	 */
    @ApiModelProperty(value="开户行全称")
	private String nameOpeningBank;
	/**
	 * 户名
	 */
    @ApiModelProperty(value="户名")
	private String accountName1;
	/**
	 * 账号
	 */
    @ApiModelProperty(value="账号")
	private String accountNumber1;
	/**
	 * 开户行全称
	 */
    @ApiModelProperty(value="开户行全称")
	private String nameOpeningBank1;
	/**
	 * （第三.4.1项次数要求）
	 */
    @ApiModelProperty(value="（第三.4.1项次数要求）")
	private BigDecimal frequencyRequirement;
	/**
	 * （含【？】次）
	 */
    @ApiModelProperty(value="（含【？】次）")
	private BigDecimal times;
	/**
	 * 年
	 */
    @ApiModelProperty(value="年")
	private BigDecimal year;
	/**
	 * 月
	 */
    @ApiModelProperty(value="月")
	private BigDecimal month;
	/**
	 * 日 至
	 */
    @ApiModelProperty(value="日 至")
	private BigDecimal day;
	/**
	 * 年
	 */
    @ApiModelProperty(value="年")
	private BigDecimal year1;
	/**
	 * 月
	 */
    @ApiModelProperty(value="月")
	private BigDecimal month1;
	/**
	 * 日
	 */
    @ApiModelProperty(value="日")
	private String day1;

}
