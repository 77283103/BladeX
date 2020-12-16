package org.springblade.contract.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractBondEntity;
import io.swagger.annotations.ApiModel;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;

import java.util.Date;
import java.util.List;

/**
 * 保证金 返回模型VO
 *
 * @author szw
 * @date : 2020-11-04 18:28:12
 */
@Getter
@Setter
@ToString
@ApiModel(description = "保证金返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractBondResponseVO extends ContractBondEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;

	@ApiModelProperty(value = "关联合同信息")
	private ContractFormInfoEntity contractFormInfoEntity;

	@ApiModelProperty(value = "关联合同ID")
	private Long contractId;

	@ApiModelProperty(value = "关联合同信息集合")
	private List<ContractFormInfoEntity> contractFormInfoList;

	@ApiModelProperty(value = "关联相对方")
	private ContractCounterpartEntity counterpart;
}
