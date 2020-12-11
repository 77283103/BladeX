package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtlAdaptationContractRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.MtlAdaptationContractEntity;
import org.springblade.contract.mapper.MtlAdaptationContractMapper;
import org.springblade.contract.service.IMtlAdaptationContractService;
import org.springframework.stereotype.Service;

/**
 * 媒体类：视频广告改编合同 服务实现类
 *
 * @author  媒体类：视频广告改编合同
 * @date : 2020-12-10 19:40:35
 */
@Service
public class MtlAdaptationContractServiceImpl extends BaseServiceImpl<MtlAdaptationContractMapper, MtlAdaptationContractEntity> implements IMtlAdaptationContractService {

	@Override
	public IPage<MtlAdaptationContractEntity> pageList(IPage<MtlAdaptationContractEntity> page, MtlAdaptationContractRequestVO mtlAdaptationContract) {
		return baseMapper.pageList(page, mtlAdaptationContract);
	}
}
