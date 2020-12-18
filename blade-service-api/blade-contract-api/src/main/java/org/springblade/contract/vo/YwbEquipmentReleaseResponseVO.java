package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.YwbEquipmentReleaseEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 业务类：19.设备投放使用协议 返回模型VO
 *
 * @author 业务类：19.设备投放使用协议
 * @date : 2020-12-18 16:07:50
 */
@Getter
@Setter
@ToString
@ApiModel(description = "业务类：19.设备投放使用协议返回对象")
@EqualsAndHashCode(callSuper = true)
public class YwbEquipmentReleaseResponseVO extends YwbEquipmentReleaseEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
