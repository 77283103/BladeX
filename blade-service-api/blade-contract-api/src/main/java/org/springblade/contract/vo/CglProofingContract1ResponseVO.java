package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.CglProofingContract1Entity;

/**
 * cgl_proofing_contract1 返回模型VO
 *
 * @author cglProofingContract1
 * @date : 2021-01-12 13:48:17
 */
@Getter
@Setter
@ApiModel(description = "cgl_proofing_contract1返回对象")
@EqualsAndHashCode(callSuper = true)
public class CglProofingContract1ResponseVO extends CglProofingContract1Entity {

	private static final long serialVersionUID = 1L;

}
