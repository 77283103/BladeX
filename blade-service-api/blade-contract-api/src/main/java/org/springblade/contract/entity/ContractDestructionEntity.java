package org.springblade.contract.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springblade.core.tool.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 合同销毁 实体类
 *
 * @author szw
 * @date : 2020-11-11 16:37:01
 */
@Getter
@Setter
@TableName("contract_destruction")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractDestruction对象", description = "合同销毁")
public class ContractDestructionEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 销毁说明
	 */
 @NotBlank(message = "销毁说明不能为空")
    @ApiModelProperty(value="销毁说明",required = true)
	private String description;
	/**
	 * 附件说明
	 */
    @ApiModelProperty(value="附件说明")
	private String file;

	/**
	 * 关联合同ID
	 */
	@ApiModelProperty(value="关联合同ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long contractId;

}
