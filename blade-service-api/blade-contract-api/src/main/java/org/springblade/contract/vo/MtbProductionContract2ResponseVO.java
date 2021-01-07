package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.MtbProductionContract2Entity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 媒体类：平面广告拍摄制作合同（关联表2） 返回模型VO
 *
 * @author 张文武
 * @date : 2021-01-04 11:24:02
 */
@Getter
@Setter
@ToString
@ApiModel(description = "媒体类：平面广告拍摄制作合同（关联表2）返回对象")
@EqualsAndHashCode(callSuper = true)
public class MtbProductionContract2ResponseVO extends MtbProductionContract2Entity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
