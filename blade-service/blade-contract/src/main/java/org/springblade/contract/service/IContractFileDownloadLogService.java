package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractFileDownloadLogEntity;
import org.springblade.contract.vo.ContractFileDownloadLogRequestVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 合同文件日志 服务类
 *
 * @author wpf
 * @date : 2021-06-23 10:30:37
 */
public interface IContractFileDownloadLogService extends BaseService<ContractFileDownloadLogEntity> {

	/**
	 * 分页查询
	 *
	 * @param page
	 * @param contractFileDownloadLog
	 * @return
	 */
	IPage<ContractFileDownloadLogEntity> pageList(IPage<ContractFileDownloadLogEntity> page, ContractFileDownloadLogRequestVO contractFileDownloadLog);

	/**
	 * 根据合同id查询下载文件日志
	 *
	 * @param contractId
	 * @return java.util.List<org.springblade.contract.entity.ContractFileDownloadLogEntity>
	 * @author jitwxs
	 * @date 2021/6/23 11:27
	 */
	List<ContractFileDownloadLogEntity> getList(Long contractId);
}
