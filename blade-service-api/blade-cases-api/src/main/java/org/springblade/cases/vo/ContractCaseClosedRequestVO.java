package org.springblade.cases.vo;

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
 * 案件结案 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author xhb
 * @date : 2020-10-30 10:03:18
 */
@Getter
@Setter
@ApiModel(description = "案件结案请求对象")
public class ContractCaseClosedRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="结案案件编号")
	private String closeCaseId;

    @ApiModelProperty(value="结案时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date closeCaseDate;

    @ApiModelProperty(value="是否已归档")
	private String isArchive;

    @ApiModelProperty(value="结案说明")
	private String closeCaseDescription;

    @ApiModelProperty(value="结案文书")
	private String closeCaseDocument;

}
