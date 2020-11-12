package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.ContractArchiveEntity;
import java.util.Date;

/**
 * 合同归档 模型DTO
 *
 * @author 合同归档
 * @date : 2020-11-05 09:41:38
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContractArchiveDTO extends ContractArchiveEntity {

	private static final long serialVersionUID = 1L;

}
