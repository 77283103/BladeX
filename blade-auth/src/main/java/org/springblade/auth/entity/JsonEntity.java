package org.springblade.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.util.Date;


/**
 * 临时对象 实体类
 *
 * @author XHB
 * @date : 2020-09-24 14:20:29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_json")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "临时对象", description = "临时对象")
public class JsonEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 依据名称
	 */
	@ApiModelProperty(value = "依据名称")
	private String accordingName;
	/**
	 * 查看依据的id
	 */
	@ApiModelProperty(value = "查看依据的id")
	private String fileId;

	/**
	 * 依据code
	 */
	@ApiModelProperty(value = "依据code")
	private String code;
	/**
	 * 单据类型
	 */
	@ApiModelProperty(value = "单据类型")
	private String documentType;
	/**
	 * 依据地址
	 */
	@ApiModelProperty(value = "依据地址")
	private String accordingAddress;

}
