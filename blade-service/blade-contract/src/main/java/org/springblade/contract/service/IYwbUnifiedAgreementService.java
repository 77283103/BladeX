package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwbUnifiedAgreementEntity;
import org.springblade.contract.vo.YwbUnifiedAgreementRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 2021年统一e商城平台入驻服务协议（统一经销商） 服务类
 *
 * @author 2021年统一e商城平台入驻服务协议（统一经销商）
 * @date : 2020-12-18 16:03:31
 */
public interface IYwbUnifiedAgreementService extends BaseService<YwbUnifiedAgreementEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywbUnifiedAgreement
	 * @return
	 */
	IPage<YwbUnifiedAgreementEntity> pageList(IPage<YwbUnifiedAgreementEntity> page, YwbUnifiedAgreementRequestVO ywbUnifiedAgreement);
}
