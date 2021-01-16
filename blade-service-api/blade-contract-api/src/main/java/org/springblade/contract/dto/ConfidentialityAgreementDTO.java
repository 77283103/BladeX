package org.springblade.contract.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ConfidentialityAgreementEntity;

/**
 * 梁艳-保密协议（三方） 模型DTO
 *
 * @author 王策
 * @date : 2021-01-15 15:36:27
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ConfidentialityAgreementDTO extends ConfidentialityAgreementEntity {

	private static final long serialVersionUID = 1L;

}
