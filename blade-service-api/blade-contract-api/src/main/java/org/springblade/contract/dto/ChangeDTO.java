package org.springblade.contract.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ChangeEntity;

/**
 * 合同变更 模型DTO
 *
 * @author szw
 * @date : 2020-09-23 19:24:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChangeDTO extends ChangeEntity {

	private static final long serialVersionUID = 1L;

}
