package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ProductOutServiceContract2Entity;

/**
 * 生产项目外包服务合同子表2 返回模型VO
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:23:07
 */
@Getter
@Setter
@ApiModel(description = "生产项目外包服务合同子表2返回对象")
@EqualsAndHashCode(callSuper = true)
public class ProductOutServiceContract2ResponseVO extends ProductOutServiceContract2Entity {

	private static final long serialVersionUID = 1L;

}
