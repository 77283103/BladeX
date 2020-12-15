package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

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
	 * 查看依据的id
	 */
	@ApiModelProperty(value = "查看依据的id")
	private String fileId;

	/**
	 * 依据code
	 */
	@ApiModelProperty(value = "依据code")
	private String code;
	/**
	 * 同步时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
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
	@JsonSerialize(using = ToStringSerializer.class)
	private Long contractId;

}
