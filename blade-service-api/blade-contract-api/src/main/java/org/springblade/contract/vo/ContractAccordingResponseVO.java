package org.springblade.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractAccordingEntity;
import io.swagger.annotations.ApiModel;

/**
 *  返回模型VO
 *
 * @author feng
 */
@Data
@ApiModel(description = "返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractAccordingResponseVO extends ContractAccordingEntity {

	private static final long serialVersionUID = 1L;

}
