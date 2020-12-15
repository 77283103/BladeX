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
 * 媒体类：视频广告拍摄制作合同关联表2 实体类
 *
 * @author 媒体类：视频广告拍摄制作合同关联表2
 * @date : 2020-12-11 05:31:03
 */
@Getter
@Setter
@TableName("mtl_shooting_and_production_contract2")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtlShootingAndProductionContract2对象", description = "媒体类：视频广告拍摄制作合同关联表2")
public class MtlShootingAndProductionContract2Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 第三方知识产权
	 */
    @ApiModelProperty(value="第三方知识产权")
	private String intellectualProperty;
	/**
	 * 小样
	 */
    @ApiModelProperty(value="小样")
	private String sample;
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
