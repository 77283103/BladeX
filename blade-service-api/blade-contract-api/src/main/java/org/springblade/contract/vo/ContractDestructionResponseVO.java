package org.springblade.contract.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractDestructionEntity;
import io.swagger.annotations.ApiModel;
import org.springblade.resource.vo.FileVO;

import java.util.Date;
import java.util.List;

/**
 * 合同销毁 返回模型VO
 *
 * @author szw
 * @date : 2020-11-11 16:37:02
 */
@Getter
@Setter
@ToString
@ApiModel(description = "合同销毁返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractDestructionResponseVO extends ContractDestructionEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;

	/**
	 * 附件list
	 * */
	@ApiModelProperty(value="附件list")
	private List<FileVO> fileList;
}
