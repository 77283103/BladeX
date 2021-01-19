package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ProductOutServiceContract1Entity;

/**
 * 生产项目外包服务合同子表1 返回模型VO
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:22:14
 */
@Getter
@Setter
@ApiModel(description = "生产项目外包服务合同子表1返回对象")
@EqualsAndHashCode(callSuper = true)
public class ProductOutServiceContract1ResponseVO extends ProductOutServiceContract1Entity {

	private static final long serialVersionUID = 1L;

}
