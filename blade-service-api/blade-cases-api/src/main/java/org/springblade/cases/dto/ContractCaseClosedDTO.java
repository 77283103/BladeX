package org.springblade.cases.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import org.springblade.cases.entity.ContractCaseClosedEntity;
import java.util.Date;

/**
 * 案件结案 模型DTO
 *
 * @author xhb
 * @date : 2020-10-30 10:03:17
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ContractCaseClosedDTO extends ContractCaseClosedEntity {

	private static final long serialVersionUID = 1L;

}
