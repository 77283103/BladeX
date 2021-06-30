package org.springblade.contract.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ContractSealEntity;

/**
 * 统一子公司（签章申请单位） 模型DTO
 *
 * @author xhb
 * @date : 2021-06-16 16:10:59
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ContractSealDTO extends ContractSealEntity {

	private static final long serialVersionUID = 1L;

}
