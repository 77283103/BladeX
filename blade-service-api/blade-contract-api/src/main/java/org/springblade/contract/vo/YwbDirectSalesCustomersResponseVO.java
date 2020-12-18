package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.YwbDirectSalesCustomersEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 业务类：22.直营客户促销执行通知函 返回模型VO
 *
 * @author 业务类：22.直营客户促销执行通知函
 * @date : 2020-12-18 16:06:14
 */
@Getter
@Setter
@ToString
@ApiModel(description = "业务类：22.直营客户促销执行通知函返回对象")
@EqualsAndHashCode(callSuper = true)
public class YwbDirectSalesCustomersResponseVO extends YwbDirectSalesCustomersEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
