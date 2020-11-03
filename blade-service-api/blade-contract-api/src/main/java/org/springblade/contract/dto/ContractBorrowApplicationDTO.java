package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractBorrowApplicationEntity;
import java.util.Date;

/**
 * 借阅申请 模型DTO
 *
 * @author xhb
 * @date : 2020-10-30 09:27:02
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ContractBorrowApplicationDTO extends ContractBorrowApplicationEntity {

	private static final long serialVersionUID = 1L;

}
