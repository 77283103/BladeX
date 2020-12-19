package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.CglSalesContract1Entity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 采购类：买卖合同（国内设备购买）附表 返回模型VO
 *
 * @author 王策
 * @date : 2020-12-18 16:12:26
 */
@Getter
@Setter
@ToString
@ApiModel(description = "采购类：买卖合同（国内设备购买）附表返回对象")
@EqualsAndHashCode(callSuper = true)
public class CglSalesContract1ResponseVO extends CglSalesContract1Entity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
