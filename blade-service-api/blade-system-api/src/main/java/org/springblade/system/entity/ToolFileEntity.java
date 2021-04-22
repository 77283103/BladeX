package org.springblade.system.entity;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 工具 实体类
 *
 * @author xhb
 * @date : 2021-04-22 10:09:28
 */
@Getter
@Setter
@TableName("tool_file")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ToolFile对象", description = "工具")
public class ToolFileEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 工具文件ID
	 */
	@ApiModelProperty(value = "工具文件ID")
	private String toolFileId;
	/**
	 * 文件名
	 */
	@ApiModelProperty(value = "文件名")
	private String fileName;
	/**
	 * 是否启
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value = "是否启")
	private Integer isEnable;

}
