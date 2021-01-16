package org.springblade.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ConfidentialityAgreementEntity;
import io.swagger.annotations.ApiModel;

/**
 * 梁艳-保密协议（三方） 返回模型VO
 *
 * @author 王策
 * @date : 2021-01-15 15:36:29
 */
@Getter
@Setter
@ApiModel(description = "梁艳-保密协议（三方）返回对象")
@EqualsAndHashCode(callSuper = true)
public class ConfidentialityAgreementResponseVO extends ConfidentialityAgreementEntity {

	private static final long serialVersionUID = 1L;

}
