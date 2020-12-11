package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.CglPaymentDaysSupplementaryEntity;
import java.util.Date;

/**
 * 采购类：账期补充协议--买卖合同 模型DTO
 *
 * @author 王策
 * @date : 2020-12-10 19:21:45
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CglPaymentDaysSupplementaryDTO extends CglPaymentDaysSupplementaryEntity {

	private static final long serialVersionUID = 1L;

}
