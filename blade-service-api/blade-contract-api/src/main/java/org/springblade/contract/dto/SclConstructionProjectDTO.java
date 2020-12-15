package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.SclConstructionProjectEntity;
import java.util.Date;

/**
 * 生产类：加工承揽合同（代工合同） 模型DTO
 *
 * @author 生产类：加工承揽合同（代工合同）
 * @date : 2020-12-11 09:52:19
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SclConstructionProjectDTO extends SclConstructionProjectEntity {

	private static final long serialVersionUID = 1L;

}
