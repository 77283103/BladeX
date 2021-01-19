package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;


/**
 * 生产项目外包服务合同子表3 实体类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:24:10
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("product_out_service_contract3")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ProductOutServiceContract3对象", description = "生产项目外包服务合同子表3")
public class ProductOutServiceContract3Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@ApiModelProperty(value = "序号")
	private Integer punishNum;
	/**
	 * 违约内容
	 */
	@ApiModelProperty(value = "违约内容")
	private String punishContent;
	/**
	 * 违约金金额（元/次）
	 */
	@ApiModelProperty(value = "违约金金额（元/次）")
	private BigDecimal punishAmount;
	/**
	 * 关联主表标识
	 */
	@ApiModelProperty(value = "关联主表标识")
	private Long proOutSerCon3Id;

}
