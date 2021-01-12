package org.springblade.contract.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.YwbBusinessContractTemplateEntity;

/**
 * 业务类：15.房屋租赁合同模板 模型DTO
 *
 * @author 王策
 * @date : 2021-01-12 17:30:26
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class YwbBusinessContractTemplateDTO extends YwbBusinessContractTemplateEntity {

	private static final long serialVersionUID = 1L;

}
