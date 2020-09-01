package org.springblade.flow.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.flow.business.mapper.ProcessMapper;
import org.springblade.flow.business.service.IProcessService;
import org.springblade.flow.core.entity.ProcessEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程定义信息表 服务实现类
 *
 * @author tianah
 * @date 2020-8-27
 */
@Service
public class ProcessServiceImpl extends BaseServiceImpl<ProcessMapper, ProcessEntity> implements IProcessService {

	@Override
	public IPage<ProcessEntity> pageList(IPage<ProcessEntity> page, ProcessEntity process) {
		return baseMapper.pageList(page, process);
	}

	@Override
	public List<ProcessEntity> getProcessByBusinessType(String businessType) {
		return baseMapper.selectList(Wrappers.<ProcessEntity>query().lambda().eq(ProcessEntity::getBusinessType, businessType));
	}
}
