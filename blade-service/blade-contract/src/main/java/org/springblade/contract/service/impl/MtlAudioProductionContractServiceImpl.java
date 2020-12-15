package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlAudioProductionContractEntity;
import org.springblade.contract.mapper.MtlAudioProductionContractMapper;
import org.springblade.contract.service.IMtlAudioProductionContractService;
import org.springblade.contract.vo.MtlAudioProductionContractRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 媒体类：音频制作合同 服务实现类
 *
 * @author 媒体类：音频制作合同
 * @date : 2020-12-10 19:21:38
 */
@Service
public class MtlAudioProductionContractServiceImpl extends BaseServiceImpl<MtlAudioProductionContractMapper, MtlAudioProductionContractEntity> implements IMtlAudioProductionContractService {

	@Override
	public IPage<MtlAudioProductionContractEntity> pageList(IPage<MtlAudioProductionContractEntity> page, MtlAudioProductionContractRequestVO mtlAudioProductionContract) {
		return baseMapper.pageList(page, mtlAudioProductionContract);
	}
}
