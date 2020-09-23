package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.AssessmentEntity;
import org.springblade.contract.mapper.AssessmentMapper;
import org.springblade.contract.service.IAssessmentService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 合同评估表 服务实现类
 *
 * @author liyj
 * @date : 2020-09-23 15:50:00
 */
@Service
public class AssessmentServiceImpl extends BaseServiceImpl<AssessmentMapper, AssessmentEntity> implements IAssessmentService {

	@Override
	public IPage<AssessmentEntity> pageList(IPage<AssessmentEntity> page, AssessmentEntity assessment) {
		return baseMapper.pageList(page, assessment);
	}
}
