package org.springblade.contract.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ContractWbhtEntity;

/**
 * 消防-维保合同 模型DTO
 *
 * @author kx
 * @date : 2021-05-10 13:41:02
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ContractWbhtDTO extends ContractWbhtEntity {

	private static final long serialVersionUID = 1L;

}
