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
 * 媒体类：平面广告拍摄制作合同（关联表2） 实体类
 *
 * @author 张文武
 * @date : 2021-01-04 11:23:59
 */
@Getter
@Setter
@TableName("mtb_production_contract2")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtbProductionContract2对象", description = "媒体类：平面广告拍摄制作合同（关联表2）")
public class MtbProductionContract2Entity extends BaseEntity {

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
	private String smallKind;
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
