package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.SclProjectOutsourcingEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 生产类：生产项目外包服务合同 返回模型VO
 *
 * @author kx
 * @date : 2020-12-11 11:03:59
 */
@Getter
@Setter
@ToString
@ApiModel(description = "生产类：生产项目外包服务合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class SclProjectOutsourcingResponseVO extends SclProjectOutsourcingEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
