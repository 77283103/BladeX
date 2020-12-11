package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglPaymentDaysSupplementaryRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.CglPaymentDaysSupplementaryEntity;
import org.springblade.contract.mapper.CglPaymentDaysSupplementaryMapper;
import org.springblade.contract.service.ICglPaymentDaysSupplementaryService;
import org.springframework.stereotype.Service;

/**
 * 采购类：账期补充协议--买卖合同 服务实现类
 *
 * @author 王策
 * @date : 2020-12-10 19:21:46
 */
@Service
public class CglPaymentDaysSupplementaryServiceImpl extends BaseServiceImpl<CglPaymentDaysSupplementaryMapper, CglPaymentDaysSupplementaryEntity> implements ICglPaymentDaysSupplementaryService {

	@Override
	public IPage<CglPaymentDaysSupplementaryEntity> pageList(IPage<CglPaymentDaysSupplementaryEntity> page, CglPaymentDaysSupplementaryRequestVO cglPaymentDaysSupplementary) {
		return baseMapper.pageList(page, cglPaymentDaysSupplementary);
	}
}
