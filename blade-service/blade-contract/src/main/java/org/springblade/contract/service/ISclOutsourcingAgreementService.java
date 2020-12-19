package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclOutsourcingAgreementEntity;
import org.springblade.contract.vo.SclOutsourcingAgreementRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 生产类：作业外包协议 服务类
 *
 * @author kx
 * @date : 2020-12-18 16:08:26
 */
public interface ISclOutsourcingAgreementService extends BaseService<SclOutsourcingAgreementEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclOutsourcingAgreement
	 * @return
	 */
	IPage<SclOutsourcingAgreementEntity> pageList(IPage<SclOutsourcingAgreementEntity> page, SclOutsourcingAgreementRequestVO sclOutsourcingAgreement);
}
