package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwbSurveyProjectContractEntity;
import org.springblade.contract.mapper.YwbSurveyProjectContractMapper;
import org.springblade.contract.service.IYwbSurveyProjectContractService;
import org.springblade.contract.vo.YwbSurveyProjectContractRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 业务类：20.售点普查项目合同 服务实现类
 *
 * @author 业务类：20.售点普查项目合同
 * @date : 2020-12-18 16:06:59
 */
@Service
public class YwbSurveyProjectContractServiceImpl extends BaseServiceImpl<YwbSurveyProjectContractMapper, YwbSurveyProjectContractEntity> implements IYwbSurveyProjectContractService {

	@Override
	public IPage<YwbSurveyProjectContractEntity> pageList(IPage<YwbSurveyProjectContractEntity> page, YwbSurveyProjectContractRequestVO ywbSurveyProjectContract) {
		return baseMapper.pageList(page, ywbSurveyProjectContract);
	}
}
