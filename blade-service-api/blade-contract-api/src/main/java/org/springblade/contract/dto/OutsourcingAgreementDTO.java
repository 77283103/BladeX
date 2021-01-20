package org.springblade.contract.dto;

import lombok.*;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.OutsourcingAgreementEntity;

/**
 * 作 业 外 包 协 议 模型DTO
 *
 * @author 王策
 * @date : 2021-01-20 13:42:13
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class OutsourcingAgreementDTO extends OutsourcingAgreementEntity {

	private static final long serialVersionUID = 1L;

}
