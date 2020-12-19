package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.CglSalesContractEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 采购类：买卖合同（国内设备购买） 返回模型VO
 *
 * @author 王策
 * @date : 2020-12-18 15:36:13
 */
@Getter
@Setter
@ToString
@ApiModel(description = "采购类：买卖合同（国内设备购买）返回对象")
@EqualsAndHashCode(callSuper = true)
public class CglSalesContractResponseVO extends CglSalesContractEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
