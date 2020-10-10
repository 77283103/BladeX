package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.vo.ContractAssessmentRequestVO;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 *  服务类
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:37
 */
public interface IContractFormInfoService extends BaseService<ContractFormInfoEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractFormInfo
	 * @return
	 */
	IPage<ContractFormInfoEntity> pageList(IPage<ContractFormInfoEntity> page, ContractFormInfoEntity contractFormInfo);

	/**
	 * 导出后修改合同状态
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
	 * 相对方保存方法
	 * @param vo 合同信息
	 * @return
	 */
	void saveCounterpart(ContractFormInfoRequestVO vo);

	/**
	 * 依据保存方法
	 * @param vo 合同信息
	 * @return
	 */
	void saveAccording(ContractFormInfoRequestVO vo);


	/**
	 * 合同评估保存方法v
	 * @param vo
	 */
	void saveAssessment(ContractFormInfoRequestVO vo);


	/**
	 * 合同保存方法
	 * @param id 合同id
	 * @return
	 */
	ContractFormInfoEntity getById(Long id);

}
