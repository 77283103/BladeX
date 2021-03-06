package org.springblade.abutment.feign;

import org.springblade.abutment.entity.*;
import org.springblade.abutment.vo.*;
import org.springblade.contract.dto.middleground.Contract;
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
	public R<EkpVo> sendEkpBatchPost(ContractFormInfoEntity entity) {
		return R.fail("请求超时，批量送审异常报错，请联系合同平台管理员处理");
	}

	@Override
	public R<EkpVo> sendEkpFormPost(ContractFormInfoEntity entity) {
		return R.fail("范本起草送审中出错，请联系管理员处理");
	}

	@Override
	public R<EkpVo> sendEkpMultiPost(ContractFormInfoEntity entity) {
		return R.fail("多方起草送审方法内出错，请联系管理员处理！");
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
	public R<AsDictVo> querySigatureInfo(AsDict entity) {
		return R.data(404,null,"接口未连接成功");
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
		return R.fail("暂无承载数据");
	}

	@Override
	public R<List<OrganizationVo>> getOrganizationInfoIncrement(OrgParme param) {
		return R.fail("获取数据失败");
	}

	@Override
	public R synEkpUserDepart(EkpSyncRequestVO ekpSyncRequestVO) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<CounterpartVo> getCounterpart(CounterpartEntity entity) {
		return R.fail("获取数据失败");
	}

	@Override
	public R sendEkp(PushEkpEntity pushEkpEntity) {
		return R.fail("获取数据失败");
	}

	@Override
	public R sendFileToFastDfs(String fileIds) {
		return R.fail("获取数据失败");
	}

	@Override
	public R transferStationPushContract(Contract contract) {
		return R.fail("获取数据失败");
	}
}
