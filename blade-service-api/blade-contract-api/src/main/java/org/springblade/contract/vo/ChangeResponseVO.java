package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ChangeEntity;

/**
 * 合同变更 返回模型VO
 *
 * @author szw
 * @date : 2020-09-23 19:24:50
 */
@Data
@ApiModel(description = "合同变更返回对象")
@EqualsAndHashCode(callSuper = true)
public class ChangeResponseVO extends ChangeEntity {

	private static final long serialVersionUID = 1L;

}
