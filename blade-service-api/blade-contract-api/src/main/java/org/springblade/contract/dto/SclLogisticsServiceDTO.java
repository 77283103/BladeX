package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.SclLogisticsServiceEntity;
import java.util.Date;

/**
 * 生产类：物流服务合同（二段配送） 模型DTO
 *
 * @author kx
 * @date : 2020-12-18 17:17:39
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SclLogisticsServiceDTO extends SclLogisticsServiceEntity {

	private static final long serialVersionUID = 1L;

}
