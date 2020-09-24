package org.springblade.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ContractAssessmentEntity;
import io.swagger.annotations.ApiModel;

/**
 * 合同评估表 返回模型VO
 *
 * @author liyj
 * @date : 2020-09-23 23:28:31
 */
@Getter
@Setter
@ApiModel(description = "合同评估表返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractAssessmentResponseVO extends ContractAssessmentEntity {

	private static final long serialVersionUID = 1L;

}
