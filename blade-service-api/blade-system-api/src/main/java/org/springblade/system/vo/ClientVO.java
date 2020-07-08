package org.springblade.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.ClientEntity;

/**
 * 应用管理 模型VO
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ClientVO extends ClientEntity {

	private static final long serialVersionUID = 1L;

}
