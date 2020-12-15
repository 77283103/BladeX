package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 生产类：生产项目外包服务合同 实体类
 *
 * @author kx
 * @date : 2020-12-11 11:03:45
 */
@Getter
@Setter
@TableName("scl_project_outsourcing")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclProjectOutsourcing对象", description = "生产类：生产项目外包服务合同")
public class SclProjectOutsourcingEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 委托方
	 */
    @ApiModelProperty(value="委托方")
	private String sclDelegate;
	/**
	 * 委托方住所
	 */
    @ApiModelProperty(value="委托方住所")
	private String sclResidence;
	/**
	 * 承包方
	 */
    @ApiModelProperty(value="承包方")
	private String sclContractor;
	/**
	 * 承包方住所
	 */
    @ApiModelProperty(value="承包方住所")
	private String sclPremises;
	/**
	 * 序号
	 */
    @ApiModelProperty(value="序号")
	private String sclNumber;
	/**
	 * 承包项目服务期限：开始时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="承包项目服务期限：开始时间")
	private Date sclStartTime;
	/**
	 * 承包项目服务期限：结束时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="承包项目服务期限：结束时间")
	private Date sclEndTime;
	/**
	 * （第四.5.（1）项赔偿金）：【？】
	 */
    @ApiModelProperty(value="（第四.5.（1）项赔偿金）：【？】")
	private String sclCompensation;
	/**
	 * 未投保人数比例超过【？】%时，甲方还有权立即终止与乙方之间的合同而不承担任何违约责任
	 */
    @ApiModelProperty(value="未投保人数比例超过【？】%时，甲方还有权立即终止与乙方之间的合同而不承担任何违约责任")
	private Double sclProportion;
	/**
	 * （第四.12项违约金）：【？】
	 */
    @ApiModelProperty(value="（第四.12项违约金）：【？】")
	private String sclContract;
	/**
	 * 乙方联系人
	 */
    @ApiModelProperty(value="乙方联系人")
	private String sclContact;
	/**
	 * 乙方联系方式
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乙方联系方式")
	private Integer sclContacts;
	/**
	 * 服务费用采用月结方式，次月【？】日前，乙方与甲方共同确认上月服务费用
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="服务费用采用月结方式，次月【？】日前，乙方与甲方共同确认上月服务费用")
	private Integer sclServiceFee;
	/**
	 * 确认无误后由乙方于次月【？】日
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="确认无误后由乙方于次月【？】日")
	private Integer sclMonth;
	/**
	 * 甲方收到发票后【？】日内
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="甲方收到发票后【？】日内")
	private Integer sclInvoice;
	/**
	 * 以【？】形式支付
	 */
    @ApiModelProperty(value="以【？】形式支付")
	private String sclPay;
	/**
	 * 户名
	 */
    @ApiModelProperty(value="户名")
	private String sclAccountName;
	/**
	 * 账号
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="账号")
	private Integer sclAccount;
	/**
	 * 开户行
	 */
    @ApiModelProperty(value="开户行")
	private String sclShere;
	/**
	 * 履约保证金：【？】万元
	 */
    @ApiModelProperty(value="履约保证金：【？】万元")
	private BigDecimal sclPerformanceBond;
	/**
	 * 发生履约保证金扣除后，乙方应在【？】日内将履约保证金补足
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="发生履约保证金扣除后，乙方应在【？】日内将履约保证金补足")
	private Integer sclMargin;
	/**
	 * 超过【？】日仍未补足的，甲方有权解除本合同
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="超过【？】日仍未补足的，甲方有权解除本合同")
	private Integer sclRemove;
	/**
	 * 保证金收款：户名
	 */
    @ApiModelProperty(value="保证金收款：户名")
	private String sclMarginCollection;
	/**
	 * 保证金收款：账号
	 */
    @ApiModelProperty(value="保证金收款：账号")
	private String sclAccounta;
	/**
	 * 保证金收款：开户行
	 */
    @ApiModelProperty(value="保证金收款：开户行")
	private String sclSdhere;
	/**
	 * （第七.2项违约金）
	 */
    @ApiModelProperty(value="（第七.2项违约金）")
	private String sclBreachA;
	/**
	 * （第七.4项违约金）
	 */
    @ApiModelProperty(value="（第七.4项违约金）")
	private String sclBreachB;
	/**
	 * （第七.5项违约金）
	 */
    @ApiModelProperty(value="（第七.5项违约金）")
	private String sclBreachC;
	/**
	 * （第七.6项违约金）
	 */
    @ApiModelProperty(value="（第七.6项违约金）")
	private String sclBreachD;
	/**
	 * （第七.8项违约金）
	 */
    @ApiModelProperty(value="（第七.8项违约金）")
	private String sclBreachF;
	/**
	 * 补充约定
	 */
    @ApiModelProperty(value="补充约定")
	private String sclAgreement;
	/**
	 * 生产项目外包服务合同
	 */
	@ApiModelProperty(value="生产项目外包服务合同")
	@TableField(exist = false)
    private List<SclProjectOutsourcing1Entity> sclProjectOutsourcing1EntityList;
}
