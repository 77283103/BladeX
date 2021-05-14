package org.springblade.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import org.springblade.contract.entity.ContractMmhtEntity;

/**
 * 国内设备买卖合同 返回模型VO
 *
 * @author kx
 * @date : 2021-05-10 13:39:50
 */
@Getter
@Setter
@ApiModel(description = "国内设备买卖合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractMmhtResponseVO extends ContractMmhtEntity {

	private static final long serialVersionUID = 1L;

}
