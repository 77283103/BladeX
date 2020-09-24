package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springblade.core.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.time.LocalDateTime;


/**
 * 合同签订表 实体类
 *
 * @author liyj
 * @date : 2020-09-23 19:27:05
 */
@Data
@TableName("contract_signing")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractSigning对象", description = "合同签订表")
public class ContractSigningEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 签订时间
	 */
	@ApiModelProperty(value = "签订时间")
	private LocalDateTime signDate;
	/**
	 * 我方签约人
	 */
	@ApiModelProperty(value = "我方签约人")
	private String signForUs;
	/**
	 * 对方签约人
	 */
	@ApiModelProperty(value = "对方签约人")
	private String signForOther;
	/**
	 * 合同起始时间
	 */
	@ApiModelProperty(value = "合同起始时间")
	private LocalDateTime contractStartTime;
	/**
	 * 合同结束时间
	 */
	@ApiModelProperty(value = "合同结束时间")
	private LocalDateTime contractEndTime;
	/**
	 * 合同文本扫描件
	 */
	@ApiModelProperty(value = "合同文本扫描件")
	private String textFiles;
	/**
	 * 合同附件扫描件
	 */
	@ApiModelProperty(value = "合同附件扫描件")
	private String attachedFiles;
	/**
	 * 备注说明
	 */
	@ApiModelProperty(value = "备注说明")
	private String remark;

}
