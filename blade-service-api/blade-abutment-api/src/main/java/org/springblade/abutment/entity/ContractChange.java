package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 合同变更信息
 * @author jitwxs
 * @date 2021/7/30 9:31
 */
@Data
public class ContractChange {
	@ApiModelProperty(value = "源合同id")
	private String contract_id;
	@ApiModelProperty(value = "源合同ekp单号")
	private String ekp_number;
	@ApiModelProperty(value = "变更实体信息")
	private ChangeEntity  change_entity;
	@ApiModelProperty(value = "变更补充协议")
	private List<Attachment> fd_change_attachment;

}
