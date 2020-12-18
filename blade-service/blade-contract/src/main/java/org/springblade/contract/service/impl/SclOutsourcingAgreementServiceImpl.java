package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclOutsourcingAgreementEntity;
import org.springblade.contract.mapper.SclOutsourcingAgreementMapper;
import org.springblade.contract.service.ISclOutsourcingAgreementService;
import org.springblade.contract.vo.SclOutsourcingAgreementRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 生产类：作业外包协议 服务实现类
 *
 * @author kx
 * @date : 2020-12-18 16:08:27
 */
@Service
public class SclOutsourcingAgreementServiceImpl extends BaseServiceImpl<SclOutsourcingAgreementMapper, SclOutsourcingAgreementEntity> implements ISclOutsourcingAgreementService {

	@Override
	public IPage<SclOutsourcingAgreementEntity> pageList(IPage<SclOutsourcingAgreementEntity> page, SclOutsourcingAgreementRequestVO sclOutsourcingAgreement) {
		return baseMapper.pageList(page, sclOutsourcingAgreement);
	}
}
