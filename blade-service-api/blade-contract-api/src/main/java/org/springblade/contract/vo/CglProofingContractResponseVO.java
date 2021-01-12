package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.CglProofingContractEntity;

/**
 * 采购类_打样合同书 返回模型VO
 *
 * @author 采购类_打样合同书
 * @date : 2021-01-12 13:24:38
 */
@Getter
@Setter
@ApiModel(description = "采购类_打样合同书返回对象")
@EqualsAndHashCode(callSuper = true)
public class CglProofingContractResponseVO extends CglProofingContractEntity {

	private static final long serialVersionUID = 1L;

}
