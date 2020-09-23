package org.springblade.contract.vo;

import lombok.*;
import org.springblade.contract.entity.AccordingEntity;
import org.springblade.contract.entity.ArchiveEntity;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;

/**
 * 合同依据管理 返回模型VO
 *
 * @author XHB
 * @date : 2020-09-23 18:40:24
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "合同依据管理返回对象")
@EqualsAndHashCode(callSuper = true)
public class AccordingResponseVO extends AccordingEntity {

	private static final long serialVersionUID = 1L;

}
