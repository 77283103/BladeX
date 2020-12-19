package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.CglSalesContract1Entity;
import java.util.Date;

/**
 * 采购类：买卖合同（国内设备购买）附表 模型DTO
 *
 * @author 王策
 * @date : 2020-12-18 16:12:23
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CglSalesContract1DTO extends CglSalesContract1Entity {

	private static final long serialVersionUID = 1L;

}
