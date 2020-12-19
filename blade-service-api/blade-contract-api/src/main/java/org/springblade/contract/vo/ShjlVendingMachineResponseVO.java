package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ShjlVendingMachineEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1) 返回模型VO
 *
 * @author 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1)
 * @date : 2020-12-18 16:14:43
 */
@Getter
@Setter
@ToString
@ApiModel(description = "售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1)返回对象")
@EqualsAndHashCode(callSuper = true)
public class ShjlVendingMachineResponseVO extends ShjlVendingMachineEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
