package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.CglTheSalesContractEntity;
import java.util.Date;

/**
 * 采购类：新增原物料补充协议--买卖合同 模型DTO
 *
 * @author 王策
 * @date : 2020-12-10 19:07:47
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CglTheSalesContractDTO extends CglTheSalesContractEntity {

	private static final long serialVersionUID = 1L;

}
