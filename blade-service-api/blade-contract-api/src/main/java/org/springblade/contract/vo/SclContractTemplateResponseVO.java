package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.SclContractTemplateEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 生产类：下脚品买卖合同模版 返回模型VO
 *
 * @author 张文武
 * @date : 2021-01-04 15:17:34
 */
@Getter
@Setter
@ToString
@ApiModel(description = "生产类：下脚品买卖合同模版返回对象")
@EqualsAndHashCode(callSuper = true)
public class SclContractTemplateResponseVO extends SclContractTemplateEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
