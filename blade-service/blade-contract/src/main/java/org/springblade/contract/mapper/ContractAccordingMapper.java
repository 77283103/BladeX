package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractAccordingEntity;

/**
 *  Mapper 接口
 *
 * @author feng
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
