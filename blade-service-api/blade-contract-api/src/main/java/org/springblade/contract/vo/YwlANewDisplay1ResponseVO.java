package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.YwlANewDisplay1Entity;
import io.swagger.annotations.ApiModel;

/**
 * 业务类：21.新陈列协议书关联表 返回模型VO
 *
 * @author kx
 * @date : 2020-12-16 16:42:41
 */
@Getter
@Setter
@ToString
@ApiModel(description = "业务类：21.新陈列协议书关联表返回对象")
@EqualsAndHashCode(callSuper = true)
public class YwlANewDisplay1ResponseVO extends YwlANewDisplay1Entity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
