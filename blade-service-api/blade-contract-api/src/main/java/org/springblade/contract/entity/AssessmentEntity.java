package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;


/**
 * 合同评估表 实体类
 *
 * @author liyj
 * @date : 2020-09-23 15:50:00
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_assessment")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Assessment对象", description = "合同评估表")
public class AssessmentEntity extends BaseEntity {

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
	 * 经办人的所有信息
	 */
	@ApiModelProperty(value = "经办人的所有信息")
	private String allInformation;
	/**
	 * 合同id
	 */
	@ApiModelProperty(value = "合同id")
	private Long contractId;

}
