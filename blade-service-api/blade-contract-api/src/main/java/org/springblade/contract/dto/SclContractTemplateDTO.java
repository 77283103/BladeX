package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.SclContractTemplateEntity;
import java.util.Date;

/**
 * 生产类：下脚品买卖合同模版 模型DTO
 *
 * @author 张文武
 * @date : 2021-01-04 15:17:27
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SclContractTemplateDTO extends SclContractTemplateEntity {

	private static final long serialVersionUID = 1L;

}
