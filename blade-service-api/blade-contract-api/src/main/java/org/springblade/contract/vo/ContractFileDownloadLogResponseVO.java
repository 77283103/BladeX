package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.ContractFileDownloadLogEntity;

/**
 * 合同文件日志 返回模型VO
 *
 * @author wpf
 * @date : 2021-06-23 10:30:40
 */
@Getter
@Setter
@ToString
@ApiModel(description = "合同文件日志返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractFileDownloadLogResponseVO extends ContractFileDownloadLogEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
