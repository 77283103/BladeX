package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractMultPaymenEntity;
import java.util.Date;

/**
 * 多方相对方收付款 模型DTO
 *
 * @author xhb
 * @date : 2021-04-23 17:30:31
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ContractMultPaymenDTO extends ContractMultPaymenEntity {

	private static final long serialVersionUID = 1L;

}
