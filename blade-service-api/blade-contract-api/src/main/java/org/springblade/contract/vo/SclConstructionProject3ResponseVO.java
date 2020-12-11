package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.SclConstructionProject3Entity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 生产类：加工承揽合同（代工合同）关联表3 返回模型VO
 *
 * @author 生产类：加工承揽合同（代工合同）关联表3
 * @date : 2020-12-11 10:36:45
 */
@Getter
@Setter
@ToString
@ApiModel(description = "生产类：加工承揽合同（代工合同）关联表3返回对象")
@EqualsAndHashCode(callSuper = true)
public class SclConstructionProject3ResponseVO extends SclConstructionProject3Entity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
