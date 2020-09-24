package org.springblade.contract.dto;

import lombok.*;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractPerformanceEntity;

/**
 * 合同履约计划 模型DTO
 *
 * @author liyj
 * @date : 2020-09-23 19:26:28
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ContractPerformanceDTO extends ContractPerformanceEntity {

	private static final long serialVersionUID = 1L;

}
