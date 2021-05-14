package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.time.LocalDateTime;


/**
 * 行销品买卖合同 实体类
 *
 * @author kx
 * @date : 2021-05-10 13:37:35
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_mmhtxxpf")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractMmhtxxpf对象", description = "行销品买卖合同")
public class ContractMmhtxxpfEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
	@ApiModelProperty(value = "甲方")
	private String contactPartyA;
	/**
	 * 乙方
	 */
	@ApiModelProperty(value = "乙方")
	private String contactPartyB;
	/**
	 * 甲方地址
	 */
	@ApiModelProperty(value = "甲方地址")
	private String contactPartyAAddr;
	/**
	 * 乙方地址
	 */
	@ApiModelProperty(value = "乙方地址")
	private String contactPartyBAddr;
	/**
	 * 约定由甲方向乙方购买?作为甲方
	 */
	@ApiModelProperty(value = "约定由甲方向乙方购买?作为甲方")
	private String contactThing;
	/**
	 * 收到订购单后?天内
	 */
	@ApiModelProperty(value = "收到订购单后?天内")
	private String contactDay;
	/**
	 * 甲方指定联络邮箱
	 */
	@ApiModelProperty(value = "甲方指定联络邮箱")
	private String contactPartyAEmail;
	/**
	 * 乙方指定联络邮箱
	 */
	@ApiModelProperty(value = "乙方指定联络邮箱")
	private String contactPartyBEmail;
	/**
	 * 甲方指定传真
	 */
	@ApiModelProperty(value = "甲方指定传真")
	private String contactPartyAFax;
	/**
	 * 乙方指定传真
	 */
	@ApiModelProperty(value = "乙方指定传真")
	private String contactPartyBFax;
	/**
	 * 双方约定采用第?种账期付款
	 */
	@ApiModelProperty(value = "双方约定采用第?种账期付款")
	private String contactType;
	/**
	 * （2）特殊付款
	 */
	@ApiModelProperty(value = "（2）特殊付款")
	private String contactSpecilpay;
	/**
	 * 开户行名称
	 */
	@ApiModelProperty(value = "开户行名称")
	private String contactBank;
	/**
	 * 账户名称
	 */
	@ApiModelProperty(value = "账户名称")
	private String contactBankname;
	/**
	 * 账号
	 */
	@ApiModelProperty(value = "账号")
	private String contactBanknum;
	/**
	 * 九、其他约定?
	 */
	@ApiModelProperty(value = "九、其他约定?")
	private String contactOther;
	/**
	 * 甲方联系地址
	 */
	@ApiModelProperty(value = "甲方联系地址")
	private String contactPartyAPlace;
	/**
	 * 乙方联系地址
	 */
	@ApiModelProperty(value = "乙方联系地址")
	private String contactPartyBPlace;
	/**
	 * 甲方联系方式
	 */
	@ApiModelProperty(value = "甲方联系方式")
	private String contactPartyAPhone;
	/**
	 * 乙方联系方式
	 */
	@ApiModelProperty(value = "乙方联系方式")
	private String contactPartyBPhone;

}
