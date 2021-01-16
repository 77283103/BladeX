package org.springblade.contract.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.LyConfidentialityAgreementEntity;

/**
 * 梁艳-保密协议 模型DTO
 *
 * @author wd
 * @date : 2021-01-15 14:57:38
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class LyConfidentialityAgreementDTO extends LyConfidentialityAgreementEntity {

	private static final long serialVersionUID = 1L;

}
