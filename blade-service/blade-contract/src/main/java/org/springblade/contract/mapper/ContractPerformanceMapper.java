package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractPerformanceEntity;

import java.util.List;

/**
 * 合同履约计划 Mapper 接口
 *
 * @author liyj
 * @date : 2020-09-23 19:26:28
 */
public interface ContractPerformanceMapper extends BaseMapper<ContractPerformanceEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param performance
	 * @return
	 */
	IPage<ContractPerformanceEntity> pageList(IPage<ContractPerformanceEntity> page, ContractPerformanceEntity performance);

	/**
	 * 根据合同id查询履约集合
	 * @param id 合同id
	 * @return
	 */
	List<ContractPerformanceEntity> selectByContractId(Long id);
}
