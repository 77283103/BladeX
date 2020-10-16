package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDateTime;


/**
 * 合同依据表 实体类
 *
 * @author XHB
 * @date : 2020-09-19 17:54:43
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_according")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "According对象", description = "合同依据表")
public class AccordingEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件编号
	 */
	@ApiModelProperty(value = "文件编号")
	private Long fileId;
	/**
	 * 依据名称
	 */
	@ApiModelProperty(value = "依据名称")
	private String accordingName;
	/**
	 * 上传时间
	 */
	@ApiModelProperty(value = "上传时间")
	private LocalDate uploadDate;
	/**
	 * 同步时间
	 */
	@ApiModelProperty(value = "同步时间")
	private LocalDate synchDate;
	/**
	 * 主题内容
	 */
	@ApiModelProperty(value = "主题内容")
	private String themeContext;
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
	 * 使用状态
	 */
	@ApiModelProperty(value = "使用状态")
	private String useStatus;
	/**
	 * 是否可以复用（0代表不可以复用，1代表可以复用，默认为可复用）
	 */
	@ApiModelProperty(value = "是否可以复用（0代表不可以复用，1代表可以复用，默认为可复用）")
	private Integer isReused;

}
