package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.SclEquipmentMaintenanceEntity;
import java.util.Date;

/**
 * 生产类：设备维修保养合同 模型DTO
 *
 * @author kx
 * @date : 2020-12-11 10:56:39
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SclEquipmentMaintenanceDTO extends SclEquipmentMaintenanceEntity {

	private static final long serialVersionUID = 1L;

}
