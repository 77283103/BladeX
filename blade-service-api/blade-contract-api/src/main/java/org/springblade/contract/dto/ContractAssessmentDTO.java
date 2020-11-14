package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.ContractAssessmentEntity;
import java.util.Date;

/**
 * 合同评估表 模型DTO
 *
 * @author 合同评估表
 * @date : 2020-11-05 09:37:39
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContractAssessmentDTO extends ContractAssessmentEntity {

	private static final long serialVersionUID = 1L;

}
