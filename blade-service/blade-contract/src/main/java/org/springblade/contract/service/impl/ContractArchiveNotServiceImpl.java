package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractTemplateEntity;
import org.springblade.contract.vo.ContractArchiveNotResponseVO;
import org.springblade.contract.vo.ContractTemplateResponseVO;
import org.springblade.contract.wrapper.ContractArchiveNotWrapper;
import org.springblade.contract.wrapper.ContractTemplateWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractArchiveNotEntity;
import org.springblade.contract.mapper.ContractArchiveNotMapper;
import org.springblade.contract.service.IContractArchiveNotService;
import org.springframework.stereotype.Service;
import org.springblade.contract.vo.ContractArchiveNotRequestVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 未归档原因 服务实现类
 *
 * @author 未归档原因
 * @date : 2020-11-09 15:19:18
 */
@Service
@AllArgsConstructor
public class ContractArchiveNotServiceImpl extends BaseServiceImpl<ContractArchiveNotMapper, ContractArchiveNotEntity> implements IContractArchiveNotService {

	private ContractArchiveNotMapper archiveNotMapper;
	@Override
	public IPage<ContractArchiveNotEntity> pageList(IPage<ContractArchiveNotEntity> page, ContractArchiveNotRequestVO contractArchiveNot) {
		return baseMapper.pageList(page, contractArchiveNot);
	}

	@Override
	public List<ContractArchiveNotResponseVO> getOldById(Long id) {
		List<ContractArchiveNotEntity> archiveNotEntity=archiveNotMapper.selectArchiveNotById(id);
		List<ContractArchiveNotResponseVO> archiveNotResponseVO= ContractArchiveNotWrapper.build().entityPVList(archiveNotEntity);
		return archiveNotResponseVO;
	}
}
