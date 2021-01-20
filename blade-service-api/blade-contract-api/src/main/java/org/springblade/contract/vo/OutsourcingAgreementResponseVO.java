package org.springblade.contract.vo;

import lombok.*;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.OutsourcingAgreementEntity;
import io.swagger.annotations.ApiModel;

/**
 * 作 业 外 包 协 议 返回模型VO
 *
 * @author 王策
 * @date : 2021-01-20 13:42:21
 */
@Getter
@Setter
@ApiModel(description = "作 业 外 包 协 议返回对象")
@EqualsAndHashCode(callSuper = true)
public class OutsourcingAgreementResponseVO extends OutsourcingAgreementEntity {

	private static final long serialVersionUID = 1L;

}
