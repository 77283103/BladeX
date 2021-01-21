package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.util.Date;


/**
 * 班车服务合同子表1 实体类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:29:12
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("bus_service_contract1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "BusServiceContract1对象", description = "班车服务合同子表1")
public class BusServiceContract1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * A线发车时间
	 */
	@ApiModelProperty(value = "A线发车时间")
	private Date busTimeA;
	/**
	 * B线发车时间
	 */
	@ApiModelProperty(value = "B线发车时间")
	private Date busTimeB;
	/**
	 * 关联主表标识
	 */
	@ApiModelProperty(value = "关联主表标识")
	private Long contractId;

}
