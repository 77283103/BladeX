package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.MtbProductionContract2Entity;
import java.util.Date;

/**
 * 媒体类：平面广告拍摄制作合同（关联表2） 模型DTO
 *
 * @author 张文武
 * @date : 2021-01-04 11:23:59
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class MtbProductionContract2DTO extends MtbProductionContract2Entity {

	private static final long serialVersionUID = 1L;

}
