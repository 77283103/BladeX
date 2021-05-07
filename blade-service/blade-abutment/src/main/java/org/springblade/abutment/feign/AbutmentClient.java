package org.springblade.abutment.feign;

import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.json.simple.JSONObject;
import org.springblade.abutment.entity.*;
import org.springblade.abutment.service.*;
import org.springblade.abutment.vo.*;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.ContractPerformanceColPayEntity;
import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springblade.contract.entity.ContractTemplateEntity;
import org.springblade.contract.feign.IContractClient;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
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
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	@Value("${api.eSeal.downloadUrl}")
	private String downloadUrl;

	@Value("${api.ekp.ftlPath}")
	private String ftlPath;

	@Override
	@PostMapping(EKP_SEND_FORM_POST)
	//推送代办
	public R<EkpVo> sendEkpFormPost(ContractFormInfoEntity entity) {
		R<EkpVo> rEkpVo = new R<>();
		EkpVo ekpVo = null;
		PushEkpEntity pushEkpEntity = new PushEkpEntity();
		pushEkpEntity.setFdTemplateId(fdTemplateId);
		if (null != entity) {
			//entity.getPersonCodeContract()
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
				//合同起草流程类型
				if ("10".equals(entity.getContractSoure()) || "20".equals(entity.getContractSoure())) {
					//合同附件名称
					List<FileVO> fileVO = fileClient.getByIds(entity.getTextFile()).getData();
					if (fileVO.size() > 0) {
						formValuesEntity.setFd_contract_name(fileVO.get(0).getName());
					}
					if ("10".equals(entity.getContractSoure())) {
						formValuesEntity.setFd_contract_type("10");
					} else {
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
					if ("FWZL_36".equals(templateEntity.getData().getTemplateCode())) {
						formValuesEntity.setFd_onetoone("2");
					} else {
						formValuesEntity.setFd_onetoone("1");
					}
				}
				//处理签章关键字 和多页合同
				formValuesEntity = fileText(formValuesEntity, entity);
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
				//相对方名称
				formValuesEntity.setFd_full_name(entity.getCounterpart().get(0).getName());
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
				try {
					//处理合同补充依据
					if (!Func.isEmpty(entity.getAttachedFiles())) {
						List<FileVO> fileVOs = fileClient.getByIds(entity.getAttachedFiles()).getData();
						String[] fileIds = new String[0];
						List<Attachment> listAttachment = new ArrayList<>();
						for (FileVO fileVO : fileVOs) {
							// 开始上传fastDFS服务器
							Attachment attachment = new Attachment();
							NameValuePair[] nvp = new NameValuePair[5];
							int index = fileVO.getName().lastIndexOf(".");
							String fileSuffix = fileVO.getName().substring(index + 1);
							nvp[0] = new NameValuePair("fdFileName", fileVO.getName());//文件名称
							nvp[1] = new NameValuePair("fileSuffix", fileSuffix);//文件后缀
							nvp[2] = new NameValuePair("fdKey", "");//文件key？？
							nvp[3] = new NameValuePair("fdFileSize", fileVO.getFileSizes());//文件大小
							nvp[4] = new NameValuePair("fileType", "");//文件类型
							//3.创建trackerServer
							TrackerServer trackerServer = null;
							try {
								trackerServer = trackerClient.getConnection();
							} catch (IOException e) {
								e.printStackTrace();
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
								e.printStackTrace();
							}
							attachment.setFilename(fileVO.getName());
							attachment.setFilePath(fileIds[0] + '/' + fileIds[1]);
							listAttachment.add(attachment);
						}
						pushEkpEntity.setFd_attachment(listAttachment);
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
					pushEkpEntity.setDocSubject(entity.getContractName());
					pushEkpEntity.setFdTemplateId(fdTemplateId);
					ekpVo = ekpService.pushData(pushEkpEntity);
					log.info("获取ekpVo的内容：" + ekpVo.getDoc_info());
					if (StrUtil.isEmpty(ekpVo.getDoc_info())) {
						rEkpVo.setCode(2);
						rEkpVo.setMsg("当前员工编号在系统中不存在，请换一个试试");
						rEkpVo.setSuccess(false);
						rEkpVo.setData(null);
						return rEkpVo;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
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
	 *
	 * @param entity
	 * @return
	 */
	@SneakyThrows
	@Override
	@PostMapping(EKP_NODE_FORM_POST)
	public R<EkpVo> nodeEkpFormPost(ContractFormInfoResponseVO entity) {
		R<EkpVo> rEkpVo = new R<>();
		EkpVo ekpVo = null;
		//KEP接口传输的入参
		PushEkpEntity pushEkpEntity = new PushEkpEntity();
		pushEkpEntity.setFdTemplateId(fdTemplateId);
		if (null != entity) {
			//17090089是登录人的编号
			//entity.getPersonCodeContract()
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
				if (entity.getContractStatus().equals("60")) {
					//4-1.合同归档的合同文本扫面件附件ID
					formValuesEntity.setFd_archive_file(entity.getSigningEntity().getAttachedFiles());
					//已归档处理合同文本扫面件
					if (Func.isNotEmpty(entity.getAttachedFiles())) {
						List<FileVO> fileVOs = fileClient.getByIds(entity.getAttachedFiles()).getData();
						String[] fileIds = new String[0];
						List<Attachment> listAttachment = new ArrayList<>();
						for (FileVO fileVO : fileVOs) {
							// 开始上传fastDFS服务器
							Attachment attachment = new Attachment();
							NameValuePair[] nvp = new NameValuePair[5];
							int index = fileVO.getName().lastIndexOf(".");
							String fileSuffix = fileVO.getName().substring(index + 1);
							//文件名称
							nvp[0] = new NameValuePair("fdFileName", fileVO.getName());
							//文件后缀
							nvp[1] = new NameValuePair("fileSuffix", fileSuffix);
							//文件key？？
							nvp[2] = new NameValuePair("fdKey", "");
							//文件大小
							nvp[3] = new NameValuePair("fdFileSize", fileVO.getFileSizes());
							//文件类型
							nvp[4] = new NameValuePair("fileType", "");
							//3.创建trackerServer
							TrackerServer trackerServer = null;
							try {
								trackerServer = trackerClient.getConnection();
							} catch (IOException e) {
								e.printStackTrace();
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
								e.printStackTrace();
							}
							attachment.setFilename(fileVO.getName());
							attachment.setFilePath(fileIds[0] + '/' + fileIds[1]);
							listAttachment.add(attachment);
						}
						pushEkpEntity.setFd_attachment(listAttachment);
					}
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

	/*public FormValuesEntity fileText(FormValuesEntity formValuesEntity,ContractFormInfoEntity entity) {
		StringBuilder downLoadUrl = new StringBuilder();
		downLoadUrl.append(downloadUrl).append(entity.getTextFilePdf()).append("&token=").append(token().getData());
		URL url = null;
		int HttpResult;
		URLConnection urlconn = null;
		try {
			url = new URL(downLoadUrl.toString());
			urlconn = url.openConnection();
			urlconn.setConnectTimeout(5 * 1000);
			urlconn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			urlconn.connect();
			HttpURLConnection httpconn = (HttpURLConnection) urlconn;
			HttpResult = httpconn.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = urlconn.getInputStream();
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
				JSONObject s=new JSONObject();
				String[] arrays;
				if((-1!=az&&-1!=bz)){
					if("1".equals(formValuesEntity.getFd_onetoone())){
						arrays = new String[]{"乙", "丙", "丁"};
						s.put("统一集团","甲方（签章）");
					}else{
						arrays = new String[]{"甲", "丙", "丁"};
						s.put("统一集团","乙方（签章）");
					}
					for (int i=0;i<entity.getInsert().size();i++) {
						s.put(entity.getInsert().get(i).getUnifiedSocialCreditCode(),arrays[i]+"方（签章）");
					}
					formValuesEntity.setFd_keyword(s.toJSONString());
				}else if((-1!=ay&&-1!=by)){
					if("1".equals(formValuesEntity.getFd_onetoone())){
						arrays = new String[]{"乙", "丙", "丁"};
						s.put("统一集团","甲方(签章)");
					}else{
						arrays = new String[]{"甲", "丙", "丁"};
						s.put("统一集团","乙方(签章)");
					}
					for (int i=0;i<entity.getInsert().size();i++) {
						s.put(entity.getInsert().get(i).getUnifiedSocialCreditCode(),arrays[i]+"方(签章)");
					}
					formValuesEntity.setFd_keyword(s.toJSONString());
				}
				document.close();
				inputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return formValuesEntity;
	}*/

	public FormValuesEntity fileText(FormValuesEntity formValuesEntity, ContractFormInfoEntity entity) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = df.format(new Date());
		File filePDF = new File(ftlPath + entity.getContractName() + date + ".pdf");
		//建立输出字节流
		FileOutputStream fos = null;
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
				formValuesEntity.setFd_keyword(s.toJSONString());
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
				formValuesEntity.setFd_keyword(s.toJSONString());
			}
			document.close();
			document.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return formValuesEntity;
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
	@GetMapping(E_SEAL_UPLOAD_FILE)
	public R<List<UploadFileVo>> uploadFiles(UploadFileEntity entity) {
		List<UploadFileVo> uploadFileVo = null;
		try {
			String token = eSealService.getToken();
			if (StrUtil.isNotEmpty(token)) {
				if (StrUtil.isNotEmpty(entity.getIsMerge())) {
					uploadFileVo = eSealService.uploadFiles(token, entity);
					System.out.println(uploadFileVo.size() + "/n" + uploadFileVo.get(0).getId());
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
	 * @return
	 */
	@Override
	@PostMapping(ORGANIZATION_INFO_INCREMENT)
	public R<List<OrganizationVo>> getOrganizationInfoIncrement() {
		return R.data(iOrganizationService.getOrganizationInfoIncrement());
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
	public R<CounterpartVo> getCounterpart(CounterpartEntity entity) {
		CounterpartVo counterpartVo = new CounterpartVo();
		String token = counterpartService.getToken();
		if (StrUtil.isNotEmpty(token)) {
			counterpartVo.setInsert(counterpartService.getInsert(entity));
			counterpartVo.setUpdate(counterpartService.getUpdate(entity));
		}
		String counterpart = contractClient.inOrUp().getData();
		if ("success".equals(counterpart)) {
			return R.data(counterpartVo);
		} else {
			return R.data(404, null, "存储失败！");
		}
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
