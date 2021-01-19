package org.springblade.contract.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.SclProductionCategoryEntity;

/**
 * 生产类：物流服务合同（一段+调拨运输 模型DTO
 *
 * @author kx
 * @date : 2021-01-16 18:10:58
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SclProductionCategoryDTO extends SclProductionCategoryEntity {

	private static final long serialVersionUID = 1L;

}
