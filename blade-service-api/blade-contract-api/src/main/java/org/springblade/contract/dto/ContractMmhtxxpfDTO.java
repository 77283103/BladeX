package org.springblade.contract.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ContractMmhtxxpfEntity;

/**
 * 行销品买卖合同 模型DTO
 *
 * @author kx
 * @date : 2021-05-10 13:37:36
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ContractMmhtxxpfDTO extends ContractMmhtxxpfEntity {

	private static final long serialVersionUID = 1L;

}
