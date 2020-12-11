package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.MtlEditedTheContractEntity;
import java.util.Date;

/**
 * 媒体类：修图合同 模型DTO
 *
 * @author 媒体类：修图合同
 * @date : 2020-12-10 19:24:45
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class MtlEditedTheContractDTO extends MtlEditedTheContractEntity {

	private static final long serialVersionUID = 1L;

}
