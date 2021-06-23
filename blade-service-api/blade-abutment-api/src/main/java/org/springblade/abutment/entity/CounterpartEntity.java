package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 相对方接口数据
 * @author xhbbo
 */
@Data
@ApiModel(value = "相对方增量数据更新")
public class CounterpartEntity implements Serializable {
	/**token自动获取*/
	@ApiModelProperty(hidden = true)
	private String token;
	@ApiModelProperty(value = "相对方数据编号")
	private String custNo;
	@ApiModelProperty(value = "相对方名称")
	private String custNm;
	@ApiModelProperty(value = "社会统一信用代码")
	private String businessId;
	@ApiModelProperty(value = "时间")
	private String yyyyMMdd;
}
