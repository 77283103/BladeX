package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.time.LocalDateTime;


/**
 * 合同依据管理 实体类
 *
 * @author XHB
 * @date : 2020-09-23 18:40:17
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_according")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "According对象", description = "合同依据管理")
public class AccordingEntity extends BaseEntity {

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
	 * 添加依据附件
	 */
	@ApiModelProperty(value = "添加依据附件")
	private String accordingFiles;
	/**
	 * 依据说明
	 */
	@ApiModelProperty(value = "依据说明")
	private String remark;
	/**
	 * 管理部门
	 */
	@ApiModelProperty(value = "管理部门")
	private Long managementDept;
	/**
	 * 管理单位
	 */
	@ApiModelProperty(value = "管理单位")
	private Long managementUnit;
	/**
	 * 管理人员
	 */
	@ApiModelProperty(value = "管理人员")
	private Long manager;
	/**
	 * 上传时间
	 */
	@ApiModelProperty(value = "上传时间")
	private LocalDateTime uploadTime;
	/**
	 * 是否可以复用（0代表不可以复用，1代表可以复用）
	 */
	@ApiModelProperty(value = "是否可以复用（0代表不可以复用，1代表可以复用）")
	private Integer isReused;

}
