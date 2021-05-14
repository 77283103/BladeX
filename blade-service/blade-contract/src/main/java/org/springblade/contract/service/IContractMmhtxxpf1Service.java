package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractMmhtxxpf1Entity;
import org.springblade.contract.vo.CglTheSalesContract1ResponseVO;
import org.springblade.contract.vo.ContractMmhtxxpf1ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 行销品买卖合同子表 服务类
 *
 * @author kx
 * @date : 2021-05-10 13:37:00
 */
public interface IContractMmhtxxpf1Service extends BaseService<ContractMmhtxxpf1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractMmhtxxpf1
	 * @return
	 */
	IPage<ContractMmhtxxpf1Entity> pageList(IPage<ContractMmhtxxpf1Entity> page, ContractMmhtxxpf1Entity contractMmhtxxpf1);

	void saveBatchByRefId(Long refId, List<ContractMmhtxxpf1ResponseVO> responseVOList);

	List<ContractMmhtxxpf1ResponseVO> selectRefList(Long refId);
}
