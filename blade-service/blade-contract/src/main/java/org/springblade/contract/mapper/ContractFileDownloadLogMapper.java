package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.contract.entity.ContractFileDownloadLogEntity;
import org.springblade.contract.vo.ContractFileDownloadLogRequestVO;

import java.util.List;

/**
 * 合同文件日志 Mapper 接口
 *
 * @author wpf
 * @date : 2021-06-23 10:30:37
 */
public interface ContractFileDownloadLogMapper extends BaseMapper<ContractFileDownloadLogEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractFileDownloadLog
	 * @return
	 */
	IPage<ContractFileDownloadLogEntity> pageList(IPage<ContractFileDownloadLogEntity> page, ContractFileDownloadLogRequestVO contractFileDownloadLog);

	/**
	 * 根据合同id查询下载文件日志
	 *
	 * @param contractId  合同ID
	 * @return java.util.List<org.springblade.contract.entity.ContractFileDownloadLogEntity>
	 * @author jitwxs
	 * @date 2021/6/23 11:27
	 */
	List<ContractFileDownloadLogEntity> selectList(@Param("contractId") Long contractId);
}
