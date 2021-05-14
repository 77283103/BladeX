package org.springblade.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ContractMmhtxxpf1Entity;

/**
 * 行销品买卖合同子表 返回模型VO
 *
 * @author kx
 * @date : 2021-05-10 13:37:04
 */
@Getter
@Setter
@ApiModel(description = "行销品买卖合同子表返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractMmhtxxpf1ResponseVO extends ContractMmhtxxpf1Entity {

	private static final long serialVersionUID = 1L;

}
