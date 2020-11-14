package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractArchiveEntity;
import org.springblade.contract.service.IContractAccordingService;
import org.springblade.contract.vo.ContractAccordingResponseVO;
import org.springblade.contract.wrapper.ContractAccordingWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.mapper.ContractAccordingMapper;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.feign.IUserClient;
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

	private IFileClient fileClient;

	private ISysClient sysClient;

	private IUserClient userClient;
	@Override
	public IPage<ContractAccordingEntity> pageList(IPage<ContractAccordingEntity> page, ContractAccordingEntity according) {
		return baseMapper.pageList(page, according);
	}

	@Override
	public void saveAccording(List<Long> ids, Long id) {

	}

}
