package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractPerformanceColPayEntity;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.vo.ContractPerformanceColPayRequestVO;
import org.springblade.contract.vo.ContractPerformanceColPayResponseVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 收付款计划清单-收付款 服务类
 *
 * @author szw
 * @date : 2020-11-05 17:07:02
 */
public interface IContractPerformanceColPayService extends BaseService<ContractPerformanceColPayEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractPerformanceColPay
	 * @return
	 */
	IPage<ContractPerformanceColPayResponseVO> pageList(IPage<ContractPerformanceColPayEntity> page, ContractPerformanceColPayRequestVO contractPerformanceColPay);
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
	 * vo
	 * @param id
	 * @return
	 */
	ContractPerformanceColPayResponseVO getById(Long id);

	/**
	 * 保存履约计划收付款清单
	 *
	 */
	void savePerformanceColPay(ContractFormInfoRequestVO contractFormInfo);

	/**
	 * 通过合同id删除
	 * @param id 合同id
	 * @return
	 */
	void deleteByContractId(Long id);
}
