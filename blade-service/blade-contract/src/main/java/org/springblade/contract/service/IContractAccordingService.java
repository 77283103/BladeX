package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.vo.ContractAccordingRequestVO;
import org.springblade.contract.vo.ContractAccordingResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 合同依据管理 服务类
 *
 * @author XHB
 * @date : 2020-09-24 14:20:32
 */
public interface IContractAccordingService extends BaseService<ContractAccordingEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param according
	 * @return
	 */
	IPage<ContractAccordingResponseVO> pageList(IPage<ContractAccordingResponseVO> page, ContractAccordingRequestVO according);


	/**
	 * 依据保存方法
	 * @param accordingEntity 保证金id
	 * @param id 合同id
	 * @return
	 */
	void saveAccording(ContractAccordingEntity accordingEntity, Long id);

	/**
	 * 依据删除方法
	 * @param id 合同id
	 * @return
	 */
	void deleteByContractId(Long id);

	/**
	 *
	 * @param id
	 * @return
	 */
	ContractAccordingEntity selectAccordingById(Long id);


	/**
	 * 根据编码查询依据
	 * @param code
	 * @return
	 */
	ContractAccordingEntity selectAccordingByCode(String code);


	/**
	 * 批量起草-保存合同、依据关系数据
	 * @param accordingId
	 * @param contractIds
	 */
	void saveByBatchDraft(Long accordingId, List<Long> contractIds);



}
