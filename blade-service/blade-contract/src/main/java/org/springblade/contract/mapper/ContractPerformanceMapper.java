package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractPerformanceEntity;

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

}
