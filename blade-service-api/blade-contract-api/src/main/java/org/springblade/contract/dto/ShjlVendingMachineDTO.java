package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.ShjlVendingMachineEntity;
import java.util.Date;

/**
 * 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1) 模型DTO
 *
 * @author 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1)
 * @date : 2020-12-18 16:14:38
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShjlVendingMachineDTO extends ShjlVendingMachineEntity {

	private static final long serialVersionUID = 1L;

}
