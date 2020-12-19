package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ShjbOnlinePaymentOffsetEntity;
import org.springblade.contract.vo.ShjbOnlinePaymentOffsetRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 售货机类：0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014） 服务类
 *
 * @author 售货机类：0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014）
 * @date : 2020-12-18 16:04:44
 */
public interface IShjbOnlinePaymentOffsetService extends BaseService<ShjbOnlinePaymentOffsetEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param shjbOnlinePaymentOffset
	 * @return
	 */
	IPage<ShjbOnlinePaymentOffsetEntity> pageList(IPage<ShjbOnlinePaymentOffsetEntity> page, ShjbOnlinePaymentOffsetRequestVO shjbOnlinePaymentOffset);
}
