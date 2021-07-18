package org.springblade.abutment.feign;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springblade.abutment.entity.*;
import org.springblade.abutment.enumJson.TemplateJsonEnum;
import org.springblade.abutment.service.*;
import org.springblade.abutment.vo.*;
import org.springblade.contract.entity.*;
import org.springblade.contract.feign.IContractClient;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.log.logger.BladeLogger;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.entity.DictBiz;
import org.springblade.system.feign.IDictBizClient;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 对接Feign实现类
 *
 * @author cc
 */
@ApiIgnore
@Log4j2
@RestController
public class AbutmentClient implements IAbutmentClient {

	@Autowired
	private BladeLogger logger;
	@Autowired
	private IEkpService ekpService;
	@Autowired
	private IDocService docService;
	@Autowired
	private IAsService iAsService;
	@Autowired
	private IESealService eSealService;
	@Autowired
	private IDictBizClient bizClient;
	@Autowired
	private IContractClient contractClient;
	@Autowired
	private IFileClient fileClient;
	@Autowired
	private IUserClient userClient;
	@Autowired
	private TrackerClient trackerClient;
	@Autowired
	private IOrganizationService iOrganizationService;
	@Autowired
	private ICounterpartService counterpartService;
	@Value("${api.ekp.fdTemplateId}")
	private String fdTemplateId;
	@Value("${api.ekp.appTemplateId}")
	private String appTemplateId;
	@Value("${api.ekp.systemName}")
	private String systemName;
	@Value("${api.eSeal.downloadUrl}")
	private String downloadUrl;
	@Value("${api.ekp.ftlPath}")
	private String ftlPath;
	@Value("${api.contract.notSign.status}")
	private String status;
	@Value("${api.contract.notSign.firstWarning}")
	private String firstWarning;
	@Value("${api.contract.notSign.secondWarning}")
	private String secondWarning;
	@Value("${api.contract.notSign.thirdWarning}")
	private String thirdWarning;
	@Value("${api.contract.notSign.estimateWarning}")
	private String estimateWarning;

	/**
	 * 模板审批
	 *
	 * @param entity
	 * @return
	 */
	@SneakyThrows
	@Override
	@PostMapping(TEMPLATE_APP)
	@ApiLog("模板审批")
	public R<EkpVo> temEkpFormPost(@RequestBody ContractTemplateEntity entity) {
		R<EkpVo> rEkpVo = new R<>();
		EkpVo ekpVo = null;
		PushEkpEntity pushEkpEntity = new PushEkpEntity();
		if (Func.isNotEmpty(entity)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//表单模版ID
			pushEkpEntity.setFdTemplateId(fdTemplateId);
			DocCreatorEntity docCreatorEntity = new DocCreatorEntity();
			//人员编号
			R<User> user = userClient.userInfoById(AuthUtil.getUserId());
			docCreatorEntity.setEmplno(user.getData().getCode());
			pushEkpEntity.setDocCreator(docCreatorEntity);
			//文档主题
			pushEkpEntity.setDocSubject("模板建档申请" + entity.getName());
			//文档主要内容
			TemplateAc ac = new TemplateAc();
			//范本名称
			ac.setName(entity.getName());
			//所属合同大类
			R<List<DictBiz>> contract_HTDL = bizClient.getList("HTDL");
			List<DictBiz> dataBiz = contract_HTDL.getData();
			for (DictBiz dictBiz : dataBiz) {
				if ((dictBiz.getId().toString()).equals(entity.getContractBigCategory())) {
					ac.setContract_big_category(dictBiz.getDictKey());
				}
			}
			//所属合同小类
			R<List<DictBiz>> contract_HTXL = bizClient.getList("HTXL");
			List<DictBiz> dataHTXLBiz = contract_HTXL.getData();
			for (DictBiz dictBiz : dataHTXLBiz) {
				if ((dictBiz.getId().toString()).equals(entity.getContractSmallCategory())) {
					ac.setContract_small_category(dictBiz.getDictKey());
				}
			}
			//范本附件url
			if (Func.isNotEmpty(entity.getAttachedFiles())) {
				List<FileVO> att = fileClient.getByIds(entity.getAttachedFiles()).getData();
				ac.setAttached_files(att.get(0).getLink());
			} else {
				ac.setAttached_files(" ");
			}
			//范本附件ID
			ac.setAttached_files_id(entity.getAttachedFiles());
			//模板文件url
			List<FileVO> tem = fileClient.getByIds(entity.getTemplateFileId()).getData();
			ac.setTemplate_file(tem.size() > 0 ? tem.get(0).getLink() : "");
			//模板文件ID
			ac.setTemplate_file_id(entity.getTemplateFileId());
			//范本状态
			ac.setTemplate_status(entity.getTemplateStatus());
			//申请人员编号
			ac.setApp_number(entity.getAppNumber());
			pushEkpEntity.setTemplateAc(ac);
			//获取token
			pushEkpEntity.setToken(ekpService.getToken());
			log.info("获取ekp的token：" + pushEkpEntity.getToken());
			if (StrUtil.isEmpty(pushEkpEntity.getToken())) {
				rEkpVo.setCode(1);
				rEkpVo.setMsg("token获取失败，连接超时");
				rEkpVo.setSuccess(false);
				rEkpVo.setData(null);
				return rEkpVo;
			}
			ekpVo = ekpService.pushData(pushEkpEntity);
			log.info("获取ekpVo的内容：" + ekpVo.getDoc_info());
			if (StrUtil.isEmpty(ekpVo.getDoc_info())) {
				rEkpVo.setCode(2);
				rEkpVo.setMsg("当前员工编号在系统中不存在，请换一个试试");
				rEkpVo.setSuccess(false);
				rEkpVo.setData(null);
				return rEkpVo;
			}
			logger.info("temEkpFormPost-return", JsonUtil.toJson(rEkpVo));
		}
		rEkpVo.setData(ekpVo);
		log.info("HttpStatus.OK.value()" + HttpStatus.OK.value());
		rEkpVo.setCode(HttpStatus.OK.value());
		rEkpVo.setSuccess(true);
		return rEkpVo;
	}

	/**
	 * 合同借阅申请
	 *
	 * @param entity 合同借阅申请
	 * @return
	 */
	@SneakyThrows
	@PostMapping(CONTRACT_BORROWING)
	@Override
	@ApiLog("合同借阅申请")
	public R<EkpVo> borEkpFormPost(@RequestBody ContractBorrowApplicationEntity entity) {
		R<EkpVo> rEkpVo = new R<>();
		EkpVo ekpVo = null;
		PushEkpEntity pushEkpEntity = new PushEkpEntity();
		if (Func.isNotEmpty(entity)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//表单模版ID
			pushEkpEntity.setFdTemplateId(appTemplateId);
			DocCreatorEntity docCreatorEntity = new DocCreatorEntity();
			//人员编号
			R<User> user = userClient.userInfoById(AuthUtil.getUserId());
			docCreatorEntity.setEmplno(user.getData().getCode());
			pushEkpEntity.setDocCreator(docCreatorEntity);
			//文档主题
			pushEkpEntity.setDocSubject("借阅申请：--" + entity.getDataName());
			//文档主要内容
			FormValuesEntity formValuesEntity = new FormValuesEntity();
			//事由说明
			formValuesEntity.setFd_reason(entity.getExplanation());
			//资料类型
			formValuesEntity.setFd_type(entity.getDataType());
			//借阅方式
			formValuesEntity.setFd_method(entity.getBorrowMode());
			//借阅周期起始时间
			formValuesEntity.setFd_begin(sdf.format(entity.getBorrowCycleStart()));
			//借阅周期截至时间
			formValuesEntity.setFd_end(sdf.format(entity.getBorrowCycleEnd()));
			//资料名称
			formValuesEntity.setFd_name(entity.getDataName());
			pushEkpEntity.setFormValues(formValuesEntity);
			//获取token
			pushEkpEntity.setToken(ekpService.getToken());
			log.info("获取ekp的token：" + pushEkpEntity.getToken());
			if (StrUtil.isEmpty(pushEkpEntity.getToken())) {
				rEkpVo.setCode(1);
				rEkpVo.setMsg("token获取失败，连接超时");
				rEkpVo.setSuccess(false);
				rEkpVo.setData(null);
				return rEkpVo;
			}
			ekpVo = ekpService.pushData(pushEkpEntity);
			log.info("获取ekpVo的内容：" + ekpVo.getDoc_info());
			if (StrUtil.isEmpty(ekpVo.getDoc_info())) {
				rEkpVo.setCode(2);
				rEkpVo.setMsg("当前员工编号在系统中不存在，请换一个试试");
				rEkpVo.setSuccess(false);
				rEkpVo.setData(null);
				return rEkpVo;
			}
			logger.info("borEkpFormPost-return", JsonUtil.toJson(rEkpVo));
		}
		rEkpVo.setData(ekpVo);
		log.info("HttpStatus.OK.value()" + HttpStatus.OK.value());
		rEkpVo.setCode(HttpStatus.OK.value());
		rEkpVo.setSuccess(true);
		return rEkpVo;
	}

	@SneakyThrows
	@PostMapping(EKP_SEND_BATCH_POST)
	@ApiLog("批量独立/批量范本")
	@Override
	public R<EkpVo> sendEkpBatchPost(ContractFormInfoEntity entity) {
		R<EkpVo> rEkpVo = new R<>();
		EkpVo ekpVo = null;
		PushEkpEntity pushEkpEntity = new PushEkpEntity();
		pushEkpEntity.setFdTemplateId(fdTemplateId);
		if (StrUtil.isNotEmpty(entity.getPersonCodeContract()) && StrUtil.isNotEmpty(entity.getAccording().get(0).getFileId())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DocCreatorEntity docCreatorEntity = new DocCreatorEntity();
			//人员编号
			R<User> user = userClient.userInfoById(AuthUtil.getUserId());
			docCreatorEntity.setEmplno(String.valueOf("".equals(user.getData().getCode()) ? 17090089 : user.getData().getCode()));
			pushEkpEntity.setDocCreator(docCreatorEntity);
			FormValuesEntity formValuesEntity = new FormValuesEntity();
			//依据编号
			formValuesEntity.setFd_accord_id(entity.getAccording().get(0).getFileId());
			//乙方电话
			formValuesEntity.setFd_b_number("13361615656");
			//乙方税籍编号
			for (int i = 0; i < entity.getCounterpart().size(); i++) {
				formValuesEntity.setFd_b_taxno(entity.getCounterpart().get(i).getUnifiedSocialCreditCode());
			}
			//合同id
			formValuesEntity.setFd_contract_id(entity.getId().toString());
			//pdf的id
			formValuesEntity.setFd_attachment_id(entity.getTextFilePdf());
			//合同的url
			formValuesEntity.setFd_contract_url("");
			//合同形式
			formValuesEntity.setFd_contract_no(entity.getContractForm());
			//合同起草流程类型
			switch (entity.getContractSoure()) {
				case "41":
					//合同附件名称   合同附件：电子合同 附件只上传一份需要盖章的合同文本     实体合同需要上传多份合同文本
					List<FileVO> fileVO = fileClient.getByIds(entity.getTextFile()).getData();
					if ("3".equals(entity.getContractForm()) || "4".equals(entity.getContractForm())) {
						StringBuilder name = new StringBuilder();
						fileVO.forEach(f -> {
							name.append(f.getName());
							name.append(",");
						});
						name.substring(0, name.length());
						formValuesEntity.setFd_contract_name(name.toString());
					} else {
						formValuesEntity.setFd_contract_name(fileVO.get(0).getName());
					}
					//  独立起草10传10   范本起草为30传20 范本起草其他约定暂时未定义 传30   多方起草为20  传40
					if ("41".equals(entity.getContractSoure())) {
						formValuesEntity.setFd_contract_type("10");
					} else if ("43".equals(entity.getContractSoure())) {
						formValuesEntity.setFd_contract_type("30");
					}
					//合同方对应关系
					if ("甲".equals(entity.getContractRoles())) {
						formValuesEntity.setFd_onetoone("1");
					} else if ("乙".equals(entity.getContractRoles())) {
						formValuesEntity.setFd_onetoone("2");
					} else {
						formValuesEntity.setFd_onetoone("3");
					}
					//履约信息
					List<KeepList> keepList = new ArrayList<KeepList>();
					List<PayList> payList = new ArrayList<PayList>();
					for (ContractPerformanceEntity performance : entity.getPerformanceList()) {
						KeepList keep = new KeepList();
						//交易类型
						switch (performance.getType()) {
							case "1":
								keep.setFd_trade_type("有效期交易");
								break;
							case "2":
								keep.setFd_trade_type("按合同量交易");
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
					for (ContractPerformanceColPayEntity performanceColPay : entity.getPerformanceColPayList()) {
						PayList pay = new PayList();
						//交易类型
						switch (performanceColPay.getType()) {
							case "1":
								pay.setFd_trade_kind("有效期交易");
								break;
							case "2":
								pay.setFd_trade_kind("按合同量交易");
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
						pay.setFd_plan_psum(performanceColPay.getPlanPayAmount().toString());
						payList.add(pay);
					}
					formValuesEntity.setFd_keep_list(keepList);
					formValuesEntity.setFd_pay_list(payList);
					break;
				case "43":
					//合同文件名称  需要查询出来 TextFile是null
					formValuesEntity.setFd_contract_name(entity.getContractListName());
					R<ContractTemplateEntity> templateEntity = contractClient.getByTemplateId(entity.getContractTemplateId());
					if ("0".equals(templateEntity.getData().getUsageRecord())) {
						formValuesEntity.setFd_contract_type("20");
					} else {
						formValuesEntity.setFd_contract_type("30");
					}
					// 特殊模板对合同方对应关系进行特殊处理
					if ("FWZL_36".equals(templateEntity.getData().getTemplateCode())) {
						formValuesEntity.setFd_onetoone("2");
					} else {
						formValuesEntity.setFd_onetoone("1");
					}
					/*************************************处理模板字段START*****************************************/
					//模板编号
					formValuesEntity.setFd_template_type(templateEntity.getData().getTemplateCode());
					//模板关键字段数据相关内容信息处理
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("targetKey", TemplateJsonEnum.fromValue(formValuesEntity.getFd_template_type()).setScheduler());
					jsonObject.put("ChineseColumn", TemplateJsonEnum.fromValue(formValuesEntity.getFd_template_type()).setScheduler(entity.getJson(), true));
					jsonObject.put("targetParentJson", TemplateJsonEnum.fromValue(formValuesEntity.getFd_template_type()).setScheduler(entity.getJson()));
					formValuesEntity.setFd_template_content(jsonObject);
					/********************************  *****处理模板字段END*****************************************/
					break;
			}
			//处理签章关键字 和多页合同 只有电子合同-我司用印   实体合同-我司用电子印  需要处理和传递keyword字段
			if (entity.getContractForm().contains("1") || entity.getContractForm().contains("2")) {
				formValuesEntity = fileText(formValuesEntity, entity);
			}
			//合同主旨
			formValuesEntity.setFd_main(entity.getContractName());
			//合同大类
			R<List<DictBiz>> contract_HTDL = bizClient.getList("HTDL");
			List<DictBiz> dataBiz = contract_HTDL.getData();
			for (DictBiz dictBiz : dataBiz) {
				if ((dictBiz.getId().toString()).equals(entity.getContractBigCategory())) {
					formValuesEntity.setFd_broad(dictBiz.getDictKey());
				}
			}
			//合同中类
			if ("30".equals(entity.getContractStatus())) {
				formValuesEntity.setFd_secondary(entity.getContractListName());
			} else {
				formValuesEntity.setFd_secondary("其他");
			}
			//合同小类
			R<List<DictBiz>> contract_HTXL = bizClient.getList("HTXL");
			List<DictBiz> dataHTXLBiz = contract_HTXL.getData();
			for (DictBiz dictBiz : dataHTXLBiz) {
				if ((dictBiz.getId().toString()).equals(entity.getContractSmallCategory())) {
					formValuesEntity.setFd_small(dictBiz.getDictKey());
				}
			}
			//合同主旨
			formValuesEntity.setFd_main(entity.getContractName());
			//申请用公章全称
			R<List<DictBiz>> application_seal = bizClient.getList("application_seal");
			List<DictBiz> dataSeal = application_seal.getData();
			for (DictBiz dictBiz : dataSeal) {
				if ((dictBiz.getDictValue()).equals(entity.getSealName())) {
					formValuesEntity.setFd_offical_seal(dictBiz.getDictKey());
				}
			}
			/********************     相对方信息START   ***********************************/
			//相对方名称
			formValuesEntity.setFd_full_name(entity.getCounterpart().get(0).getName());
			if (Func.isNotEmpty(entity.getBankCode())) {
				formValuesEntity.setFd_bank_code(entity.getBankCode());
			}
			if (Func.isNotEmpty(entity.getBankAccountCode())) {
				formValuesEntity.setFd_account_code(entity.getBankAccountCode());
			}
			if (Func.isNotEmpty(entity.getBankAccountName())) {
				formValuesEntity.setFd_account_name(entity.getBankAccountName());
			}
			if (Func.isNotEmpty(entity.getBankDepositName())) {
				//相对方开户行名称
				formValuesEntity.setFd_account_name(entity.getBankDepositName());
			}
			if (Func.isNotEmpty(entity.getCounterpartPerson())) {
				//相对方联系人
				formValuesEntity.setFd_linkman(entity.getCounterpartPerson());
			}
			if (Func.isNotEmpty(entity.getTelephonePerson())) {
				//相对方联系电话
				formValuesEntity.setFd_contact_number(entity.getTelephonePerson());
			}
			if (Func.isNotEmpty(entity.getEmailPerson())) {
				//相对方邮箱
				formValuesEntity.setFd_email(entity.getEmailPerson());
			}
			if (Func.isNotEmpty(entity.getAddressPerson())) {
				//相对方联系地址
				formValuesEntity.setFd_address(entity.getAddressPerson());
			}
			/********************     相对方信息END   ***********************************/
			//合同负责人
			formValuesEntity.setFd_emplno(entity.getPersonCodeContract());
			//合同份数
			formValuesEntity.setFd_copies(entity.getShare());
			//合同期限
			switch (entity.getContractPeriod()) {
				case "1095":
					formValuesEntity.setFd_contract_period("1");
					break;
				case "1460":
					formValuesEntity.setFd_contract_period("2");
					break;
				case "365000":
					formValuesEntity.setFd_contract_period("3");
					break;
				default:
			}
			//合同时间起
			formValuesEntity.setFd_start_time(sdf.format(entity.getStartingTime()));
			//合同时间止
			formValuesEntity.setFd_lasttime(sdf.format(entity.getEndTime()));
			/***************************************   收付款START    ******************************************/
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
			//收款明细列表
			List<CollectionInde> indeList = new ArrayList<>();
			entity.getCollection().forEach(conI -> {
				CollectionInde collectionInde = new CollectionInde();
				//总期数
				collectionInde.setFd_period_num(conI.getPeriodNum());
				//第几期
				collectionInde.setFd_period_idx(conI.getPeriodIdx());
				//条件+帐期天数
				collectionInde.setFd_pay_condition(conI.getPayCondition());
				collectionInde.setFd_pay_days_inde(conI.getDays().toString());
				//是否开票
				collectionInde.setFd_is_receipt(conI.getIsReceipt());
				//比例
				collectionInde.setFd_per(conI.getPayPer().toString());
				collectionInde.setFd_pay_amount(conI.getPayAmount().toString());
				indeList.add(collectionInde);
			});
			formValuesEntity.setFd_collection_inde(indeList);
			if (!Func.isEmpty(entity.getDays())) {
				//签约后收款  需要填写
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
			/**************************************   收付款END    *********************************************/
			//币种
			R<List<DictBiz>> contract_bz = bizClient.getList("bz");
			List<DictBiz> databz = contract_bz.getData();
			for (DictBiz dictBiz : databz) {
				if ((dictBiz.getDictKey()).equals(entity.getCurrencyCategory())) {
					formValuesEntity.setFd_currency(dictBiz.getDictValue());
				}
			}
			//是否延期条款
			if ("0".equals(entity.getExtension())) {
				formValuesEntity.setFd_automatic("1");
			} else {
				formValuesEntity.setFd_automatic("2");
			}
			if (entity.getContractBond().size() > 0) {
				if (!Func.isEmpty(entity.getContractBond().get(0).getPlanPayAmount())) {
					formValuesEntity.setFd_cash(entity.getContractBond().get(0).getPlanPayAmount().toString());
				}
				//保证金类别
				formValuesEntity.setFd_deposit_type(entity.getContractBond().get(0).getType());
				//保证金编号
				formValuesEntity.setFd_number(entity.getContractBond().get(0).getId().toString());
				//有无押金
				if ("0".equals(entity.getContractBond().get(0).getIsNotBond())) {
					formValuesEntity.setFd_cash_pledge("1");
				} else if ("1".equals(entity.getContractBond().get(0).getIsNotBond())) {
					formValuesEntity.setFd_cash_pledge("2");
					//押金
					formValuesEntity.setFd_cash("");
				} else {
					formValuesEntity.setFd_cash_pledge("3");
				}
				if (!Func.isEmpty(entity.getContractBond().get(0).getPlanPayTime())) {
					//缴交时间
					formValuesEntity.setFd_pay_time(sdf.format(entity.getContractBond().get(0).getPlanPayTime()));
				}
				if (!Func.isEmpty(entity.getContractBond().get(0).getPlanReturnTime())) {
					//退回时间
					formValuesEntity.setFd_back_time(sdf.format(entity.getContractBond().get(0).getPlanReturnTime()));
				}
			}
			pushEkpEntity.setFormValues(formValuesEntity);
			pushEkpEntity.setDocSubject(entity.getContractName());
			pushEkpEntity.setFdTemplateId(fdTemplateId);
			if (Func.isNotEmpty(entity.getAttachedFiles())) {
				pushEkpEntity.setFd_attachment(attachedFile(entity).getData());
				if (Func.isEmpty(pushEkpEntity.getFd_attachment())) {
					rEkpVo.setCode(3);
					rEkpVo.setMsg("依据附件上传失败，请检查FDS文件服务器配置是否正常");
					rEkpVo.setSuccess(false);
					rEkpVo.setData(null);
					return rEkpVo;
				}
			}
			pushEkpEntity.setToken(ekpService.getToken());
			if (StrUtil.isEmpty(pushEkpEntity.getToken())) {
				rEkpVo.setCode(1);
				rEkpVo.setMsg("token获取失败，连接超时");
				rEkpVo.setSuccess(false);
				rEkpVo.setData(null);
				return rEkpVo;
			}
			logger.info("inde-pushData", JsonUtil.toJson(pushEkpEntity));
			ekpVo = ekpService.pushData(pushEkpEntity);
			if (StrUtil.isEmpty(ekpVo.getDoc_info())) {
				rEkpVo.setCode(2);
				rEkpVo.setMsg("当前员工编号在系统中不存在，请换一个试试");
				rEkpVo.setSuccess(false);
				rEkpVo.setData(null);
				return rEkpVo;
			}
		}
		rEkpVo.setData(ekpVo);
		rEkpVo.setCode(HttpStatus.OK.value());
		rEkpVo.setSuccess(true);
		return rEkpVo;
	}

	public R<List<Attachment>> attachedFile(ContractFormInfoEntity entity) {
		List<Attachment> listAttachment = new ArrayList<>();
		String[] fileIds = new String[0];
		try {
			List<FileVO> fileVOs = fileClient.getByIds(entity.getAttachedFiles()).getData();
			for (FileVO fileVO : fileVOs) {
				// 开始上传fastDFS服务器
				Attachment attachment = new Attachment();
				NameValuePair[] nvp = new NameValuePair[5];
				int index = fileVO.getName().lastIndexOf(".");
				String fileSuffix = fileVO.getName().substring(index + 1);
				nvp[0] = new NameValuePair("fdFileName", fileVO.getName());
				nvp[1] = new NameValuePair("fileSuffix", fileSuffix);
				nvp[2] = new NameValuePair("fdKey", "");
				nvp[3] = new NameValuePair("fdFileSize", fileVO.getFileSizes());
				nvp[4] = new NameValuePair("fileType", "");
				//3.创建trackerServer
				TrackerServer trackerServer = null;
				try {
					trackerServer = trackerClient.getConnection();
					if (Func.isNull(trackerServer)) {
						log.info("FDS服务器链接地址：" + ClientGlobal.CONF_KEY_TRACKER_SERVER);
						return R.fail("Connection timed out: connect FSD文件服务器连接失败，请联系管理员处理！");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 4、创建一个 StorageServer 的引用，值为 null
				StorageServer storageServer = null;
				// 5、创建一个 StorageClient 对象，需要两个参数 TrackerServer 对象、StorageServer 的引用
				StorageClient storageClient = new StorageClient(trackerServer, storageServer);
				InputStream in;
				BufferedInputStream bin;
				ByteArrayOutputStream baos;
				BufferedOutputStream bout;
				byte[] bytes;
				try {
					URL url = new URL(fileVO.getLink());
					URLConnection conn = url.openConnection();
					in = conn.getInputStream();
					bin = new BufferedInputStream(in);
					baos = new ByteArrayOutputStream();
					bout = new BufferedOutputStream(baos);
					byte[] buffer = new byte[1024];
					int len = bin.read(buffer);
					while (len != -1) {
						bout.write(buffer, 0, len);
						len = bin.read(buffer);
					}
					//刷新此输出流并强制写出所有缓冲的输出字节
					bout.flush();
					bytes = baos.toByteArray();
					// 上传
					fileIds = storageClient.upload_file(bytes, fileSuffix, nvp);
				} catch (IOException | MyException e) {
					e.printStackTrace();
				}
				attachment.setFilename(fileVO.getName());
				attachment.setFilePath(fileIds[0] + '/' + fileIds[1]);
				listAttachment.add(attachment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.data(listAttachment);
	}

	/**
	 * 独立起草/范本起草-合同起草
	 */
	@Override
	@PostMapping(EKP_SEND_FORM_POST)
	@ApiLog("独立起草/范本起草-合同起草")
	public R<EkpVo> sendEkpFormPost(@RequestBody ContractFormInfoEntity entity) {
		R<EkpVo> rEkpVo = new R<>();
		EkpVo ekpVo = null;
		PushEkpEntity pushEkpEntity = new PushEkpEntity();
		pushEkpEntity.setFdTemplateId(fdTemplateId);
		if (StrUtil.isNotEmpty(entity.getPersonCodeContract()) && StrUtil.isNotEmpty(entity.getAccording().get(0).getFileId())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DocCreatorEntity docCreatorEntity = new DocCreatorEntity();
			//人员编号
			R<User> user = userClient.userInfoById(AuthUtil.getUserId());
			docCreatorEntity.setEmplno(String.valueOf("".equals(user.getData().getCode()) ? entity.getPersonCodeContract() : user.getData().getCode()));
			pushEkpEntity.setDocCreator(docCreatorEntity);
			FormValuesEntity formValuesEntity = new FormValuesEntity();
			//依据编号
			formValuesEntity.setFd_accord_id(entity.getAccording().get(0).getFileId());
			//乙方电话
			formValuesEntity.setFd_b_number("13361615656");
			//乙方税籍编号
			for (int i = 0; i < entity.getCounterpart().size(); i++) {
				formValuesEntity.setFd_b_taxno(entity.getCounterpart().get(i).getUnifiedSocialCreditCode());
			}
			//合同id
			formValuesEntity.setFd_contract_id(entity.getId().toString());
			//pdf的id
			formValuesEntity.setFd_attachment_id(entity.getTextFilePdf());
			//合同的url
			formValuesEntity.setFd_contract_url("");
			//合同形式
			formValuesEntity.setFd_contract_no(entity.getContractForm());
			//合同起草流程类型
			if ("10".equals(entity.getContractSoure()) || "20".equals(entity.getContractSoure())) {
				//合同附件名称   合同附件：电子合同 附件只上传一份需要盖章的合同文本     实体合同需要上传多份合同文本
				List<FileVO> fileVO = fileClient.getByIds(entity.getTextFile()).getData();
				if (fileVO.size() > 0) {
					if ("3".equals(entity.getContractForm()) || "4".equals(entity.getContractForm())) {
						StringBuilder name = new StringBuilder();
						fileVO.forEach(f -> {
							name.append(f.getName());
							name.append(",");
						});
						name.substring(0, name.length());
						formValuesEntity.setFd_contract_name(name.toString());
					} else {
						formValuesEntity.setFd_contract_name(fileVO.get(0).getName());
					}
				}
				//  独立起草10传10   范本起草为30传20 范本起草其他约定暂时未定义 传30   多方起草为20  传40
				if ("10".equals(entity.getContractSoure())) {
					formValuesEntity.setFd_contract_type("10");
				} else if ("30".equals(entity.getContractSoure())) {
					formValuesEntity.setFd_contract_type("20");
				} else if ("20".equals(entity.getContractSoure())) {
					formValuesEntity.setFd_contract_type("40");
				}
				//合同方对应关系
				if ("甲".equals(entity.getContractRoles())) {
					formValuesEntity.setFd_onetoone("1");
				} else if ("乙".equals(entity.getContractRoles())) {
					formValuesEntity.setFd_onetoone("2");
				} else {
					formValuesEntity.setFd_onetoone("3");
				}
				//履约信息
				List<KeepList> keepList = new ArrayList<KeepList>();
				List<PayList> payList = new ArrayList<PayList>();
				for (ContractPerformanceEntity performance : entity.getPerformanceList()) {
					KeepList keep = new KeepList();
					//交易类型
					switch (performance.getType()) {
						case "1":
							keep.setFd_trade_type("有效期交易");
							break;
						case "2":
							keep.setFd_trade_type("按合同量交易");
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
				for (ContractPerformanceColPayEntity performanceColPay : entity.getPerformanceColPayList()) {
					PayList pay = new PayList();
					//交易类型
					switch (performanceColPay.getType()) {
						case "1":
							pay.setFd_trade_kind("有效期交易");
							break;
						case "2":
							pay.setFd_trade_kind("按合同量交易");
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
					pay.setFd_plan_psum(performanceColPay.getPlanPayAmount().toString());
					payList.add(pay);
				}
				formValuesEntity.setFd_keep_list(keepList);
				formValuesEntity.setFd_pay_list(payList);
			} else if ("30".equals(entity.getContractSoure())) {
				//合同文件名称  需要查询出来 TextFile是null
				formValuesEntity.setFd_contract_name(entity.getContractListName());
				R<ContractTemplateEntity> templateEntity = contractClient.getByTemplateId(entity.getContractTemplateId());
				if ("0".equals(templateEntity.getData().getUsageRecord())) {
					formValuesEntity.setFd_contract_type("20");
				} else {
					formValuesEntity.setFd_contract_type("30");
				}
				// 特殊模板对合同方对应关系进行特殊处理
				if ("FWZL_36".equals(templateEntity.getData().getTemplateCode())) {
					formValuesEntity.setFd_onetoone("2");
				} else {
					formValuesEntity.setFd_onetoone("1");
				}
				/*************************************处理模板字段START*****************************************/
				//模板编号
				formValuesEntity.setFd_template_type(templateEntity.getData().getTemplateCode());
				//模板关键字段数据相关内容信息处理
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("targetKey", TemplateJsonEnum.fromValue(formValuesEntity.getFd_template_type()).setScheduler());
				jsonObject.put("ChineseColumn", TemplateJsonEnum.fromValue(formValuesEntity.getFd_template_type()).setScheduler(entity.getJson(), true));
				jsonObject.put("targetParentJson", TemplateJsonEnum.fromValue(formValuesEntity.getFd_template_type()).setScheduler(entity.getJson()));
				formValuesEntity.setFd_template_content(jsonObject);
				/********************************  *****处理模板字段END*****************************************/
			}
			//处理签章关键字 和多页合同 只有电子合同-我司用印   实体合同-我司用电子印  需要处理和传递keyword字段
			if (entity.getContractForm().contains("1") || entity.getContractForm().contains("2")) {
				formValuesEntity = fileText(formValuesEntity, entity);
			}
			//合同主旨
			formValuesEntity.setFd_main(entity.getContractName());
			//合同大类
			R<List<DictBiz>> contract_HTDL = bizClient.getList("HTDL");
			List<DictBiz> dataBiz = contract_HTDL.getData();
			for (DictBiz dictBiz : dataBiz) {
				if ((dictBiz.getId().toString()).equals(entity.getContractBigCategory())) {
					formValuesEntity.setFd_broad(dictBiz.getDictKey());
				}
			}
			//合同中类
			if ("30".equals(entity.getContractStatus())) {
				formValuesEntity.setFd_secondary(entity.getContractListName());
			} else {
				formValuesEntity.setFd_secondary("其他");
			}
			//合同小类
			R<List<DictBiz>> contract_HTXL = bizClient.getList("HTXL");
			List<DictBiz> dataHTXLBiz = contract_HTXL.getData();
			for (DictBiz dictBiz : dataHTXLBiz) {
				if ((dictBiz.getId().toString()).equals(entity.getContractSmallCategory())) {
					formValuesEntity.setFd_small(dictBiz.getDictKey());
				}
			}
			//合同主旨
			formValuesEntity.setFd_main(entity.getContractName());
			//申请用公章全称
			R<List<DictBiz>> application_seal = bizClient.getList("application_seal");
			List<DictBiz> dataSeal = application_seal.getData();
			for (DictBiz dictBiz : dataSeal) {
				if ((dictBiz.getDictValue()).equals(entity.getSealName())) {
					formValuesEntity.setFd_offical_seal(dictBiz.getDictKey());
				}
			}
			/********************     相对方信息START   ***********************************/
			//相对方名称
			formValuesEntity.setFd_full_name(entity.getCounterpart().get(0).getName());
			if (Func.isNotEmpty(entity.getBankCode())) {
				formValuesEntity.setFd_bank_code(entity.getBankCode());
			}
			if (Func.isNotEmpty(entity.getBankAccountCode())) {
				formValuesEntity.setFd_account_code(entity.getBankAccountCode());
			}
			if (Func.isNotEmpty(entity.getBankAccountName())) {
				formValuesEntity.setFd_account_name(entity.getBankAccountName());
			}
			if (Func.isNotEmpty(entity.getBankDepositName())) {
				//相对方开户行名称
				formValuesEntity.setFd_account_name(entity.getBankDepositName());
			}
			//相对方联系人
			formValuesEntity.setFd_linkman(entity.getCounterpartPerson());
			//相对方联系电话
			formValuesEntity.setFd_contact_number(entity.getTelephonePerson());
			//相对方邮箱
			formValuesEntity.setFd_email(entity.getEmailPerson());
			//相对方联系地址
			formValuesEntity.setFd_address(entity.getAddressPerson());
			/********************     相对方信息END   ***********************************/
			//合同负责人
			formValuesEntity.setFd_emplno(entity.getPersonCodeContract());
			//合同份数
			formValuesEntity.setFd_copies(entity.getShare());
			//合同期限
			switch (entity.getContractPeriod()) {
				case "1095":
					formValuesEntity.setFd_contract_period("1");
					break;
				case "1460":
					formValuesEntity.setFd_contract_period("2");
					break;
				case "365000":
					formValuesEntity.setFd_contract_period("3");
					break;
				default:
			}
			//合同时间起
			formValuesEntity.setFd_start_time(sdf.format(entity.getStartingTime()));
			//合同时间止
			formValuesEntity.setFd_lasttime(sdf.format(entity.getEndTime()));
			/***************************************   收付款START    ******************************************/
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
			//收款明细列表
			List<CollectionInde> indeList = new ArrayList<>();
			if (Func.isNotEmpty(indeList)) {
				entity.getCollection().forEach(conI -> {
					CollectionInde collectionInde = new CollectionInde();
					//总期数
					collectionInde.setFd_period_num(conI.getPeriodNum());
					//第几期
					collectionInde.setFd_period_idx(conI.getPeriodIdx());
					//条件+帐期天数
					collectionInde.setFd_pay_condition(conI.getPayCondition());
					collectionInde.setFd_pay_days_inde(conI.getDays().toString());
					//是否开票
					collectionInde.setFd_is_receipt(conI.getIsReceipt());
					//比例
					collectionInde.setFd_per(conI.getPayPer().toString());
					collectionInde.setFd_pay_amount(conI.getPayAmount().toString());
					indeList.add(collectionInde);
				});
				formValuesEntity.setFd_collection_inde(indeList);
			}
			if (!Func.isEmpty(entity.getDays())) {
				//签约后收款  需要填写
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
			/**************************************   收付款END    *********************************************/
			//币种
			R<List<DictBiz>> contract_bz = bizClient.getList("bz");
			List<DictBiz> databz = contract_bz.getData();
			for (DictBiz dictBiz : databz) {
				if ((dictBiz.getDictKey()).equals(entity.getCurrencyCategory())) {
					formValuesEntity.setFd_currency(dictBiz.getDictValue());
				}
			}
			//是否延期条款
			if ("0".equals(entity.getExtension())) {
				formValuesEntity.setFd_automatic("1");
			} else {
				formValuesEntity.setFd_automatic("2");
			}
			if (entity.getContractBond().size() > 0) {
				if (!Func.isEmpty(entity.getContractBond().get(0).getPlanPayAmount())) {
					formValuesEntity.setFd_cash(entity.getContractBond().get(0).getPlanPayAmount().toString());
				}
				//保证金类别
				formValuesEntity.setFd_deposit_type(entity.getContractBond().get(0).getType());
				//保证金编号
				formValuesEntity.setFd_number(entity.getContractBond().get(0).getId().toString());
				//有无押金
				if ("0".equals(entity.getContractBond().get(0).getIsNotBond())) {
					formValuesEntity.setFd_cash_pledge("1");
				} else if ("1".equals(entity.getContractBond().get(0).getIsNotBond())) {
					formValuesEntity.setFd_cash_pledge("2");
					//押金
					formValuesEntity.setFd_cash("");
				} else {
					formValuesEntity.setFd_cash_pledge("3");
				}
				if (!Func.isEmpty(entity.getContractBond().get(0).getPlanPayTime())) {
					//缴交时间
					formValuesEntity.setFd_pay_time(sdf.format(entity.getContractBond().get(0).getPlanPayTime()));
				}
				if (!Func.isEmpty(entity.getContractBond().get(0).getPlanReturnTime())) {
					//退回时间
					formValuesEntity.setFd_back_time(sdf.format(entity.getContractBond().get(0).getPlanReturnTime()));
				}
			}
			pushEkpEntity.setDocSubject(entity.getContractName());
			pushEkpEntity.setFdTemplateId(fdTemplateId);
			pushEkpEntity.setFormValues(formValuesEntity);
			try {
				if (Func.isNotEmpty(entity.getAttachedFiles())) {
					pushEkpEntity.setFd_attachment(attachedFile(entity).getData());
					if (Func.isEmpty(pushEkpEntity.getFd_attachment())) {
						rEkpVo.setCode(3);
						rEkpVo.setMsg("依据附件上传失败，请检查FDS文件服务器配置是否正常");
						rEkpVo.setSuccess(false);
						rEkpVo.setData(null);
						return rEkpVo;
					}
				}
				pushEkpEntity.setToken(ekpService.getToken());
				log.info("获取ekp的token：" + pushEkpEntity.getToken());
				if (StrUtil.isEmpty(pushEkpEntity.getToken())) {
					rEkpVo.setCode(1);
					rEkpVo.setMsg("token获取失败，连接超时");
					rEkpVo.setSuccess(false);
					rEkpVo.setData(null);
					return rEkpVo;
				}
				logger.info("inde-pushData", JsonUtil.toJson(pushEkpEntity));
				ekpVo = ekpService.pushData(pushEkpEntity);
				log.info("获取ekpVo的内容：" + ekpVo.getDoc_info());
				if (StrUtil.isEmpty(ekpVo.getDoc_info())) {
					rEkpVo.setCode(2);
					rEkpVo.setMsg("当前员工编号在系统中不存在，请换一个试试");
					rEkpVo.setSuccess(false);
					rEkpVo.setData(null);
					return rEkpVo;
				}
				logger.info("sendEkpFormPost-return", JsonUtil.toJson(rEkpVo));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		rEkpVo.setData(ekpVo);
		log.info("HttpStatus.OK.value()" + HttpStatus.OK.value());
		rEkpVo.setCode(HttpStatus.OK.value());
		rEkpVo.setSuccess(true);
		return rEkpVo;
	}

	/**
	 * 合同起草-多方 推送EKP信息
	 */
	@SneakyThrows
	@Override
	@PostMapping(EKP_SEND_MULTI_POST)
	@ApiLog("合同起草-多方 推送EKP信息")
	public R<EkpVo> sendEkpMultiPost(@RequestBody ContractFormInfoEntity entity) {
		R<EkpVo> rEkpVo = new R<>();
		EkpVo ekpVo = null;
		PushEkpEntity pushEkpEntity = new PushEkpEntity();
		pushEkpEntity.setFdTemplateId(fdTemplateId);
		if (StrUtil.isNotEmpty(entity.getPersonCodeContract()) && StrUtil.isNotEmpty(entity.getAccording().get(0).getFileId())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DocCreatorEntity docCreatorEntity = new DocCreatorEntity();
			//人员编号
			docCreatorEntity.setEmplno(entity.getPersonCodeContract());
			pushEkpEntity.setDocCreator(docCreatorEntity);
			FormValuesEntity formValuesEntity = new FormValuesEntity();
			//合同方对应关系   多方的角色值在ContractFormIfoController 保存多方向对方身份信息 那段代码里处理的
			if ("甲".equals(entity.getContractRoles())) {
				formValuesEntity.setFd_onetoone("1");
			} else if ("乙".equals(entity.getContractRoles())) {
				formValuesEntity.setFd_onetoone("2");
			} else if ("丙".equals(entity.getContractRoles())) {
				formValuesEntity.setFd_onetoone("3");
			} else if ("丁".equals(entity.getContractRoles())) {
				formValuesEntity.setFd_onetoone("4");
			} else {
				formValuesEntity.setFd_onetoone("5");
			}
			//依据文档id    唯一
			formValuesEntity.setFd_accord_id(entity.getAccording().get(0).getFileId());
			//合同id       唯一
			formValuesEntity.setFd_contract_id(entity.getId().toString());
			//pdf的id      唯一【实体合同-我司不用电子印，电子合同-对方平台    这两种方式，可以上传多份 】
			formValuesEntity.setFd_attachment_id(entity.getTextFilePdf());
			//合同查看链接
			formValuesEntity.setFd_contract_url("");
			//合同文件名称
			List<FileVO> fileText = fileClient.getByIds(entity.getTextFile()).getData();
			if (fileText.size() > 0) {
				if ("3".equals(entity.getContractForm()) || "4".equals(entity.getContractForm())) {
					StringBuilder name = new StringBuilder();
					fileText.forEach(f -> {
						name.append(f.getName());
						name.append(",");
					});
					name.substring(0, name.length());
					formValuesEntity.setFd_contract_name(name.toString());
				} else {
					formValuesEntity.setFd_contract_name(fileText.get(0).getName());
				}
			}
			//合同起草类型  可能涉及变更暂时做判断
			if ("20".equals(entity.getContractSoure())) {
				formValuesEntity.setFd_contract_type("40");
			}
			//合同主旨
			formValuesEntity.setFd_main(entity.getContractName());
			//合同大类
			R<List<DictBiz>> contract_HTDL = bizClient.getList("HTDL");
			List<DictBiz> dataBiz = contract_HTDL.getData();
			for (DictBiz dictBiz : dataBiz) {
				if ((dictBiz.getId().toString()).equals(entity.getContractBigCategory())) {
					formValuesEntity.setFd_broad(dictBiz.getDictKey());
				}
			}
			//合同中类
			formValuesEntity.setFd_secondary(entity.getContractListName());
			//合同小类
			R<List<DictBiz>> contract_HTXL = bizClient.getList("HTXL");
			List<DictBiz> dataHTXLBiz = contract_HTXL.getData();
			for (DictBiz dictBiz : dataHTXLBiz) {
				if ((dictBiz.getId().toString()).equals(entity.getContractSmallCategory())) {
					formValuesEntity.setFd_small(dictBiz.getDictKey());
				}
			}
			//申请用公章全称  多方该字段由前端赋值form.sealName 签章申请单位的单位编号
			formValuesEntity.setFd_offical_seal(entity.getSealName());
			//合同负责人
			formValuesEntity.setFd_emplno(entity.getPersonCodeContract());
			//合同份数
			formValuesEntity.setFd_copies(entity.getShare());
			//合同期限
			switch (entity.getContractPeriod()) {
				case "1095":
					formValuesEntity.setFd_contract_period("1");
					break;
				case "1460":
					formValuesEntity.setFd_contract_period("2");
					break;
				case "365000":
					formValuesEntity.setFd_contract_period("3");
					break;
				default:
			}
			//合同时间起
			formValuesEntity.setFd_start_time(sdf.format(entity.getStartingTime()));
			//合同时间止
			formValuesEntity.setFd_lasttime(sdf.format(entity.getEndTime()));
			//币种
			R<List<DictBiz>> contract_bz = bizClient.getList("bz");
			List<DictBiz> databz = contract_bz.getData();
			for (DictBiz dictBiz : databz) {
				if ((dictBiz.getDictKey()).equals(entity.getCurrencyCategory())) {
					formValuesEntity.setFd_currency(dictBiz.getDictValue());
				}
			}
			//是否延期条款
			if ("0".equals(entity.getExtension())) {
				formValuesEntity.setFd_automatic("1");
			} else {
				formValuesEntity.setFd_automatic("2");
			}
			//合同形式
			formValuesEntity.setFd_contract_no(entity.getContractForm());
			//子公司以及签章单位信息（关联表）
			List<MultiSa> sas = new ArrayList<>();
			entity.getContractSeal().forEach(c -> {
				MultiSa sa = new MultiSa();
				//子公司名称
				sa.setFd_seal_factName(c.getFdFactname());
				//单位编号
				sa.setFd_role_taxNo(c.getFdTaxno());
				//企业编号
				sa.setFd_seal_factNo(c.getFdFactno());
				sas.add(sa);
			});
			formValuesEntity.setFd_multise(sas);
			//相对方企业信息（关联表）
			List<MultiCo> cos = new ArrayList<>();
			entity.getCounterpart().forEach(c -> {
				MultiCo co = new MultiCo();
				//相对方名称
				co.setFd_opposite_name(c.getName());
				//乙方税籍编号(必填)
				co.setFd_opposite_taxno(c.getUnifiedSocialCreditCode());
				cos.add(co);
			});
			formValuesEntity.setFd_multico(cos);
			//多方身份信息列表
			List<MultiRo> ros = new ArrayList<>();
			entity.getDraftContractCounterpartList().forEach(r -> {
				MultiRo ro = new MultiRo();
				//子公司名称
				ro.setFd_role_real(r.getSubsidiaryPerson());
				//相对方身份
				ro.setFd_role_identity(r.getCounterpartIdentity());
				//相对方联系人
				ro.setFd_role_name(r.getCounterpartPerson());
				//相对方联系电话
				ro.setFd_role_number(entity.getTelephonePerson());
				//相对方邮箱
				ro.setFd_role_email(r.getEmailPerson());
				//相对方联系地址
				ro.setFd_role_email(r.getAddressPerson());
				if (Func.isNotEmpty(r.getBankCode())) {
					ro.setFd_role_bank_code(r.getBankCode());
				}
				if (Func.isNotEmpty(r.getBankAccountCode())) {
					ro.setFd_role_account_code(r.getBankAccountCode());
				}
				if (Func.isNotEmpty(r.getBankAccountName())) {
					ro.setFd_role_account_name(r.getBankAccountName());
				}
				if (Func.isNotEmpty(r.getBankDepositName())) {
					//相对方开户行名称
					ro.setFd_role_deposit_name(r.getBankDepositName());
				}
				ros.add(ro);
			});
			formValuesEntity.setFd_multiro(ros);
			/******************************  收付款START  ***************************************************/
			//多方收付款信息
			List<MultiPay> pays = new ArrayList<>();
			entity.getMultPaymenEntityList().forEach(p -> {
				MultiPay pay = new MultiPay();
				//子公司名称
				pay.setFd_pay_seal(p.getSubsidiaryPerson());
				//相对方名称
				pay.setFd_pay_name(p.getCounterpartName());
				//收付款
				switch (p.getColPayType()) {
					case "1323239541401841666":
						pay.setFd_pay_type("1");
						break;
					case "1323239469884764161":
						pay.setFd_pay_type("2");
						break;
					case "1323239716493062146":
						pay.setFd_pay_type("3");
						break;
					default:
				}
				//收付款条件
				switch (entity.getColPayTerm()) {
					case "1323242418597916674":
						pay.setFd_receive_condition("1");
						break;
					case "1323243129146568706":
						pay.setFd_receive_condition("2");
						break;
					case "1323243216736219137":
						pay.setFd_receive_condition("3");
						break;
					case "1323243330431217665":
						pay.setFd_receive_condition("4");
						break;
					case "1323243409321881601":
						pay.setFd_receive_condition("5");
						break;
					case "1323242740267479042":
						pay.setFd_receive_condition("6");
						break;
					case "1323242875978379265":
						pay.setFd_receive_condition("7");
						break;
					case "1323240326596521986":
						pay.setFd_pay_condition("1");
						break;
					case "1323240755069841410":
						pay.setFd_pay_condition("2");
						break;
					case "1323241507062411265":
						pay.setFd_pay_condition("3");
						break;
					default:
				}
				//收款明细列表
				List<CollectionMulti> multiList = new ArrayList<>();
				p.getCollection().forEach(pI -> {
					CollectionMulti collectionInde = new CollectionMulti();
					//总期数
					collectionInde.setFd_period_num_multi(pI.getPeriodNum());
					//第几期
					collectionInde.setFd_period_idx_multi(pI.getPeriodIdx());
					//条件+帐期天数
					collectionInde.setFd_payee_condition_multi(pI.getPayCondition());
					collectionInde.setFd_pay_days_multi(pI.getDays().toString());
					//是否开票
					collectionInde.setFd_is_receipt_multi(pI.getIsReceipt());
					//比例
					collectionInde.setFd_per_multi(pI.getPayPer().toString());
					collectionInde.setFd_pay_amount_multi(pI.getPayAmount().toString());
					multiList.add(collectionInde);
				});
				pay.setFd_collection_multi(multiList);
				if (Func.isNotEmpty(p.getDays())) {
					//合同未税金额
					pay.setFd_pay_days(p.getDays().toString());
				}
				if (Func.isNotEmpty(p.getContractAmount())) {
					//合同未税金额
					pay.setFd_pay_untaxed(p.getContractAmount().toString());
				}
				if (Func.isNotEmpty(p.getContractTaxRate())) {
					//税率
					pay.setFd_pay_rate(p.getContractTaxRate().toString());
				}
				if (Func.isNotEmpty(p.getContractTaxAmount())) {
					//含税金额
					pay.setFd_pay_include(p.getContractTaxAmount().toString());
				}
				pays.add(pay);
			});
			formValuesEntity.setFd_multipay(pays);
			/******************************  收付款END   ***************************************************/
			//多方保证金信息
			List<MultiBon> bons = new ArrayList<>();
			entity.getContractBond().forEach(b -> {
				MultiBon bon = new MultiBon();
				//子公司名称
				bon.setFd_bon_seal(b.getSubsidiaryPerson());
				//相对方名称
				bon.setFd_bon_name(b.getCounterpartName());
				//计划缴纳金额
				if (Func.isNotEmpty(b.getPlanPayAmount())) {
					bon.setFd_bon_cash(b.getPlanPayAmount().toString());
				}
				//保证金类别
				bon.setFd_bon_type(b.getType());
				//保证金编号
				bon.setFd_bon_id(b.getId().toString());
				//有无押金
				if ("0".equals(b.getIsNotBond())) {
					bon.setFd_bon_pledge("1");
				} else if ("1".equals(b.getIsNotBond())) {
					bon.setFd_bon_pledge("2");
					//押金
					bon.setFd_bon_cash("");
				} else {
					bon.setFd_bon_pledge("3");
				}
				if (Func.isNotEmpty(b.getPlanPayTime())) {
					//缴交时间
					bon.setFd_bon_paytime(sdf.format(b.getPlanPayTime()));
				}
				if (Func.isNotEmpty(b.getPlanReturnTime())) {
					//退回时间
					bon.setFd_bon_backtime(sdf.format(b.getPlanReturnTime()));
				}
				bons.add(bon);
			});
			formValuesEntity.setFd_multibon(bons);
			//履约信息
			List<KeepList> keepList = new ArrayList<KeepList>();
			List<PayList> payList = new ArrayList<PayList>();
			for (ContractPerformanceEntity performance : entity.getPerformanceList()) {
				KeepList keep = new KeepList();
				//交易类型
				switch (performance.getType()) {
					case "1":
						keep.setFd_trade_type("有效期交易");
						break;
					case "2":
						keep.setFd_trade_type("按合同量交易");
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
			for (ContractPerformanceColPayEntity performanceColPay : entity.getPerformanceColPayList()) {
				PayList pay = new PayList();
				//交易类型
				switch (performanceColPay.getType()) {
					case "1":
						pay.setFd_trade_kind("有效期交易");
						break;
					case "2":
						pay.setFd_trade_kind("按合同量交易");
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
				pay.setFd_plan_psum(performanceColPay.getPlanPayAmount().toString());
				payList.add(pay);
			}
			//履约，服务
			formValuesEntity.setFd_keep_list(keepList);
			formValuesEntity.setFd_pay_list(payList);
			//处理签章关键字 和多页合同  只有电子合同-我司用印   实体合同-我司用电子印  需要处理和传递keyword字段
			if ("1".equals(entity.getContractForm()) || "2".equals(entity.getContractForm())) {
				formValuesEntity = fileTextMulti(formValuesEntity, entity);
			}
			pushEkpEntity.setDocSubject(entity.getContractName());
			pushEkpEntity.setFdTemplateId(fdTemplateId);
			pushEkpEntity.setFormValues(formValuesEntity);
			try {
				//处理合同补充依据
				if (Func.isNotEmpty(entity.getAttachedFiles())) {
					pushEkpEntity.setFd_attachment(attachedFile(entity).getData());
					if (Func.isEmpty(pushEkpEntity.getFd_attachment())) {
						rEkpVo.setCode(3);
						rEkpVo.setMsg("依据附件上传失败，请检查FDS文件服务器配置是否正常");
						rEkpVo.setSuccess(false);
						rEkpVo.setData(null);
						return rEkpVo;
					}
				}
				pushEkpEntity.setToken(ekpService.getToken());
				log.info("获取ekp的token：" + pushEkpEntity.getToken());
				if (StrUtil.isEmpty(pushEkpEntity.getToken())) {
					rEkpVo.setCode(1);
					rEkpVo.setMsg("token获取失败，连接超时");
					rEkpVo.setSuccess(false);
					rEkpVo.setData(null);
					return rEkpVo;
				}

				logger.info("multi-pushData", JsonUtil.toJson(pushEkpEntity));
				ekpVo = ekpService.pushData(pushEkpEntity);
				log.info("获取ekpVo的内容：" + ekpVo.getDoc_info());
				if (StrUtil.isEmpty(ekpVo.getDoc_info())) {
					rEkpVo.setCode(2);
					rEkpVo.setMsg("当前员工编号在系统中不存在，请换一个试试");
					rEkpVo.setSuccess(false);
					rEkpVo.setData(null);
					return rEkpVo;
				}
				logger.info("sendEkpMultiPost-return", JsonUtil.toJson(rEkpVo));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		rEkpVo.setData(ekpVo);
		log.info("HttpStatus.OK.value()" + HttpStatus.OK.value());
		rEkpVo.setCode(HttpStatus.OK.value());
		rEkpVo.setSuccess(true);
		return rEkpVo;
	}

	/**
	 * 推送EKP节点信息
	 */
	@SneakyThrows
	@Override
	@PostMapping(EKP_NODE_FORM_POST)
	@ApiLog("推送EKP节点信息")
	public R<EkpVo> nodeEkpFormPost(@RequestBody ContractFormInfoResponseVO entity) {
		R<EkpVo> rEkpVo = new R<>();
		EkpVo ekpVo = null;
		//KEP接口传输的入参
		PushEkpEntity pushEkpEntity = new PushEkpEntity();
		pushEkpEntity.setFdTemplateId(fdTemplateId);
		if (StrUtil.isNotEmpty(entity.getPersonCodeContract())) {
			DocCreatorEntity docCreatorEntity = new DocCreatorEntity();
			//人员编号
			R<User> user = userClient.userInfoById(AuthUtil.getUserId());
			docCreatorEntity.setEmplno(Func.isEmpty(entity.getPersonCodeContract()) ? user.getData().getCode() : entity.getPersonCodeContract());
			pushEkpEntity.setDocCreator(docCreatorEntity);
			//===数据实体===
			FormValuesEntity formValuesEntity = new FormValuesEntity();
			//1.合同状态
			formValuesEntity.setFd_contract_status(entity.getContractStatus());
			//2.合同主旨
			pushEkpEntity.setDocSubject(entity.getContractName());
			//3.表单模版ID
			pushEkpEntity.setFdTemplateId(fdTemplateId);
			//4.不同条件参数(归档所需的附件--上传到哪需要对接)
			if ("60".equals(entity.getContractStatus())) {
				//4-1.合同归档的合同文本扫面件附件ID
				formValuesEntity.setFd_archive_file(entity.getSigningEntity().getAttachedFiles());
				//已归档处理合同文本扫面件
				if (Func.isNotEmpty(entity.getAttachedFiles())) {
					pushEkpEntity.setFd_attachment(attachedFile(entity).getData());
				}
			}
			//获取ekp的token
			pushEkpEntity.setToken(ekpService.getToken());
			log.info("获取ekp的token：" + pushEkpEntity.getToken());
			if (StrUtil.isEmpty(pushEkpEntity.getToken())) {
				rEkpVo.setCode(1);
				rEkpVo.setMsg("token获取失败，连接超时");
				rEkpVo.setSuccess(false);
				rEkpVo.setData(null);
				return rEkpVo;
			}
			//推送数据
			ekpVo = ekpService.pushData(pushEkpEntity);
			log.info("获取ekpVo的内容：" + ekpVo.getDoc_info());
			if (StrUtil.isEmpty(ekpVo.getDoc_info())) {
				rEkpVo.setCode(2);
				rEkpVo.setMsg("当前员工编号在系统中不存在，请换一个试试");
				rEkpVo.setSuccess(false);
				rEkpVo.setData(null);
				return rEkpVo;
			}
		}
		rEkpVo.setData(ekpVo);
		log.info("HttpStatus.OK.value()" + HttpStatus.OK.value());
		rEkpVo.setCode(HttpStatus.OK.value());
		rEkpVo.setSuccess(true);
		return rEkpVo;
	}

	/*
	 * 未归档预警
	 */
	@SneakyThrows
	@Override
	@PostMapping(EKP_SIG_FORM_POST)
	@ApiLog("需要推送预警的数据")
	public R<List<EkpVo>> pushNotSig(ContractFormInfoEntity entity) {
		List<EkpVo> ekpVo = new ArrayList<>();
		HashMap<String, String> mapVo = new HashMap<>();
		//KEP接口传输的入参
		PushEkpEntity pushEkpEntity = new PushEkpEntity();
		List<ContractFormInfoEntity> formInfoEntities = new ArrayList<>();
		if ("0".equals(entity.getFilePerson())) {
			//第一次预警时机：合同用印审批流通过后即发出代办于申请人系统
			formInfoEntities.add(entity);
		} else {
			formInfoEntities = contractClient.getByStatus(this.status).getData();
			if (Func.isNull(formInfoEntities) || Func.isEmpty(formInfoEntities)) {
				return R.data(200, ekpVo, "暂无数据推送");
			}
			formInfoEntities.forEach(l -> {
				int day = differentDaysByMillisecond(l.getCreateTime(), new Date());
				mapVo.put("day", String.valueOf(day));
				mapVo.put("contractName", l.getContractName() + "---（归档预警：请及时归档）");
			});
			log.info("需要推送的符合条件的数据：" + JsonUtil.toJson(formInfoEntities));
		}
		//获取TOKEN
		pushEkpEntity.setToken(ekpService.getToken());
		if (StrUtil.isEmpty(pushEkpEntity.getToken())) {
			return R.data(404, null, "获取token失败");
		}
		log.info("获取一次token信息：" + JsonUtil.toJson(pushEkpEntity.getToken()));
		formInfoEntities.forEach(f -> {
			EkpVo ekp = null;
			try {
				//docCreator 待办接收人员工编号
				pushEkpEntity.setEmplno(f.getPersonCodeContract());
				//系统类型，请传递固定字符串c_p_notify
				pushEkpEntity.setSystemName(this.systemName);
				//docSubject 合同主旨
				//notifyType：待办类型：1待办，2待阅；如第一和第二预警传2(待阅)，第三次传1(待办)；只接收这两种参数
				if ("0".equals(f.getFilePerson())) {
					pushEkpEntity.setDocSubject("《" + f.getContractName() + "》合同，合同编号为" + f.getContractNumber() + this.firstWarning);
					pushEkpEntity.setNotifyType("2");
				} else if ("15".equals(f.getFilePerson())) {
					pushEkpEntity.setDocSubject("《" + f.getContractName() + "》合同，合同编号为" + f.getContractNumber() + this.secondWarning);
					pushEkpEntity.setNotifyType("2");
				} else if ("45".equals(f.getFilePerson())) {
					pushEkpEntity.setDocSubject("对《" + f.getContractName() + "》合同，合同编号为" + f.getContractNumber() + this.thirdWarning);
					pushEkpEntity.setNotifyType("1");
				} else {
					pushEkpEntity.setDocSubject("《" + f.getContractName() + "》" + this.estimateWarning);
					pushEkpEntity.setNotifyType("1");
				}
				//合同编号
				pushEkpEntity.setContractNo(f.getId().toString());
				//推送数据
				ekp = ekpService.pushAgency(pushEkpEntity);
				if (Func.isNotEmpty(ekp.getNotifyId())) {
					ekpVo.add(ekp);
				}
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		logger.info("contractFormList", JsonUtil.toJson(formInfoEntities));
		logger.info("pushEkpContractFormList", JsonUtil.toJson(ekpVo));
		log.info("推送完成返回的依据信息：" + JsonUtil.toJson(ekpVo));
		return R.data(200, ekpVo, "数据推送成功");
	}

	/**
	 * 通过时间秒毫秒数判断两个时间的间隔
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public int differentDaysByMillisecond(Date date1, Date date2) {
		return (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
	}

	/**
	 * 时间格式转换方法
	 *
	 * @return
	 */
	private static String getDf() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(new Date());
	}

	public FormValuesEntity fileText(FormValuesEntity formValuesEntity, ContractFormInfoEntity entity) {
		File filePDF = new File(ftlPath + entity.getContractName() + getDf() + ".pdf");
		//建立输出字节流
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filePDF);
			fos.write(getUrlFileData(entity.getOtherInformation()));
			fos.close();
			InputStream inputStream = new FileInputStream(filePDF);
			PDFParser parser = new PDFParser(inputStream);
			parser.parse();
			PDDocument document = parser.getPDDocument();
			int pageCount = document.getNumberOfPages();
			PDFTextStripper stripper = new PDFTextStripper();
			String text = stripper.getText(document);
			formValuesEntity.setFd_multipage(pageCount > 1 ? "y" : "n");
			int az = text.indexOf("甲方（签章）");
			int bz = text.indexOf("乙方（签章）");
			int ay = text.indexOf("甲方(签章)");
			int by = text.indexOf("乙方(签章)");
			JSONObject s = new JSONObject();
			String[] arrays;
			if ((-1 != az && -1 != bz)) {
				if ("1".equals(formValuesEntity.getFd_onetoone())) {
					arrays = new String[]{"乙", "丙", "丁"};
					s.put("统一集团", "甲方（签章）");
				} else {
					arrays = new String[]{"甲", "丙", "丁"};
					s.put("统一集团", "乙方（签章）");
				}
				for (int i = 0; i < entity.getCounterpart().size(); i++) {
					s.put(entity.getCounterpart().get(i).getUnifiedSocialCreditCode(), arrays[i] + "方（签章）");
				}
				formValuesEntity.setFd_keyword(s);
			} else if ((-1 != ay && -1 != by)) {
				if ("1".equals(formValuesEntity.getFd_onetoone())) {
					arrays = new String[]{"乙", "丙", "丁"};
					s.put("统一集团", "甲方(签章)");
				} else {
					arrays = new String[]{"甲", "丙", "丁"};
					s.put("统一集团", "乙方(签章)");
				}
				for (int i = 0; i < entity.getCounterpart().size(); i++) {
					s.put(entity.getCounterpart().get(i).getUnifiedSocialCreditCode(), arrays[i] + "方(签章)");
				}
				formValuesEntity.setFd_keyword(s);
			}
			document.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return formValuesEntity;
	}

	/**
	 * 多方签章关键字处理
	 *
	 * @param formValuesEntity formValuesEntity
	 * @param entity           entity
	 * @return FormMultiEntity
	 */
	public FormValuesEntity fileTextMulti(FormValuesEntity formValuesEntity, ContractFormInfoEntity entity) {
		File filePDF = new File(ftlPath + entity.getContractName() + getDf() + ".pdf");
		//建立输出字节流
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filePDF);
			fos.write(getUrlFileData(entity.getOtherInformation()));
			fos.close();
			InputStream inputStream = new FileInputStream(filePDF);
			PDFParser parser = new PDFParser(inputStream);
			parser.parse();
			PDDocument document = parser.getPDDocument();
			int pageCount = document.getNumberOfPages();
			PDFTextStripper stripper = new PDFTextStripper();
			String text = stripper.getText(document);
			formValuesEntity.setFd_multipage(pageCount > 1 ? "y" : "n");
			int az = text.indexOf("甲方（签章）");
			int bz = text.indexOf("乙方（签章）");
			int cz = text.indexOf("丙方（签章）");
			int dz = text.indexOf("丁方（签章）");
			int ay = text.indexOf("甲方(签章)");
			int by = text.indexOf("乙方(签章)");
			int cy = text.indexOf("丙方(签章)");
			int dy = text.indexOf("丁方(签章)");
			JSONObject s = new JSONObject();
			String[] arrays;
			//（全角） 半角判断--三方
			boolean threeFull = (-1 != az) && (-1 != bz) && (-1 != cz);
			//全角 （半角）判断---三方
			boolean threeHalf = (-1 != ay) && (-1 != by) && (-1 != cy);
			//（全角） 半角判断--四方
			boolean fourFull = (-1 != az) && (-1 != bz) && (-1 != cz) && (-1 != dz);
			//全角 （半角）判断--四方
			boolean fourHalf = (-1 != ay) && (-1 != by) && (-1 != cy) && (-1 != dy);
			//给统一集团设置-角色
			if (formValuesEntity.getFd_multico().size() <= 3) {
				if ((threeFull)) {
					if ("1".equals(formValuesEntity.getFd_onetoone())) {
						s.put("统一集团", "甲方（签章）");
					} else if ("2".equals(formValuesEntity.getFd_onetoone())) {
						s.put("统一集团", "乙方（签章）");
					} else if ("3".equals(formValuesEntity.getFd_onetoone())) {
						s.put("统一集团", "丙方（签章）");
					} else {
						s.put("统一集团", "丁方（签章）");
					}
					//给向对方设置角色
					for (DraftContractCounterpartEntity dr : entity.getDraftContractCounterpartList()) {
						for (ContractCounterpartEntity ce : entity.getCounterpart()) {
							if (dr.getCounterpartId().equals(String.valueOf(ce.getId()))) {
								s.put(ce.getUnifiedSocialCreditCode(), dr.getCounterpartIdentity() + "方（签章）");
							}
						}
					}
					//给向对方设置角色
					for (DraftContractCounterpartEntity dr : entity.getDraftContractCounterpartList()) {
						for (ContractSealEntity cs : entity.getContractSeal()) {
							if (dr.getCounterpartId().equals(String.valueOf(cs.getId()))) {
								s.put(cs.getFdTaxno(), dr.getCounterpartIdentity() + "方（签章）");
								if (entity.getSealName().equals(cs.getFdTaxno())) {
									s.remove(cs.getFdTaxno());
								}
							}
						}
					}

					formValuesEntity.setFd_keyword(s);
				} else if ((threeHalf)) {
					if ("1".equals(formValuesEntity.getFd_onetoone())) {
						s.put("统一集团", "甲方(签章)");
					} else if ("2".equals(formValuesEntity.getFd_onetoone())) {
						s.put("统一集团", "乙方(签章)");
					} else if ("3".equals(formValuesEntity.getFd_onetoone())) {
						s.put("统一集团", "丙方(签章)");
					} else {
						s.put("统一集团", "丁方(签章)");
					}
					//给向对方设置角色
					for (DraftContractCounterpartEntity dr : entity.getDraftContractCounterpartList()) {
						for (ContractCounterpartEntity ce : entity.getCounterpart()) {
							if (dr.getCounterpartId().equals(String.valueOf(ce.getId()))) {
								s.put(ce.getUnifiedSocialCreditCode(), dr.getCounterpartIdentity() + "方（签章）");
							}
						}
					}
					//给向对方设置角色
					for (DraftContractCounterpartEntity dr : entity.getDraftContractCounterpartList()) {
						for (ContractSealEntity cs : entity.getContractSeal()) {
							if (dr.getCounterpartId().equals(String.valueOf(cs.getId()))) {
								s.put(cs.getFdTaxno(), dr.getCounterpartIdentity() + "方（签章）");
								if (entity.getSealName().equals(cs.getFdTaxno())) {
									s.remove(cs.getFdTaxno());
								}
							}
						}
					}
					formValuesEntity.setFd_keyword(s);
				}
			} else {
				if ((fourFull)) {
					if ("1".equals(formValuesEntity.getFd_onetoone())) {
						s.put("统一集团", "甲方（签章）");
					} else if ("2".equals(formValuesEntity.getFd_onetoone())) {
						s.put("统一集团", "乙方（签章）");
					} else if ("3".equals(formValuesEntity.getFd_onetoone())) {
						s.put("统一集团", "丙方（签章）");
					} else {
						s.put("统一集团", "丁方（签章）");
					}
					//给向对方设置角色
					for (DraftContractCounterpartEntity dr : entity.getDraftContractCounterpartList()) {
						for (ContractCounterpartEntity ce : entity.getCounterpart()) {
							if (dr.getCounterpartId().equals(String.valueOf(ce.getId()))) {
								s.put(ce.getUnifiedSocialCreditCode(), dr.getCounterpartIdentity() + "方（签章）");
							}
						}
					}
					//给向对方设置角色
					for (DraftContractCounterpartEntity dr : entity.getDraftContractCounterpartList()) {
						for (ContractSealEntity cs : entity.getContractSeal()) {
							if (dr.getCounterpartId().equals(String.valueOf(cs.getId()))) {
								s.put(cs.getFdTaxno(), dr.getCounterpartIdentity() + "方（签章）");
								if (entity.getSealName().equals(cs.getFdTaxno())) {
									s.remove(cs.getFdTaxno());
								}
							}
						}
					}
					formValuesEntity.setFd_keyword(s);
				} else if ((fourHalf)) {
					if ("1".equals(formValuesEntity.getFd_onetoone())) {
						s.put("统一集团", "甲方(签章)");
					} else if ("2".equals(formValuesEntity.getFd_onetoone())) {
						s.put("统一集团", "乙方(签章)");
					} else if ("3".equals(formValuesEntity.getFd_onetoone())) {
						s.put("统一集团", "丙方(签章)");
					} else {
						s.put("统一集团", "丁方(签章)");
					}
					//给向对方设置角色
					for (DraftContractCounterpartEntity dr : entity.getDraftContractCounterpartList()) {
						for (ContractCounterpartEntity ce : entity.getCounterpart()) {
							if (dr.getCounterpartId().equals(String.valueOf(ce.getId()))) {
								s.put(ce.getUnifiedSocialCreditCode(), dr.getCounterpartIdentity() + "方（签章）");
							}
						}
					}
					//给向对方设置角色
					for (DraftContractCounterpartEntity dr : entity.getDraftContractCounterpartList()) {
						for (ContractSealEntity cs : entity.getContractSeal()) {
							if (dr.getCounterpartId().equals(String.valueOf(cs.getId()))) {
								s.put(cs.getFdTaxno(), dr.getCounterpartIdentity() + "方（签章）");
								if (entity.getSealName().equals(cs.getFdTaxno())) {
									s.remove(cs.getFdTaxno());
								}
							}
						}
					}
					formValuesEntity.setFd_keyword(s);
				}
			}
			document.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return formValuesEntity;
	}

	public static String deleteCharString0(String sourceString, char chElemData, char chElemDate1, char chElemDate2) {
		StringBuilder deleteString = new StringBuilder();
		for (int i = 0; i < sourceString.length(); i++) {
			if (sourceString.charAt(i) != chElemData && sourceString.charAt(i) != chElemDate1 && sourceString.charAt(i) != chElemDate2) {
				deleteString.append(sourceString.charAt(i));
			}
		}
		return deleteString.toString();
	}

	public byte[] getUrlFileData(String fileUrl) {
		//连不上就会一直不走
		URL url = null;
		HttpURLConnection httpConn = null;
		byte[] fileData = null;
		try {
			url = new URL(fileUrl);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.connect();
			InputStream cin = httpConn.getInputStream();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = cin.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			cin.close();
			fileData = outStream.toByteArray();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileData;
	}

	@Override
	@GetMapping(DOC_QUERY_INFO)
	public R<List<DocVo>> queryDocInfo(DocEntity entity) {
		List<DocVo> docVo = null;
		try {
			entity.setToken(docService.getToken());
			if (StrUtil.isNotEmpty(entity.getToken())) {
				docVo = docService.getDocInfo(entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(docVo);
	}

	@Override
	@GetMapping(SIGNATURE_QUERY_INFO)
	@ApiLog("签章单位接口更新或来的源数据")
	public R<AsDictVo> querySigatureInfo(AsDict entity) {
		AsDictVo sigVo = null;
		try {
			String token = iAsService.getToken();
			if (StrUtil.isNotEmpty(token)) {
				sigVo = iAsService.getDocInfo(token).getData();
				logger.info("as_dict", JsonUtil.toJson(sigVo));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(200, sigVo, "数据获取成功");
	}

	@Override
	@GetMapping(E_SEAL_UPLOAD_FILE)
	public R<List<UploadFileVo>> uploadFiles(UploadFileEntity entity) {
		List<UploadFileVo> uploadFileVo = null;
		try {
			String token = eSealService.getToken();
			if (StrUtil.isNotEmpty(token)) {
				if (StrUtil.isNotEmpty(entity.getIsMerge())) {
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
			if (StrUtil.isNotEmpty(token)) {
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
			if (StrUtil.isNotEmpty(token)) {
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
			if (StrUtil.isNotEmpty(token)) {
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
			if (StrUtil.isNotEmpty(token)) {
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
			if (StrUtil.isNotEmpty(token)) {
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
			if (StrUtil.isNotEmpty(token)) {
				companyInfoVo = eSealService.getCompanyInfo(token, entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(companyInfoVo);
	}

	@Override
	@GetMapping(E_SEAL_TOKEN)
	public R<String> token() {
		String token = null;
		try {
			token = eSealService.getToken();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(token);
	}

	/**
	 * 增量更新组织及人员信息数据
	 *
	 * @param param
	 * @return
	 */
	@Override
	@PostMapping(ORGANIZATION_INFO_INCREMENT)
	@ApiLog("组织机构任务更新时间")
	public R<List<OrganizationVo>> getOrganizationInfoIncrement(OrgParme param) {
		log.info("查看查询时间的参数：" + param);
		return iOrganizationService.getOrganizationInfoIncrement(param);
	}

	/**
	 * 相对方数据新增更新
	 *
	 * @param entity
	 * @return
	 */
	@SneakyThrows
	@Override
	@GetMapping(COUNTERPART_INSERT_OR_UPDATE)
	@ApiLog("相对方定时任务-查询参数")
	public R<CounterpartVo> getCounterpart(CounterpartEntity entity) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
		CounterpartVo counterpartVo = null;
		String token = counterpartService.getToken();
		log.info("获取相对方的token：" + JsonUtil.toJson(token));
		entity.setToken(token);
		if (StrUtil.isEmpty(entity.getToken())) {
			return R.data(404, null, "获取token失败！");
		}
		counterpartVo = counterpartService.getInsOrUp(entity).getData();
		if (Func.isNull(counterpartVo)) {
			return R.data(500, null, "获取数据失败！");
		}
		if (Func.isEmpty(counterpartVo.getInsert()) & Func.isEmpty(counterpartVo.getUpdate())) {
			return R.data(200, counterpartVo, "获取数据成功,暂时没有需要更新的数据！");
		}
		//保存获取到的数据的日志
		logger.info("相对方元数据", JsonUtil.toJson(counterpartVo));
		log.info("更新到的向对方数据：" + JsonUtil.toJson(counterpartVo));
		List<ContractCounterpartEntity> listInsert = new ArrayList<>();
		List<ContractCounterpartEntity> listUpdate = new ArrayList<>();
		counterpartVo.getInsert().forEach(i -> {
			ContractCounterpartEntity in = new ContractCounterpartEntity();
			in.setName(i.getCustNm());
			in.setUnifiedSocialCreditCode(i.getBusinessId());
			in.setOrganizationCode(i.getBusinessId());
			in.setElectronicSealSerialId(i.getBusinessId());
			in.setCreateUser(1374895070913761282L);
			in.setUpdateUser(1374895070913761282L);
			in.setCreateDept(1367272379264299021L);
			//更名每月检视(编号)
			in.setRenameReview(simpleDateFormat.format(new Date()));
			//半角名称
			in.setHalfWidthName(i.getCustNm());
			List<ContractCounterpartEntity> entityList = contractClient.selectByName(in.getUnifiedSocialCreditCode()).getData();
			if (Func.isEmpty(entityList)) {
				if (in.getUnifiedSocialCreditCode().length() >= 18) {
					listInsert.add(in);
				}
			} else {
				in.setId(entityList.get(0).getId());
				listUpdate.add(in);
			}
		});
		counterpartVo.getUpdate().forEach(u -> {
			List<ContractCounterpartEntity> entityList = contractClient.selectByName(u.getBusinessId()).getData();
			ContractCounterpartEntity up = new ContractCounterpartEntity();
			up.setName(u.getCustNm());
			up.setUnifiedSocialCreditCode(u.getBusinessId());
			up.setOrganizationCode(u.getBusinessId());
			up.setElectronicSealSerialId(u.getBusinessId());
			up.setCreateUser(1374895070913761282L);
			up.setUpdateUser(1374895070913761282L);
			up.setCreateDept(1367272379264299021L);
			//更名每月检视(编号)
			up.setRenameReview(simpleDateFormat.format(new Date()));
			//半角名称
			up.setHalfWidthName(u.getCustNm());
			if (Func.isNotEmpty(entityList) && Func.isNotEmpty(entityList.get(0).getId())) {
				up.setId(entityList.get(0).getId());
				listUpdate.add(up);
			} else {
				if (up.getUnifiedSocialCreditCode().length() >= 18) {
					listInsert.add(up);
				}
			}
		});
		if (Func.isNotEmpty(listInsert)) {
			R<List<ContractCounterpartEntity>> listIn = contractClient.saveBatch(listInsert);
			log.info("新增的数据：" + JsonUtil.toJson(listIn.getData()));
		}
		if (Func.isNotEmpty(listUpdate)) {
			R<List<ContractCounterpartEntity>> listUp = contractClient.saveOrUpdate(listUpdate);
			log.info("更新的数据：" + JsonUtil.toJson(listUp.getData()));
		}
		log.info("新增的数据：" + JsonUtil.toJson(listInsert));
		log.info("更新的数据：" + JsonUtil.toJson(listUpdate));
		log.info("counterpartVo：" + JsonUtil.toJson(counterpartVo));
		return R.data(200, counterpartVo, "获取数据成功！");
	}

	@Override
	public R sendEkp(PushEkpEntity pushEkpEntity) {
		try {
			pushEkpEntity.setToken(ekpService.getToken());
			return R.data(ekpService.pushData(pushEkpEntity));
		} catch (Exception e) {
			return R.fail(e.getMessage());
		}
	}

	@Override
	public R sendFileToFastDfs(String filesIds) {
		//处理合同补充依据
		if (Func.isNotEmpty(filesIds)) {
			List<FileVO> fileVOs = fileClient.getByIds(filesIds).getData();
			String[] fileIds = new String[0];
			List<Attachment> listAttachment = new ArrayList<>();
			for (FileVO fileVO : fileVOs) {
				// 开始上传fastDFS服务器
				Attachment attachment = new Attachment();
				NameValuePair[] nvp = new NameValuePair[5];
				int index = fileVO.getName().lastIndexOf(".");
				String fileSuffix = fileVO.getName().substring(index + 1);
				nvp[0] = new NameValuePair("fdFileName", fileVO.getName());
				nvp[1] = new NameValuePair("fileSuffix", fileSuffix);
				nvp[2] = new NameValuePair("fdKey", "");
				nvp[3] = new NameValuePair("fdFileSize", fileVO.getFileSizes());
				nvp[4] = new NameValuePair("fileType", "");
				//3.创建trackerServer
				TrackerServer trackerServer = null;
				try {
					trackerServer = trackerClient.getConnection();
				} catch (IOException e) {
					return R.fail(e.getMessage());
				}
				// 4、创建一个 StorageServer 的引用，值为 null
				StorageServer storageServer = null;
				// 5、创建一个 StorageClient 对象，需要两个参数 TrackerServer 对象、StorageServer 的引用
				StorageClient storageClient = new StorageClient(trackerServer, storageServer);
				InputStream in = null;
				BufferedInputStream bin = null;
				ByteArrayOutputStream baos = null;
				BufferedOutputStream bout = null;
				byte[] bytes = null;
				try {
					URL url = new URL(fileVO.getLink());
					URLConnection conn = url.openConnection();
					in = conn.getInputStream();
					bin = new BufferedInputStream(in);
					baos = new ByteArrayOutputStream();
					bout = new BufferedOutputStream(baos);
					byte[] buffer = new byte[1024];
					int len = bin.read(buffer);
					while (len != -1) {
						bout.write(buffer, 0, len);
						len = bin.read(buffer);
					}
					//刷新此输出流并强制写出所有缓冲的输出字节
					bout.flush();
					bytes = baos.toByteArray();
					// 上传
					fileIds = storageClient.upload_file(bytes, fileSuffix, nvp);
				} catch (IOException | MyException e) {
					return R.fail(e.getMessage());
				}
				attachment.setFilename(fileVO.getName());
				attachment.setFilePath(fileIds[0] + '/' + fileIds[1]);
				listAttachment.add(attachment);
			}
			return R.data(listAttachment);
		}
		return R.fail("文件标识表达式不能为空");
	}

	@Override
	@GetMapping(E_EKP_TOKEN)
	public R<String> tokenEkp() {
		String token = null;
		try {
			token = ekpService.getToken();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(token);
	}

	@Override
	@PostMapping(EKP_SEND_FORM)
	public R<EkpVo> pushData(PushEkpEntity entity) throws Exception {
		return R.data(ekpService.pushData(entity));
	}


}
