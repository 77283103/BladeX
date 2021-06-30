package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractSealEntity;
import org.springblade.contract.vo.ContractSealRequestVO;
import org.springblade.contract.vo.ContractSealResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 统一子公司（签章申请单位） 服务类
 *
 * @author xhb
 * @date : 2021-06-16 16:11:00
 */
public interface IContractSealService extends BaseService<ContractSealEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractSeal
	 * @return
	 */
	IPage<ContractSealEntity> pageList(IPage<ContractSealEntity> page, ContractSealRequestVO contractSeal);
	/**
	 *
	 * @author jitwxs
	 * @date 2021/6/18 11:57
	 * @param fdTaxno
	 * @return org.springblade.contract.vo.ContractSealRequestVO
	 */
	ContractSealEntity getByFdNo(String fdTaxno);

	/**
	 * 根据合同ID查询子公司集合
	 * @author jitwxs
	 * @date 2021/6/29 22:19
	 * @param contractId
	 * @return java.util.List<org.springblade.contract.vo.ContractSealResponseVO>
	 */

	List<ContractSealResponseVO> findBySealContractId(Long contractId);
}
