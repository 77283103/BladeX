package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.MtlShootingAndProductionContract3Entity;

/**
 * 媒体类：视频广告拍摄制作合同关联表2 返回模型VO
 *
 * @author 媒体类：视频广告拍摄制作合同关联表2
 * @date : 2020-12-11 05:31:05
 */
@Getter
@Setter
@ToString
@ApiModel(description = "媒体类：视频广告拍摄制作合同关联表3返回对象")
@EqualsAndHashCode(callSuper = true)
public class MtlShootingAndProductionContract3ResponseVO extends MtlShootingAndProductionContract3Entity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
