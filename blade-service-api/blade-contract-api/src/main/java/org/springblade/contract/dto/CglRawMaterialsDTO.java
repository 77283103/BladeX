package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.CglRawMaterialsEntity;
import java.util.Date;

/**
 * 采购类：原物料-买卖合同 模型DTO
 *
 * @author 王策
 * @date : 2020-12-10 19:17:21
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CglRawMaterialsDTO extends CglRawMaterialsEntity {

	private static final long serialVersionUID = 1L;

}
