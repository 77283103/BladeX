package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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

import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


/**
 * 业务类：21.新陈列协议书 实体类
 *
 * @author szw
 * @date : 2020-12-07 15:37:41
 */
@Getter
@Setter
@TableName("ywl_a_new_display")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "YwlANewDisplay对象", description = "业务类：21.新陈列协议书")
public class YwlANewDisplayEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 合作内容：乙方提供位于【？】的位置供甲方陈列其产品并负责甲方产品摆放的外观维护，甲方支付产品陈列费用
	 */
    @ApiModelProperty(value="合作内容：乙方提供位于【？】的位置供甲方陈列其产品并负责甲方产品摆放的外观维护，甲方支付产品陈列费用")
	private String ywlCooperationContent;
	/**
	 * 陈列开始时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="陈列开始时间")
	private Date ywlTheStartTime;
	/**
	 * 陈列结束时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="陈列结束时间")
	private Date ywlEndOfTime;

	/**
	 * 支付形式
	 */
	@ApiModelProperty(value="支付形式")
	private String ywlDisplayType;

	/**
	 * 陈列费用
	 */
    @ApiModelProperty(value="陈列费用")
	private BigDecimal ywlDisplayFee;

	/**
	 * 人民币大写
	 */
	@ApiModelProperty(value="人民币大写")
	private String ywlDisplayDfee;
	/**
	 * 其他
	 */
    @ApiModelProperty(value="其他")
	private String ywlOther;

	@ApiModelProperty(value = "新陈列协议书集合")
	@TableField(exist = false)
	private List<YwlANewDisplay1Entity> ywlANewDisplay1List;
}
