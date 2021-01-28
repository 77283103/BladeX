package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 媒体类：平面广告拍摄制作合同（关联表2） 实体类
 *
 * @author 韩杨
 * @date : 2021-01-21 11:27:00
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("mtb_production_contract3")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtbProductionContract3对象", description = "媒体类：平面广告拍摄制作合同（关联表2）")
public class MtbProductionContract3Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件
	 */
	@ApiModelProperty(value = "文件")
	private String wenJian;
	/**
	 * 首次创建时间
	 */
	@ApiModelProperty(value = "首次创建时间")
	private String shouChuang;
	/**
	 * 完成地点
	 */
	@ApiModelProperty(value = "完成地点")
	private String wanCheng;
	/**
	 * 创作者
	 */
	@ApiModelProperty(value = "创作者")
	private String createZhe;
	/**
	 * 合同ID
	 */
	@ApiModelProperty(value = "合同ID")
	private Long contractId;
	/**
	 * 职务作品or委托创作
	 */
	@ApiModelProperty(value = "职务作品or委托创作")
	private String zhiWuChuang;

}
