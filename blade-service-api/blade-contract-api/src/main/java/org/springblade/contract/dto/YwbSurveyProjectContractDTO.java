package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.YwbSurveyProjectContractEntity;
import java.util.Date;

/**
 * 业务类：20.售点普查项目合同 模型DTO
 *
 * @author 业务类：20.售点普查项目合同
 * @date : 2020-12-18 16:06:56
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class YwbSurveyProjectContractDTO extends YwbSurveyProjectContractEntity {

	private static final long serialVersionUID = 1L;

}
