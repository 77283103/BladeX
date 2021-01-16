package org.springblade.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.LyConfidentialityAgreementEntity;
import io.swagger.annotations.ApiModel;

/**
 * 梁艳-保密协议 返回模型VO
 *
 * @author wd
 * @date : 2021-01-15 14:57:41
 */
@Getter
@Setter
@ApiModel(description = "梁艳-保密协议返回对象")
@EqualsAndHashCode(callSuper = true)
public class LyConfidentialityAgreementResponseVO extends LyConfidentialityAgreementEntity {

	private static final long serialVersionUID = 1L;

}
