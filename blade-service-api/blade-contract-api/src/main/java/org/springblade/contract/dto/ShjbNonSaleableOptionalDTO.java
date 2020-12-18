package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.ShjbNonSaleableOptionalEntity;
import java.util.Date;

/**
 * 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本） 模型DTO
 *
 * @author 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本）
 * @date : 2020-12-18 16:01:16
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShjbNonSaleableOptionalDTO extends ShjbNonSaleableOptionalEntity {

	private static final long serialVersionUID = 1L;

}
