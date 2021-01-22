package org.springblade.contract.vo;

import lombok.EqualsAndHashCode;
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
 * 媒体类：平面广告拍摄制作合同 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 韩杨
 * @date : 2021-01-21 11:26:18
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "媒体类：平面广告拍摄制作合同请求对象")
public class MtbProductionContractRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="甲方")
	private String mtbPatyA;

	@ApiModelProperty(value="甲方联络邮箱")
	private String mtbContactEmail;

	@ApiModelProperty(value="甲方地址")
	private String mtbAddress;

	@ApiModelProperty(value="乙方")
	private String mtbPatyB;

	@ApiModelProperty(value="乙方联络邮箱")
	private String mtbPatyBEmail;

	@ApiModelProperty(value="住所")
	private String mtbPatyBAddress;

	@ApiModelProperty(value="甲方委托乙方完成【？】拍摄制作事宜")
	private String mtbMakeMatters;

	@ApiModelProperty(value="广告名称")
	private String mtbNameOfAdvertising;

	@ApiModelProperty(value="广告内容")
	private String mtbContentsOfAdvertisements;

	@ApiModelProperty(value="【？】（有/未）做成下拉选 二选一")
	private String mtbHaveHasNot;

	@ApiModelProperty(value="拍摄开始时间")
	private Date mtbShootingStartTime;

	@ApiModelProperty(value="拍摄完成时间")
	private Date mtbShootingCompletionTime;

	@ApiModelProperty(value="甲方验收人员")
	private String mtbAcceptancePersonnel;

	@ApiModelProperty(value="本广告制作合同价款为未税额人民币")
	private String mtbUnpaidTaxRmb;

	@ApiModelProperty(value="税率")
	private Double mtbRate;

	@ApiModelProperty(value="含税金额人民币")
	private BigDecimal mtbTaxInclusiveInRmb;

	@ApiModelProperty(value="合同付款时间及比例依以下第【？】项执行")
	private String mtbManyItems;

	@ApiModelProperty(value="合同签订且甲方收到乙方相应金额合法税务发票后 【？】天内")
	private String mtbLegalTaxInvoice;

	@ApiModelProperty(value="乙方支付人民币（大写）【？】元")
	private String mtbPayRmb;

	@ApiModelProperty(value="甲方向乙方支付剩余全部款项即人民币（大写）【？】元。")
	private String mtbAllRemainingMoney;

	@ApiModelProperty(value="乙方公司名")
	private String mtbCompanyName;

	@ApiModelProperty(value="乙方开户行")
	private String mtbBankOfPartyB;

	@ApiModelProperty(value="乙方账号")
	private String mtbPartyAccount;

	@ApiModelProperty(value="合同包含甲方对本广告中出现演员的肖像权 【？】 年")
	private String mtbPortrait;

	@ApiModelProperty(value="合同签订日期")
	private Date qiandingTime;

	@ApiModelProperty(value="知识产权确认书签订日期")
	private Date zhiqiandingTime;

	@ApiModelProperty(value="知识产权编号")
	private String zhichichanquan;

	@ApiModelProperty(value="合同编号")
	private String hetongbianhao;

	@ApiModelProperty(value="委托我司进行****之工作")
	private String weituo;

}

