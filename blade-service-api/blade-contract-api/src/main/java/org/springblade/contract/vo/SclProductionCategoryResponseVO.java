package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.SclProductionCategoryEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 生产类：物流服务合同（一段+调拨运输） 返回模型VO
 *
 * @author kx
 * @date : 2020-12-18 17:18:07
 */
@Getter
@Setter
@ToString
@ApiModel(description = "生产类：物流服务合同（一段+调拨运输）返回对象")
@EqualsAndHashCode(callSuper = true)
public class SclProductionCategoryResponseVO extends SclProductionCategoryEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
