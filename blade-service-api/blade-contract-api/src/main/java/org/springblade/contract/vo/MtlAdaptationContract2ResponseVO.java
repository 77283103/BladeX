package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.MtlAdaptationContract2Entity;
import io.swagger.annotations.ApiModel;

/**
 * 媒体类：视频广告改编合同关联表2 返回模型VO
 *
 * @author 媒体类：视频广告改编合同关联表2
 * @date : 2020-12-11 08:36:49
 */
@Getter
@Setter
@ToString
@ApiModel(description = "媒体类：视频广告改编合同关联表2返回对象")
@EqualsAndHashCode(callSuper = true)
public class MtlAdaptationContract2ResponseVO extends MtlAdaptationContract2Entity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
