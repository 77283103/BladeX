package org.springblade.system.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


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
	 * 依据名称
	 */
	@ApiModelProperty(value = "依据名称")
	private String accordingName;
	/**
	 * 文件编号
	 */
	@ApiModelProperty(value = "文件编号")
	private String fileId;
	/**
	 * 同步时间
	 */
	@ApiModelProperty(value = "同步时间")
	private Date synchDate;
	/**
	 * 主题内容
	 */
	@ApiModelProperty(value = "主题内容")
	private String themeContext;
	/**
	 * 使用状态
	 */
	@ApiModelProperty(value = "使用状态")
	private String useStatus;
	/**
	 * 单据类型
	 */
	@ApiModelProperty(value = "单据类型")
	private String documentType;
	/**
	 * 依据地址
	 */
	@ApiModelProperty(value = "依据地址")
	private String accordingAddress;
	/**
	 * 是否可以复用
	 */
	@ApiModelProperty(value = "是否可以复用")
	private Integer isReused;
	/**
	 * 关联合同
	 */
	@ApiModelProperty(value = "关联合同")
	private Long contractId;

}
