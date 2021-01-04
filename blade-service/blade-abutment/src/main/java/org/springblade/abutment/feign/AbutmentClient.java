package org.springblade.abutment.feign;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.entity.*;
import org.springblade.abutment.service.IDocService;
import org.springblade.abutment.service.IESealService;
import org.springblade.abutment.service.IEkpService;
import org.springblade.abutment.vo.*;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.ContractPerformanceColPayEntity;
import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.entity.DictBiz;
import org.springblade.system.feign.IDictBizClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 对接Feign实现类
 *
 * @author cc
 */
@ApiIgnore
@Slf4j
@RestController
public class AbutmentClient implements IAbutmentClient {

	@Autowired
	private IEkpService ekpService;
	@Autowired
	private IDocService docService;
	@Autowired
	private IESealService eSealService;
	@Autowired
	private IDictBizClient bizClient;
	@Autowired
	private IFileClient fileClient;
	@Value("${api.ekp.fdTemplateId}")
	private String fdTemplateId;

	@Override
	@PostMapping(EKP_SEND_FORM_POST)
	public R<EkpVo> sendEkpFormPost(ContractFormInfoEntity entity) {

		EkpVo ekpVo = null;
		try {
			PushEkpEntity pushEkpEntity = new PushEkpEntity();
			pushEkpEntity.setFdTemplateId(fdTemplateId);
			if(entity != null) {
				//17090089是登录人的编号
				if(StrUtil.isNotEmpty("17090089") && StrUtil.isNotEmpty(entity.getAccording().get(0).getFileId())) {
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					DocCreatorEntity docCreatorEntity = new DocCreatorEntity();
					//人员编号
					docCreatorEntity.setEmplno("17090089");
					pushEkpEntity.setDocCreator(docCreatorEntity);
					FormValuesEntity formValuesEntity = new FormValuesEntity();
					//依据编号
					//formValuesEntity.setFd_accord_id(entity.getAccording().get(0).getFileId());
					//乙方电话
					formValuesEntity.setFd_b_number("13350894824");
					//乙方税籍编号
					formValuesEntity.setFd_b_taxno("91360823092907952B");
					formValuesEntity.setFd_accord_id("1762642a34c79442253858b4b2aab793");
					//合同id
					formValuesEntity.setFd_contract_id(entity.getId().toString());
					//合同文件名称  需要查询出来 TextFile是null
					formValuesEntity.setFd_contract_name("新陈列室合同");
					/*List<FileVO> fileVO=fileClient.getByIds(entity.getTextFile()).getData();
					if(fileVO.size()>0){
						formValuesEntity.setFd_contract_name(fileVO.get(0).getName());
					}*/
					//pdf的id
					formValuesEntity.setFd_attachment_id(entity.getTextFilePdf());
					//pdf的id
					formValuesEntity.setFd_contract_url("");
					//合同起草流程类型
					if("10".equals(entity.getContractSoure())){
						formValuesEntity.setFd_contract_type("10");
						//performanceColPayList
						List <KeepList> keepList=new ArrayList<KeepList>();
						KeepList keep=new KeepList();
						List <PayList> payList=new ArrayList<PayList>();
						PayList pay=new PayList();
						for(ContractPerformanceEntity performance:entity.getPerformanceList()){
							//交易类型
							switch (performance.getType()) {
								case "1":
									keep.setFd_trade_type("有效期交易");
									break;
								case "2":
									keep.setFd_trade_type("安合同量交易");
									break;
								case "3":
									keep.setFd_trade_type("单次交易");
									break;
								case "4":
									keep.setFd_trade_type("按周期交易");
									break;
								default:
							}
							keep.setFd_receipt(performance.getAcceptanceConditions());
							keep.setFd_plan_time(sdf.format(performance.getPlanPayTime()));
							keep.setFd_plan_content(performance.getName());
							keepList.add(keep);
						}
						for(ContractPerformanceColPayEntity performanceColPay:entity.getPerformanceColPayList()){
							//交易类型
							switch (performanceColPay.getType()) {
								case "1":
									pay.setFd_trade_kind("有效期交易");
									break;
								case "2":
									pay.setFd_trade_kind("安合同量交易");
									break;
								case "3":
									pay.setFd_trade_kind("单次交易");
									break;
								case "4":
									pay.setFd_trade_kind("按周期交易");
									break;
								default:
							}
							pay.setFd_p_receipt(performanceColPay.getAcceptanceConditions());
							pay.setFd_plan_ptime(sdf.format(performanceColPay.getPlanPayTime()));
							pay.setFd_plan_psum(performanceColPay.getName());
							payList.add(pay);
						}
						formValuesEntity.setFd_keep_list(keepList);
						formValuesEntity.setFd_pay_list(payList);
					}else if("30".equals(entity.getContractSoure())||StrUtil.isNotEmpty(entity.getOtherInformation())){
						formValuesEntity.setFd_contract_type("30");
					}else{
						formValuesEntity.setFd_contract_type("20");
					}
					//合同主旨
					formValuesEntity.setFd_main(entity.getContractName());
					//合同大类
					R<List<DictBiz>> contract_HTDL = bizClient.getList("HTDL");
					List<DictBiz> dataBiz = contract_HTDL.getData();
					dataBiz.forEach(bz -> {
						if ((bz.getId().toString()).equals(entity.getContractBigCategory())) {
							formValuesEntity.setFd_broad(bz.getDictKey());
						}
					});
					//合同主旨
					formValuesEntity.setFd_main(entity.getContractName());
					//申请用公章全称
					formValuesEntity.setFd_offical_seal(entity.getSealName());
					//相对方名称
					formValuesEntity.setFd_full_name(entity.getCounterpart().get(0).getName());
					//合同负责人
					formValuesEntity.setFd_emplno("08048200");
					//合同份数
					formValuesEntity.setFd_copies(entity.getShare());
					//合同期限
					switch (entity.getContractPeriod()) {
						case "xysn":
							formValuesEntity.setFd_contract_period("1");
							break;
						case "dysn":
							formValuesEntity.setFd_contract_period("2");
							break;
						case "wzzqx":
							formValuesEntity.setFd_contract_period("3");
							break;
						default:
					}
					//合同时间起
					formValuesEntity.setFd_start_time(sdf.format(entity.getStartingTime()));
					//合同时间止
					formValuesEntity.setFd_lasttime(sdf.format(entity.getEndTime()));
					//收付款
					switch (entity.getColPayType()) {
						case "1323239541401841666":
							formValuesEntity.setFd_payment("1");
							break;
						case "1323239469884764161":
							formValuesEntity.setFd_payment("2");
							break;
						case "1323239716493062146":
							formValuesEntity.setFd_payment("3");
							break;
						default:
					}
					//收付款条件
					switch (entity.getColPayTerm()) {
						case "1323242418597916674":
							formValuesEntity.setFd_condition("1");
							break;
						case "1323243129146568706":
							formValuesEntity.setFd_condition("2");
							break;
						case "1323243216736219137":
							formValuesEntity.setFd_condition("3");
							break;
						case "1323243330431217665":
							formValuesEntity.setFd_condition("4");
							break;
						case "1323243409321881601":
							formValuesEntity.setFd_condition("5");
							break;
						case "1323242740267479042":
							formValuesEntity.setFd_condition("6");
							break;
						case "1323242875978379265":
							formValuesEntity.setFd_condition("7");
							break;
						case "1323240326596521986":
							formValuesEntity.setFd_payee_condition("1");
							break;
						case "1323240755069841410":
							formValuesEntity.setFd_payee_condition("2");
							break;
						case "1323241507062411265":
							formValuesEntity.setFd_payee_condition("3");
							break;
						default:
					}
					if (!Func.isEmpty(entity.getDays())) {
						formValuesEntity.setFd_payee_days(entity.getDays().toString());
					}
					if (!Func.isEmpty(entity.getContractAmount())) {
						//合同未税金额
						formValuesEntity.setFd_taxed_price(entity.getContractAmount().toString());
					}
					if (!Func.isEmpty(entity.getContactTaxRate())) {
						//税率
						formValuesEntity.setFd_tax_rate(entity.getContactTaxRate().toString());
					}
					if (!Func.isEmpty(entity.getContractTaxAmount())) {
						//含税金额
						formValuesEntity.setFd_tax_include(entity.getContractTaxAmount().toString());
					}
					//币种
					R<List<DictBiz>> contract_bz = bizClient.getList("bz");
					List<DictBiz> databz = contract_bz.getData();
					databz.forEach(bz -> {
						if (bz.getDictKey().equals(entity.getCurrencyCategory())) {
							formValuesEntity.setFd_currency(bz.getDictValue());
						}
					});
					//是否延期条款
					if("0".equals(entity.getExtension())){
						formValuesEntity.setFd_automatic("1");
					}else{
						formValuesEntity.setFd_automatic("2");
					}
					if(entity.getContractBond().size()>0){
						//有无押金
						formValuesEntity.setFd_cash_pledge(entity.getContractBond().get(0).getIsNotBond());
						//押金
						formValuesEntity.setFd_cash(entity.getContractBond().get(0).getIsNotBond());
						//缴交时间
						formValuesEntity.setFd_pay_time(sdf.format(entity.getContractBond().get(0).getPlanPayTime()));
						//退回时间
						formValuesEntity.setFd_back_time(sdf.format(entity.getContractBond().get(0).getPlanReturnTime()));
						//保证金类别
						formValuesEntity.setFd_deposit_type(entity.getContractBond().get(0).getType());
						//保证金编号
						formValuesEntity.setFd_number(entity.getContractBond().get(0).getId().toString());
					}
					//合同形式
					formValuesEntity.setFd_contract_no(entity.getContractForm());
					//相对方联系人
					formValuesEntity.setFd_linkman(entity.getCounterpartPerson());
					//相对方联系电话
					formValuesEntity.setFd_contact_number(entity.getTelephonePerson());
					//相对方邮箱
					formValuesEntity.setFd_email(entity.getEmailPerson());
					//相对方联系地址
					formValuesEntity.setFd_address(entity.getAddressPerson());

					pushEkpEntity.setFormValues(formValuesEntity);
					//依据id
					if (!Func.isEmpty(entity.getAccording().get(0).getFileId())) {
						//pushEkpEntity.setDocSubject(entity.getAccording().get(0).getFileId());
						pushEkpEntity.setDocSubject("1762642a34c79442253858b4b2aab793");
					}
					pushEkpEntity.setToken(ekpService.getToken());
					pushEkpEntity.setDocSubject(entity.getContractName());
					pushEkpEntity.setFdTemplateId("176212613bf6f84e6bf1ad942cbb8344");
					if (StrUtil.isNotEmpty(pushEkpEntity.getToken())) {
						ekpVo = ekpService.pushData(pushEkpEntity);
					}
				}
			}
		} catch(Exception exception) {
			log.error(exception.getMessage());
		}
		return R.data(ekpVo);
	}

	@Override
	@GetMapping(DOC_QUERY_INFO)
	public R<List<DocVo>> queryDocInfo(DocEntity entity) {
		List<DocVo> docVo = null;
		try {
			entity.setToken(docService.getToken());
			if(StrUtil.isNotEmpty(entity.getToken())) {
				docVo = docService.getDocInfo(entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(docVo);
	}

	@Override
	@GetMapping(E_SEAL_UPLOAD_FILE)
	public R<List<UploadFileVo>> uploadFiles(UploadFileEntity entity) {
		List<UploadFileVo> uploadFileVo = null;
		try {
			String token = eSealService.getToken();
			if(StrUtil.isNotEmpty(token)) {
				if(StrUtil.isNotEmpty(entity.getIsMerge())) {
					uploadFileVo = eSealService.uploadFiles(token, entity);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(uploadFileVo);
	}

	@Override
	@PostMapping(E_SEAL_SINGLE_SIGN)
	public R<SingleSignVo> singleSign(SingleSignEntity entity) {
		SingleSignVo singleSignVo = null;
		try {
			String token = eSealService.getToken();
			if(StrUtil.isNotEmpty(token)) {
				singleSignVo = eSealService.singleSign(token, entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(singleSignVo);
	}

	@Override
	@PostMapping(E_SEAL_SINGLE_SIGN_POST)
	public R<SingleSignVo> singleSignPost(SingleSignEntity entity) {
		SingleSignVo singleSignVo = null;
		try {
			String token = eSealService.getToken();
			if(StrUtil.isNotEmpty(token)) {
				singleSignVo = eSealService.singleSign(token, entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(singleSignVo);
	}

	@Override
	@GetMapping(E_SEAL_SEND_SMS)
	public R sendSms(SendSmsEntity entity) {
		try {
			String token = eSealService.getToken();
			if(StrUtil.isNotEmpty(token)) {
				eSealService.sendSms(token, entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	@GetMapping(E_SEAL_SINGLE_SIGN_MULTI)
	public R<MultiSignVo> multiSign(MultiSignEntity entity) {
		MultiSignVo multiSignVo = null;
		try {
			String token = eSealService.getToken();
			if(StrUtil.isNotEmpty(token)) {
				multiSignVo = eSealService.multiSign(token, entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(multiSignVo);
	}

	@Override
	@GetMapping(E_SEAL_READ_SIGNED)
	public R<String> readSigned(ReadSignedEntity entity) {
		String url = null;
		try {
			String token = eSealService.getToken();
			if(StrUtil.isNotEmpty(token)) {
				url = eSealService.readSigned(token, entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(url);
	}

	@Override
	@GetMapping(E_SEAL_COMPANY_INFO)
	public R<CompanyInfoVo> queryCompanyInfo(CompanyInfoEntity entity) {
		CompanyInfoVo companyInfoVo = null;
		try {
			String token = eSealService.getToken();
			if(StrUtil.isNotEmpty(token)) {
				companyInfoVo = eSealService.getCompanyInfo(token, entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(companyInfoVo);
	}
}
