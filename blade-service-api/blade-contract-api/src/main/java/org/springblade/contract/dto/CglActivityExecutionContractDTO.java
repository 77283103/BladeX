package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.CglActivityExecutionContractEntity;
import java.util.Date;

/**
 * 采购类：活动执行合同 模型DTO
 *
 * @author 采购类：活动执行合同
 * @date : 2020-12-10 18:29:51
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CglActivityExecutionContractDTO extends CglActivityExecutionContractEntity {

	private static final long serialVersionUID = 1L;

}
