package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.SclProductionCategoryEntity;

/**
 * 生产类：物流服务合同（一段+调拨运输 返回模型VO
 *
 * @author kx
 * @date : 2021-01-16 18:11:05
 */
@Getter
@Setter
@ToString
@ApiModel(description = "生产类：物流服务合同（一段+调拨运输返回对象")
@EqualsAndHashCode(callSuper = true)
public class SclProductionCategoryResponseVO extends SclProductionCategoryEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
