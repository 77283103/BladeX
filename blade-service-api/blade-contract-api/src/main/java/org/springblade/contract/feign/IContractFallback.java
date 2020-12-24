package org.springblade.contract.feign;

import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.vo.ContractTemplateResponseVO;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.TemplateEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xhbbo
 */
@Component
public class IContractFallback implements IContractClient {
	@Override
	public R<ContractFormInfoResponseVO> getById(Long id) {
		return R.fail("获取合同信息失败！");
	}

	@Override
	public R<ContractTemplateResponseVO> templateUpdate(TemplateEntity entity) {
		return R.fail("更新模板json信息失败！");
	}
}
