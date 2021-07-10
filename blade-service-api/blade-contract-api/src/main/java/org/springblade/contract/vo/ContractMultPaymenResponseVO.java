package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ContractMultPaymenEntity;

/**
 * 国内设备买卖合同 返回模型VO
 *
 * @author kx
 * @date : 2021-05-10 13:39:50
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractMultPaymen对象", description = "多方相对方收付款")
public class ContractMultPaymenResponseVO extends ContractMultPaymenEntity {

	private static final long serialVersionUID = 1L;

}
