package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.ContractDestructionEntity;
import java.util.Date;

/**
 * 合同销毁 模型DTO
 *
 * @author szw
 * @date : 2020-11-11 16:37:01
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContractDestructionDTO extends ContractDestructionEntity {

	private static final long serialVersionUID = 1L;

}
