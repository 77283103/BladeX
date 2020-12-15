package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.MtlShootingAndProductionContract1Entity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 媒体类：视频广告拍摄制作合同关联表 返回模型VO
 *
 * @author 媒体类：视频广告拍摄制作合同关联表
 * @date : 2020-12-11 05:30:05
 */
@Getter
@Setter
@ToString
@ApiModel(description = "媒体类：视频广告拍摄制作合同关联表返回对象")
@EqualsAndHashCode(callSuper = true)
public class MtlShootingAndProductionContract1ResponseVO extends MtlShootingAndProductionContract1Entity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
