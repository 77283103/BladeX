package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TransferStationTokenVo implements Serializable {

	@ApiModelProperty("响应状态码-200成功")
	private String code;

	@ApiModelProperty("返回数据")
	private String data;

	@ApiModelProperty("响应信息")
	private String message;

	@ApiModelProperty("响应结果")
	private Boolean success;
}
