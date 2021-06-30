package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractSealEntity;
import org.springblade.contract.mapper.ContractSealMapper;
import org.springblade.contract.service.IContractSealService;
import org.springblade.contract.vo.ContractSealRequestVO;
import org.springblade.contract.vo.ContractSealResponseVO;
import org.springblade.contract.wrapper.ContractSealWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 统一子公司（签章申请单位） 服务实现类
 *
 * @author xhb
 * @date : 2021-06-16 16:11:01
 */
@Service
public class ContractSealServiceImpl extends BaseServiceImpl<ContractSealMapper, ContractSealEntity> implements IContractSealService {

	@Override
	public IPage<ContractSealEntity> pageList(IPage<ContractSealEntity> page, ContractSealRequestVO contractSeal) {
		return baseMapper.pageList(page, contractSeal);
	}

	@Override
	public ContractSealEntity getByFdNo(String fdTaxno) {
		return baseMapper.selectAllByFdTaxnoAfter(fdTaxno);
	}

	@Override
	public List<ContractSealResponseVO> findBySealContractId(Long contractId) {
		return ContractSealWrapper.build().entityPVList(baseMapper.selectByIds(contractId));
	}


}
