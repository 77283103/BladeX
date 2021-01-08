package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.vo.ContractCounterpartRequestVO;

import java.util.List;

/**
 * 相对方管理 Mapper 接口
 *
 * @author XHB
 * @date : 2020-09-23 19:35:04
 */
public interface ContractCounterpartMapper extends BaseMapper<ContractCounterpartEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param counterpart
	 * @return
	 */
	IPage<ContractCounterpartEntity> pageList(IPage<ContractCounterpartEntity> page, ContractCounterpartRequestVO counterpart);

	/**
	 * 根据合同id查询相对方集合
	 * @param id 合同id
	 * @return
	 */
	List<ContractCounterpartEntity> selectByIds(Long id);

	/**
	 *
	 * @param id
	 * @return
	 */
	ContractCounterpartEntity selectById(Long id);

	/**
	 * 根据相对方名称获取集合
	 * @param name 合同name
	 * @return
	 */
	List<ContractCounterpartEntity> selectByName(String name);

	/**
	 *
	 * @param entity
	 */
	void updateByName(ContractCounterpartEntity entity);
}
