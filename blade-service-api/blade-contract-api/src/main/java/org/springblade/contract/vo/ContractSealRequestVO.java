package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 统一子公司（签章申请单位） 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author xhb
 * @date : 2021-06-16 16:10:59
 */
@Getter
@Setter
@ApiModel(description = "统一子公司（签章申请单位）请求对象")
public class ContractSealRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="序号")
	private String fdSeq;

    @ApiModelProperty(value="企业编号")
	private String fdFactno;

    @ApiModelProperty(value="单位名称")
	private String fdFactname;

    @ApiModelProperty(value="单位编号")
	private String fdTaxno;

}
