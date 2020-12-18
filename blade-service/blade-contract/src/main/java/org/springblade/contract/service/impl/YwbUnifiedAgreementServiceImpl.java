package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwbUnifiedAgreementEntity;
import org.springblade.contract.mapper.YwbUnifiedAgreementMapper;
import org.springblade.contract.service.IYwbUnifiedAgreementService;
import org.springblade.contract.vo.YwbUnifiedAgreementRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 2021年统一e商城平台入驻服务协议（统一经销商） 服务实现类
 *
 * @author 2021年统一e商城平台入驻服务协议（统一经销商）
 * @date : 2020-12-18 16:03:31
 */
@Service
public class YwbUnifiedAgreementServiceImpl extends BaseServiceImpl<YwbUnifiedAgreementMapper, YwbUnifiedAgreementEntity> implements IYwbUnifiedAgreementService {

	@Override
	public IPage<YwbUnifiedAgreementEntity> pageList(IPage<YwbUnifiedAgreementEntity> page, YwbUnifiedAgreementRequestVO ywbUnifiedAgreement) {
		return baseMapper.pageList(page, ywbUnifiedAgreement);
	}
}
