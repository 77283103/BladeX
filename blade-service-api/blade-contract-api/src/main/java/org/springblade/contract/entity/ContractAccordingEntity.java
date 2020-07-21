package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.time.LocalDateTime;


/**
 *  实体类
 *
 * @author Chill
 */
@Data
@TableName("contract_according")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractAccording对象", description = "")
public class ContractAccordingEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 依据名称
	 */
	@ApiModelProperty(value = "依据名称")
	private String name;
	/**
	 * 依据附件
	 */
	@ApiModelProperty(value = "依据附件")
	private String accordingFiles;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;
	/**
	 * 是否复用
	 */
	@ApiModelProperty(value = "是否复用")
	private Integer isReused;

}
