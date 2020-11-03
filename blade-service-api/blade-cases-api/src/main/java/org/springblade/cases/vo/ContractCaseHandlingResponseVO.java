package org.springblade.cases.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.cases.entity.ContractCaseHandlingEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 案件处理 返回模型VO
 *
 * @author xhb
 * @date : 2020-10-30 10:04:27
 */
@Getter
@Setter
@ToString
@ApiModel(description = "案件处理返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractCaseHandlingResponseVO extends ContractCaseHandlingEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
