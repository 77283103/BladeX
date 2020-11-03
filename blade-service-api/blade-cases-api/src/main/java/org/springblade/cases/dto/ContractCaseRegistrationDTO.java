package org.springblade.cases.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import org.springblade.cases.entity.ContractCaseRegistrationEntity;
import java.util.Date;

/**
 * 案件登记表 模型DTO
 *
 * @author xhb
 * @date : 2020-10-30 10:04:51
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ContractCaseRegistrationDTO extends ContractCaseRegistrationEntity {

	private static final long serialVersionUID = 1L;

}
