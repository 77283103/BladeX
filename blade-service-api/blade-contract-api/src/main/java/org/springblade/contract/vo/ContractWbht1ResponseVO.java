package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ContractWbht1Entity;

/**
 * 消防-维保合同子表 返回模型VO
 *
 * @author kx
 * @date : 2021-05-10 13:40:23
 */
@Getter
@Setter
@ApiModel(description = "消防-维保合同子表返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractWbht1ResponseVO extends ContractWbht1Entity {

	private static final long serialVersionUID = 1L;

}
