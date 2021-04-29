package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.PerCollectPayEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 履约收付款 返回模型VO
 *
 * @author chenzy
 * @date : 2021-04-25 10:32:31
 */
@Getter
@Setter
@ToString
@ApiModel(description = "履约收付款返回对象")
@EqualsAndHashCode(callSuper = true)
public class PerCollectPayResponseVO extends PerCollectPayEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
