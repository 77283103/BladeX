package org.springblade.cases.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import org.springblade.cases.entity.ContractCaseHandlingEntity;
import java.util.Date;

/**
 * 案件处理 模型DTO
 *
 * @author xhb
 * @date : 2020-10-30 10:04:14
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ContractCaseHandlingDTO extends ContractCaseHandlingEntity {

	private static final long serialVersionUID = 1L;

}
