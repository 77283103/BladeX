package org.springblade.contract.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

import org.springblade.contract.entity.ContractSigningArchiveEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import org.springblade.core.mp.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;


/**
 * 合同签订表 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 合同签订
 * @date : 2020-11-05 09:34:31
 */
@Getter
@Setter
@ToString
@ApiModel(description = "合同签订表请求对象")
public class ContractSigningRequestVO extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="关联合同ID")
	private Long contractId;

	@ApiModelProperty(value="承办单位")
	private String manageUnit;

	@ApiModelProperty(value="承办人")
	private String manager;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="签订时间")
	private Date signDate;

	@ApiModelProperty(value="我方签约人")
	private String signForUs;

	@ApiModelProperty(value="对方签约人")
	private String signForOther;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="合同起始时间")
	private Date contractStartTime;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="合同结束时间")
	private Date contractEndTime;

	@ApiModelProperty(value="合同文本扫描件")
	private String textFiles;

	@ApiModelProperty(value="合同附件扫描件")
	private String attachedFiles;

	@ApiModelProperty(value="递交方式（字典）")
	private String submissionType;

	@ApiModelProperty(value="快递单号")
	private String courierNum;

	@ApiModelProperty(value="快递公司")
	private String courierCompany;

	@ApiModelProperty(value="收件人")
	private String addressee;

	@ApiModelProperty(value="收件人地址")
	private String address;

	@ApiModelProperty(value="文件存放位置")
	private String fileAddress;

	@ApiModelProperty(value="备注说明")
	private String remark;

	@ApiModelProperty(value="关联归档目录文件")
	private List<ContractSigningArchiveEntity> signingArchiveEntityList;
}
