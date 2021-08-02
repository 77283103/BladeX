package org.springblade.contract.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.entity.ContractFileDownloadLogEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.enums.ContractErorrCodeEnum;
import org.springblade.contract.enums.ContractStatusEnum;
import org.springblade.contract.enums.ContractTypeEnum;
import org.springblade.contract.service.*;
import org.springblade.contract.util.AsposeWordToPdfUtils;
import org.springblade.contract.util.TemplateExportUntil;
import org.springblade.contract.util.TemplateSaveUntil;
import org.springblade.contract.vo.ContractAccordingRequestVO;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.vo.ContractImportBatchDraftRequest;
import org.springblade.contract.wrapper.ContractFormInfoWrapper;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Charsets;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.entity.FileEntity;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.entity.TemplateFieldEntity;
import org.springblade.system.feign.IDictBizClient;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
import org.springblade.system.vo.DataSealAuthorityResponseVO;
import org.springblade.system.vo.TemplateRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 控制器
 *
 * @author : 史智伟
 * @date : 2020-09-23 18:04:37
 */
@Log4j2
@RestController
@RequestMapping("/contractFormInfo")
@Api(value = "", tags = "")
public class ContractFormInfoController extends BladeController {
	@Autowired
	private IContractCounterpartService counterpartService;
	@Autowired
	private IContractFormInfoService contractFormInfoService;
	@Autowired
	private IContractPerformanceService performanceService;
	@Autowired
	private IContractPerformanceColPayService contractPerformanceColPayService;
	@Autowired
	private IContractChangeService changeService;
	@Autowired
	private IDictBizClient bizClient;
	@Autowired
	private IFileClient fileClient;
	@Autowired
	private ISysClient roleService;
	@Autowired
	private IUserClient userClient;
	@Autowired
	private TemplateExportUntil templateExportUntil;
	@Autowired
	private IPerServiceContentService perServiceContentService;
	@Autowired
	private IPerCollectPayService perCollectPayService;
	@Autowired
	private IContractFileDownloadLogService fileDownloadLogService;
	private static final String CONTRACT_SEAL_USING_INFO_STATUS = "50";
	private static final String CONTRACT_ARCHIVE_STATUS = "110";
	private static final String CONTRACT_ASSESSMENT_STATUS = "100";
	@Value("${api.file.ftlPath}")
	private String ftlPath;


	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:detail')")
	public R<ContractFormInfoResponseVO> detail(@RequestParam Long id) {
		ContractFormInfoResponseVO detail = contractFormInfoService.getById(id);
		return R.data(detail);
	}

	@GetMapping("/version")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "合同变更历史详情", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:version')")
	public R<ContractFormInfoResponseVO> version(@RequestParam Long id) {
		ContractFormInfoResponseVO version = contractFormInfoService.getByChangeHistoryId(id);
		return R.data(version);
	}


	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:list')")
	public R<IPage<ContractFormInfoResponseVO>> list(ContractFormInfoRequestVO contractFormInfo, Query query) {
		ContractFormInfoRequestVO requestVO = ContractFormInfoWrapper.build().createQV();
		BeanUtil.copy(contractFormInfo, requestVO);
		BladeUser user = AuthUtil.getUser();
		List<String> realNameList = roleService.getRoleAliases(user.getRoleId()).getData();
		log.info("当前登陆人的角色别名：" + realNameList);
		List<String> seal = new ArrayList<>();
		if (realNameList.contains("contract_admin")) {
			R<DataSealAuthorityResponseVO> responseVO = roleService.getByIdData(user.getUserId().toString(), user.getRoleId());
			if (responseVO.getCode() == HttpStatus.OK.value()) {
				log.info("根据用户id和用户角色ID查询该用户的数据权限：" + responseVO.getCode());
				responseVO.getData().getSealList().forEach(s -> {
					seal.add(bizClient.getValue("application_seal", s).getData());
				});
				requestVO.setSealNames(seal);
			} else {
				return R.fail(HttpStatus.NOT_FOUND.value(), "该用户拥有合同管理员权限，但未配置管理合同数据");
			}
		}
		log.info("该用户的数据权限内容：" + seal);
		IPage<ContractFormInfoResponseVO> pages = contractFormInfoService.pageList(Condition.getPage(query), requestVO);
		return R.data(pages);
	}

	@GetMapping("/statistics")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "統計分析查詢列表", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:statistics')")
	public R<IPage<ContractFormInfoEntity>> statistics(ContractFormInfoRequestVO contractFormInfo, Query query) {
		IPage<ContractFormInfoEntity> pages = contractFormInfoService.statisticsList(Condition.getPage(query), contractFormInfo);
		return R.data(pages);
	}

	@GetMapping("/listSealInfo")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "用印分页", notes = "传入ContractFormInfoRequestVO")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:listSealInfo')")
	public R<IPage<ContractFormInfoResponseVO>> listSealInfo(ContractFormInfoRequestVO contractFormInfoRequestVO, Query query) {
		IPage<ContractFormInfoEntity> pages = contractFormInfoService.pageListSealInfo(Condition.getPage(query), contractFormInfoRequestVO);
		return R.data(ContractFormInfoWrapper.build().entityPVPage(pages));
	}

	@Transactional(rollbackFor = Exception.class)
	public ContractFormInfoRequestVO unifiedMethodHandle(ContractFormInfoRequestVO contractFormInfo){
		/*保存多方向对方身份信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getDraftContractCounterpartList())) {
			contractFormInfoService.saveDraftContractCounterpartList(contractFormInfo);
		}
		/*保存相对方收付款信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getMultPaymenEntityList())) {
			contractFormInfo = contractFormInfoService.setMultPaymenEntityList(contractFormInfo);
		}
		/*保存子公司信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getContractSeal())) {
			contractFormInfoService.saveContractSeal(contractFormInfo);
		}
		//保存合同首款明细数据
		if (CollectionUtil.isNotEmpty(contractFormInfo.getCollection())) {
			contractFormInfoService.saveCollection(contractFormInfo);
		}
		/*保存相对方信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())) {
			contractFormInfoService.saveCounterpart(contractFormInfo);
		}
		/*保存保证金信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())) {
			contractFormInfoService.saveBondMultiPlan(contractFormInfo);
		}
		/*保存依据信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getAccording())) {
			contractFormInfoService.saveAccording(contractFormInfo);
		}
		/*保存履约信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())) {
			performanceService.savePerformance(contractFormInfo);
		}
		/*保存履约计划收付款*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())) {
			contractPerformanceColPayService.savePerformanceColPay(contractFormInfo);
		}
		return contractFormInfo;
	}

	@PostMapping("/multiAdd")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "多方起草新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:multiAdd')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> multiAdd(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		R<ContractFormInfoEntity> r = new R<>();
		contractFormInfo.setContractSoure(ContractTypeEnum.MULTI.getKey().toString());
		ContractFormInfoEntity contractFormInfoCopy = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, contractFormInfoCopy);
		if (Func.isEmpty(contractFormInfo.getId())) {
			contractFormInfoService.save(contractFormInfoCopy);
			contractFormInfo.setId(contractFormInfoCopy.getId());
			//增加履约计划信息
			perServiceContentService.addPerData(Func.isEmpty(contractFormInfo.getPerServiceContentList()) ? null :
				contractFormInfo.getPerServiceContentList().get(0), contractFormInfoCopy.getId());
			//增加履约收付款信息
			perCollectPayService.addListData(contractFormInfo.getPerCollectPayList(), contractFormInfoCopy.getId());
		}
		//统一处理相同相同数据的方法
		contractFormInfo=this.unifiedMethodHandle(contractFormInfo);
		//开始接口处理
		r.setMsg(ContractErorrCodeEnum.MULTI_PARTY_DRAFT_SAVE.getValue());
		r.setCode(ContractErorrCodeEnum.MULTI_PARTY_DRAFT_SAVE.getKey());
		if (ContractStatusEnum.APPROVAL.getKey().toString().equals(contractFormInfo.getContractStatus())) {
			//处理电子签章和oa流程
			if (ContractTypeEnum.ELECTRONIC_CONTRACT_WE.getKey().toString().equals(contractFormInfo.getContractForm())
				|| ContractTypeEnum.ENTITY_CONTRACT_WE.getKey().toString().equals(contractFormInfo.getContractForm())) {
				r = contractFormInfoService.SingleSign(R.data(contractFormInfo));
			} else {
				r = contractFormInfoService.SingleSignE(R.data(contractFormInfo));
			}
			contractFormInfoCopy = r.getData();
			if (r.getCode() != HttpStatus.OK.value()) {
				contractFormInfoCopy.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
				r.setMsg(ContractErorrCodeEnum.MULTI_PARTY_DRAFT_APPROVAL_FAIL.getValue());
				r.setCode(ContractErorrCodeEnum.MULTI_PARTY_DRAFT_APPROVAL_FAIL.getKey());
			} else {
				contractFormInfoCopy.setCreateTime(new Date());
				r.setMsg(ContractErorrCodeEnum.MULTI_PARTY_DRAFT_APPROVAL.getValue());
				r.setCode(ContractErorrCodeEnum.MULTI_PARTY_DRAFT_APPROVAL.getKey());
			}
		}
		contractFormInfoService.updateById(contractFormInfoCopy);
		return R.data(r.getCode(), ContractFormInfoWrapper.build().entityPV(contractFormInfoCopy), r.getMsg());
	}

	@PostMapping("/add")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "独立起草新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:add')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> save(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		R<ContractFormInfoEntity> r = new R<>();
		contractFormInfo.setContractSoure(ContractTypeEnum.INDE.getKey().toString());
		ContractFormInfoEntity contractFormInfoCopy = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, contractFormInfoCopy);
		if (Func.isEmpty(contractFormInfo.getId())) {
			contractFormInfoService.save(contractFormInfoCopy);
			//增加履约计划信息
			perServiceContentService.addPerData(Func.isEmpty(contractFormInfo.getPerServiceContentList()) ? null :
				contractFormInfo.getPerServiceContentList().get(0), contractFormInfoCopy.getId());
			//增加履约收付款信息
			perCollectPayService.addListData(contractFormInfo.getPerCollectPayList(), contractFormInfoCopy.getId());
		}
		contractFormInfo.setId(contractFormInfoCopy.getId());
		//统一处理相同相同数据的方法
		contractFormInfo=this.unifiedMethodHandle(contractFormInfo);
		r.setMsg(ContractErorrCodeEnum.INDE_DRAFT_SAVE.getValue());
		if (ContractStatusEnum.APPROVAL.getKey().toString().equals(contractFormInfo.getContractStatus())) {
			if (ContractTypeEnum.ELECTRONIC_CONTRACT_WE.getKey().toString().equals(contractFormInfo.getContractForm())
				|| ContractTypeEnum.ENTITY_CONTRACT_WE.getKey().toString().equals(contractFormInfo.getContractForm())) {
				r = contractFormInfoService.SingleSign(R.data(contractFormInfo));
			} else {
				r = contractFormInfoService.SingleSignE(R.data(contractFormInfo));
			}
			contractFormInfoCopy = r.getData();
			if (r.getCode() != HttpStatus.OK.value()) {
				contractFormInfoCopy.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
				r.setMsg(ContractErorrCodeEnum.INDE_DRAFT_APPROVAL_FAIL.getValue());
				r.setCode(ContractErorrCodeEnum.INDE_DRAFT_APPROVAL_FAIL.getKey());
			} else {
				contractFormInfoCopy.setCreateTime(new Date());
				r.setMsg(ContractErorrCodeEnum.INDE_DRAFT_APPROVAL.getValue());
				r.setCode(ContractErorrCodeEnum.INDE_DRAFT_APPROVAL.getKey());
			}
		}
		contractFormInfoService.updateById(contractFormInfoCopy);
		return R.data(r.getCode(), ContractFormInfoWrapper.build().entityPV(contractFormInfoCopy), r.getMsg());
	}

	@PostMapping("/templateSave")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "范本起草新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:templateSave')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> templateSave(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		R<ContractFormInfoEntity> r = new R<>();
		TemplateRequestVO template = contractFormInfo.getTemplate();
		List<TemplateFieldEntity> templateFieldList = JSON.parseArray(template.getJson(), TemplateFieldEntity.class);
		JSONObject j = new JSONObject();
		for (TemplateFieldEntity templateField : templateFieldList) {
			j.put(templateField.getFieldName(), templateField.getFieldValue());
		}
		ContractFormInfoEntity contractFormInfoEntity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, contractFormInfoEntity);
		Long id = TemplateSaveUntil.templateSave(contractFormInfoEntity, template, j);
		contractFormInfo.setId(id);
		//统一处理相同相同数据的方法
		contractFormInfo=this.unifiedMethodHandle(contractFormInfo);
		//保存合同和关联表 这个方法有问题
		contractFormInfoEntity = contractFormInfoService.templateDraft(contractFormInfoEntity, template.getJson());
		//页面用这个字段来判断是否提交
		r.setMsg(ContractErorrCodeEnum.TEMPLATE_PARTY_DRAFT_SAVE.getValue());
		if (ContractStatusEnum.APPROVAL.getKey().toString().equals(template.getBean())) {
			//导出pdf文件
			FileVO filevo = templateExportUntil.templateSave(contractFormInfoEntity, template, contractFormInfoEntity.getJson(), j);
			contractFormInfoEntity.setTextFile(filevo.getId() + ",");
			contractFormInfoEntity.setTextFilePdf(filevo.getId() + ",");
			contractFormInfoEntity.setContractStatus(template.getBean());
			contractFormInfoEntity.setFilePdf(filevo.getDomain());
			r = contractFormInfoService.SingleSign(R.data(contractFormInfoEntity));
			contractFormInfoEntity = r.getData();
			if (r.getCode() != HttpStatus.OK.value()) {
				contractFormInfoEntity.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
				r.setMsg(ContractErorrCodeEnum.TEMPLATE_PARTY_DRAFT_APPROVAL_FAIL.getValue());
				r.setCode(ContractErorrCodeEnum.TEMPLATE_PARTY_DRAFT_APPROVAL_FAIL.getKey());
			} else {
				contractFormInfoEntity.setContractStatus(ContractStatusEnum.APPROVAL.getKey().toString());
				r.setMsg(ContractErorrCodeEnum.TEMPLATE_PARTY_DRAFT_APPROVAL.getValue());
				r.setCode(ContractErorrCodeEnum.TEMPLATE_PARTY_DRAFT_APPROVAL.getKey());
				contractFormInfoEntity.setCreateTime(new Date());
			}
		}
		contractFormInfoService.updateById(contractFormInfoEntity);
		//增加履约计划信息
		perServiceContentService.addPerData(Func.isEmpty(contractFormInfo.getPerServiceContentList()) ? null :
			contractFormInfo.getPerServiceContentList().get(0), contractFormInfoEntity.getId());
		//增加履约收付款信息
		perCollectPayService.addListData(contractFormInfo.getPerCollectPayList(), contractFormInfoEntity.getId());
		return R.data(r.getCode(), ContractFormInfoWrapper.build().entityPV(contractFormInfoEntity), r.getMsg());
	}

	/**
	 * 判断电子签章和关键字是否存在
	 */
	@PostMapping("/singleSignIsNot")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "判断电子签章是否存在", notes = "传入contractFormInfo")
	@Transactional(rollbackFor = Exception.class)
	public R singleSignIsNot(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		FileVO files = null;
		if ("30".equals(contractFormInfo.getContractSoure())) {
			R<FileVO> file = contractBrowse(contractFormInfo);
			files = file.getData();
		} else if ("10".equals(contractFormInfo.getContractSoure()) || "20".equals(contractFormInfo.getContractSoure())) {
			List<FileVO> fileVO = fileClient.getByIds(contractFormInfo.getTextFile()).getData();
			files = fileVO.get(0);
		}
		return contractFormInfoService.singleSignIsNot(contractFormInfo, files);
	}

	/**
	 * 合同预览
	 */
	@PostMapping("/contractBrowse")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "合同预览", notes = "template")
	@Transactional(rollbackFor = Exception.class)
	public R<FileVO> contractBrowse(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		FileVO files = null;
		if (Func.isEmpty(contractFormInfo.getTextFile())) {
			TemplateRequestVO template = contractFormInfo.getTemplate();
			List<TemplateFieldEntity> templateFieldList = JSON.parseArray(template.getJson(), TemplateFieldEntity.class);
			JSONObject j = new JSONObject();
			for (TemplateFieldEntity templateField : templateFieldList) {
				j.put(templateField.getFieldName(), templateField.getFieldValue());
			}
			//把json串转换成一个对象
			ContractFormInfoEntity contractFormInfoEntity = JSONObject.toJavaObject(j, ContractFormInfoEntity.class);
			BeanUtil.copy(contractFormInfo, contractFormInfoEntity);
			//导出pdf文件
			files = templateExportUntil.templateSave(contractFormInfoEntity, template, template.getJson(), j);
		} else {
			List<FileVO> list = fileClient.getByIds(contractFormInfo.getTextFile().trim()).getData();
			files = list.get(0);
		}
		return R.data(files);
	}


	/**
	 * 批量送审
	 */
	@PostMapping("/submitBatch")
	@ApiOperationSupport(order = 12)
	@ApiOperation(value = "批量送审", notes = "传入依据和合同ids")
	@Transactional(rollbackFor = Exception.class)
	public R submitBatch(@Valid @RequestBody ContractAccordingRequestVO according) {
		List<ContractFormInfoEntity> infoEntityList = new ArrayList<>();
		//保存依据信息，把依据信息替换到json串中
		for (String id : according.getContractIds()) {
			ContractAccordingEntity entity = BeanUtil.copy(according, ContractAccordingEntity.class);
			JSONObject jsonObj = JSON.parseObject(JSON.toJSONString(entity));
			ContractFormInfoResponseVO infoEntity = contractFormInfoService.getById(Long.parseLong(id));
			ContractFormInfoEntity contractFormInfoEntity = new ContractFormInfoEntity();
			BeanUtil.copy(infoEntity, contractFormInfoEntity);
			String json = infoEntity.getJson();
			if (Func.isNotEmpty(json)) {
				com.alibaba.fastjson.JSONArray objects = new com.alibaba.fastjson.JSONArray();
				try {
					objects = JSONArray.parseArray(json);
					for (int i = 0; i < objects.size(); i++) {
						JSONObject temp = objects.getJSONObject(i);
						String relationCode = temp.getString("relationCode");
						if ("ContractAccording".equals(relationCode)) {
							temp.put("tableData", jsonObj);
							objects.set(i, temp);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				infoEntity.setJson(objects.toJSONString());
			}
			//先存储为状态为草稿  保证出现异常 避免无法回滚
			infoEntity.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
			infoEntityList.add(contractFormInfoEntity);
		}
		boolean status = contractFormInfoService.saveOrUpdateBatch(infoEntityList);
		if (status) {
			contractFormInfoService.pushBatch(infoEntityList);
		}
		return R.data("送审成功");
	}


	/**
	 * 复用
	 */
	@PostMapping("/multiplex")
	@ApiOperationSupport(order = 13)
	@ApiOperation(value = "复用", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:multiplex')")
	public R<ContractFormInfoResponseVO> multiplex(@RequestParam Long id) {
		ContractFormInfoEntity entity = contractFormInfoService.getById(id);
		ContractFormInfoEntity contractFormInfo = new ContractFormInfoEntity();
		BeanUtil.copy(entity, contractFormInfo);
		String json = contractFormInfo.getJson();
		if (!Func.isEmpty(json)) {
			com.alibaba.fastjson.JSONArray objects = new com.alibaba.fastjson.JSONArray();
			try {
				objects = JSONArray.parseArray(json);
				for (int i = 0; i < objects.size(); i++) {
					JSONObject temp = objects.getJSONObject(i);
					String componentType = temp.getString("componentType");
					if ("id".equals(componentType)) {
						temp.put("fieldValue", "");
						objects.set(i, temp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			json = objects.toJSONString();
			contractFormInfo.setJson(json);
		}
		return R.data(ContractFormInfoWrapper.build().entityPV(contractFormInfo));
	}


	/**
	 * 修改
	 * 判断变更合同id是否为空 否则修改元年合同状态 为已消毁
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:update')")
	public R update(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		if (Func.isEmpty(contractFormInfo.getId())) {
			throw new ServiceException("id不能为空");
		}
		ContractFormInfoEntity entity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, entity);
		if (Func.isNotEmpty(entity.getChangeContractId())) {
			changeService.updateExportStatus(ContractStatusEnum.ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS.getKey().toString(), Long.parseLong(entity.getChangeContractId()));
		}
		return R.status(contractFormInfoService.updateById(entity));
	}

	/**
	 * 导出后修改合同状态为待用印 并统计下载次数 修改下载状态
	 * 30>40
	 */
	@PostMapping("/updateExport")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:updateExport')")
	public R updateExport(@RequestParam Long id) {
		ContractFormInfoEntity infoEntity = contractFormInfoService.getById(id);
		Integer fileExportCount = infoEntity.getFileExportCount();
		fileExportCount += 1;
		//修改合同下载次数 下载状态
		contractFormInfoService.textExportCount(id, fileExportCount, ContractStatusEnum.FILE_EXPORT_CATEGORY.getKey());
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		//附加条件解决BUG
		if (ContractStatusEnum.CONTRACT_AUDIT_QUALITY.getKey().toString().equals(infoEntity.getContractStatus())) {
			infoEntity.setContractStatus(ContractStatusEnum.CONTRACT_EXPORT_STATUS.getKey().toString());
		}
		infoEntity.setFileExportCount(fileExportCount);
		infoEntity.setFileExportCategory(ContractStatusEnum.FILE_EXPORT_CATEGORY.getKey());
		boolean status = contractFormInfoService.updateById(infoEntity);
		if (status) {
			BladeUser user = AuthUtil.getUser();
			ContractFileDownloadLogEntity fileDownloadLogEntity = new ContractFileDownloadLogEntity();
			R<User> info = userClient.userInfoById(user.getUserId());
			if (info.getCode() == HttpStatus.OK.value()) {
				fileDownloadLogEntity.setCode(info.getData().getCode());
				fileDownloadLogEntity.setRealName(info.getData().getRealName());
			}
			fileDownloadLogEntity.setAccount(user.getAccount());
			fileDownloadLogEntity.setContractId(String.valueOf(infoEntity.getId()));
			fileDownloadLogService.save(fileDownloadLogEntity);
		}
		return R.status(status);
	}

	/**
	 * 复用导出合同文本
	 */
	@PostMapping("/repeatExport")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:repeatExport')")
	public R<ContractFormInfoResponseVO> repeatExport(@RequestParam Long id) {
		ContractFormInfoEntity infoEntity = contractFormInfoService.getById(id);
		Integer fileExportCount = infoEntity.getFileExportCount();
		fileExportCount += 1;
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		//统计到导出次数
		contractFormInfoService.textExportCount(id, fileExportCount, ContractStatusEnum.FILE_EXPORT_CATEGORY.getKey());
		infoEntity.setFileExportCount(fileExportCount);
		//附加条件解决BUG
		if (ContractStatusEnum.CONTRACT_AUDIT_QUALITY.getKey().toString().equals(infoEntity.getContractStatus())) {
			infoEntity.setContractStatus(ContractStatusEnum.CONTRACT_EXPORT_STATUS.getKey().toString());
		}
		contractFormInfoService.updateById(infoEntity);
		ContractFormInfoResponseVO formInfoResponseVO = ContractFormInfoWrapper.build().entityPV(infoEntity);
		return R.data(formInfoResponseVO);
	}


	/**
	 * 用印后修改状态为待签定
	 * 40>50
	 */
	@PostMapping("/updateSealStatus")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:updateSealStatus')")
	public R sealStatus(@RequestParam Long id) {
		ContractFormInfoEntity infoEntity = contractFormInfoService.getById(id);
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		infoEntity.setContractStatus(CONTRACT_SEAL_USING_INFO_STATUS);
		return R.status(contractFormInfoService.updateById(infoEntity));
	}

	/**
	 * 归档后修改状态待评估
	 * 60>110
	 */
	@PostMapping("/updateArchiveStatus")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:updateArchiveStatus')")
	public R archiveStatus(@RequestParam Long id) {
		ContractFormInfoEntity infoEntity = contractFormInfoService.getById(id);
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		infoEntity.setContractStatus(CONTRACT_ARCHIVE_STATUS);
		return R.status(contractFormInfoService.updateById(infoEntity));
	}

	/**
	 * 评估后修改状态为待分析
	 * 100
	 */
	@PostMapping("/updateAssessmentStatus")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:updateAssessmentStatus')")
	public R assessmentStatus(@RequestParam Long id) {
		ContractFormInfoEntity infoEntity = contractFormInfoService.getById(id);
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		infoEntity.setContractStatus(CONTRACT_ASSESSMENT_STATUS);
		return R.status(contractFormInfoService.updateById(infoEntity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractFormInfoService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 合同大类金额
	 */
	@PostMapping("/getAmountList")
	@ApiOperation(value = "合同大类金额", notes = "")
	public ArrayList<Map<String, String>> getAmountList() {
		List<ContractFormInfoEntity> list = contractFormInfoService.getAmountList();
		ArrayList<Map<String, String>> listMap = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> map = new HashMap<>();
			map.put("name", list.get(i).getDictValue());
			map.put("value", String.valueOf(list.get(i).getContractAmount()));
			listMap.add(map);
		}
		return listMap;
	}

	/**
	 * 合同大类数量
	 */
	@PostMapping("/getNumList")
	@ApiOperation(value = "合同大类数量", notes = "传入ids")
	public ArrayList<Map<String, String>> getNumList() {
		List<ContractFormInfoEntity> list = contractFormInfoService.getNumList();
		ArrayList<Map<String, String>> listMap = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> map = new HashMap<>();
			map.put("name", list.get(i).getDictValue());
			map.put("value", String.valueOf(list.get(i).getCount()));
			listMap.add(map);
		}
		return listMap;
	}


	@GetMapping("/listStatistics")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "合同统计分析分页", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:listStatistics')")
	public R<IPage<ContractFormInfoResponseVO>> listStatistics(ContractFormInfoRequestVO contractFormInfo, Query query) {
		IPage<ContractFormInfoResponseVO> pages = contractFormInfoService.pageListStatistics(Condition.getPage(query), contractFormInfo);
		return R.data(pages);
	}

	@PostMapping("/exportTargetDataResultStatistics")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "导出excel", notes = "")
	public void exportTargetDataResult(@RequestBody List<ContractFormInfoResponseVO> formInfoEntityList, HttpServletResponse response) {
		if (CollectionUtil.isNotEmpty(formInfoEntityList)) {
			/* 导出文件名称 */
			String fileName = "合同统计分析信息导出";
			WriteSheet sheet1 = new WriteSheet();
			/* 导出的sheet的名称 */
			sheet1.setSheetName("合同统计分析信息导出");
			sheet1.setSheetNo(0);
			/* 需要存入的数据 */
			List<List<Object>> data = new ArrayList<>();
			/* formInfoEntityList 表示要写入的数据 因为是前台显示列表 由前台进行传值，后期可以根据自己的需求进行改变 */
			for (ContractFormInfoResponseVO contractFormInfoEntity : formInfoEntityList) {
				/* 属性 cloumns 表示一行，cloumns包含的数据是一行的数据
				  要将一行的每个值 作为list的一个属性存进到list里 ，数据要和展示的excel表头一致*/
				List<Object> cloumns = new ArrayList<Object>();
				/*合同类别*/
				cloumns.add(contractFormInfoEntity.getContractBigCategory());
				/*合同金额*/
				cloumns.add(contractFormInfoEntity.getContractAmount());
				/*签订数量*/
				cloumns.add(contractFormInfoEntity.getSigningCount());
				/*占比金额比例*/
				cloumns.add(contractFormInfoEntity.getAmountRatio());
				/*收支类型*/
				cloumns.add(contractFormInfoEntity.getColPayType());
				/*合同状态*/
				cloumns.add(bizClient.getValue("contract_status", contractFormInfoEntity.getContractStatus()).getData());
				/*签订单位*/
				cloumns.add(contractFormInfoEntity.getSigningEntity().getManageUnit());
				data.add(cloumns);
			}
			/* 表头名称，excel的表头 一个list对象为一行里的一个表头名称 */
			List<List<String>> headList = new ArrayList<List<String>>();
			/* 此处表头为一行要显示的所有表头，要和数据的顺序对应上  需要转换为list */
			List<String> head = Arrays.asList("合同类别", "合同金额", "签订数量", "占比金额比例", "收支类型", "合同状态", "签订单位");
			/* 为了生成一个独立的list对象，所进行的初始化 */
			List<String> head2 = null;
			for (String head1 : head) {
				head2 = new ArrayList<>();
				/* 将表头的数据赋值进入list对象 */
				head2.add(head1);
				/* 将数据赋值进入最终要输出的表头 */
				headList.add(head2);
			}
			try {
				response.setContentType("application/vnd.ms-excel");
				response.setCharacterEncoding(Charsets.UTF_8.name());
				fileName = URLEncoder.encode(fileName, Charsets.UTF_8.name());
				response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
				EasyExcel.write(response.getOutputStream()).head(headList).sheet().doWrite(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@PostMapping("/exportCounterpart")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "导出导出相对方数据excel", notes = "")
	public void exportCounterpart(HttpServletResponse response) {
		List<ContractCounterpartEntity> ce = counterpartService.list();
		if (CollectionUtil.isNotEmpty(ce)) {
			/* 导出文件名称 */
			String fileName = "相对方信息导出";
			WriteSheet sheet1 = new WriteSheet();
			/* 导出的sheet的名称 */
			sheet1.setSheetName("相对方信息导出");
			sheet1.setSheetNo(0);
			/* 需要存入的数据 */
			List<List<Object>> data = new ArrayList<>();
			/* formInfoEntityList 表示要写入的数据 因为是前台显示列表 由前台进行传值，后期可以根据自己的需求进行改变 */
			for (ContractCounterpartEntity counterpartEntity : ce) {
				/* 属性 cloumns 表示一行，cloumns包含的数据是一行的数据
				  要将一行的每个值 作为list的一个属性存进到list里 ，数据要和展示的excel表头一致*/
				List<Object> cloumns = new ArrayList<Object>();
				/*相对方名称*/
				cloumns.add(counterpartEntity.getName());
				/*统一社会信用代码*/
				cloumns.add(counterpartEntity.getUnifiedSocialCreditCode());
				/*创建时间*/
				cloumns.add(counterpartEntity.getCreateTime());
				/*更新时间*/
				cloumns.add(counterpartEntity.getUpdateTime());
				/*状态*/
				cloumns.add(1 == counterpartEntity.getStatus() ? "使用中" : "未使用");
				/*是否删除*/
				cloumns.add(counterpartEntity.getIsDeleted() == 0 ? "正常" : "已删除");
				data.add(cloumns);
			}
			/* 表头名称，excel的表头 一个list对象为一行里的一个表头名称 */
			List<List<String>> headList = new ArrayList<List<String>>();
			/* 此处表头为一行要显示的所有表头，要和数据的顺序对应上  需要转换为list */
			List<String> head = Arrays.asList("相对方名称", "统一社会信用代码", "创建时间", "更新时间", "状态", "是否删除");
			/* 为了生成一个独立的list对象，所进行的初始化 */
			List<String> head2 = null;
			for (String head1 : head) {
				head2 = new ArrayList<>();
				/* 将表头的数据赋值进入list对象 */
				head2.add(head1);
				/* 将数据赋值进入最终要输出的表头 */
				headList.add(head2);
			}
			try {
				response.setContentType("application/vnd.ms-excel");
				response.setCharacterEncoding(Charsets.UTF_8.name());
				fileName = URLEncoder.encode(fileName, Charsets.UTF_8.name());
				response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
				EasyExcel.write(response.getOutputStream()).head(headList).sheet().doWrite(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@PostMapping("/addChange")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "独立起草变更", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:addChange')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> saveChange(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		R<ContractFormInfoEntity> r = new R<>();
		ContractFormInfoEntity contractFormInfoCopy = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, contractFormInfoCopy);
		//判断合同变更ID为空  说明合同第一次变更 此次不论是暂存还是送审都会保存原合同ID为变更标识 并将变更后合同状态存为草稿或审批中
		if (Func.isBlank(contractFormInfo.getChangeContractId())) {
			contractFormInfoCopy.setId(null);
			contractFormInfoCopy.setFileExportCount(0);
			contractFormInfoCopy.setFileExportCategory(0);
			contractFormInfoCopy.setChangeContractId(contractFormInfo.getId().toString());
			contractFormInfoCopy.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
		} else {
			//变更ID不为空说明有变更操作  此时判断合同状态是否为履约中的合同  如果是  那么进行变更操作  否则判断为变更合同的修改操作
			if (ContractStatusEnum.CONTRACT_PERFORMANCE_STATUS.getKey().toString().equals(contractFormInfo.getContractStatus())) {
				contractFormInfoCopy.setId(null);
				contractFormInfoCopy.setFileExportCount(0);
				contractFormInfoCopy.setFileExportCategory(0);
				contractFormInfoCopy.setChangeContractId(contractFormInfo.getId().toString());
				contractFormInfoCopy.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
			}
		}
		contractFormInfoService.saveOrUpdate(contractFormInfoCopy);
		contractFormInfo.setId(contractFormInfoCopy.getId());
		//统一处理相同数据方法
		contractFormInfo=this.unifiedMethodHandle(contractFormInfo);
		/*送审*/
		r.setMsg(ContractErorrCodeEnum.CHANGE_INDE_DRAFT_SAVE.getValue());
		r.setCode(ContractErorrCodeEnum.CHANGE_INDE_DRAFT_SAVE.getKey());
		if (ContractStatusEnum.APPROVAL.getKey().toString().equals(contractFormInfoCopy.getContractStatus())) {
			if (ContractTypeEnum.ELECTRONIC_CONTRACT_WE.getKey().toString().equals(contractFormInfo.getContractForm())
				|| ContractTypeEnum.ENTITY_CONTRACT_WE.getKey().toString().equals(contractFormInfo.getContractForm())) {
				r = contractFormInfoService.SingleSign(R.data(contractFormInfo));
			} else {
				r = contractFormInfoService.SingleSignE(R.data(contractFormInfo));
			}
			if (r.getCode() != HttpStatus.OK.value()) {
				contractFormInfoCopy.setChangeCategory(ContractStatusEnum.DRAFT.getKey().toString());
			} else {
				contractFormInfoCopy.setCreateTime(new Date());
				contractFormInfoCopy.setContractStatus(ContractStatusEnum.APPROVAL.getKey().toString());
				changeService.updateExportStatus(ContractStatusEnum.ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS.getKey().toString(), Long.parseLong(contractFormInfoCopy.getChangeContractId()));
			}
			contractFormInfoCopy = r.getData();
			//注意**因为合同送审时生成合同编号，变更合同编号需要与原合同编号有关联性 即需要覆盖自动生成的合同编号 使用原合同原合同编号加后缀编号(-1+n)
			contractFormInfoCopy.setContractNumber(
				contractFormInfo.getContractNumber().contains("-") ?
					contractFormInfo.getContractNumber().substring(
						0, contractFormInfo.getContractNumber().indexOf("-") + 1) + Integer.parseInt(
						contractFormInfo.getContractNumber().substring(
							contractFormInfo.getContractNumber().lastIndexOf("-"))) + 1 : contractFormInfo.getContractNumber() + "-1");
			r.setMsg(ContractErorrCodeEnum.CHANGE_INDE_DRAFT_APPROVAL.getValue());
			r.setCode(ContractErorrCodeEnum.CHANGE_INDE_DRAFT_APPROVAL.getKey());
		}
		contractFormInfoService.updateById(contractFormInfoCopy);
		return R.data(r.getCode(), ContractFormInfoWrapper.build().entityPV(contractFormInfoCopy), r.getMsg());
	}

	@PostMapping("/multiAddChange")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "多方起草变更新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:multiAddChange')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> multiAddChange(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		R<ContractFormInfoEntity> r = new R<>();
		ContractFormInfoEntity contractFormInfoCopy = BeanUtil.copy(contractFormInfo, ContractFormInfoEntity.class);
		assert contractFormInfoCopy != null;
		//判断合同变更ID为空  说明合同第一次变更 此次不论是暂存还是送审都会保存原合同ID为变更标识 并将变更后合同状态存为草稿或审批中
		if (Func.isBlank(contractFormInfo.getChangeContractId())) {
			contractFormInfoCopy.setId(null);
			contractFormInfoCopy.setFileExportCount(0);
			contractFormInfoCopy.setFileExportCategory(0);
			contractFormInfoCopy.setChangeContractId(contractFormInfo.getId().toString());
			contractFormInfoCopy.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
		} else {
			//变更ID不为空说明有变更操作  此时判断合同状态是否为履约中的合同  如果是  那么进行变更操作  否则判断为变更合同的修改操作
			if (ContractStatusEnum.CONTRACT_PERFORMANCE_STATUS.getKey().toString().equals(contractFormInfo.getContractStatus())) {
				contractFormInfoCopy.setId(null);
				contractFormInfoCopy.setFileExportCount(0);
				contractFormInfoCopy.setFileExportCategory(0);
				contractFormInfoCopy.setChangeContractId(contractFormInfo.getId().toString());
				contractFormInfoCopy.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
			}
		}
		contractFormInfoService.saveOrUpdate(contractFormInfoCopy);
		contractFormInfo.setId(contractFormInfoCopy.getId());
		//统一处理相同数据方法
		contractFormInfo=this.unifiedMethodHandle(contractFormInfo);
		r.setMsg(ContractErorrCodeEnum.CHANGE_MULTI_PARTY_DRAFT_SAVE.getValue());
		r.setCode(ContractErorrCodeEnum.CHANGE_MULTI_PARTY_DRAFT_SAVE.getKey());
		if (ContractStatusEnum.APPROVAL.getKey().toString().equals(contractFormInfo.getContractStatus())) {
			if (ContractTypeEnum.ELECTRONIC_CONTRACT_WE.getKey().toString().equals(contractFormInfo.getContractForm())
				|| ContractTypeEnum.ENTITY_CONTRACT_WE.getKey().toString().equals(contractFormInfo.getContractForm())) {
				r = contractFormInfoService.SingleSign(R.data(contractFormInfo));
			} else {
				r = contractFormInfoService.SingleSignE(R.data(contractFormInfo));
			}
			contractFormInfoCopy = r.getData();
			if (r.getCode() != HttpStatus.OK.value()) {
				contractFormInfoCopy.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
			} else {
				contractFormInfoCopy.setCreateTime(new Date());
				contractFormInfoCopy.setContractStatus(ContractStatusEnum.APPROVAL.getKey().toString());
				changeService.updateExportStatus(ContractStatusEnum.ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS.getKey().toString(), Long.parseLong(contractFormInfoCopy.getChangeContractId()));
			}
			r.setMsg(ContractErorrCodeEnum.CHANGE_MULTI_PARTY_DRAFT_APPROVAL.getValue());
			r.setCode(ContractErorrCodeEnum.CHANGE_MULTI_PARTY_DRAFT_APPROVAL.getKey());
		}
		contractFormInfoService.updateById(contractFormInfoCopy);
		return R.data(r.getCode(), ContractFormInfoWrapper.build().entityPV(contractFormInfoCopy), r.getMsg());
	}

	@PostMapping("/templateChangeSave")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "范本起草变更新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:templateChangeSave')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> templateChangeSave(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		R<ContractFormInfoEntity> r = new R<>();
		TemplateRequestVO template = contractFormInfo.getTemplate();
		List<TemplateFieldEntity> templateFieldList = JSON.parseArray(template.getJson(), TemplateFieldEntity.class);
		JSONObject j = new JSONObject();
		for (TemplateFieldEntity templateField : templateFieldList) {
			j.put(templateField.getFieldName(), templateField.getFieldValue());
		}
		ContractFormInfoEntity contractFormInfoEntity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, contractFormInfoEntity);
		//****************************************变更新增代码START***************************************//
		//判断合同变更ID为空  说明合同第一次变更 此次不论是暂存还是送审都会保存原合同ID为变更标识 并将变更后合同状态存为草稿或审批中
		if (Func.isBlank(contractFormInfo.getChangeContractId())) {
			contractFormInfoEntity.setId(null);
			//清空合同推送的合同正文ID  TextFile为本地合同ID  TextFilePDF为推送到他们的平台的文件ID
			contractFormInfoEntity.setTextFilePdf("");
			contractFormInfoEntity.setTextFile("");
			//清空下载记录
			contractFormInfoEntity.setFileExportCount(0);
			contractFormInfoEntity.setFileExportCategory(0);
			//存入原合同ID 进行关联
			contractFormInfoEntity.setChangeContractId(contractFormInfo.getId().toString());
			contractFormInfoEntity.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
		} else {
			//变更ID不为空说明有变更操作  此时判断合同状态是否为履约中的合同  如果是  那么进行变更操作  否则判断为变更合同的修改操作
			if (ContractStatusEnum.CONTRACT_PERFORMANCE_STATUS.getKey().toString().equals(contractFormInfo.getContractStatus())) {
				contractFormInfoEntity.setId(null);
				//清空合同推送的合同正文ID  TextFile为本地合同ID  TextFilePDF为推送到他们的平台的文件ID
				contractFormInfoEntity.setTextFilePdf("");
				contractFormInfoEntity.setTextFile("");
				//清空下载记录
				contractFormInfoEntity.setFileExportCount(0);
				contractFormInfoEntity.setFileExportCategory(0);
				//存入原合同ID 进行关联
				contractFormInfoEntity.setChangeContractId(contractFormInfo.getId().toString());
				contractFormInfoEntity.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
			}
		}
		//*****************************************变更新增代码END***************************************//
		Long id = TemplateSaveUntil.templateSave(contractFormInfoEntity, template, j);
		contractFormInfo.setId(id);
		//统一处理相同数据方法
		contractFormInfo=this.unifiedMethodHandle(contractFormInfo);
		//保存合同和关联表 这个方法有问题
		contractFormInfoEntity = contractFormInfoService.templateDraft(contractFormInfoEntity, template.getJson());
		//页面用这个字段来判断是否提交
		r.setMsg(ContractErorrCodeEnum.CHANGE_TEMPLATE_PARTY_DRAFT_SAVE.getValue());
		r.setCode(ContractErorrCodeEnum.CHANGE_TEMPLATE_PARTY_DRAFT_SAVE.getKey());
		if (ContractStatusEnum.APPROVAL.getKey().toString().equals(template.getBean())) {
			//导出pdf文件
			FileVO filevo = templateExportUntil.templateSave(contractFormInfoEntity, template, contractFormInfoEntity.getJson(), j);
			contractFormInfoEntity.setContractStatus(ContractStatusEnum.APPROVAL.getKey().toString());
			contractFormInfoEntity.setTextFile(filevo.getId() + ",");
			contractFormInfoEntity.setTextFilePdf(filevo.getId() + ",");
			contractFormInfoEntity.setContractStatus(template.getBean());
			contractFormInfoEntity.setFilePdf(filevo.getDomain());
			//处理电子签章和oa流程
			r = contractFormInfoService.SingleSign(R.data(contractFormInfo));
			contractFormInfoEntity = r.getData();
			if (r.getCode() != HttpStatus.OK.value()) {
				contractFormInfoEntity.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
			} else {
				contractFormInfoEntity.setCreateTime(new Date());
				contractFormInfoEntity.setContractStatus(ContractStatusEnum.APPROVAL.getKey().toString());
				//送审成功修改原合同状态
				changeService.updateExportStatus(ContractStatusEnum.ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS.getKey().toString(), Long.parseLong(contractFormInfoEntity.getChangeContractId()));
			}
			//覆盖自动生成的合同编号
			contractFormInfoEntity.setContractNumber(
				contractFormInfo.getContractNumber().contains("-") ?
					contractFormInfo.getContractNumber().substring(
						0, contractFormInfo.getContractNumber().indexOf("-") + 1) + Integer.parseInt(
						contractFormInfo.getContractNumber().substring(
							contractFormInfo.getContractNumber().lastIndexOf("-"))) + 1 : contractFormInfo.getContractNumber() + "-1");
			r.setMsg(ContractErorrCodeEnum.CHANGE_TEMPLATE_PARTY_DRAFT_APPROVAL.getValue());
			r.setCode(ContractErorrCodeEnum.CHANGE_TEMPLATE_PARTY_DRAFT_APPROVAL.getKey());
		}
		contractFormInfoService.updateById(contractFormInfoEntity);
		return R.data(r.getCode(), ContractFormInfoWrapper.build().entityPV(contractFormInfoEntity), r.getMsg());
	}

	/**
	 * 下载文件-实现预览在线预览功能
	 */
	@GetMapping("/downloadFiles")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "下载文件", notes = "传入文件id")
	public void downloadFiles(@RequestParam String id, HttpServletResponse response) {
		try {
			FileEntity fileEntity = fileClient.getById(Long.valueOf(id)).getData();
			String fileName = fileEntity.getGenerateName();
			int index = fileEntity.getName().lastIndexOf(".");
			String suffix = fileEntity.getName().substring(index);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String pathname = ftlPath + fileEntity.getName().substring(0, index) + df.format(new Date()) + suffix;
			//创建空docx文件
			File filePDF = new File(pathname);
			//建立输出字节流
			FileOutputStream fosx = null;
			try {
				fosx = new FileOutputStream(filePDF);
				//将根据URL获取到的数据流写到空docx文件
				fosx.write(AsposeWordToPdfUtils.getUrlFileData(fileEntity.getLink()));
				fosx.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String pdf = ftlPath + fileEntity.getName().substring(0, index) + df.format(new Date()) + ".pdf";
			AsposeWordToPdfUtils.doc2pdf(pathname, pdf);
			File fileDoc = new File(pathname);
			fileDoc.delete();
			InputStream object = new FileInputStream(pdf);
			byte buf[] = new byte[1024];
			int length = 0;
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileEntity.getName(), "UTF-8"));
			response.setContentType("application/octet-stream");
			response.setCharacterEncoding("utf-8");
			OutputStream outputStream = response.getOutputStream();
			while ((length = object.read(buf)) > 0) {
				outputStream.write(buf, 0, length);
			}
			outputStream.close();
		} catch (Exception ex) {
			System.out.println("导出失败");
		}
	}


	@PostMapping("/importBatchDraft")
	@ApiOperationSupport(order = 12)
	@ApiOperation(value = "批量导入", notes = "传入excel、大小类、范本json")
	public R importBatchDraft(ContractImportBatchDraftRequest contractImportBatchDraftRequest) {
		contractFormInfoService.batchDraftingImport(contractImportBatchDraftRequest);
		return R.success("操作成功");
	}

	@PostMapping("/importBatchDraftUp")
	@ApiOperationSupport(order = 12)
	@ApiOperation(value = "批量导入更新", notes = "传入ContractFormInfoRequestVO")
	@Transactional(rollbackFor = Exception.class)
	public R importBatchDraftUp(@RequestBody ContractFormInfoRequestVO contractFormInfo) {
		contractFormInfoService.batchDraftingImportUp(contractFormInfo);
		return R.success("操作成功");
	}
}
