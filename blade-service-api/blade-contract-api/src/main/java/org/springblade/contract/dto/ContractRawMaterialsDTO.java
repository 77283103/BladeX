package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.ContractRawMaterialsEntity;
import java.util.Date;

/**
 * 原物料1v多 模型DTO
 *
 * @author szw
 * @date : 2020-11-22 16:42:02
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContractRawMaterialsDTO extends ContractRawMaterialsEntity {

	private static final long serialVersionUID = 1L;

}
