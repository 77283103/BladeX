package org.springblade.contract.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ContractSigningEntity;

/**
 * 合同签订表 模型DTO
 *
 * @author liyj
 * @date : 2020-09-23 19:27:05
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ContractSigningDTO extends ContractSigningEntity {

	private static final long serialVersionUID = 1L;

}
