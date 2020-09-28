package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springblade.contract.entity.AccordingEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  返回模型VO
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:39
 */
@Data
@ApiModel(description = "返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractFormInfoResponseVO extends ContractFormInfoEntity {
	private static final long serialVersionUID = 1L;

}
