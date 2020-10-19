package org.springblade.contract.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.contract.entity.ContractAssessmentEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;

/**
 * 合同评估表 模型DTO
 *
 * @author liyj
 * @date : 2020-09-24 10:41:34
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContractAssessmentDTO extends ContractAssessmentEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 一对一关联的合同对象
	 */
	@ApiModelProperty(value = "一对一关联的合同对象")
	private ContractFormInfoDTO infoEntity;

}
