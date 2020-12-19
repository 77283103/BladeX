package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.SclProductionCategoryEntity;
import java.util.Date;

/**
 * 生产类：物流服务合同（一段+调拨运输） 模型DTO
 *
 * @author kx
 * @date : 2020-12-18 17:18:03
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SclProductionCategoryDTO extends SclProductionCategoryEntity {

	private static final long serialVersionUID = 1L;

}
