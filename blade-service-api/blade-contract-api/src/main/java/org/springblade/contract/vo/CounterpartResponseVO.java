package org.springblade.contract.vo;

import lombok.*;
import io.swagger.annotations.ApiModel;
import org.springblade.contract.entity.CounterpartEntity;

/**
 * 合同相对方的管理 返回模型VO
 *
 * @author XHB
 * @date : 2020-09-18 21:14:02
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "合同相对方的管理返回对象")
@EqualsAndHashCode(callSuper = true)
public class CounterpartResponseVO extends CounterpartEntity {

	private static final long serialVersionUID = 1L;

}
