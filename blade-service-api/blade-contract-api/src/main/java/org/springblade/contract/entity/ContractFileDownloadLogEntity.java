package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;


/**
 * 合同文件日志 实体类
 *
 * @author wpf
 * @date : 2021-06-23 10:30:35
 */
@Getter
@Setter
@TableName("contract_file_download_log")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractFileDownloadLog对象", description = "合同文件日志")
public class ContractFileDownloadLogEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号")
	private String code;
	/**
	 * 账号
	 */
	@ApiModelProperty(value = "账号")
	private String account;
	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
	private String realName;
	/**
	 * 合同ID
	 */
	@ApiModelProperty(value = "合同ID")
	private String contractId;

}
