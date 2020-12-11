package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtbMarketResearchContractRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.MtbMarketResearchContractEntity;
import org.springblade.contract.mapper.MtbMarketResearchContractMapper;
import org.springblade.contract.service.IMtbMarketResearchContractService;
import org.springframework.stereotype.Service;

/**
 * 媒体类：市调合同（定性+定量) 服务实现类
 *
 * @author 王策
 * @date : 2020-12-10 19:37:19
 */
@Service
public class MtbMarketResearchContractServiceImpl extends BaseServiceImpl<MtbMarketResearchContractMapper, MtbMarketResearchContractEntity> implements IMtbMarketResearchContractService {

	@Override
	public IPage<MtbMarketResearchContractEntity> pageList(IPage<MtbMarketResearchContractEntity> page, MtbMarketResearchContractRequestVO mtbMarketResearchContract) {
		return baseMapper.pageList(page, mtbMarketResearchContract);
	}
}
