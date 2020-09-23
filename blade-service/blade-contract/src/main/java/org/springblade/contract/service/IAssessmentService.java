package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.AssessmentEntity;
import org.springblade.core.mp.base.BaseService;

/**
 * 合同评估表 服务类
 *
 * @author liyj
 * @date : 2020-09-23 15:50:00
 */
public interface IAssessmentService extends BaseService<AssessmentEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param assessment
	 * @return
	 */
	IPage<AssessmentEntity> pageList(IPage<AssessmentEntity> page, AssessmentEntity assessment);
}
