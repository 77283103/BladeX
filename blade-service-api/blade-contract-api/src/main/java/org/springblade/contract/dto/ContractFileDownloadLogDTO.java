package org.springblade.contract.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ContractFileDownloadLogEntity;

/**
 * 合同文件日志 模型DTO
 *
 * @author wpf
 * @date : 2021-06-23 10:30:35
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ContractFileDownloadLogDTO extends ContractFileDownloadLogEntity {

	private static final long serialVersionUID = 1L;

}
