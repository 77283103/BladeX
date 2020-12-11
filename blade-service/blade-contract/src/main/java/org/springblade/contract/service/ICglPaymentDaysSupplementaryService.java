package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglPaymentDaysSupplementaryRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.CglPaymentDaysSupplementaryEntity;

/**
 * 采购类：账期补充协议--买卖合同 服务类
 *
 * @author 王策
 * @date : 2020-12-10 19:21:46
 */
public interface ICglPaymentDaysSupplementaryService extends BaseService<CglPaymentDaysSupplementaryEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglPaymentDaysSupplementary
	 * @return
	 */
	IPage<CglPaymentDaysSupplementaryEntity> pageList(IPage<CglPaymentDaysSupplementaryEntity> page, CglPaymentDaysSupplementaryRequestVO cglPaymentDaysSupplementary);
}
