package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.CglLowCostHardwareEntity;
import java.util.Date;

/**
 * 采购类：买卖合同（五金低耗类） 模型DTO
 *
 * @author 王策
 * @date : 2020-12-10 19:01:54
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CglLowCostHardwareDTO extends CglLowCostHardwareEntity {

	private static final long serialVersionUID = 1L;

}
