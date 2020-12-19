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
 * 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1) 实体类
 *
 * @author 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1)
 * @date : 2020-12-18 16:14:38
 */
@Getter
@Setter
@TableName("shjl_vending_machine")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ShjlVendingMachine对象", description = "售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1)")
public class ShjlVendingMachineEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String partyB;
	/**
	 * 乙方联系地址
	 */
    @ApiModelProperty(value="乙方联系地址")
	private String contactB;
	/**
	 * 乙方自愿将位于
	 */
    @ApiModelProperty(value="乙方自愿将位于")
	private String bVoluntarily;
	/**
	 * 的
	 */
    @ApiModelProperty(value="的")
	private String of;
	/**
	 * 个点位以每个点位
	 */
    @ApiModelProperty(value="个点位以每个点位")
	private BigDecimal eachParts;
	/**
	 * 元的含税价格授权给甲方使用
	 */
    @ApiModelProperty(value="元的含税价格授权给甲方使用")
	private BigDecimal partyUse;
	/**
	 * 本合同约定中的双方合作售货机，甲方同意均开放
	 */
    @ApiModelProperty(value="本合同约定中的双方合作售货机，甲方同意均开放")
	private String partyContract;
	/**
	 * 货道/台供乙方销售其自选产品使用
	 */
    @ApiModelProperty(value="货道/台供乙方销售其自选产品使用")
	private String optionalProducts;
	/**
	 * （第一.4.6项时间要求）
	 */
    @ApiModelProperty(value="（第一.4.6项时间要求）")
	private String timeRequirement;
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
	private String addressTelephone;
	/**
	 * 开户行及账号
	 */
    @ApiModelProperty(value="开户行及账号")
	private String bankNumber;
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
	private String fullBank;

}
