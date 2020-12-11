package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.MtlVideoProductionContract1Entity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 媒体类：视频制作合同关联表 返回模型VO
 *
 * @author 媒体类：视频制作合同关联表
 * @date : 2020-12-11 08:47:28
 */
@Getter
@Setter
@ToString
@ApiModel(description = "媒体类：视频制作合同关联表返回对象")
@EqualsAndHashCode(callSuper = true)
public class MtlVideoProductionContract1ResponseVO extends MtlVideoProductionContract1Entity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
