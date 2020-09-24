package org.springblade.contract.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ContractAssessmentEntity;

/**
 * 合同评估表 模型DTO
 *
 * @author liyj
 * @date : 2020-09-24 10:41:34
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ContractAssessmentDTO extends ContractAssessmentEntity {

	private static final long serialVersionUID = 1L;

}
