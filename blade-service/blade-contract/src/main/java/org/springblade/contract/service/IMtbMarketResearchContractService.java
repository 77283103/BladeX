package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtbMarketResearchContractRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.MtbMarketResearchContractEntity;

/**
 * 媒体类：市调合同（定性+定量) 服务类
 *
 * @author 王策
 * @date : 2020-12-10 19:37:18
 */
public interface IMtbMarketResearchContractService extends BaseService<MtbMarketResearchContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtbMarketResearchContract
	 * @return
	 */
	IPage<MtbMarketResearchContractEntity> pageList(IPage<MtbMarketResearchContractEntity> page, MtbMarketResearchContractRequestVO mtbMarketResearchContract);
}
