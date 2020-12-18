package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.YwbSurveyProjectContractEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 业务类：20.售点普查项目合同 返回模型VO
 *
 * @author 业务类：20.售点普查项目合同
 * @date : 2020-12-18 16:07:01
 */
@Getter
@Setter
@ToString
@ApiModel(description = "业务类：20.售点普查项目合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class YwbSurveyProjectContractResponseVO extends YwbSurveyProjectContractEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
