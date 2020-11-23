package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractPerformanceColPayEntity;
import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springblade.contract.vo.ContractPerformanceColPayRequestVO;

import java.util.List;

/**
 * 收付款计划清单-收付款 Mapper 接口
 *
 * @author szw
 * @date : 2020-11-05 17:07:02
 */
public interface ContractPerformanceColPayMapper extends BaseMapper<ContractPerformanceColPayEntity> {

	/**
	 * 分页查询
	 * 一般超期
	 * @param page
	 * @param contractPerformanceColPay
	 * @return
	 */
	IPage<ContractPerformanceColPayEntity> pageList(IPage<ContractPerformanceColPayEntity> page, ContractPerformanceColPayRequestVO contractPerformanceColPay);

	/**
	 * 分页查询
	 * 严重超期
	 * @param page
	 * @param contractPerformanceColPay
	 * @return
	 */
	IPage<ContractPerformanceColPayEntity> pageListSerious(IPage<ContractPerformanceColPayEntity> page, ContractPerformanceColPayRequestVO contractPerformanceColPay);
	/**
	 * 分页查询
	 * 超期超期
	 * @param page
	 * @param contractPerformanceColPay
	 * @return
	 */
	IPage<ContractPerformanceColPayEntity> pageListLong(IPage<ContractPerformanceColPayEntity> page, ContractPerformanceColPayRequestVO contractPerformanceColPay);

	/**
	 * 根据合同id查询查询履约计划收付款信息
	 *
	 * @param id
	 * @return
	 */
	List<ContractPerformanceColPayEntity> selectByIds(Long id);

	/**
	 * 根据合同id删除履约计划收付款信息
	 * @param id
	 * @return
	 */
	void deleteByContractId(Long id);

	/**
	 *查询合同履约计划个数
	 * @param id
	 * @return
	 */
	Integer countById(Long id);
	/**
	 * 计算合同履约计划完成的条数
	 * @param id
	 * @return
	 */
	Integer countOKById(Long id);

}
