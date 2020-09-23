package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDateTime;


/**
 * 相对方管理 实体类
 *
 * @author XHB
 * @date : 2020-09-23 19:35:02
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_counterpart")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Counterpart对象", description = "相对方管理")
public class CounterpartEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 相对方名称
	 */
	@ApiModelProperty(value = "相对方名称")
	private String name;
	/**
	 * 相对方性质
	 */
	@ApiModelProperty(value = "相对方性质")
	private String counterpartCategory;
	/**
	 * 注册地址
	 */
	@ApiModelProperty(value = "注册地址")
	private String registeredAddress;
	/**
	 * 法定代表人
	 */
	@ApiModelProperty(value = "法定代表人")
	private String legalRepresentative;
	/**
	 * 注册资金
	 */
	@ApiModelProperty(value = "注册资金")
	private BigDecimal registeredCapital;
	/**
	 * 币种
	 */
	@ApiModelProperty(value = "币种")
	private String currencyCategory;
	/**
	 * 成立日期
	 */
	@ApiModelProperty(value = "成立日期")
	private LocalDateTime establishDate;
	/**
	 * 统一社会信用代码
	 */
	@ApiModelProperty(value = "统一社会信用代码")
	private String unifiedSocialCreditCode;
	/**
	 * 首次合作日期
	 */
	@ApiModelProperty(value = "首次合作日期")
	private LocalDateTime firstCooperationDate;
	/**
	 * 合同记录
	 */
	@ApiModelProperty(value = "合同记录")
	private String contractRecord;
	/**
	 * 上榜记录
	 */
	@ApiModelProperty(value = "上榜记录")
	private String listRecord;
	/**
	 * 相关合同
	 */
	@ApiModelProperty(value = "相关合同")
	private String  contractId;

}
