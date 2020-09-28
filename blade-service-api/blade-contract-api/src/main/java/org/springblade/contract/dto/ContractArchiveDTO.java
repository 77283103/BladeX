package org.springblade.contract.dto;

import lombok.*;
import org.springblade.contract.entity.ContractArchiveEntity;

/**
 * 合同归档管理 模型DTO
 *
 * @author XHB
 * @date : 2020-09-23 18:32:13
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContractArchiveDTO extends ContractArchiveEntity {

	private static final long serialVersionUID = 1L;

}
