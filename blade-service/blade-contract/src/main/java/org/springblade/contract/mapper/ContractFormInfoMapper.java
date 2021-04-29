package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.MonthTypeSelect;
import org.springblade.contract.vo.ContractFormInfoRequestVO;

import java.math.BigDecimal;
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
	IPage<ContractFormInfoEntity> pageList(IPage<ContractFormInfoEntity> page, ContractFormInfoRequestVO contractFormInfo);
	/**
	 * 部门合同数量、金额统计表
	 * @param page
	 * @param contractFormInfo
	 * @return
	 */
	IPage<ContractFormInfoEntity> deptType(IPage<ContractFormInfoEntity> page, ContractFormInfoEntity contractFormInfo);

	/**
	 * 月度合同数量统计表
	 * @param
	 * @return
	 */
	List<MonthTypeSelect> monthType(Long createDept, String yearStart);
	List<ContractFormInfoEntity> monthByIdInfo(@Param("createDept")Long createDept,
											   @Param("yearStart") String yearStart,
											   @Param("month") String month);
	IPage<ContractFormInfoEntity> monthTypeFirm(IPage<ContractFormInfoEntity> page, ContractFormInfoEntity contractFormInfo);

	/**
	 * 各类型合同统计表
	 * @param page
	 * @param contractFormInfo
	 * @return
	 */
	IPage<ContractFormInfoEntity> eachType(IPage<ContractFormInfoEntity> page, ContractFormInfoEntity contractFormInfo);

	BigDecimal payTypeAmount(Long contractBigCategory, Long colPayType, Long createDept, String yearStart, String yearEnd);
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
	 * 变更合同详情
	 * @param id
	 * @return
	 */
	ContractFormInfoEntity selectByChangeId(Long id);

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
	void saveAccording(Long  id, List<ContractAccordingEntity> accordingIds);

	/**
	 * 保存合同依据关联表
	 * @param accordingIds 相对方id
	 * @param id 合同id
	 */
	void saveAccordingIds(Long  id, List<String> accordingIds);
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
	 * 保存合同签订关联表
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
	 * 動態返回合同選擇的下拉選
	 * @return list
	 */
	List<ContractFormInfoEntity> getChooseList();

	/**
	 * 编号查询
	 * @return list
	 */
	List<ContractFormInfoEntity> selectByContractNumber(@Param("contractFormInfo") ContractFormInfoEntity contractFormInfo);

	/**
	 *
	 * @return
	 */
	Double getNumAmount(String contractBigCategory);
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

	/**
	 * 根据保证金id查询合同id
	 *
	 * @param id
	 * @return
	 */
	List<ContractFormInfoEntity> findContractList(Long id);

	/**
	 * 范本使用的合同集合
	 * @param id
	 * @return
	 */
	List<ContractFormInfoEntity> getByIdForm(Long id);
}
