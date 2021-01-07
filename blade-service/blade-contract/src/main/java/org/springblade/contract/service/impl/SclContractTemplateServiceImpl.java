package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclContractTemplateEntity;
import org.springblade.contract.mapper.SclContractTemplateMapper;
import org.springblade.contract.service.ISclContractTemplateService;
import org.springblade.contract.vo.SclContractTemplateRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 生产类：下脚品买卖合同模版 服务实现类
 *
 * @author 张文武
 * @date : 2021-01-04 15:17:31
 */
@Service
public class SclContractTemplateServiceImpl extends BaseServiceImpl<SclContractTemplateMapper, SclContractTemplateEntity> implements ISclContractTemplateService {

	@Override
	public IPage<SclContractTemplateEntity> pageList(IPage<SclContractTemplateEntity> page, SclContractTemplateRequestVO sclContractTemplate) {
		return baseMapper.pageList(page, sclContractTemplate);
	}
}
