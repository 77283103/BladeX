package org.springblade.abutment.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xhbbo
 */
@Data
@ApiModel(value = "范本模板制作申请")
public class TemplateAc {

	@ApiModelProperty(value = "范本名称")
	private String name;
	@ApiModelProperty(value = "所属合同大类")
	private String contract_big_category;
	@ApiModelProperty(value = "所属合同小类")
	private String contract_small_category;
	@ApiModelProperty(value = "范本附件url")
	private String attached_files;
	@ApiModelProperty(value = "范本附件ID")
	private String attached_files_id;
	@ApiModelProperty(value = "范本状态")
	private String template_status;
	@ApiModelProperty(value = "模板文件url")
	private String template_file;
	@ApiModelProperty(value = "模板文件ID")
	private String template_file_id;
	@ApiModelProperty(value = "申请人员编号")
	private String app_number;
}
