package org.springblade.contract.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.DeviceLaunchUseContractEntity;

/**
 * 设备投放使用协议 模型DTO
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:18:10
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class DeviceLaunchUseContractDTO extends DeviceLaunchUseContractEntity {

	private static final long serialVersionUID = 1L;

}
