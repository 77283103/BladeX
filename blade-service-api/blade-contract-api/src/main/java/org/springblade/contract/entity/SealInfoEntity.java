package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.time.LocalDateTime;


/**
 * 用印名称 实体类
 *
 * @author szw
 * @date : 2020-09-24 01:27:14
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_seal_using_info")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SealInfo对象", description = "用印名称")
public class SealInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 用印时间
	 */
	@ApiModelProperty(value = "用印时间")
	private LocalDateTime signTime;
	/**
	 * 用印人
	 */
	@ApiModelProperty(value = "用印人")
	private Long signPerson;
	/**
	 * 用印说明
	 */
	@ApiModelProperty(value = "用印说明")
	private String signRemark;
	/**
	 * 关联合同id
	 */
	@ApiModelProperty(value = "关联合同id")
	private Long refContractId;

}
