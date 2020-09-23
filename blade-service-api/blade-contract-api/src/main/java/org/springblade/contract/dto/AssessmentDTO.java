package org.springblade.contract.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.AssessmentEntity;

/**
 * 合同评估表 模型DTO
 *
 * @author liyj
 * @date : 2020-09-23 15:50:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AssessmentDTO extends AssessmentEntity {

	private static final long serialVersionUID = 1L;

}
