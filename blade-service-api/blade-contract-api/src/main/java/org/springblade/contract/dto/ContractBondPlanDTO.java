package org.springblade.contract.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.ContractBondEntity;
import org.springblade.contract.entity.ContractBondPlanEntity;

/**
 * 保证金 模型DTO
 *
 * @author szw
 * @date : 2020-11-04 18:28:10
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContractBondPlanDTO extends ContractBondPlanEntity {

	private static final long serialVersionUID = 1L;

}
