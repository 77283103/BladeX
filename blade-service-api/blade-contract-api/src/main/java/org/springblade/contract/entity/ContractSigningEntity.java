package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


/**
 * 合同签订表 实体类
 *
 * @author 合同签订
 * @date : 2020-11-05 09:34:31
 */
@Getter
@Setter
@TableName("contract_signing")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractSigning对象", description = "合同签订表")
public class ContractSigningEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 关联合同ID
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="关联合同ID")
	private Long contractId;
	/**
	 * 承办单位
	 */
    @ApiModelProperty(value="承办单位")
	private String manageUnit;
	/**
	 * 承办人
	 */
    @ApiModelProperty(value="承办人")
	private String manager;
	/**
	 * 签订时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="签订时间")
	private Date signDate;
	/**
	 * 我方签约人
	 */
    @ApiModelProperty(value="我方签约人")
	private String signForUs;
	/**
	 * 对方签约人
	 */
    @ApiModelProperty(value="对方签约人")
	private String signForOther;
	/**
	 * 合同起始时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="合同起始时间")
	private Date contractStartTime;
	/**
	 * 合同结束时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="合同结束时间")
	private Date contractEndTime;
	/**
	 * 合同文本扫描件
	 */
    @ApiModelProperty(value="合同文本扫描件")
	private String textFiles;
	/**
	 * 合同附件扫描件
	 */
    @ApiModelProperty(value="合同附件扫描件")
	private String attachedFiles;
	/**
	 * 递交方式（字典）
	 */
    @ApiModelProperty(value="递交方式（字典）")
	private String submissionType;
	/**
	 * 快递单号
	 */
    @ApiModelProperty(value="快递单号")
	private String courierNum;
	/**
	 * 快递公司
	 */
    @ApiModelProperty(value="快递公司")
	private String courierCompany;
	/**
	 * 收件人
	 */
    @ApiModelProperty(value="收件人")
	private String addressee;
	/**
	 * 收件人地址
	 */
    @ApiModelProperty(value="收件人地址")
	private String address;
	/**
	 * 文件存放位置
	 */
    @ApiModelProperty(value="文件存放位置")
	private String fileAddress;
	/**
	 * 备注说明
	 */
    @ApiModelProperty(value="备注说明")
	private String remark;
    /**
     * 关联归档目录文件
     */
    @ApiModelProperty(value="关联归档目录文件")
    @TableField(exist = false)
    private List<ContractSigningArchiveEntity> signingArchiveEntityList;

	@TableField(exist = false)
	@ApiModelProperty(value="关联归档目录文件")
	private String signingArchiveJson;

}
