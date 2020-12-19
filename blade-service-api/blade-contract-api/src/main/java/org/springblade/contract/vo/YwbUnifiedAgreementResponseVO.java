package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.YwbUnifiedAgreementEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 2021年统一e商城平台入驻服务协议（统一经销商） 返回模型VO
 *
 * @author 2021年统一e商城平台入驻服务协议（统一经销商）
 * @date : 2020-12-18 16:03:34
 */
@Getter
@Setter
@ToString
@ApiModel(description = "2021年统一e商城平台入驻服务协议（统一经销商）返回对象")
@EqualsAndHashCode(callSuper = true)
public class YwbUnifiedAgreementResponseVO extends YwbUnifiedAgreementEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
