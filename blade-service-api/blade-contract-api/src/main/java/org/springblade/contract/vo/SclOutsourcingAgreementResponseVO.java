package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.SclOutsourcingAgreementEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 生产类：作业外包协议 返回模型VO
 *
 * @author kx
 * @date : 2020-12-18 16:08:30
 */
@Getter
@Setter
@ToString
@ApiModel(description = "生产类：作业外包协议返回对象")
@EqualsAndHashCode(callSuper = true)
public class SclOutsourcingAgreementResponseVO extends SclOutsourcingAgreementEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
