package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractAccordingEntity;

/**
 * 合同依据管理 Mapper 接口
 *
 * @author XHB
 * @date : 2020-09-24 14:20:31
 */
public interface ContractAccordingMapper extends BaseMapper<ContractAccordingEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param according
	 * @return
	 */
	IPage<ContractAccordingEntity> pageList(IPage<ContractAccordingEntity> page, ContractAccordingEntity according);

}
