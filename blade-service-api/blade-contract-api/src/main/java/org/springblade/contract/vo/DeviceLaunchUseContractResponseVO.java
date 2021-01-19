package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.DeviceLaunchUseContractEntity;

/**
 * 设备投放使用协议 返回模型VO
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:18:15
 */
@Getter
@Setter
@ApiModel(description = "设备投放使用协议返回对象")
@EqualsAndHashCode(callSuper = true)
public class DeviceLaunchUseContractResponseVO extends DeviceLaunchUseContractEntity {

	private static final long serialVersionUID = 1L;

}
