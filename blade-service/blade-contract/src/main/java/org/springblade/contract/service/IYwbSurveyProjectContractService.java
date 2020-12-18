package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwbSurveyProjectContractEntity;
import org.springblade.contract.vo.YwbSurveyProjectContractRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 业务类：20.售点普查项目合同 服务类
 *
 * @author 业务类：20.售点普查项目合同
 * @date : 2020-12-18 16:06:58
 */
public interface IYwbSurveyProjectContractService extends BaseService<YwbSurveyProjectContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywbSurveyProjectContract
	 * @return
	 */
	IPage<YwbSurveyProjectContractEntity> pageList(IPage<YwbSurveyProjectContractEntity> page, YwbSurveyProjectContractRequestVO ywbSurveyProjectContract);
}
