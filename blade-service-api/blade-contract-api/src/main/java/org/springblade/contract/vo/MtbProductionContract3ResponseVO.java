package org.springblade.contract.vo;

import lombok.*;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.MtbProductionContract3Entity;
import io.swagger.annotations.ApiModel;

/**
 * 媒体类：平面广告拍摄制作合同（关联表2） 返回模型VO
 *
 * @author 韩杨
 * @date : 2021-01-21 11:27:03
 */
@Getter
@Setter
@ApiModel(description = "媒体类：平面广告拍摄制作合同（关联表2）返回对象")
@EqualsAndHashCode(callSuper = true)
public class MtbProductionContract3ResponseVO extends MtbProductionContract3Entity {

	private static final long serialVersionUID = 1L;
	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
