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
	 * @param ids 保证金id
	 * @param id 合同id
	 * @return
	 */
	void saveAccording(List<Long> ids, Long id);

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

}
