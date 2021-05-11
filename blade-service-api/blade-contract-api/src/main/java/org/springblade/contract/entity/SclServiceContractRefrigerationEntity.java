package org.springblade.contract.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springblade.core.tool.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


/**
 * kx 实体类
 *
 * @author kx
 * @date : 2021-03-15 13:48:14
 */
@Getter
@Setter
@TableName("scl_service_contract_refrigeration")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclServiceContractRefrigeration对象", description = "kx")
public class SclServiceContractRefrigerationEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
    @ApiModelProperty(value="甲方")
	private String sclPatyA;
	/**
	 * 甲方地址
	 */
    @ApiModelProperty(value="甲方地址")
	private String sclPatyAAddress;
	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String sclPatyB;
	/**
	 * 乙方地址
	 */
    @ApiModelProperty(value="乙方地址")
	private String sclPatyBAddress;
	/**
	 * 签订日期
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="签订日期")
	private Date sclSigningDate;
	/**
	 * 签订地点
	 */
    @ApiModelProperty(value="签订地点")
	private String sclSite;
	/**
	 * 交接日期
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="交接日期")
	private Date sclHandover;
	/**
	 * 每月与甲方核对日期（D+)
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="每月与甲方核对日期（D+)")
	private Integer sclCheckTheDate;
	/**
	 * 油价标准
	 */
    @ApiModelProperty(value="油价标准")
	private String sclPriceStandard;
	/**
	 * 违约金为总结的倍数
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="违约金为总结的倍数")
	private Integer sclMultiple;
	/**
	 * 需更改送货地址的，应由甲方授权人员至少提前上海市内【？】小时
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="需更改送货地址的，应由甲方授权人员至少提前上海市内【？】小时")
	private Integer sclFewHours;
	/**
	 * 华东区（即   ？ 省、    省）
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="华东区（即   ？ 省、    省）")
	private Integer sclEast;
	/**
	 * 华东区（即   省、   ？ 省）
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="华东区（即   省、   ？ 省）")
	private Integer sclEast2;
	/**
	 * 乙方缴纳保证金人民币
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乙方缴纳保证金人民币")
	private Integer sclMarginRmb;
	/**
	 * 乙方缴纳保证金人民币（大写）
	 */
    @ApiModelProperty(value="乙方缴纳保证金人民币（大写）")
	private String sclMarginRmb2;
	/**
	 * 甲方有权自应付给乙方运费中直接单方扣款以补足至  ？万元
	 */
    @ApiModelProperty(value="甲方有权自应付给乙方运费中直接单方扣款以补足至  ？万元")
	private String sclFewDollars;
	/**
	 * 合同履行期限自？起
	 */
    @ApiModelProperty(value="合同履行期限自？起")
	private String sclStartTime;
	/**
	 * 合同履行期限到？止
	 */
    @ApiModelProperty(value="合同履行期限到？止")
	private String sclEndOfTime;
	/**
	 * 甲方签订日期
	 */
    @ApiModelProperty(value="甲方签订日期")
	private String sclPartyAToSign;
	/**
	 * 乙方签订日期
	 */
    @ApiModelProperty(value="乙方签订日期")
	private String sclPartyBToSign;
	/**
	 * 本承诺书自签立日起生效。
	 */
    @ApiModelProperty(value="本承诺书自签立日起生效")
	private String sclAdvance;
	/**
	 * 乙方法定代表人
	 */
    @ApiModelProperty(value="乙方法定代表人")
	private String sclLegalRepresentative;
	/**
	 * 地址
	 */
    @ApiModelProperty(value="地址")
	private String sclAddress;
	/**
	 * 立约日
	 */
    @ApiModelProperty(value="立约日")
	private String sclSomeDay;
	/**
	 * ？公司
	 */
    @ApiModelProperty(value="？公司")
	private String sclNameOfTheCompany;
	/**
	 * ？公司
	 */
    @ApiModelProperty(value="？公司")
	private String sclNameOfTheCompany2;
	/**
	 * 广东省【？】小时通知乙方
	 */
	@ApiModelProperty(value="广东省【？】小时通知乙方")
	private String sclNotified;
	/**附件
	 */
	@ApiModelProperty(value="附件")
	private String annex;

}
