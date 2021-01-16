package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.LyConfidentialityAgreementEntity;
import org.springblade.contract.mapper.LyConfidentialityAgreementMapper;
import org.springblade.contract.service.ILyConfidentialityAgreementService;
import org.springframework.stereotype.Service;

/**
 * 梁艳-保密协议 服务实现类
 *
 * @author wd
 * @date : 2021-01-15 14:57:39
 */
@Service
public class LyConfidentialityAgreementServiceImpl extends BaseServiceImpl<LyConfidentialityAgreementMapper, LyConfidentialityAgreementEntity> implements ILyConfidentialityAgreementService {

	@Override
	public IPage<LyConfidentialityAgreementEntity> pageList(IPage<LyConfidentialityAgreementEntity> page, LyConfidentialityAgreementEntity lyConfidentialityAgreement) {
		return baseMapper.pageList(page, lyConfidentialityAgreement);
	}
}
