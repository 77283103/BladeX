package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.YwbEquipmentReleaseEntity;
import java.util.Date;

/**
 * 业务类：19.设备投放使用协议 模型DTO
 *
 * @author 业务类：19.设备投放使用协议
 * @date : 2020-12-18 16:07:45
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class YwbEquipmentReleaseDTO extends YwbEquipmentReleaseEntity {

	private static final long serialVersionUID = 1L;

}
