package org.springblade.contract.dto;

import lombok.*;
import org.springblade.contract.entity.CounterpartEntity;

/**
 * 合同相对方的管理 模型DTO
 *
 * @author XHB
 * @date : 2020-09-18 21:13:52
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CounterpartDTO extends CounterpartEntity {

	private static final long serialVersionUID = 1L;

}
