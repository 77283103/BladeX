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

	/**
	 * 重写依据vo方法返回附件 包装返回视图层
	 * @param id
	 * @return
	 */
	@Override
	public ContractAccordingResponseVO getById(Long id) {
		ContractAccordingEntity accordingEntity=baseMapper.selectById(id);
		ContractAccordingResponseVO accordingResponseVO= ContractAccordingWrapper.build().entityVO(accordingEntity);
		//查询依据附件
		//@Func.isNoneBlank判断是否全为非空字符串
		if (Func.isNoneBlank(accordingResponseVO.getAccordingFiles())){
			R<List<FileVO>> result = fileClient.getByIds(accordingResponseVO.getAccordingFiles());
			if (result.isSuccess()){
				accordingResponseVO.setAccordingFilesVOList(result.getData());
			}
		}
		return accordingResponseVO;
	}
}
