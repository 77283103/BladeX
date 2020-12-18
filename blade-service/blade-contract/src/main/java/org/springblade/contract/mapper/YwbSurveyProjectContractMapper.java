package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwbSurveyProjectContractEntity;
import org.springblade.contract.vo.YwbSurveyProjectContractRequestVO;

/**
 * 业务类：20.售点普查项目合同 Mapper 接口
 *
 * @author 业务类：20.售点普查项目合同
 * @date : 2020-12-18 16:06:57
 */
public interface YwbSurveyProjectContractMapper extends BaseMapper<YwbSurveyProjectContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywbSurveyProjectContract
	 * @return
	 */
	IPage<YwbSurveyProjectContractEntity> pageList(IPage<YwbSurveyProjectContractEntity> page, YwbSurveyProjectContractRequestVO ywbSurveyProjectContract);

}
