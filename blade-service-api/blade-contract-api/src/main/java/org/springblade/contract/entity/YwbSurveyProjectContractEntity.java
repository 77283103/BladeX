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
 * 业务类：20.售点普查项目合同 实体类
 *
 * @author 业务类：20.售点普查项目合同
 * @date : 2020-12-18 16:06:56
 */
@Getter
@Setter
@TableName("ywb_survey_project_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "YwbSurveyProjectContract对象", description = "业务类：20.售点普查项目合同")
public class YwbSurveyProjectContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
    @ApiModelProperty(value="甲方")
	private String firstParty;
	/**
	 * 甲方住所
	 */
    @ApiModelProperty(value="甲方住所")
	private String addressPartyA;
	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String partyB;
	/**
	 * 乙方住所
	 */
    @ApiModelProperty(value="乙方住所")
	private String addressPartyB;
	/**
	 * 包括但不限于乙方为履行【？】合同之目的而向乙方人员和第三方支付的所有服务报酬和费用
	 */
    @ApiModelProperty(value="包括但不限于乙方为履行【？】合同之目的而向乙方人员和第三方支付的所有服务报酬和费用")
	private String partyPerformance;
	/**
	 * 甲方地址
	 */
    @ApiModelProperty(value="甲方地址")
	private String addressPartyA1;
	/**
	 * 主要联系人
	 */
    @ApiModelProperty(value="主要联系人")
	private String primaryContact;
	/**
	 * 电子邮箱
	 */
    @ApiModelProperty(value="电子邮箱")
	private String mailBox;
	/**
	 * 邮编
	 */
    @ApiModelProperty(value="邮编")
	private String postcode;
	/**
	 * 电话号码
	 */
    @ApiModelProperty(value="电话号码")
	private String telephoneNumber;
	/**
	 * 传真号码
	 */
    @ApiModelProperty(value="传真号码")
	private String faxNumber;
	/**
	 * 乙方地址
	 */
    @ApiModelProperty(value="乙方地址")
	private String addressPartyB1;
	/**
	 * 主要联系人
	 */
    @ApiModelProperty(value="主要联系人")
	private String primaryContact1;
	/**
	 * 电子邮箱
	 */
    @ApiModelProperty(value="电子邮箱")
	private String mailBox1;
	/**
	 * 邮编
	 */
    @ApiModelProperty(value="邮编")
	private String postcode1;
	/**
	 * 电话号码
	 */
    @ApiModelProperty(value="电话号码")
	private String telephoneNumber1;
	/**
	 * 传真号码
	 */
    @ApiModelProperty(value="传真号码")
	private String faxNumber1;
	/**
	 * 合同有效期为【？】年
	 */
    @ApiModelProperty(value="合同有效期为【？】年")
	private BigDecimal year;
	/**
	 * 月
	 */
    @ApiModelProperty(value="月")
	private BigDecimal month;
	/**
	 * 日
	 */
    @ApiModelProperty(value="日")
	private BigDecimal day;
	/**
	 * 年
	 */
    @ApiModelProperty(value="年")
	private String year1;
	/**
	 * 月
	 */
    @ApiModelProperty(value="月")
	private String month1;
	/**
	 * 日
	 */
    @ApiModelProperty(value="日")
	private String day1;

}
