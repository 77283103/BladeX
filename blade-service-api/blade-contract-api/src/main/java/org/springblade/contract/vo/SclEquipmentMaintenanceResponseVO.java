package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.SclEquipmentMaintenanceEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 生产类：设备维修保养合同 返回模型VO
 *
 * @author kx
 * @date : 2020-12-11 10:56:51
 */
@Getter
@Setter
@ToString
@ApiModel(description = "生产类：设备维修保养合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class SclEquipmentMaintenanceResponseVO extends SclEquipmentMaintenanceEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
