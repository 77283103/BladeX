package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.SealInfoEntity;

/**
 * 用印名称 返回模型VO
 *
 * @author szw
 * @date : 2020-09-24 01:27:14
 */
@Data
@ApiModel(description = "用印名称返回对象")
@EqualsAndHashCode(callSuper = true)
public class SealInfoResponseVO extends SealInfoEntity {

	private static final long serialVersionUID = 1L;

}
