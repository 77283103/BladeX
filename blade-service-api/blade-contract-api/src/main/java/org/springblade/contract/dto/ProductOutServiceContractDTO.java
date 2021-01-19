package org.springblade.contract.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ProductOutServiceContractEntity;

/**
 * 生产项目外包服务合同 模型DTO
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:20:01
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ProductOutServiceContractDTO extends ProductOutServiceContractEntity {

	private static final long serialVersionUID = 1L;

}
