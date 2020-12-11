package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtbMarketResearchContractEntity;
import org.springblade.contract.vo.MtbMarketResearchContractRequestVO;

/**
 * 媒体类：市调合同（定性+定量) Mapper 接口
 *
 * @author 王策
 * @date : 2020-12-10 19:37:16
 */
public interface MtbMarketResearchContractMapper extends BaseMapper<MtbMarketResearchContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtbMarketResearchContract
	 * @return
	 */
	IPage<MtbMarketResearchContractEntity> pageList(IPage<MtbMarketResearchContractEntity> page, MtbMarketResearchContractRequestVO mtbMarketResearchContract);

}
