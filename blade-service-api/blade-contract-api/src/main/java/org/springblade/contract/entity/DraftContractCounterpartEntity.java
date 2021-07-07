package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;


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
@TableName("contract_draft_counterpart")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DraftContractCounterpart对象", description = "多方起草向对方")
public class DraftContractCounterpartEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 合同ID
	 */
	@ApiModelProperty(value = "合同ID")
	private String contractId;
	/**
	 * 相对方ID
	 */
	@ApiModelProperty(value = "相对方ID")
	private String counterpartId;
	/**
	 * 相对方身份
	 */
	@ApiModelProperty(value = "相对方身份")
	private String counterpartIdentity;
	/**
	 * 相对方联系人
	 */
	@ApiModelProperty(value = "相对方名称")
	private String counterpartPerson;
	/**
	 * 子公司名称
	 */
	@ApiModelProperty(value = "子公司名称")
	private String subsidiaryPerson;

	/**
	 * 联系人电话
	 */
	@ApiModelProperty(value = "联系人电话")
	private String telephonePerson;
	/**
	 * 联系人地址
	 */
	@ApiModelProperty(value = "联系人地址")
	private String addressPerson;
	/**
	 * 联系人邮箱
	 */
	@ApiModelProperty(value = "联系人邮箱")
	private String emailPerson;
	/**
	 * 相对方联行号
	 */
	@ApiModelProperty(value = "相对方联行号")
	private String bankCode;
	/**
	 * 相对方账户户号
	 */
	@ApiModelProperty(value = "相对方账户户号")
	private String bankAccountCode;
	/**
	 * 相对方银行帐户名称
	 */
	@ApiModelProperty(value = "相对方银行帐户名称")
	private String bankAccountName;
}
