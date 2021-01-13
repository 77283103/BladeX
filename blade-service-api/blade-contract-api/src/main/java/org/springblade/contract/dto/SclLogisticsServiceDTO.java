package org.springblade.contract.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.SclLogisticsServiceEntity;

/**
 * 生产类：物流服务合同（二段配送） 模型DTO
 *
 * @author 张文武
 * @date : 2021-01-04 14:30:49
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SclLogisticsServiceDTO extends SclLogisticsServiceEntity {

	private static final long serialVersionUID = 1L;

}
