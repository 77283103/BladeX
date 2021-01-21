package org.springblade.contract.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.BusServiceContractEntity;

/**
 * 班车服务合同 模型DTO
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:25:15
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BusServiceContractDTO extends BusServiceContractEntity {

	private static final long serialVersionUID = 1L;

}
