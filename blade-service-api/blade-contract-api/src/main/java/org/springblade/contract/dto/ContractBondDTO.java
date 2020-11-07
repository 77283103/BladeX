package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.ContractBondEntity;
import java.util.Date;

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
public class ContractBondDTO extends ContractBondEntity {

	private static final long serialVersionUID = 1L;

}
