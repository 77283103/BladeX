package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.PerBondEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 履约计划保证金 返回模型VO
 *
 * @author chenzy
 * @date : 2021-04-27 17:06:23
 */
@Getter
@Setter
@ToString
@ApiModel(description = "履约计划保证金返回对象")
@EqualsAndHashCode(callSuper = true)
public class PerBondResponseVO extends PerBondEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
