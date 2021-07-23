package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class EkpSyncRequestVO implements Serializable {

	@ApiModelProperty("时间")
	private String yyyyMMdd;

	@ApiModelProperty("ekp令牌")
	private String token;

	@ApiModelProperty("类型（'initialize':全部,null:增量）")
	private String type;
}
