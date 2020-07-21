package org.springblade.contract.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractAccordingEntity;

/**
 *  模型DTO
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContractAccordingDTO extends ContractAccordingEntity {

	private static final long serialVersionUID = 1L;

}
