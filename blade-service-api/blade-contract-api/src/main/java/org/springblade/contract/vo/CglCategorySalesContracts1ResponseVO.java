package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.CglCategorySalesContracts1Entity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 采购类：买卖合同（行销品） 返回模型VO
 *
 * @author 采购类：买卖合同（行销品）
 * @date : 2020-12-10 18:58:23
 */
@Getter
@Setter
@ToString
@ApiModel(description = "采购类：买卖合同（行销品）返回对象")
@EqualsAndHashCode(callSuper = true)
public class CglCategorySalesContracts1ResponseVO extends CglCategorySalesContracts1Entity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
