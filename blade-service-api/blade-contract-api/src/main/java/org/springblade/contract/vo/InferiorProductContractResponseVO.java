package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.InferiorProductContractEntity;

/**
 * 下脚品买卖合同模板 返回模型VO
 *
 * @author Wang Pengfei
 * @date : 2021-01-15 15:54:22
 */
@Getter
@Setter
@ApiModel(description = "下脚品买卖合同模板返回对象")
@EqualsAndHashCode(callSuper = true)
public class InferiorProductContractResponseVO extends InferiorProductContractEntity {

	private static final long serialVersionUID = 1L;

}
