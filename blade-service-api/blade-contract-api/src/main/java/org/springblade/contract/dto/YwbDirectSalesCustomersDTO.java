package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.YwbDirectSalesCustomersEntity;
import java.util.Date;

/**
 * 业务类：22.直营客户促销执行通知函 模型DTO
 *
 * @author 业务类：22.直营客户促销执行通知函
 * @date : 2020-12-18 16:06:12
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class YwbDirectSalesCustomersDTO extends YwbDirectSalesCustomersEntity {

	private static final long serialVersionUID = 1L;

}
