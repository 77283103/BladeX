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
 * 业务类：21.新陈列协议书关联表 实体类
 *
 * @author kx
 * @date : 2020-12-16 16:42:38
 */
@Getter
@Setter
@TableName("ywl_a_new_display1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "YwlANewDisplay1对象", description = "业务类：21.新陈列协议书关联表")
public class YwlANewDisplay1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
    @ApiModelProperty(value="序号")
	private String ywlNumber;
	/**
	 * 陈列产品
	 */
    @ApiModelProperty(value="陈列产品")
	private String ywlDisplayProducts;
	/**
	 * 陈列方式
	 */
    @ApiModelProperty(value="陈列方式")
	private String ywlDisplayMode;
	/**
	 * 陈列标准
	 */
    @ApiModelProperty(value="陈列标准")
	private String ywlMerchandisingStandards;
	/**
	 * 合同ID
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="合同ID")
	private Long contractId;

}
