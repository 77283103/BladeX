package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractBorrowHandleEntity;
import java.util.Date;

/**
 * 借阅处理 模型DTO
 *
 * @author xhb
 * @date : 2020-10-30 09:28:18
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ContractBorrowHandleDTO extends ContractBorrowHandleEntity {

	private static final long serialVersionUID = 1L;

}
