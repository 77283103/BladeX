package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xhbbo
 */
@Data
@ApiModel(value = "多方相对方企业信息列表")
public class MultiCo {
	@ApiModelProperty(value = "单据内容:相对方名称(必填)")
	private String fd_opp_name;
	@ApiModelProperty(value = "单据内容:乙方信用代码或税籍编号(必填)")
	private String fd_opp_taxno;
	@ApiModelProperty(value = "法定代表人")
	private String fd_legal_person;
}
