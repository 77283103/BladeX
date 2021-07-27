package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.contract.vo.ContractPerformanceRequestVO;
import org.springblade.contract.vo.ContractPerformanceResponseVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 接收/提供服务计划清单 服务类
 *
 * @author szw
 * @date : 2020-11-05 17:06:55
 */
public interface IContractPerformanceService extends BaseService<ContractPerformanceEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractPerformance
	 * @return
	 */
	IPage<ContractPerformanceResponseVO> pageList(IPage<ContractPerformanceEntity> page, ContractPerformanceRequestVO contractPerformance);
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
	 * vo
	 * @param id
	 * @return
	 */
	ContractPerformanceResponseVO getById(Long id);

	/**
	 * 保存履约计划清单
	 *
	 * @param contractFormInfo
	 */
	void savePerformance(ContractFormInfoRequestVO contractFormInfo);


	/**
	 * 通过合同id删除
	 * @param id 合同id
	 * @return
	 */
	void deleteByContractId(Long id);

}
