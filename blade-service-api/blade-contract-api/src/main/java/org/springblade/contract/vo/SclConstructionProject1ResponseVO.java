package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.SclConstructionProject1Entity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 生产类：加工承揽合同（代工合同）关联表 返回模型VO
 *
 * @author 生产类：加工承揽合同（代工合同）关联表
 * @date : 2020-12-11 10:10:15
 */
@Getter
@Setter
@ToString
@ApiModel(description = "生产类：加工承揽合同（代工合同）关联表返回对象")
@EqualsAndHashCode(callSuper = true)
public class SclConstructionProject1ResponseVO extends SclConstructionProject1Entity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
