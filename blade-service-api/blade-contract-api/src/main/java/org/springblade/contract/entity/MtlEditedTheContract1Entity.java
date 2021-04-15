package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deepoove.poi.data.PictureRenderData;
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
 * 媒体类：修图合同关联表 实体类
 *
 * @author 媒体类：修图合同关联表
 * @date : 2020-12-11 05:00:47
 */
@Getter
@Setter
@TableName("mtl_edited_the_contract1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtlEditedTheContract1对象", description = "媒体类：修图合同关联表")
public class MtlEditedTheContract1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 图片小样
	 */
    @ApiModelProperty(value="图片小样")
	private String pictureSample;
	/**
	 * 图片小样
	 */
	@TableField(exist = false)
	private PictureRenderData pictureSampleD;
	/**
	 * 修改要求
	 */
    @ApiModelProperty(value="修改要求")
	private String modificationRequirements;
	/**
	 * 交付时间
	 */
    @ApiModelProperty(value="交付时间")
	private String deliveryTime;
	/**
	 * 交付方式
	 */
    @ApiModelProperty(value="交付方式")
	private String modeDelivery;
	/**
	 * 合同ID
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="合同ID")
	private Long contractId;
}
