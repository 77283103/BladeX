package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclServiceContractRefrigerationEntity;
import org.springblade.contract.mapper.SclServiceContractRefrigerationMapper;
import org.springblade.contract.service.ISclServiceContractRefrigerationService;
import org.springblade.contract.vo.SclServiceContractRefrigerationRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * kx 服务实现类
 *
 * @author kx
 * @date : 2021-03-15 13:48:20
 */
@Service
public class SclServiceContractRefrigerationServiceImpl extends BaseServiceImpl<SclServiceContractRefrigerationMapper, SclServiceContractRefrigerationEntity> implements ISclServiceContractRefrigerationService {

	@Override
	public IPage<SclServiceContractRefrigerationEntity> pageList(IPage<SclServiceContractRefrigerationEntity> page, SclServiceContractRefrigerationRequestVO SclServiceContractRefrigeration) {
		return baseMapper.pageList(page, SclServiceContractRefrigeration);
	}
}
