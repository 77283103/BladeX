package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.contract.entity.ContractAssessmentEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;

/**
 * 合同评估表 Mapper 接口
 *
 * @author liyj
 * @date : 2020-09-24 10:41:34
 */
public interface ContractAssessmentMapper extends BaseMapper<ContractAssessmentEntity> {

	/**
	 * 评估信息分页查询
	 *
	 * @param page
	 * @param assessment
	 * @return
	 */
	IPage<ContractAssessmentEntity> pageList(IPage<ContractAssessmentEntity> page, ContractAssessmentEntity assessment);

}
