package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.MtbProductionContractEntity;
import java.util.Date;

/**
 * 媒体类：平面广告拍摄制作合同 模型DTO
 *
 * @author 王策
 * @date : 2020-12-10 19:30:51
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class MtbProductionContractDTO extends MtbProductionContractEntity {

	private static final long serialVersionUID = 1L;

}
