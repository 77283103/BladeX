package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;


/**
 * 生产项目外包服务合同子表2 实体类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:23:05
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("product_out_service_contract2")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ProductOutServiceContract2对象", description = "生产项目外包服务合同子表2")
public class ProductOutServiceContract2Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@ApiModelProperty(value = "序号")
	private Integer rewardNum;
	/**
	 * 奖励内容
	 */
	@ApiModelProperty(value = "奖励内容")
	private String rewardContent;
	/**
	 * 奖励金额（元/次）
	 */
	@ApiModelProperty(value = "奖励金额（元/次）")
	private BigDecimal rewardAmount;
	/**
	 * 关联主表标识
	 */
	@ApiModelProperty(value = "关联主表标识")
	private Long proOutSerConId;

}
