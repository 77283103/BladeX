package org.springblade.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.RelieveEntity;
import io.swagger.annotations.ApiModel;

/**
 *  返回模型VO
 *
 * @author szw
 * @date : 2020-09-23 20:10:29
 */
@Data
@ApiModel(description = "返回对象")
@EqualsAndHashCode(callSuper = true)
public class RelieveResponseVO extends RelieveEntity {

	private static final long serialVersionUID = 1L;

}
