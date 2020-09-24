package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;


/**
 * 合同评估表 实体类
 *
 * @author liyj
 * @date : 2020-09-23 23:28:30
 */
@Data
@TableName("contract_assessment")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractAssessment对象", description = "合同评估表")
public class ContractAssessmentEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 相关附件
	 */
	@ApiModelProperty(value = "相关附件")
	private String attachedFiles;
	/**
	 * 评估说明
	 */
	@ApiModelProperty(value = "评估说明")
	private String assessmentRemark;
	/**
	 * 合同id
	 */
	@ApiModelProperty(value = "合同id")
	private Long contractId;

}
