package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import org.springblade.contract.entity.SclServiceContractRefrigerationEntity;

import java.util.Date;

/**
 * kx 返回模型VO
 *
 * @author kx
 * @date : 2021-03-15 13:48:25
 */
@Getter
@Setter
@ToString
@ApiModel(description = "kx返回对象")
@EqualsAndHashCode(callSuper = true)
public class SclServiceContractRefrigerationResponseVO extends SclServiceContractRefrigerationEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
