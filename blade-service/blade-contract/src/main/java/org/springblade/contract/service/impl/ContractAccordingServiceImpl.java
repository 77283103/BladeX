package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.mapper.ContractAccordingMapper;
import org.springblade.contract.service.IContractAccordingService;
import org.springblade.contract.vo.ContractAccordingRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合同依据管理 服务实现类
 *
 * @author XHB
 * @date : 2020-09-24 14:20:32
 */
@AllArgsConstructor
@Service
public class ContractAccordingServiceImpl extends BaseServiceImpl<ContractAccordingMapper, ContractAccordingEntity> implements IContractAccordingService {


	@Override
	public IPage<ContractAccordingEntity> pageList(IPage<ContractAccordingEntity> page, ContractAccordingRequestVO according) {
		page= baseMapper.pageList(page, according);
		return page;
	}

	@Override
	public void saveAccording(List<Long> ids, Long id) {
	}


	@Override
	public void deleteByContractId(Long id) {
		//baseMapper.selectByContractId(id);
		baseMapper.deleteAccording(id);
	}
}
