package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.CglPaymentDaysSupplementaryEntity;
import io.swagger.annotations.ApiModel;

/**
 * 采购类：账期补充协议--买卖合同 返回模型VO
 *
 * @author 王策
 * @date : 2020-12-10 19:21:48
 */
@Getter
@Setter
@ToString
@ApiModel(description = "采购类：账期补充协议--买卖合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class CglPaymentDaysSupplementaryResponseVO extends CglPaymentDaysSupplementaryEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
