package org.springblade.contract.vo;

import lombok.*;
import org.springblade.contract.entity.AccordingEntity;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;

/**
 * 合同依据表 返回模型VO
 *
 * @author XHB
 * @date : 2020-09-19 17:54:54
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "合同依据表返回对象")
@EqualsAndHashCode(callSuper = true)
public class AccordingResponseVO extends AccordingEntity {

	private static final long serialVersionUID = 1L;

}
