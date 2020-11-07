package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.ContractPerformanceColPayEntity;
import java.util.Date;

/**
 * 收付款计划清单-收付款 模型DTO
 *
 * @author szw
 * @date : 2020-11-05 17:07:01
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContractPerformanceColPayDTO extends ContractPerformanceColPayEntity {

	private static final long serialVersionUID = 1L;

}
