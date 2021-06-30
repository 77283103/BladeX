package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractWbht1Entity;
import org.springblade.contract.vo.ContractWbht1ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 消防-维保合同子表 服务类
 *
 * @author kx
 * @date : 2021-05-10 13:40:20
 */
public interface IContractWbht1Service extends BaseService<ContractWbht1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractWbht1
	 * @return
	 */
	IPage<ContractWbht1Entity> pageList(IPage<ContractWbht1Entity> page, ContractWbht1Entity contractWbht1);

	void saveBatchByRefId(Long refId, List<ContractWbht1ResponseVO> responseVOList);

	List<ContractWbht1ResponseVO> selectRefList(Long refId);
}
