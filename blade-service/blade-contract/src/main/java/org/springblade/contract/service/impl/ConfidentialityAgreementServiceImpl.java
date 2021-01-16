package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ConfidentialityAgreementEntity;
import org.springblade.contract.mapper.ConfidentialityAgreementMapper;
import org.springblade.contract.service.IConfidentialityAgreementService;
import org.springframework.stereotype.Service;

/**
 * 梁艳-保密协议（三方） 服务实现类
 *
 * @author 王策
 * @date : 2021-01-15 15:36:28
 */
@Service
public class ConfidentialityAgreementServiceImpl extends BaseServiceImpl<ConfidentialityAgreementMapper, ConfidentialityAgreementEntity> implements IConfidentialityAgreementService {

	@Override
	public IPage<ConfidentialityAgreementEntity> pageList(IPage<ConfidentialityAgreementEntity> page, ConfidentialityAgreementEntity confidentialityAgreement) {
		return baseMapper.pageList(page, confidentialityAgreement);
	}
}
