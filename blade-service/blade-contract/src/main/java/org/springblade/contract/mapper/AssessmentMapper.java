package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.AssessmentEntity;

/**
 * 合同评估表 Mapper 接口
 *
 * @author liyj
 * @date : 2020-09-23 15:50:00
 */
public interface AssessmentMapper extends BaseMapper<AssessmentEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param assessment
	 * @return
	 */
	IPage<AssessmentEntity> pageList(IPage<AssessmentEntity> page, AssessmentEntity assessment);

}
