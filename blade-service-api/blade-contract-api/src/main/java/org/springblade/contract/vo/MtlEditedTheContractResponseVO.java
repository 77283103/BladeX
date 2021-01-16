package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.MtlEditedTheContractEntity;

/**
 * 1 返回模型VO
 *
 * @author kx
 * @date : 2021-01-15 15:45:50
 */
@Getter
@Setter
@ApiModel(description = "1返回对象")
@EqualsAndHashCode(callSuper = true)
public class MtlEditedTheContractResponseVO extends MtlEditedTheContractEntity {

	private static final long serialVersionUID = 1L;

}
