package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.util.Date;


/**
 * 配送服务合同 实体类
 *
 * @author 王策
 * @date : 2021-01-18 17:24:25
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("distribution_service_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DistServiceContract", description = "配送服务合同")
public class DistServiceContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 委托方
	 */
	@ApiModelProperty(value = "委托方")
	private String clientA;
	/**
	 * 委托方地址
	 */
	@ApiModelProperty(value = "委托方地址")
	private String clientAddress;
	/**
	 * 受托方
	 */
	@ApiModelProperty(value = "受托方")
	private String trusteeB;
	/**
	 * 受托方地址
	 */
	@ApiModelProperty(value = "受托方地址")
	private String trusteeAddress;
	/**
	 * 甲方指定收货卖场及地址
	 */
	@ApiModelProperty(value = "甲方指定收货卖场及地址")
	private String designatedAddress;
	/**
	 * 双方约定按如下第几种方式核算服务费用
	 */
	@ApiModelProperty(value = "双方约定按如下第几种方式核算服务费用")
	private String several;
	/**
	 * 甲方将以什么方式于费用确认之日起日内支付乙方上月服务费用
	 */
	@ApiModelProperty(value = "甲方将以什么方式于费用确认之日起日内支付乙方上月服务费用")
	private String inWay;
	/**
	 * 本合同有效期自年月日起
	 */
	@ApiModelProperty(value = "本合同有效期自年月日起")
	private Date validityContractA;
	/**
	 * 本合同有效期至年月日止
	 */
	@ApiModelProperty(value = "本合同有效期至年月日止")
	private Date validityContractB;
	/**
	 * 委托方电话
	 */
	@ApiModelProperty(value = "委托方电话")
	private String clientTelephone;
	/**
	 * 受托方电话
	 */
	@ApiModelProperty(value = "受托方电话")
	private String trusteeTelephone;
	/**
	 * 委托方传真
	 */
	@ApiModelProperty(value = "委托方传真")
	private String clientFax;
	/**
	 * 受托方传真
	 */
	@ApiModelProperty(value = "受托方传真")
	private String trusteeFax;

}
