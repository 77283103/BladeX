package org.springblade.contract.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractPerformanceEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 接收/提供服务计划清单 返回模型VO
 *
 * @author szw
 * @date : 2020-11-05 17:06:56
 */
@Getter
@Setter
@ToString
@ApiModel(description = "接收/提供服务计划清单返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractPerformanceResponseVO extends ContractPerformanceEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
	@ApiModelProperty(value = "履约规定结束时间")
	private String  planPayTimeEnd;
}
