package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractBondEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 保证金 返回模型VO
 *
 * @author szw
 * @date : 2020-11-04 18:28:12
 */
@Getter
@Setter
@ToString
@ApiModel(description = "保证金返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractBondResponseVO extends ContractBondEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
