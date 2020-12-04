package org.springblade.contract.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.ContractSigningArchiveEntity;


/**
 * 合同签订关联表 实体类
 *
 * @author 合同签订关联表
 * @date : 2020-11-05 09:34:31
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContractSigningArchiveDTO extends ContractSigningArchiveEntity {

	private static final long serialVersionUID = 1L;

}
