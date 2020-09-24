package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractAssessmentEntity;

/**
 * 合同评估表 Mapper 接口
 *
 * @author liyj
 * @date : 2020-09-23 23:28:31
 */
public interface ContractAssessmentMapper extends BaseMapper<ContractAssessmentEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param assessment
	 * @return
	 */
	IPage<ContractAssessmentEntity> pageList(IPage<ContractAssessmentEntity> page, ContractAssessmentEntity assessment);

}
