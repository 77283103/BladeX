package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtlShootingAndProductionContractRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.MtlShootingAndProductionContractEntity;
import org.springblade.contract.mapper.MtlShootingAndProductionContractMapper;
import org.springblade.contract.service.IMtlShootingAndProductionContractService;
import org.springframework.stereotype.Service;

/**
 * 媒体类：视频广告拍摄制作合同 服务实现类
 *
 * @author 媒体类：视频广告拍摄制作合同
 * @date : 2020-12-10 19:36:06
 */
@Service
public class MtlShootingAndProductionContractServiceImpl extends BaseServiceImpl<MtlShootingAndProductionContractMapper, MtlShootingAndProductionContractEntity> implements IMtlShootingAndProductionContractService {

	@Override
	public IPage<MtlShootingAndProductionContractEntity> pageList(IPage<MtlShootingAndProductionContractEntity> page, MtlShootingAndProductionContractRequestVO mtlShootingAndProductionContract) {
		return baseMapper.pageList(page, mtlShootingAndProductionContract);
	}
}
