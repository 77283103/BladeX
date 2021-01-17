package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.util.List;


/**
 * 1 实体类
 *
 * @author kx
 * @date : 2021-01-15 15:45:45
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("mtl_edited_the_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtlEditedTheContract对象", description = "1")
public class MtlEditedTheContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
	@ApiModelProperty(value = "甲方")
	private String mtlPatyA;
	/**
	 * 甲方联络邮箱
	 */
	@ApiModelProperty(value = "甲方联络邮箱")
	private String mtlPatyAEmail;
	/**
	 * 甲方地址
	 */
	@ApiModelProperty(value = "甲方地址")
	private String mtlContactEmail;
	/**
	 * 乙方
	 */
	@ApiModelProperty(value = "乙方")
	private String mtlPatyB;
	/**
	 * 乙方联络邮箱
	 */
	@ApiModelProperty(value = "乙方联络邮箱")
	private String mtlPatyBEmail;
	/**
	 * 乙方住所
	 */
	@ApiModelProperty(value = "乙方住所")
	private String mtlPatyBHome;
	/**
	 * 制作总费用(未税额人民币)
	 */
	@ApiModelProperty(value = "制作总费用(未税额人民币)")
	private BigDecimal mtlUnpaidTaxRmb;
	/**
	 * 税率
	 */
	@ApiModelProperty(value = "税率")
	private Double mtlRate;
	/**
	 * 现含税金额人民币
	 */
	@ApiModelProperty(value = "现含税金额人民币")
	private BigDecimal mtlTaxAmountIsRmb;
	/**
	 * 乙方公司名
	 */
	@ApiModelProperty(value = "乙方公司名")
	private String mtlCompanyName;
	/**
	 * 乙方开户行
	 */
	@ApiModelProperty(value = "乙方开户行")
	private String mtlWhereItIs;
	/**
	 * 乙方账号
	 */
	@ApiModelProperty(value = "乙方账号")
	private String mtlAccount;
	/**
	 * 修图合同关联表
	 */
	@ApiModelProperty(value = "修图合同关联表")
	private List<MtlEditedTheContract1Entity> mtlEditedTheContract1EntityList;

}
