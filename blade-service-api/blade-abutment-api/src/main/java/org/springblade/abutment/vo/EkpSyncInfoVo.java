package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EkpSyncInfoVo implements Serializable {

	@ApiModelProperty("ekp响应状态")
	private String code;

	@ApiModelProperty("ekp响应信息")
	private String msg;

	@ApiModelProperty("ekp用户数据集合")
	private List<EkpSyncUserInfoVo> userList;

	@ApiModelProperty("ekp部门数据集合")
	private List<EkpSyncDeptInfoVo> orgList;

}
