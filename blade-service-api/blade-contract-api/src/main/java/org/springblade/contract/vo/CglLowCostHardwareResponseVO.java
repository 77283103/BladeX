package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.CglLowCostHardwareEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 采购类：买卖合同（五金低耗类） 返回模型VO
 *
 * @author 王策
 * @date : 2020-12-10 19:02:00
 */
@Getter
@Setter
@ToString
@ApiModel(description = "采购类：买卖合同（五金低耗类）返回对象")
@EqualsAndHashCode(callSuper = true)
public class CglLowCostHardwareResponseVO extends CglLowCostHardwareEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
