package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.CglActivityExecutionContractEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 采购类：活动执行合同 返回模型VO
 *
 * @author 采购类：活动执行合同
 * @date : 2020-12-10 18:29:51
 */
@Getter
@Setter
@ToString
@ApiModel(description = "采购类：活动执行合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class CglActivityExecutionContractResponseVO extends CglActivityExecutionContractEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
