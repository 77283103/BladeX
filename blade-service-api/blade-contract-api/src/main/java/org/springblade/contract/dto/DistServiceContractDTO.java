package org.springblade.contract.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.DistServiceContractEntity;

/**
 * 配送服务合同 模型DTO
 *
 * @author 王策
 * @date : 2021-01-18 17:24:25
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class DistServiceContractDTO extends DistServiceContractEntity {

	private static final long serialVersionUID = 1L;

}
