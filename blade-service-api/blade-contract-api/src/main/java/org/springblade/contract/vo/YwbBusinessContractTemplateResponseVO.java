package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.YwbBusinessContractTemplateEntity;

/**
 * 业务类：15.房屋租赁合同模板 返回模型VO
 *
 * @author 王策
 * @date : 2021-01-12 17:30:34
 */
@Getter
@Setter
@ApiModel(description = "业务类：15.房屋租赁合同模板返回对象")
@EqualsAndHashCode(callSuper = true)
public class YwbBusinessContractTemplateResponseVO extends YwbBusinessContractTemplateEntity {

	private static final long serialVersionUID = 1L;

}
