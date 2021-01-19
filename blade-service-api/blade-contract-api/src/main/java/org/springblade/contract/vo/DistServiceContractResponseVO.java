package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.DistServiceContractEntity;

/**
 * 配送服务合同 返回模型VO
 *
 * @author 王策
 * @date : 2021-01-18 17:24:29
 */
@Getter
@Setter
@ApiModel(description = "配送服务合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class DistServiceContractResponseVO extends DistServiceContractEntity {

	private static final long serialVersionUID = 1L;

}
