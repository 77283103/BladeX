package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.CglRawMaterials1Entity;
import java.util.Date;

/**
 * 采购类：原物料-买卖合同 模型DTO
 *
 * @author 采购类：原物料-买卖合同
 * @date : 2020-12-10 18:54:33
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CglRawMaterials1DTO extends CglRawMaterials1Entity {

	private static final long serialVersionUID = 1L;

}
