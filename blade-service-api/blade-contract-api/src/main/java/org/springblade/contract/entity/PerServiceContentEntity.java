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
 * 履约服务内容 实体类
 *
 * @author chenzy
 * @date : 2021-04-20 16:02:05
 */
@Getter
@Setter
@TableName("per_service_content")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PerServiceContent对象", description = "履约服务内容")
public class PerServiceContentEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 服务内容
	 */
    @ApiModelProperty(value="服务内容")
	private String serviceContent;
	/**
	 * 合同标识
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="合同标识")
	private Long contractId;

}
