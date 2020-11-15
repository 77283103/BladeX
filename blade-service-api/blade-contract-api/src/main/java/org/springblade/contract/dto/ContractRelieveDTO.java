package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.ContractRelieveEntity;
import java.util.Date;

/**
 * 合同解除 模型DTO
 *
 * @author 合同解除
 * @date : 2020-11-05 09:24:00
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContractRelieveDTO extends ContractRelieveEntity {

	private static final long serialVersionUID = 1L;

}
