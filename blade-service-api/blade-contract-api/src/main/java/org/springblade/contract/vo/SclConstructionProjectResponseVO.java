package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.SclConstructionProjectEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 生产类：加工承揽合同（代工合同） 返回模型VO
 *
 * @author 生产类：加工承揽合同（代工合同）
 * @date : 2020-12-11 09:52:27
 */
@Getter
@Setter
@ToString
@ApiModel(description = "生产类：加工承揽合同（代工合同）返回对象")
@EqualsAndHashCode(callSuper = true)
public class SclConstructionProjectResponseVO extends SclConstructionProjectEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
