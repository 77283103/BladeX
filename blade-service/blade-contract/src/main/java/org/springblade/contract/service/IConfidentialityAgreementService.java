package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ConfidentialityAgreementEntity;

/**
 * 梁艳-保密协议（三方） 服务类
 *
 * @author 王策
 * @date : 2021-01-15 15:36:28
 */
public interface IConfidentialityAgreementService extends BaseService<ConfidentialityAgreementEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param confidentialityAgreement
	 * @return
	 */
	IPage<ConfidentialityAgreementEntity> pageList(IPage<ConfidentialityAgreementEntity> page, ConfidentialityAgreementEntity confidentialityAgreement);
}
