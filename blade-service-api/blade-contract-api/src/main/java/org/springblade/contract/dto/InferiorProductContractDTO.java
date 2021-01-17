package org.springblade.contract.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.InferiorProductContractEntity;

/**
 * 下脚品买卖合同模板 模型DTO
 *
 * @author Wang Pengfei
 * @date : 2021-01-15 15:54:14
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class InferiorProductContractDTO extends InferiorProductContractEntity {

	private static final long serialVersionUID = 1L;

}
