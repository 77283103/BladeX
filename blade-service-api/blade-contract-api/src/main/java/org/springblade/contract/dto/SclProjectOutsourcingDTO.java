package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.SclProjectOutsourcingEntity;
import java.util.Date;

/**
 * 生产类：生产项目外包服务合同 模型DTO
 *
 * @author kx
 * @date : 2020-12-11 11:03:46
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SclProjectOutsourcingDTO extends SclProjectOutsourcingEntity {

	private static final long serialVersionUID = 1L;

}
