package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.SclServiceEntity;
import java.util.Date;

/**
 * 生产类：物流服务合同（二段仓储+配送） 模型DTO
 *
 * @author kx
 * @date : 2020-12-18 17:08:00
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SclServiceDTO extends SclServiceEntity {

	private static final long serialVersionUID = 1L;

}
