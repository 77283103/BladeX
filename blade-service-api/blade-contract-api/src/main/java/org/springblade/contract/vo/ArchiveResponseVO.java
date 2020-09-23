package org.springblade.contract.vo;

import lombok.*;
import org.springblade.contract.entity.ArchiveEntity;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;

/**
 * 合同归档管理 返回模型VO
 *
 * @author XHB
 * @date : 2020-09-23 18:32:17
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "合同归档管理返回对象")
@EqualsAndHashCode(callSuper = true)
public class ArchiveResponseVO extends ArchiveEntity {

	private static final long serialVersionUID = 1L;

}
