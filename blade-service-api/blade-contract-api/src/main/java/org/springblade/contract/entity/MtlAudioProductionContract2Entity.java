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
 * 媒体类：音频制作合同关联表2 实体类
 *
 * @author 媒体类：音频制作合同关联表2
 * @date : 2020-12-11 03:31:35
 */
@Getter
@Setter
@TableName("mtl_audio_production_contract2")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtlAudioProductionContract2对象", description = "媒体类：音频制作合同关联表2")
public class MtlAudioProductionContract2Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 涉第三方权利
	 */
    @ApiModelProperty(value="涉第三方权利")
	private String intellectualProperty;
	/**
	 * 使用期限
	 */
    @ApiModelProperty(value="使用期限")
	private String serviceLife;
	/**
	 * 使用区域
	 */
    @ApiModelProperty(value="使用区域")
	private String useArea;
	/**
	 * 合同ID
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="合同ID")
	private Long contractId;

}
