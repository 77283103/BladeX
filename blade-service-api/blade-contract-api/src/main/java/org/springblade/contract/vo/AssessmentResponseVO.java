package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.AssessmentEntity;

/**
 * 合同评估表 返回模型VO
 *
 * @author liyj
 * @date : 2020-09-23 15:50:00
 */
@Data
@ApiModel(description = "合同评估表返回对象")
@EqualsAndHashCode(callSuper = true)
public class AssessmentResponseVO extends AssessmentEntity {

	private static final long serialVersionUID = 1L;

}
