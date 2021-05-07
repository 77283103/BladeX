package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.PerServiceContentEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;
import java.util.List;

/**
 * 履约服务内容 返回模型VO
 *
 * @author chenzy
 * @date : 2021-04-20 16:02:06
 */
@Getter
@Setter
@ToString
@ApiModel(description = "履约服务内容返回对象")
@EqualsAndHashCode(callSuper = true)
public class PerServiceContentResponseVO extends PerServiceContentEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;

	private List<PerPlanFinshTimeResponseVO> perPlanFinshTimeRequestVOList;
}
