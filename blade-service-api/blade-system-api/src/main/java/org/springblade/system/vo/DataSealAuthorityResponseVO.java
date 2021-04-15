package org.springblade.system.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.DataSealAuthorityEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;
import java.util.List;

/**
 * DataSealAuthority 返回模型VO
 *
 * @author xhb
 * @date : 2021-04-12 16:51:03
 */
@Getter
@Setter
@ToString
@ApiModel(description = "DataSealAuthority返回对象")
@EqualsAndHashCode(callSuper = true)
public class DataSealAuthorityResponseVO extends DataSealAuthorityEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
	/**
	 * 管理签章申请单位集合
	 */
	@ApiModelProperty(value = "管理签章申请单位集合")
	private List<String> sealList;
}
