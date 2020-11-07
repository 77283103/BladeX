package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springblade.contract.vo.ContractPerformanceRequestVO;

/**
 * 接收/提供服务计划清单 Mapper 接口
 *
 * @author szw
 * @date : 2020-11-05 17:06:55
 */
public interface ContractPerformanceMapper extends BaseMapper<ContractPerformanceEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractPerformance
	 * @return
	 */
	IPage<ContractPerformanceEntity> pageList(IPage<ContractPerformanceEntity> page, ContractPerformanceRequestVO contractPerformance);

}
