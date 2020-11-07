package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.ContractPerformanceEntity;
import java.util.Date;

/**
 * 接收/提供服务计划清单 模型DTO
 *
 * @author szw
 * @date : 2020-11-05 17:06:55
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContractPerformanceDTO extends ContractPerformanceEntity {

	private static final long serialVersionUID = 1L;

}
