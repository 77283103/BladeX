package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.OutsourcingAgreementEntity;

/**
 * 作 业 外 包 协 议 服务类
 *
 * @author 王策
 * @date : 2021-01-20 13:42:16
 */
public interface IOutsourcingAgreementService extends BaseService<OutsourcingAgreementEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param outsourcingAgreement
	 * @return
	 */
	IPage<OutsourcingAgreementEntity> pageList(IPage<OutsourcingAgreementEntity> page, OutsourcingAgreementEntity outsourcingAgreement);
}
