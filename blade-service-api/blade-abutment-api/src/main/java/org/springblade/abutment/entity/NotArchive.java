package org.springblade.abutment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author xhbbo
 */
@Data
@ApiModel(value = "ekp审核通过的数据")
public class NotArchive {

	@ApiModelProperty(value = "合同id")
	private String contractId;
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
	@ApiModelProperty(value = "预计归档时间")
	private Date estimateArchiveDate;
	@ApiModelProperty(value = "未归档原因")
	private String notArchiveReason;
}
