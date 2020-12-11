package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.CglCategorySalesContractsEntity;
import java.util.Date;

/**
 * 采购类：买卖合同（行销品） 模型DTO
 *
 * @author 王策
 * @date : 2020-12-10 18:52:44
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CglCategorySalesContractsDTO extends CglCategorySalesContractsEntity {

	private static final long serialVersionUID = 1L;

}
