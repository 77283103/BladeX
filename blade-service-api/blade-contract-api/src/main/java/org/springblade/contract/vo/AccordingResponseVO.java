package org.springblade.contract.vo;

import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;

/**
 * 合同依据管理 返回模型VO
 *
 * @author XHB
 * @date : 2020-09-24 14:20:35
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "合同依据管理返回对象")
@EqualsAndHashCode(callSuper = true)
public class AccordingResponseVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

}
