package org.springblade.contract.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.ContractSigningArchiveEntity;
import org.springblade.contract.entity.ContractSigningEntity;
import org.springblade.core.mp.base.BaseEntity;


/**
 * 合同签订关联表 实体类
 *
 * @author 合同签订关联表
 * @date : 2020-11-05 09:34:31
 */
@Getter
@Setter
@ToString
@ApiModel(description = "合同签订关联表请求对象")
public class ContractSigningArchiveResponseVO extends ContractSigningArchiveEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;

}
