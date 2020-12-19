package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.SclOutsourcingAgreementEntity;
import java.util.Date;

/**
 * 生产类：作业外包协议 模型DTO
 *
 * @author kx
 * @date : 2020-12-18 16:08:24
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SclOutsourcingAgreementDTO extends SclOutsourcingAgreementEntity {

	private static final long serialVersionUID = 1L;

}
