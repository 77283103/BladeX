package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;


/**
 * 合同变更 实体类
 *
 * @author szw
 * @date : 2020-09-23 19:24:49
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_change")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Change对象", description = "合同变更")
public class ContractChangeEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 变更协议名称
	 */
	@ApiModelProperty(value = "变更协议名称")
	private String changeAgreementName;
	/**
	 * 变更原因
	 */
	@ApiModelProperty(value = "变更原因")
	private String changeReason;
	/**
	 * 变更内容
	 */
	@ApiModelProperty(value = "变更内容")
	private String changeContent;
	/**
	 * 变更主导方
	 */
	@ApiModelProperty(value = "变更主导方")
	private String changeParty;
	/**
	 * 变更说明
	 */
	@ApiModelProperty(value = "变更说明")
	private String changeExplain;
	/**
	 * 补充协议
	 */
	@ApiModelProperty(value = "补充协议")
	private String suppleAgreement;
	/**
	 * 关联合同id
	 */
	@ApiModelProperty(value = "关联合同id")
	private Long refContractId;

}
