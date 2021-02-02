package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Attachment {
	@ApiModelProperty(value = "文件名称")
	private String filename;
	@ApiModelProperty(value = "文件地址")
	private String filePath;
}
