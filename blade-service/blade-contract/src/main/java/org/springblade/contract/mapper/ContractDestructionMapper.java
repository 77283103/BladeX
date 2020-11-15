package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractDestructionEntity;
import org.springblade.contract.vo.ContractDestructionRequestVO;

/**
 * 合同销毁 Mapper 接口
 *
 * @author szw
 * @date : 2020-11-11 16:37:01
 */
public interface ContractDestructionMapper extends BaseMapper<ContractDestructionEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractDestruction
	 * @return
	 */
	IPage<ContractDestructionEntity> pageList(IPage<ContractDestructionEntity> page, ContractDestructionRequestVO contractDestruction);

}
