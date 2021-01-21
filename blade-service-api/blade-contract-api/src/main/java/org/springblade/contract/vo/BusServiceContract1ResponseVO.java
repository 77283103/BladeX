package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.BusServiceContract1Entity;

/**
 * 班车服务合同子表1 返回模型VO
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:29:14
 */
@Getter
@Setter
@ApiModel(description = "班车服务合同子表1返回对象")
@EqualsAndHashCode(callSuper = true)
public class BusServiceContract1ResponseVO extends BusServiceContract1Entity {

	private static final long serialVersionUID = 1L;

}
