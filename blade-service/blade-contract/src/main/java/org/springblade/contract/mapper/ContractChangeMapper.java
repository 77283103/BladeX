package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractChangeEntity;

/**
 * 合同变更 Mapper 接口
 *
 * @author szw
 * @date : 2020-09-23 19:24:50
 */
public interface ContractChangeMapper extends BaseMapper<ContractChangeEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param change
	 * @return
	 */
	IPage<ContractChangeEntity> pageList(IPage<ContractChangeEntity> page, ContractChangeEntity change);

	/**
	 *
	 * @param id
	 * @return
	 */
	ContractChangeEntity selectById(Long id);

}
