package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ShjbOnlinePaymentOffsetEntity;
import org.springblade.contract.mapper.ShjbOnlinePaymentOffsetMapper;
import org.springblade.contract.service.IShjbOnlinePaymentOffsetService;
import org.springblade.contract.vo.ShjbOnlinePaymentOffsetRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 售货机类：0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014） 服务实现类
 *
 * @author 售货机类：0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014）
 * @date : 2020-12-18 16:04:45
 */
@Service
public class ShjbOnlinePaymentOffsetServiceImpl extends BaseServiceImpl<ShjbOnlinePaymentOffsetMapper, ShjbOnlinePaymentOffsetEntity> implements IShjbOnlinePaymentOffsetService {

	@Override
	public IPage<ShjbOnlinePaymentOffsetEntity> pageList(IPage<ShjbOnlinePaymentOffsetEntity> page, ShjbOnlinePaymentOffsetRequestVO shjbOnlinePaymentOffset) {
		return baseMapper.pageList(page, shjbOnlinePaymentOffset);
	}
}
