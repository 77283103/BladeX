package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.YwlAnewDisplayEntity;

/**
 * 业务类：21.新陈列协议书 返回模型VO
 *
 * @author szw
 * @date : 2020-12-07 15:37:43
 */
@Getter
@Setter
@ToString
@ApiModel(description = "业务类：21.新陈列协议书返回对象")
@EqualsAndHashCode(callSuper = true)
public class YwlAnewDisplayResponseVO extends YwlAnewDisplayEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
