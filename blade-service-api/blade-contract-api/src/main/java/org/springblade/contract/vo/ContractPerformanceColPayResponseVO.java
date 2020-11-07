package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractPerformanceColPayEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 收付款计划清单-收付款 返回模型VO
 *
 * @author szw
 * @date : 2020-11-05 17:07:03
 */
@Getter
@Setter
@ToString
@ApiModel(description = "收付款计划清单-收付款返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractPerformanceColPayResponseVO extends ContractPerformanceColPayEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
