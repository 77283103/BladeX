package org.springblade.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 国内设备买卖合同 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author kx
 * @date : 2021-05-10 13:39:21
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "国内设备买卖合同请求对象")
public class ContractMmhtRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="?统一")
	private String contactPartyAA;

    @ApiModelProperty(value="统一？有限公司")
	private String contactPartyAB;

    @ApiModelProperty(value="甲方住所")
	private String contactPartyAAddr;

    @ApiModelProperty(value="乙方住所")
	private String contactPartyBAddr;

    @ApiModelProperty(value="未税总价人民币?元")
	private BigDecimal contactYuanSma;

    @ApiModelProperty(value="未税总价人民币?元大写")
	private String contactYuanBig;

    @ApiModelProperty(value="包装标准")
	private String contactBzbz;

    @ApiModelProperty(value="交货时间")
	private Date contactJhss;

    @ApiModelProperty(value="交货地址")
	private String contactJhdz;

    @ApiModelProperty(value="运输方式")
	private String contactYsff;

    @ApiModelProperty(value="运费承担方")
	private String contactCdf;

    @ApiModelProperty(value="卸货方")
	private String contactBcdf;

    @ApiModelProperty(value="费用承担方")
	private String contactFycdf;

    @ApiModelProperty(value="特别约定")
	private String contactTbyd;

    @ApiModelProperty(value="乙方应随设备提供以下文件?")
	private String contactFile;

    @ApiModelProperty(value="七2（2）特别约定")
	private String contactSpecil;

    @ApiModelProperty(value="甲方有权在?时至乙方工厂对设备状况进行初步检查")
	private String contactWhen;

    @ApiModelProperty(value="甲方收到乙方的设备后，应在?日内")
	private String contactDay;

    @ApiModelProperty(value="本合同适用以下?的约定")
	private String contactNext;

    @ApiModelProperty(value="乙方应在甲方指定的期限内派人员在?日内")
	private String contactDayt;

    @ApiModelProperty(value="甲方确认安装调试运行?小时无误后")
	private String contactHour;

    @ApiModelProperty(value="安装调试运行?小时")
	private String contactHourt;

    @ApiModelProperty(value="本合同适用以下?的约定")
	private String contactSpecilf;

    @ApiModelProperty(value="质保期满?天内")
	private String contactDayf;

    @ApiModelProperty(value="乙方应在?天内")
	private String contactDays;

    @ApiModelProperty(value="逾期?天")
	private String contactDayx;

    @ApiModelProperty(value="合同总金额?%作为违约金")
	private Double contactPercent;

    @ApiModelProperty(value="七3（6）特别约定")
	private String contactSpecilu;

    @ApiModelProperty(value="双方同意采用以下第?种约定付款")
	private String contactTypeg;

    @ApiModelProperty(value="款项均以?方式支付")
	private String contactTypes;

    @ApiModelProperty(value="发票后?日内付清全款")
	private String contactDayn;

    @ApiModelProperty(value="甲方与乙方签订本合同后?日内")
	private String contactDayk;

    @ApiModelProperty(value="合同总金额?%")
	private Double contactPencenty;

    @ApiModelProperty(value="发货前在乙方工厂初验合格后?日内")
	private String contactAcceptw;

    @ApiModelProperty(value="支付合同总金额?%")
	private Double contactPencentr;

    @ApiModelProperty(value="甲方收货验收合格后?日内")
	private String contactAccept;

    @ApiModelProperty(value="付合同总金额?%")
	private Double contactPencente;

    @ApiModelProperty(value="设备安装调试经甲方竣工验收合格后?日内")
	private String contactDayp;

    @ApiModelProperty(value="付合同总金额?%")
	private Double contactPencentw;

    @ApiModelProperty(value="合同总金额?%作为质保金")
	private Double contactPencentq;

    @ApiModelProperty(value="质保验收合格后?日内")
	private String contactQuaty;

    @ApiModelProperty(value="前述金额合法税务发票提供时间")
	private Date contactLawtime;

    @ApiModelProperty(value="其他方式")
	private String contactElsytype;

    @ApiModelProperty(value="乙方开户行")
	private String contactBbank;

    @ApiModelProperty(value="乙方户  名")
	private String contactBname;

    @ApiModelProperty(value="乙方账  号")
	private String contactBnum;

    @ApiModelProperty(value="方竣工验收合格之日）起?个月")
	private String contactMonth;

    @ApiModelProperty(value="乙方应在接到甲方通知后?天内")
	private String contactMonthq;

    @ApiModelProperty(value="若经甲方?次催告")
	private String contactMonthw;

    @ApiModelProperty(value="（3）特别约定?")
	private String contactMonthe;

    @ApiModelProperty(value="并向甲方支付合同总金额?%作为违约金")
	private Double contactMonthr;

    @ApiModelProperty(value="本合同总金额的 ?%的违约金")
	private Double contactMontht;

    @ApiModelProperty(value="逾期交货超过（含）?日")
	private String contactMonthy;

    @ApiModelProperty(value="付款金额的?%作为违约金")
	private Double contactMonthyy;

    @ApiModelProperty(value="产品质量鉴定以甲方住所地?单位出具")
	private String contactMonthu;

    @ApiModelProperty(value="应向甲方支付合同总金额?%作为违约金")
	private Double contactMonthi;

    @ApiModelProperty(value="7、其他约定?")
	private String contactMontho;

    @ApiModelProperty(value="本合同一式?份")
	private String contactMonthp;

    @ApiModelProperty(value="甲方执?份")
	private String contactMontha;

    @ApiModelProperty(value="乙方执?份")
	private String contactMonths;

    @ApiModelProperty(value="本合同包括以下附件?")
	private String contactMonthd;

    @ApiModelProperty(value="十八：特别约定?")
	private String contactMonthf;

    @ApiModelProperty(value="甲方联系地址")
	private String contactPartyAPlace;

    @ApiModelProperty(value="乙方联系地址")
	private String contactPartyBPlace;

    @ApiModelProperty(value="甲方联系人")
	private String contactPartyAPerson;

    @ApiModelProperty(value="乙方联系人")
	private String contactPartyBPerson;

    @ApiModelProperty(value="甲方联系电话")
	private String contactPartyAPhone;

    @ApiModelProperty(value="乙方联系电话")
	private String contactPartyBPhone;

}
