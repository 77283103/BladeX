package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.PerPlanFinshTimeEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 履约计划完成时间 返回模型VO
 *
 * @author chenzy
 * @date : 2021-04-20 16:34:52
 */
@Getter
@Setter
@ToString
@ApiModel(description = "履约计划完成时间返回对象")
@EqualsAndHashCode(callSuper = true)
public class PerPlanFinshTimeResponseVO extends PerPlanFinshTimeEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
