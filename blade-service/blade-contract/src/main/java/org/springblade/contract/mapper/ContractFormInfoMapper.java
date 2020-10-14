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
	 * 用印后修改合同状态
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
	 * 合同评估后修改合同状态
	 * @param contractStatus 合同状态
	 * @param id 合同id
	 * @return
	 */
	boolean updateAssessmentStatus(String  contractStatus,Long id);

	/**
	 * 保存合同相对方关联表
	 * @param counterpartIds 相对方id
	 * @param id 合同id
	 */
	void saveCounterpart(Long  id, List<Long> counterpartIds);

	/**
	 * 保存合同相对方关联表
	 * @param accordingIds 相对方id
	 * @param id 合同id
	 */
	void saveAccording(Long  id, List<Long> accordingIds);

	/**
	 * 保存合同评估关联表
	 * @param assessmentId 评估id
	 * @param contractId 合同id
	 */
	void saveAssessment(Long  contractId, Long assessmentId);

	/**
	 * 保存合同相对方关联表
	 * @param performance 履约对象
	 */
	void savePerformance(ContractPerformanceEntity performance);

	/**
	 *统计合同导出次数 修改下载状态
	 * @param id 合同id
	 * @param fileExportCount 下载次数
	 * @param fileExportCategory 下载状态
	 */
	boolean textExportCount(Long id,Integer fileExportCount,String  fileExportCategory);

}
