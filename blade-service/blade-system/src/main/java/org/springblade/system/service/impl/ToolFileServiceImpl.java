package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.entity.ToolFileEntity;
import org.springblade.system.mapper.ToolFileMapper;
import org.springblade.system.service.IToolFileService;
import org.springblade.system.vo.ToolFileResponseVO;
import org.springblade.system.wrapper.ToolFileWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springblade.system.vo.ToolFileRequestVO;

import java.util.List;

/**
 * 工具 服务实现类
 *
 * @author xhb
 * @date : 2021-04-22 10:09:30
 */
@Service
public class ToolFileServiceImpl extends BaseServiceImpl<ToolFileMapper, ToolFileEntity> implements IToolFileService {
	@Autowired
	private IFileClient fileClient;
	@Override
	public IPage<ToolFileEntity> pageList(IPage<ToolFileEntity> page, ToolFileRequestVO toolFile) {
		return baseMapper.pageList(page, toolFile);
	}

	@Override
	public ToolFileResponseVO getById(Long id) {
		ToolFileEntity fileEntity=baseMapper.selectById(id);
		ToolFileResponseVO fileResponseVO= ToolFileWrapper.build().entityPV(fileEntity);
		//查询合同附件
		if (Func.isNoneBlank(fileEntity.getToolFileId())) {
			R<List<FileVO>> result = fileClient.getByIds(fileEntity.getToolFileId());
			if (result.isSuccess()) {
				fileResponseVO.setToolFilesList(result.getData());
			}
		}
		return fileResponseVO;
	}
}
