package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;


/**
 * 售货机类：0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014） 实体类
 *
 * @author 售货机类：0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014）
 * @date : 2020-12-18 16:05:23
 */
@Getter
@Setter
@TableName("shjb_point_operation")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ShjbPointOperation对象", description = "售货机类：0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014）")
public class ShjbPointOperationEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方联系地址
	 */
    @ApiModelProperty(value="甲方联系地址")
	private String addressPartyA;
	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String partyB;
	/**
	 * 乙方联系地址
	 */
    @ApiModelProperty(value="乙方联系地址")
	private String addressPartyB;
	/**
	 * 乙方自愿将位于【？】的
	 */
    @ApiModelProperty(value="乙方自愿将位于【？】的")
	private String partyBVoluntarily;
	/**
	 * 个点位以每个点位
	 */
    @ApiModelProperty(value="个点位以每个点位")
	private BigDecimal pointDivided;
	/**
	 * 元的含税价格授权给甲方使用
	 */
    @ApiModelProperty(value="元的含税价格授权给甲方使用")
	private BigDecimal authorizedParty;
	/**
	 * 本合同约定中的双方合作售货机，甲方同意均开放【？】货道/台供乙方销售其自选产品使用
	 */
    @ApiModelProperty(value="本合同约定中的双方合作售货机，甲方同意均开放【？】货道/台供乙方销售其自选产品使用")
	private BigDecimal vendingMachines;
	/**
	 * 点位费（含税）
	 */
    @ApiModelProperty(value="点位费（含税）")
	private BigDecimal spotFee;
	/**
	 * 支付方式
	 */
    @ApiModelProperty(value="支付方式")
	private String paymentMethod;
	/**
	 * 名称
	 */
    @ApiModelProperty(value="名称")
	private String name;
	/**
	 * 税号
	 */
    @ApiModelProperty(value="税号")
	private String dutyParagraph1;
	/**
	 * 地址及电话
	 */
    @ApiModelProperty(value="地址及电话")
	private String addressTelephone;
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
	private String fullNameOpening;
	/**
	 * 名称
	 */
    @ApiModelProperty(value="名称")
	private String name1;
	/**
	 * 税号
	 */
    @ApiModelProperty(value="税号")
	private String dutyParagraph;
	/**
	 * 地址及电话
	 */
    @ApiModelProperty(value="地址及电话")
	private String addressTelephone1;
	/**
	 * 开户行及账号
	 */
    @ApiModelProperty(value="开户行及账号")
	private String accountBankAccount;
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
	private String fullOpeningBank;
	/**
	 * （第三.5项次数要求）
	 */
    @ApiModelProperty(value="（第三.5项次数要求）")
	private String frequencyRequirement;
	/**
	 * （含【？】次）
	 */
    @ApiModelProperty(value="（含【？】次）")
	private BigDecimal including;

}
