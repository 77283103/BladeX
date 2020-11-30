package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springblade.contract.vo.ContractFormInfoRequestVO;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:37
 */
public interface ContractFormInfoMapper extends BaseMapper<ContractFormInfoEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractFormInfo
	 * @return
	 */
	IPage<ContractFormInfoEntity> pageList(IPage<ContractFormInfoEntity> page, ContractFormInfoEntity contractFormInfo);

	/**
	 * 合同类型统计分析分页查询
	 * @param page
	 * @param contractFormInfo
	 * @return
	 */
	IPage<ContractFormInfoEntity> pageListStatisticsType(IPage<ContractFormInfoEntity> page, ContractFormInfoEntity contractFormInfo);
	/**
	 * 合同状态分组统计分析分页查询
	 * @param page
	 * @param contractFormInfo
	 * @return
	 */
	IPage<ContractFormInfoEntity> pageListStatisticsStatus(IPage<ContractFormInfoEntity> page, ContractFormInfoEntity contractFormInfo);

	/**
	 * 合同支付类型分组统计分析分页查询
	 * @param page
	 * @param contractFormInfo
	 * @return
	 */
	IPage<ContractFormInfoEntity> pageListStatisticsColPayType(IPage<ContractFormInfoEntity> page, ContractFormInfoEntity contractFormInfo);
	/**
	 * 修改合同状态
	 * @param contractStatus,id
	 * @return
	 */
	boolean updateExportStatus(String contractStatus,Long id);

	/**
	 * 用印分页查询
	 * @param page
	 * @param contractFormInfoRequestVO
	 * @return
	 */
	IPage<ContractFormInfoEntity> pageListSealInfo(IPage<ContractFormInfoRequestVO> page, ContractFormInfoRequestVO contractFormInfoRequestVO);

	/**
	 * 合同详情
	 * @param id
	 * @return
	 */
	ContractFormInfoEntity selectById(Long id);

	/**
	 * 保存合同相对方关联表
	 * @param counterpart 相对方
	 * @param id 合同id
	 */
	void saveCounterpart(Long  id, List<ContractCounterpartEntity> counterpart);


	/**
	 * 删除合同相对方关联表
	 * @param id 合同id
	 */
	void deleteCounterpart(Long  id);


	/**
	 * 保存合同依据关联表
	 * @param accordingIds 相对方id
	 * @param id 合同id
	 */
	void saveAccording(Long  id, List<Long> accordingIds);

	/**
	 * 保存合同用印关联表
	 * @param refContractId
	 * @param sealId
	 */
	void saveSeal(Long refContractId,Long sealId);
	/**
	 * 保存合同评估关联表
	 * @param assessmentId 评估id
	 * @param contractId 合同id
	 */
	void saveAssessment(Long  contractId, Long assessmentId);

	/**
	 * 保存合同归档关联表
	 * @param contractId 合同id
	 * @param archiveId 归档id
	 */
	void saveArchive(Long contractId,Long archiveId);

	/**
	 * 保存合同归档关联表
	 * @param contractId 合同id
	 * @param signingId 签订id
	 */
	void saveSigning(Long contractId,Long signingId);
	/**
	 * 保存合同相对方关联表
	 * @param performance 履约对象
	 * void savePerformance(ContractPerformanceEntity performance);
	 */


	/**
	 *统计合同导出次数 修改下载状态
	 * @param id 合同id
	 * @param fileExportCount 下载次数
	 * @param fileExportCategory 下载状态
	 */
	boolean textExportCount(Long id,Integer fileExportCount,String  fileExportCategory);

	/**
	 * 合同大类金额
	 * @return list
	 */
	List<ContractFormInfoEntity> getAmountList();

	/**
	 * 合同大类数量
	 * @return list
	 */
	List<ContractFormInfoEntity> getNumList();

	/**
	 * 获取合同金额sum
	 * @return
	 */
	Double selectAmountSum();

	/**
	 * 根据合同类型查询已签订合同数量
	 * @param contractBigCategory
	 * @return
	 */
	Integer selectSigningCount(String contractBigCategory);
}
