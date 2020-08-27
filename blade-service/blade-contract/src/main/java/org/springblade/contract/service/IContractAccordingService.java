package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractAccordingEntity;

import java.util.List;

/**
 *  服务类
 *
 * @author feng
 */
public interface IContractAccordingService extends BaseService<ContractAccordingEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param according
	 * @return
	 */
	IPage<ContractAccordingEntity> pageList(IPage<ContractAccordingEntity> page, ContractAccordingEntity according);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	ContractAccordingEntity selectById(Long id);

	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	boolean del(String ids);
}
