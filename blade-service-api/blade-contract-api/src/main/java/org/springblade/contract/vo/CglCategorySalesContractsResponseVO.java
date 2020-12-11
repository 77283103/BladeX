package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.CglCategorySalesContractsEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 采购类：买卖合同（行销品） 返回模型VO
 *
 * @author 王策
 * @date : 2020-12-10 18:52:49
 */
@Getter
@Setter
@ToString
@ApiModel(description = "采购类：买卖合同（行销品）返回对象")
@EqualsAndHashCode(callSuper = true)
public class CglCategorySalesContractsResponseVO extends CglCategorySalesContractsEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
