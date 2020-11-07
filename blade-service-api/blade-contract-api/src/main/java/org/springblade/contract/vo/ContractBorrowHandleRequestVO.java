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
 * 借阅处理 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author xhb
 * @date : 2020-10-30 09:28:19
 */
@Getter
@Setter
@ApiModel(description = "借阅处理请求对象")
public class ContractBorrowHandleRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="借阅处理申请编号")
	private String handleId;

    @ApiModelProperty(value="合同名称")
	private String contractName;

    @ApiModelProperty(value="合同编号")
	private String contractNumber;

    @ApiModelProperty(value="借阅页数")
	private Integer borrowPages;

    @ApiModelProperty(value="快递公司")
	private String courierCompany;

    @ApiModelProperty(value="快递单号")
	private String trackingNumber;

    @ApiModelProperty(value="收件人")
	private String addressee;

    @ApiModelProperty(value="收件地址")
	private String recipientAddress;

    @ApiModelProperty(value="收件人电话")
	private String recipientPhone;

}
