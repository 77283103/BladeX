package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.MtlShootingAndProductionContract2Entity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 媒体类：视频广告拍摄制作合同关联表2 返回模型VO
 *
 * @author 媒体类：视频广告拍摄制作合同关联表2
 * @date : 2020-12-11 05:31:05
 */
@Getter
@Setter
@ToString
@ApiModel(description = "媒体类：视频广告拍摄制作合同关联表2返回对象")
@EqualsAndHashCode(callSuper = true)
public class MtlShootingAndProductionContract2ResponseVO extends MtlShootingAndProductionContract2Entity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
