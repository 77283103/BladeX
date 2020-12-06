package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.YwlShopRecruitmentEntity;
import io.swagger.annotations.ApiModel;

/**
 * 业务类：14.店招合同 返回模型VO
 *
 * @author szw
 * @date : 2020-12-04 19:04:58
 */
@Getter
@Setter
@ToString
@ApiModel(description = "业务类：14.店招合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class YwlShopRecruitmentResponseVO extends YwlShopRecruitmentEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
