package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;


/**
 * 生产项目外包服务合同子表1 实体类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:22:11
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("product_out_service_contract1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ProductOutServiceContract1对象", description = "生产项目外包服务合同子表1")
public class ProductOutServiceContract1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@ApiModelProperty(value = "序号")
	private Integer num;
	/**
	 * 服务需求项目名称
	 */
	@ApiModelProperty(value = "服务需求项目名称")
	private String name;
	/**
	 * 计量单位
	 */
	@ApiModelProperty(value = "计量单位")
	private String unit;
	/**
	 * 未税单价
	 */
	@ApiModelProperty(value = "未税单价")
	private BigDecimal unitPrice;
	/**
	 * 服务内容及要求概述
	 */
	@ApiModelProperty(value = "服务内容及要求概述")
	private String content;
	/**
	 * 关联主表标识
	 */
	@ApiModelProperty(value = "关联主表标识")
	private Long proOutSerConId;

}
