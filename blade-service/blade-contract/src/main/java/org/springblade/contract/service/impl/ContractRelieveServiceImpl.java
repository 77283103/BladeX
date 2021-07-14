package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.abutment.entity.Attachment;
import org.springblade.abutment.entity.DocCreatorEntity;
import org.springblade.abutment.entity.FormValuesEntity;
import org.springblade.abutment.entity.PushEkpEntity;
import org.springblade.abutment.enums.EkpTemplateEnum;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.contract.enums.ContractStatusEnum;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractRelieveEntity;
import org.springblade.contract.mapper.ContractRelieveMapper;
import org.springblade.contract.service.IContractRelieveService;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.user.entity.UserInfo;
import org.springblade.system.user.feign.IUserClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springblade.contract.vo.ContractRelieveRequestVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 合同解除 服务实现类
 *
 * @author 合同解除
 * @date : 2020-11-05 09:24:01
 */
@Service
public class ContractRelieveServiceImpl extends BaseServiceImpl<ContractRelieveMapper, ContractRelieveEntity> implements IContractRelieveService {

	@Autowired
	private IContractFormInfoService formInfoService;
	@Autowired
	private IAbutmentClient abutmentClient;
	@Autowired
	private IUserClient userClient;

	@Override
	public IPage<ContractRelieveEntity> pageList(IPage<ContractRelieveEntity> page, ContractRelieveRequestVO contractRelieve) {
		return baseMapper.pageList(page, contractRelieve);
	}

	@Override
	public R submit(Long contractId) {
		//获取合同信息
		ContractFormInfoResponseVO contractFormInfoResponseVO = formInfoService.getById(contractId);
		if(null == contractFormInfoResponseVO){
			return R.fail("合同标识无效:"+contractId);
		}
		//修改合同状态
		formInfoService.updateExportStatus(ContractStatusEnum.RELIEVE_SUBMIT.getKey().toString(),contractId);

		//根据合同id获取解除信息
		ContractRelieveEntity contractRelieveEntity = baseMapper.selectRelieveById(contractId);
		if(null == contractRelieveEntity){
			return R.fail("合同未包含解除信息:"+contractId);
		}

		//组装送审数据
		PushEkpEntity pushEkpEntity = new PushEkpEntity();
		pushEkpEntity.setDocSubject(contractFormInfoResponseVO.getContractName()+"解除申请");
		UserInfo userInfo = userClient.userInfo("000000",AuthUtil.getUser().getAccount()).getData();

		pushEkpEntity.setDocCreator(new DocCreatorEntity(userInfo.getUser().getCode()));
		pushEkpEntity.setFdTemplateId(EkpTemplateEnum.RELIEVE_TEMPLATE_ID.getKey());
		//提交解除数据中附件至ekp，将附件推送返回结果组装进送审数据
		R<List<Attachment>>r = abutmentClient.sendFileToFastDfs(contractRelieveEntity.getTermAgreement());
		if(r.isSuccess()){
			pushEkpEntity.setFd_attachment(Func.isEmpty(r.getData())?null:r.getData());
		}
		//组装解除信息至送审数据主体
		FormValuesEntity formValuesEntity = new FormValuesEntity();
		formValuesEntity.setFd_amount(contractFormInfoResponseVO.getContractTaxAmount().toString());
		formValuesEntity.setFd_relieve_date(contractRelieveEntity.getCreateTime().toString());
		formValuesEntity.setFd_relieve_reason(contractRelieveEntity.getRelieveCategory());
		formValuesEntity.setFd_liquidated(contractRelieveEntity.getBreachAmount().toString());
		formValuesEntity.setFd_flow_direction(contractRelieveEntity.getCompensationCategory());
		formValuesEntity.setFd_remarks(contractRelieveEntity.getRelieveRemark());
		//完成组装，推送数据至ekp送审
		pushEkpEntity.setFormValues(formValuesEntity);
		return abutmentClient.sendEkp(pushEkpEntity);
	}


}
