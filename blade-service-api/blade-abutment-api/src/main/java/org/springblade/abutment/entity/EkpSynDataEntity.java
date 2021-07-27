package org.springblade.abutment.entity;

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
 * ekp同步数据 实体类
 *
 * @author chenzy
 * @date : 2021-07-27 15:42:11
 */
@Getter
@Setter
@TableName("ekp_syn_data")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "EkpSynData对象", description = "ekp同步数据")
public class EkpSynDataEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 数据json
	 */
    @ApiModelProperty(value="数据json")
	private String data;

}
