package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.MtlAdaptationContractEntity;
import java.util.Date;

/**
 * 媒体类：视频广告改编合同 模型DTO
 *
 * @author  媒体类：视频广告改编合同
 * @date : 2020-12-10 19:40:29
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class MtlAdaptationContractDTO extends MtlAdaptationContractEntity {

	private static final long serialVersionUID = 1L;

}
