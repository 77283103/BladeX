package org.springblade.flow.business.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.flow.core.entity.ProcessEntity;

/**
 * 流程定义信息表 模型DTO
 *
 * @author tianah
 * @date 2020-9-9
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class ProcessDTO extends ProcessEntity {

	private static final long serialVersionUID = 1L;

}
