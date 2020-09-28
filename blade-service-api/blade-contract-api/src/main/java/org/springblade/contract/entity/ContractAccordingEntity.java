package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 合同依据管理 实体类
 *
 * @author XHB
 * @date : 2020-09-24 14:20:29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_according")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "According对象", description = "合同依据管理")
public class ContractAccordingEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 项目依据
	 */
	@ApiModelProperty(value = "项目依据")
	private String accordingName;
	/**
	 * 关联合同
	 */
	@ApiModelProperty(value = "关联合同")
	private String contractId;
	/**
	 * 依据附件
	 */
	@ApiModelProperty(value = "依据附件")
	private String accordingFiles;
	/**
	 * 依据说明
	 */
	@ApiModelProperty(value = "依据说明")
	private String remark;
	/**
	 * 版本号
	 */
	@ApiModelProperty(value = "版本号")
	private String recordVersion;
	/**
	 * 创建单位标识
	 */
	@ApiModelProperty(value = "创建单位标识")
	private Long createUnit;

}
