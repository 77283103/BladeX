package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.MtlVideoProductionContract2Entity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 媒体类：视频制作合同关联表2 返回模型VO
 *
 * @author 媒体类：视频制作合同关联表2
 * @date : 2020-12-11 08:48:39
 */
@Getter
@Setter
@ToString
@ApiModel(description = "媒体类：视频制作合同关联表2返回对象")
@EqualsAndHashCode(callSuper = true)
public class MtlVideoProductionContract2ResponseVO extends MtlVideoProductionContract2Entity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
