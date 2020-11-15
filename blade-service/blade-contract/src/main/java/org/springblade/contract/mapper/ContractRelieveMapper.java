package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractRelieveEntity;
import org.springblade.contract.vo.ContractRelieveRequestVO;

/**
 * 合同解除 Mapper 接口
 *
 * @author 合同解除
 * @date : 2020-11-05 09:24:00
 */
public interface ContractRelieveMapper extends BaseMapper<ContractRelieveEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractRelieve
	 * @return
	 */
	IPage<ContractRelieveEntity> pageList(IPage<ContractRelieveEntity> page, ContractRelieveRequestVO contractRelieve);

	/**
	 * 根据合同id查询信息解除
	 * @param id
	 * @return
	 */
	ContractRelieveEntity selectRelieveById(Long id);
}
