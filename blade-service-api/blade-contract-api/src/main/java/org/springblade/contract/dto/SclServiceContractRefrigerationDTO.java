package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.SclServiceContractRefrigerationEntity;

import java.util.Date;

/**
 * kx 模型DTO
 *
 * @author kx
 * @date : 2021-03-15 13:48:15
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SclServiceContractRefrigerationDTO extends SclServiceContractRefrigerationEntity {

	private static final long serialVersionUID = 1L;

}
