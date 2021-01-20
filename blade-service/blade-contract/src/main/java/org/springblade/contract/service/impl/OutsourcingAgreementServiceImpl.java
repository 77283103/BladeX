package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.OutsourcingAgreementEntity;
import org.springblade.contract.mapper.OutsourcingAgreementMapper;
import org.springblade.contract.service.IOutsourcingAgreementService;
import org.springframework.stereotype.Service;

/**
 * 作 业 外 包 协 议 服务实现类
 *
 * @author 王策
 * @date : 2021-01-20 13:42:17
 */
@Service
public class OutsourcingAgreementServiceImpl extends BaseServiceImpl<OutsourcingAgreementMapper, OutsourcingAgreementEntity> implements IOutsourcingAgreementService {

	@Override
	public IPage<OutsourcingAgreementEntity> pageList(IPage<OutsourcingAgreementEntity> page, OutsourcingAgreementEntity outsourcingAgreement) {
		return baseMapper.pageList(page, outsourcingAgreement);
	}
}
