package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.ContractSigningEntity;
import java.util.Date;

/**
 * 合同签订表 模型DTO
 *
 * @author 合同签订
 * @date : 2020-11-05 09:34:31
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContractSigningDTO extends ContractSigningEntity {

	private static final long serialVersionUID = 1L;

}
