package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractRelieveEntity;

/**
 *  Mapper 接口
 *
 * @author szw
 * @date : 2020-09-23 20:10:29
 */
public interface ContractRelieveMapper extends BaseMapper<ContractRelieveEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param relieve
	 * @return
	 */
	IPage<ContractRelieveEntity> pageList(IPage<ContractRelieveEntity> page, ContractRelieveEntity relieve);

}
