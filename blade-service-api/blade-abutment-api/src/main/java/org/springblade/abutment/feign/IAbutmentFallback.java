package org.springblade.abutment.feign;

import org.springblade.abutment.entity.*;
import org.springblade.abutment.vo.*;
import org.springblade.contract.entity.ContractBorrowApplicationEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.ContractTemplateEntity;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.tool.api.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xhbbo
 */
@Component
public class IAbutmentFallback implements IAbutmentClient {
	@Override
	public R<EkpVo> temEkpFormPost(ContractTemplateEntity entity) {
		return R.fail("请求超时，获取数据失败");
	}

	@Override
	public R<EkpVo> borEkpFormPost(ContractBorrowApplicationEntity entity) {
		return R.fail("请求超时，获取数据失败");
	}

	@Override
	public R<EkpVo> sendEkpFormPost(ContractFormInfoEntity entity) {
		return R.fail("请求超时，获取数据失败");
	}

	@Override
	public R<EkpVo> sendEkpMultiPost(ContractFormInfoEntity entity) {
		return R.fail("请求超时，获取数据失败");
	}

	@Override
	public R<EkpVo> nodeEkpFormPost(ContractFormInfoResponseVO entity) {
		return R.fail("请求超时，获取数据失败");
	}

	@Override
	public R<List<EkpVo>> pushNotSig(ContractFormInfoEntity entity) {
		return R.fail("请求超时，获取数据失败");
	}

	@Override
	public R<List<DocVo>> queryDocInfo(DocEntity entity) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<List<UploadFileVo>> uploadFiles(UploadFileEntity entity) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<SingleSignVo> singleSign(SingleSignEntity entity) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<SingleSignVo> singleSignPost(SingleSignEntity entity) {
		return R.fail("获取数据失败");
	}

	@Override
	public R sendSms(SendSmsEntity entity) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<MultiSignVo> multiSign(MultiSignEntity entity) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<String> readSigned(ReadSignedEntity entity) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<CompanyInfoVo> queryCompanyInfo(CompanyInfoEntity entity) {
		return R.fail("获取数据失败");	}

	@Override
	public R<String> token() {
		return R.fail("获取数据失败");	}

	@Override
	public R<String> tokenEkp() {
		return R.fail("获取数据失败");
	}

	@Override
	public R<EkpVo> pushData(PushEkpEntity entity) throws Exception {
		return R.fail("获取数据失败");
	}

	@Override
	public R<List<OrganizationVo>> getOrganizationInfoIncrement() {
		return R.fail("获取数据失败");
	}

	@Override
	public R<CounterpartVo> getCounterpart(CounterpartEntity entity) {
		return R.fail("获取数据失败");
	}
}
