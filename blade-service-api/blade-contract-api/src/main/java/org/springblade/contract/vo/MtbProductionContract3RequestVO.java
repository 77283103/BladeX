package org.springblade.contract.vo;

import lombok.*;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 媒体类：平面广告拍摄制作合同（关联表2） 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 韩杨
 * @date : 2021-01-21 11:27:00
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "媒体类：平面广告拍摄制作合同（关联表2）请求对象")
public class MtbProductionContract3RequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="文件")
	private String wenjian;

    @ApiModelProperty(value="首次创建时间")
	private String shouchuang;

    @ApiModelProperty(value="完成地点")
	private String wancheng;

    @ApiModelProperty(value="创作者")
	private String createzhe;

    @ApiModelProperty(value="合同ID")
	private Long contractId;

    @ApiModelProperty(value="职务作品or委托创作")
	private String zhiwuorchuang;

}
