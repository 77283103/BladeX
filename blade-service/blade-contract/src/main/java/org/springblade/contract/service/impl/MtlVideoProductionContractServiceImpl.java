package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtlVideoProductionContractRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.MtlVideoProductionContractEntity;
import org.springblade.contract.mapper.MtlVideoProductionContractMapper;
import org.springblade.contract.service.IMtlVideoProductionContractService;
import org.springframework.stereotype.Service;

/**
 * 媒体类：视频制作合同 服务实现类
 *
 * @author 媒体类：视频制作合同
 * @date : 2020-12-10 19:31:03
 */
@Service
public class MtlVideoProductionContractServiceImpl extends BaseServiceImpl<MtlVideoProductionContractMapper, MtlVideoProductionContractEntity> implements IMtlVideoProductionContractService {

	@Override
	public IPage<MtlVideoProductionContractEntity> pageList(IPage<MtlVideoProductionContractEntity> page, MtlVideoProductionContractRequestVO mtlVideoProductionContract) {
		return baseMapper.pageList(page, mtlVideoProductionContract);
	}
}
