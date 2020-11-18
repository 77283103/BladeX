package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.*;

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
public class ContractBondPlanResponseVO extends ContractBondPlanEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;

	@ApiModelProperty(value = "关联合同信息")
	private ContractFormInfoEntity contractFormInfoEntity;

	@ApiModelProperty(value = "合同相对方")
	private List<ContractCounterpartEntity> counterpartEntityList;

	@ApiModelProperty(value = "签订")
	private ContractSigningEntity signingEntity;

	@ApiModelProperty(value = "履约规定结束时间")
	private String  planPayTimeEnd;
}
