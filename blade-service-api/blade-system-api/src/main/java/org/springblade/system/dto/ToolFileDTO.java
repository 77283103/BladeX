package org.springblade.system.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.ToolFileEntity;
import java.util.Date;

/**
 * 工具 模型DTO
 *
 * @author xhb
 * @date : 2021-04-22 10:09:28
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ToolFileDTO extends ToolFileEntity {

	private static final long serialVersionUID = 1L;

}
