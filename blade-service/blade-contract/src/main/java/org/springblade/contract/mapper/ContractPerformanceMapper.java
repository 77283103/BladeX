package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springblade.contract.vo.ContractPerformanceRequestVO;

import java.util.List;

/**
 * 接收/提供服务计划清单 Mapper 接口
 *
 * @author szw
 * @date : 2020-11-05 17:06:55
 */
public interface ContractPerformanceMapper extends BaseMapper<ContractPerformanceEntity> {

	/**
	 * 分页查询
	 * 一般超期
	 * @param page
	 * @param contractPerformance
	 * @return
	 */
	IPage<ContractPerformanceEntity> pageList(IPage<ContractPerformanceEntity> page, ContractPerformanceRequestVO contractPerformance);

	/**
	 * 分页查询
	 * 严重超期
	 * @param page
	 * @param contractPerformance
	 * @return
	 */
	IPage<ContractPerformanceEntity> pageListSerious(IPage<ContractPerformanceEntity> page, ContractPerformanceRequestVO contractPerformance);

	/**
	 * 分页查询
	 * 超期超期
	 * @param page
	 * @param contractPerformance
	 * @return
	 */
	IPage<ContractPerformanceEntity> pageListLong(IPage<ContractPerformanceEntity> page, ContractPerformanceRequestVO contractPerformance);

	/**
	 * 根据合同id查询查询履约计划信息
	 *
	 * @param id
	 * @return
	 */
	List<ContractPerformanceEntity> selectByIds(Long id);


	Integer selectByIdDays(Long id);

	/**
	 * 根据合同id删除履约计划信息
	 * @param id
	 * @return
	 */
	void deleteByContractId(Long id);
}
