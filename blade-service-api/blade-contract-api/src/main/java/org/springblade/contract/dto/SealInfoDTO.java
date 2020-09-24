package org.springblade.contract.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.SealInfoEntity;

/**
 * 用印名称 模型DTO
 *
 * @author szw
 * @date : 2020-09-24 01:27:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SealInfoDTO extends SealInfoEntity {

	private static final long serialVersionUID = 1L;

}
