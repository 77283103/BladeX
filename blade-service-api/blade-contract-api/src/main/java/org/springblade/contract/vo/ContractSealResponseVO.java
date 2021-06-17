package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.ContractSealEntity;

/**
 * 统一子公司（签章申请单位） 返回模型VO
 *
 * @author xhb
 * @date : 2021-06-16 16:11:03
 */
@Getter
@Setter
@ToString
@ApiModel(description = "统一子公司（签章申请单位）返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractSealResponseVO extends ContractSealEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
