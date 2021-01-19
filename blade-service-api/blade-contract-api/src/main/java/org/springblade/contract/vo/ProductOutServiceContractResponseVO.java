package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ProductOutServiceContractEntity;

/**
 * 生产项目外包服务合同 返回模型VO
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:20:11
 */
@Getter
@Setter
@ApiModel(description = "生产项目外包服务合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class ProductOutServiceContractResponseVO extends ProductOutServiceContractEntity {

	private static final long serialVersionUID = 1L;

}
