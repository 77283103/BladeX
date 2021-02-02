package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springblade.contract.entity.ContractSigningArchiveEntity;


/**
 * 合同签订关联表 实体类
 *
 * @author 合同签订关联表
 * @date : 2020-11-05 09:34:31
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合同签订关联表请求对象")
public class ContractSigningArchiveResponseVO extends ContractSigningArchiveEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;

}
