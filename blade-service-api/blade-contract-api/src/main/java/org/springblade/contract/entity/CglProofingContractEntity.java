package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 采购类_打样合同书 实体类
 *
 * @author 采购类_打样合同书
 * @date : 2021-01-12 13:24:34
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("cgl_proofing_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglProofingContract对象", description = "采购类_打样合同书")
public class CglProofingContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
	@ApiModelProperty(value = "甲方")
	private String partyA;
	/**
	 * 乙方
	 */
	@ApiModelProperty(value = "乙方")
	private String partyB;
	/**
	 * 元
	 */
	@ApiModelProperty(value = "元")
	private BigDecimal element;
	/**
	 * 日期
	 */
	@ApiModelProperty(value = "日期")
	private Date date;
	/**
	 * 元
	 */
	@ApiModelProperty(value = "元")
	private BigDecimal element1;
	/**
	 * 税率
	 */
	@ApiModelProperty(value = "税率")
	private Long taxRate;
	/**
	 * 元
	 */
	@ApiModelProperty(value = "元")
	private BigDecimal element2;
	/**
	 * 户名
	 */
	@ApiModelProperty(value = "户名")
	private String accountName;
	/**
	 * 账号
	 */
	@ApiModelProperty(value = "账号")
	private String accountNumber;
	/**
	 * 开户行
	 */
	@ApiModelProperty(value = "开户行")
	private String bankDeposit;
	/**
	 * 是否启用（0未启用，1已启用）
	 */
	@ApiModelProperty(value = "是否启用（0未启用，1已启用）")
	private Integer isEnable;
	/**
	 * 关联子表1
	 */
	@ApiModelProperty(value = "关联子表1")
	@TableField(exist = false)
	private List<CglProofingContract1Entity> contract1EntityList;

}
