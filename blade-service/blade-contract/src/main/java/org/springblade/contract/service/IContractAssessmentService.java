package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.dto.ContractAssessmentDTO;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.vo.ContractAssessmentRequestVO;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractAssessmentEntity;

/**
 * 合同评估表 服务类
 *
 * @author liyj
 * @date : 2020-09-24 10:41:34
 */
public interface IContractAssessmentService extends BaseService<ContractAssessmentEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param assessment
	 * @return
	 */
	IPage<ContractAssessmentEntity> pageList(IPage<ContractAssessmentEntity> page, ContractAssessmentEntity assessment);

	/**
	 * 合同评估保存方法
	 * @param vo
	 */
	void saveAssessment(ContractAssessmentRequestVO vo);

	/**
	 * 保存评估信息与修改合同状态
	 * @param contractStatus
	 * @param entity
	 */
	boolean save(String contractStatus,ContractAssessmentEntity entity);

}
