package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtbProductionContractRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.MtbProductionContractEntity;
import org.springblade.contract.mapper.MtbProductionContractMapper;
import org.springblade.contract.service.IMtbProductionContractService;
import org.springframework.stereotype.Service;

/**
 * 媒体类：平面广告拍摄制作合同 服务实现类
 *
 * @author 王策
 * @date : 2020-12-10 19:30:55
 */
@Service
public class MtbProductionContractServiceImpl extends BaseServiceImpl<MtbProductionContractMapper, MtbProductionContractEntity> implements IMtbProductionContractService {

	@Override
	public IPage<MtbProductionContractEntity> pageList(IPage<MtbProductionContractEntity> page, MtbProductionContractRequestVO mtbProductionContract) {
		return baseMapper.pageList(page, mtbProductionContract);
	}
}
