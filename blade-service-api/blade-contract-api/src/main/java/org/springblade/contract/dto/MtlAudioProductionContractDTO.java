package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.MtlAudioProductionContractEntity;
import java.util.Date;

/**
 * 媒体类：音频制作合同 模型DTO
 *
 * @author 媒体类：音频制作合同
 * @date : 2020-12-10 19:21:34
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class MtlAudioProductionContractDTO extends MtlAudioProductionContractEntity {

	private static final long serialVersionUID = 1L;

}
