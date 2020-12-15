package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.SclEquipmentMaintenance1Entity;
import java.util.Date;

/**
 * 生产类：设备维修保养合同(关联表） 模型DTO
 *
 * @author kx
 * @date : 2020-12-11 10:59:49
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SclEquipmentMaintenance1DTO extends SclEquipmentMaintenance1Entity {

	private static final long serialVersionUID = 1L;

}
