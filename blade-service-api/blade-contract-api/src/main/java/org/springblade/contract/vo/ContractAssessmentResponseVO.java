package org.springblade.contract.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.contract.entity.ContractAssessmentEntity;
import io.swagger.annotations.ApiModel;
import org.springblade.contract.entity.ContractFormInfoEntity;

/**
 * 合同评估表 返回模型VO
 *
 * @author liyj
 * @date : 2020-09-24 10:41:34
 */
@Getter
@Setter
@ToString
@ApiModel(description = "合同评估表返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractAssessmentResponseVO extends ContractAssessmentEntity {

	private static final long serialVersionUID = 1L;

}
