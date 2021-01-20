package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.BusServiceContractEntity;

/**
 * 班车服务合同 返回模型VO
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:25:20
 */
@Getter
@Setter
@ApiModel(description = "班车服务合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class BusServiceContractResponseVO extends BusServiceContractEntity {

	private static final long serialVersionUID = 1L;

}
