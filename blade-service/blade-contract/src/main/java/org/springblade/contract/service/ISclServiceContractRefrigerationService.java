package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclServiceContractRefrigerationEntity;
import org.springblade.contract.vo.SclServiceContractRefrigerationRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * kx 服务类
 *
 * @author kx
 * @date : 2021-03-15 13:48:18
 */
public interface ISclServiceContractRefrigerationService extends BaseService<SclServiceContractRefrigerationEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param SclServiceContractRefrigeration
	 * @return
	 */
	IPage<SclServiceContractRefrigerationEntity> pageList(IPage<SclServiceContractRefrigerationEntity> page, SclServiceContractRefrigerationRequestVO SclServiceContractRefrigeration);
}
