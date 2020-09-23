package org.springblade.contract.dto;

import lombok.*;
import org.springblade.contract.entity.CounterpartEntity;

/**
 * 相对方管理 模型DTO
 *
 * @author XHB
 * @date : 2020-09-23 19:35:03
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CounterpartDTO extends CounterpartEntity {

	private static final long serialVersionUID = 1L;

}
