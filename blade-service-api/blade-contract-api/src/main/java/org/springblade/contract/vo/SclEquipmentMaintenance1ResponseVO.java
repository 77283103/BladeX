package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.SclEquipmentMaintenance1Entity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 生产类：设备维修保养合同(关联表） 返回模型VO
 *
 * @author kx
 * @date : 2020-12-11 10:59:54
 */
@Getter
@Setter
@ToString
@ApiModel(description = "生产类：设备维修保养合同(关联表）返回对象")
@EqualsAndHashCode(callSuper = true)
public class SclEquipmentMaintenance1ResponseVO extends SclEquipmentMaintenance1Entity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
