package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xhbbo
 */
@Data
@ApiModel(value = "ekp根据合同ID查询的信息")
public class ContractVo {
	@ApiModelProperty(value = "合同状态（30合同已审批通过待到出打印   40已导出打印待用印  50已用印待归档  60已归档 ）")
	private String submitStatus;
	@ApiModelProperty(value = "用印时间")
	private String signingDate;
	@ApiModelProperty(value = "归档时间")
	private String archiveDate;
	@ApiModelProperty(value = "归档扫描件文件链接")
	private String archiveFileLink;
	@ApiModelProperty(value = "数据返回状态")
	private String code;
}
