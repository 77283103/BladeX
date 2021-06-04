package org.springblade.contract.feign;

import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.ContractTemplateEntity;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.vo.ContractTemplateResponseVO;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.TemplateEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xhbbo
 */
@Component
public class IContractFallback implements IContractClient {
	@Override
	public R<Boolean> saveBatch(List<ContractCounterpartEntity> listInsert) {
		return R.fail("保存相对方增量信息失败！");
	}

	@Override
	public R<Boolean> updateById(ContractCounterpartEntity updateCounterpart) {
		return R.fail("保存相对方修改信息失败！");
	}

	@Override
	public R<List<ContractCounterpartEntity>> selectByName(String unifiedSocialCreditCode) {
		return R.fail("获取合同信息失败！");
	}

	@Override
	public R<ContractFormInfoResponseVO> getById(Long id) {
		return R.fail("获取合同信息失败！");
	}

	@Override
	public R<List<ContractFormInfoEntity>> getByStatus(String status) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<List<ContractFormInfoEntity>> getChooseList() {
		return R.fail("获取数据失败");
	}

	@Override
	public R<ContractTemplateResponseVO> templateUpdate(TemplateEntity entity) {
		return R.fail("更新模板json信息失败！");
	}

	@Override
	public R saveContractFormInfo(Long id, String status) {
		return R.fail("更新模板json信息失败！");
	}

	@Override
	public R<ContractTemplateEntity> getByTemplateId(Long id) {
		return R.fail("更新模板json信息失败！");
	}

}
