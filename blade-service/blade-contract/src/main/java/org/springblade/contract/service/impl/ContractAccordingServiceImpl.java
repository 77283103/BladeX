package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springblade.contract.vo.ContractAccordingResponseVO;
import org.springblade.contract.wrapper.ContractAccordingWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.mapper.ContractAccordingMapper;
import org.springblade.contract.service.IContractAccordingService;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  服务实现类
 *
 * @author feng
 */
@Service
@AllArgsConstructor
public class ContractAccordingServiceImpl extends BaseServiceImpl<ContractAccordingMapper, ContractAccordingEntity> implements IContractAccordingService {

	private IFileClient fileClient;

	@Override
	public IPage<ContractAccordingEntity> pageList(IPage<ContractAccordingEntity> page, ContractAccordingEntity according) {
		return baseMapper.pageList(page, according);
	}

	@Override
	public ContractAccordingEntity selectById(Long id) {
		ContractAccordingEntity entity = super.getById(id);
		ContractAccordingResponseVO contractAccordingResponseVO = ContractAccordingWrapper.build().entityVO(entity);
		if (Func.isNoneBlank(entity.getAccordingFiles())){
			/* 查询附件 */
			R<List<FileVO>> result = fileClient.getByIds(entity.getAccordingFiles());
			if (result.isSuccess()){
				contractAccordingResponseVO.setFileVOList(result.getData());
			}
		}
		return contractAccordingResponseVO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean del(String ids) {
		/* 查询所有数据，获取待删除的所有文件的id */
		List<ContractAccordingEntity> entityList = baseMapper.selectBatchIds(Func.toLongList(ids));
		List<Long> fileIds = Lists.newArrayList();
		entityList.forEach(entity -> fileIds.addAll(Func.toLongList(entity.getAccordingFiles())));
		/* 删除文件 */
		R result = fileClient.remove(fileIds);
		/* 删除合同依据 */
		super.removeByIds(Func.toLongList(ids));

		return result.isSuccess();
	}
}
