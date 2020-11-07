package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractBorrowReturnEntity;
import java.util.Date;

/**
 * 借阅归还 模型DTO
 *
 * @author xhb
 * @date : 2020-10-30 09:28:55
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ContractBorrowReturnDTO extends ContractBorrowReturnEntity {

	private static final long serialVersionUID = 1L;

}
