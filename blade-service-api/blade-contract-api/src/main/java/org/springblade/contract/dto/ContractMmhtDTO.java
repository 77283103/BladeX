package org.springblade.contract.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ContractMmhtEntity;

/**
 * 国内设备买卖合同 模型DTO
 *
 * @author kx
 * @date : 2021-05-10 13:39:20
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ContractMmhtDTO extends ContractMmhtEntity {

	private static final long serialVersionUID = 1L;

}
