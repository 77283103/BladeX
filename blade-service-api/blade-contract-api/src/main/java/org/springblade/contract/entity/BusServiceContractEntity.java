package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.util.Date;
import java.util.List;


/**
 * 班车服务合同 实体类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:25:15
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("bus_service_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "BusServiceContract对象", description = "班车服务合同")
public class BusServiceContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
	@ApiModelProperty(value = "甲方")
	private String busSaler;
	/**
	 * 甲方地址
	 */
	@ApiModelProperty(value = "甲方地址")
	private String busSalerAddr;
	/**
	 * 乙方
	 */
	@ApiModelProperty(value = "乙方")
	private String busBuyer;
	/**
	 * 乙方地址
	 */
	@ApiModelProperty(value = "乙方地址")
	private String busBuyerAddr;
	/**
	 * 关联子表bus_service_contract1标识
	 */
	@ApiModelProperty(value = "关联子表bus_service_contract1标识")
	private Long busServiceContract1Id;
	/**
	 * 服务期限始
	 */
	@ApiModelProperty(value = "服务期限始")
	private Date busServiceTimeStart;
	/**
	 * 服务期限止
	 */
	@ApiModelProperty(value = "服务期限止")
	private Date busServiceTimeEnd;
	/**
	 * 每月?日双方对上月产生的服务费进行核对
	 */
	@ApiModelProperty(value = "每月?日双方对上月产生的服务费进行核对")
	private String busDateRequireFir;
	/**
	 * 发票(普通/专用)
	 */
	@ApiModelProperty(value = "发票(普通/专用)")
	private String busInvoiceType;
	/**
	 * 户名
	 */
	@ApiModelProperty(value = "户名")
	private String busBuyerAccountName;
	/**
	 * 账号
	 */
	@ApiModelProperty(value = "账号")
	private String busBuyerAccountId;
	/**
	 * 开户行
	 */
	@ApiModelProperty(value = "开户行")
	private String busBuyerAccountBank;
	/**
	 * 累计达?日无法为甲方提供班车服务的
	 */
	@ApiModelProperty(value = "累计达?日无法为甲方提供班车服务的")
	private String busDateRequireSec;
	/**
	 * 附件1：报价单
	 */
	@ApiModelProperty(value = "附件1：报价单")
	private String infAnnexFir;
	/**
	 *服务内容：
	 */
	@ApiModelProperty(value = "服务内容：")
	@TableField(exist = false)
	private List<BusServiceContract1Entity> busServiceContract1EntityList;

}
