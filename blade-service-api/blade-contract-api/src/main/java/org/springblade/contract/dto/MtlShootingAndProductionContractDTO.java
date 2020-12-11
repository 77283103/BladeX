package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.MtlShootingAndProductionContractEntity;
import java.util.Date;

/**
 * 媒体类：视频广告拍摄制作合同 模型DTO
 *
 * @author 媒体类：视频广告拍摄制作合同
 * @date : 2020-12-10 19:36:03
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class MtlShootingAndProductionContractDTO extends MtlShootingAndProductionContractEntity {

	private static final long serialVersionUID = 1L;

}
