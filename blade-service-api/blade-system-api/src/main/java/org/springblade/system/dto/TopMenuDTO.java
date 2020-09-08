package org.springblade.system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.TopMenu;

/**
 * 顶部菜单表 模型DTO
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TopMenuDTO extends TopMenu {

	private static final long serialVersionUID = 1L;

}
