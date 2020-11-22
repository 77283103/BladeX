package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractRawMaterialsEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 原物料1v多 返回模型VO
 *
 * @author szw
 * @date : 2020-11-22 16:42:04
 */
@Getter
@Setter
@ToString
@ApiModel(description = "原物料1v多返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractRawMaterialsResponseVO extends ContractRawMaterialsEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
