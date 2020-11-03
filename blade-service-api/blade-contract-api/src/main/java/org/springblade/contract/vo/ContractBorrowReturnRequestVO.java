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
 * 借阅归还 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author xhb
 * @date : 2020-10-30 09:28:55
 */
@Getter
@Setter
@ApiModel(description = "借阅归还请求对象")
public class ContractBorrowReturnRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="借阅归还申请编号")
	private String returnId;

    @ApiModelProperty(value="合同名称")
	private String contractName;

    @ApiModelProperty(value="合同编号")
	private String contractNumber;

    @ApiModelProperty(value="归还人")
	private String returnee;

    @ApiModelProperty(value="归还部门")
	private String returnDepartment;

    @ApiModelProperty(value="归还页数")
	private Integer returnPages;

    @ApiModelProperty(value="归还位置")
	private String returnLocation;

    @ApiModelProperty(value="经办人")
	private String manager;

    @ApiModelProperty(value="经办单位")
	private String manageUnit;

    @ApiModelProperty(value="经办时间")
	private Date manageDate;

}
