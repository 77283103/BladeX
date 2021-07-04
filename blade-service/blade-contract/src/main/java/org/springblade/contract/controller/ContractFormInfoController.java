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
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.contract.entity.*;
import org.springblade.contract.enums.ContractStatusEnum;
import org.springblade.contract.mapper.ContractMultPaymenMapper;
import org.springblade.contract.mapper.DraftContractCounterpartMapper;
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
import org.springblade.core.tool.jackson.JsonUtil;
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
import org.springframework.web.multipart.MultipartFile;

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
	//EKP推送信息
	@Autowired
	private IContractCounterpartService counterpartService;
	@Autowired
	private IAbutmentClient abutmentClient;
	@Autowired
	private IContractFormInfoService contractFormInfoService;
	@Autowired
	private IContractPerformanceService performanceService;
	@Autowired
	private IContractAccordingService accordingService;
	@Autowired
	private IContractBondService contractBondService;
	@Autowired
	private IContractPerformanceColPayService contractPerformanceColPayService;
	@Autowired
	private IContractBondPlanService contractBondPlanService;
	@Autowired
	private IContractChangeService changeService;
	@Autowired
	private IDraftContractCounterparService draftContractCounterparService;
	@Autowired
	private DraftContractCounterpartMapper draftContractCounterpartMapper;
	@Autowired
	private IContractMultPaymenService contractMultPaymenService;
	@Autowired
	private ContractMultPaymenMapper contractMultPaymenMapper;
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
	@Autowired
	private IContractSealService iContractSealService;
	private static final String CHANGE_REVIEW_STATUS = "10";
	private static final String APPROVE_REVIEW_STATUS = "10";
	private static final String CONTRACT_REVIEW_STATUS = "20";
	private static final Integer FILE_EXPORT_CATEGORY = 1;
	private static final String CONTRACT_AUDIT_QUALITY = "30";
	private static final String CONTRACT_EXPORT_STATUS = "40";
	private static final String CONTRACT_SEAL_USING_INFO_STATUS = "50";
	private static final String CONTRACT_SIGNING_STATUS = "60";
	private static final String CONTRACT_PERFORMANCE_STATUS = "70";
	private static final String CONTRACT_ARCHIVE_STATUS = "110";
	private static final String CONTRACT_ASSESSMENT_STATUS = "100";
	private static final String ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS = "75";
	@Value("${api.file.ftlPath}")
	private String ftlPath;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:detail')")
	public R<ContractFormInfoResponseVO> detail(@RequestParam Long id) {
		ContractFormInfoResponseVO detail = contractFormInfoService.getById(id);
		return R.data(detail);
	}

	/**
	 * 合同变更历史详情
	 */
	@GetMapping("/version")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:version')")
	public R<ContractFormInfoResponseVO> version(@RequestParam Long id) {
		ContractFormInfoResponseVO version = contractFormInfoService.getByChangeHistoryId(id);
		return R.data(version);
	}

	/**
	 * 分页
	 */
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

	/**
	 * 統計分析查詢列表
	 */
	@GetMapping("/statistics")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:statistics')")
	public R<IPage<ContractFormInfoEntity>> statistics(ContractFormInfoRequestVO contractFormInfo, Query query) {
		IPage<ContractFormInfoEntity> pages = contractFormInfoService.statisticsList(Condition.getPage(query), contractFormInfo);
		return R.data(pages);
	}

	/**
	 * 用印分页
	 */
	@GetMapping("/listSealInfo")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入ContractFormInfoRequestVO")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:listSealInfo')")
	public R<IPage<ContractFormInfoResponseVO>> listSealInfo(ContractFormInfoRequestVO contractFormInfoRequestVO, Query query) {
		IPage<ContractFormInfoEntity> pages = contractFormInfoService.pageListSealInfo(Condition.getPage(query), contractFormInfoRequestVO);
		return R.data(ContractFormInfoWrapper.build().entityPVPage(pages));
	}


	/**
	 * 多方起草新增
	 */
	@PostMapping("/multiAdd")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:multiAdd')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> multiAdd(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		R<ContractFormInfoEntity> r = null;
		contractFormInfo.setContractSoure("20");
		ContractFormInfoEntity entity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, entity);
		if (Func.isEmpty(contractFormInfo.getId())) {
			contractFormInfoService.save(entity);
			contractFormInfo.setId(entity.getId());
			//增加履约计划信息
			perServiceContentService.addPerData(Func.isEmpty(contractFormInfo.getPerServiceContentList()) ? null :
				contractFormInfo.getPerServiceContentList().get(0), entity.getId());
			//增加履约收付款信息
			perCollectPayService.addListData(contractFormInfo.getPerCollectPayList(), entity.getId());
		} else {
			contractFormInfoService.updateById(entity);
		}
		/*保存多方向对方身份信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getDraftContractCounterpartList())) {
			draftContractCounterpartMapper.deleteDraftCounterpart(contractFormInfo.getId());
			ContractSealEntity se=iContractSealService.getByFdNo(entity.getSealName());
			contractFormInfo.getDraftContractCounterpartList().forEach(dcl -> {
				if (se.getFdFactname().equals(dcl.getSubsidiaryPerson())){
					entity.setContractRoles(dcl.getCounterpartIdentity());
				}
				dcl.setContractId(contractFormInfo.getId().toString());
				draftContractCounterparService.save(dcl);
			});
		}
		/*保存相对方收付款信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getMultPaymenEntityList())) {
			contractMultPaymenMapper.deleteMult(contractFormInfo.getId());
			contractFormInfo.getMultPaymenEntityList().forEach(mult -> {
				mult.setCurrencyCategory(contractFormInfo.getCurrencyCategory());
				mult.setContractId(contractFormInfo.getId().toString());
				contractMultPaymenService.save(mult);
			});
		}
		/*保存子公司信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getContractSeal())) {
			contractFormInfoService.saveContractSeal(contractFormInfo);
		}
		/*保存相对方信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())) {
			contractFormInfoService.saveCounterpart(contractFormInfo);
		}
		/*保存保证金信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())) {
			//村保证金ID
			List<Long> list = new ArrayList<>();
			ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
			//删除保证金库脏数据
			contractBondService.deleteByContractId(contractFormInfo.getId());
			//删除保证金履约计划脏数据
			contractBondPlanService.deleteByContractId(contractFormInfo.getId());
			for (ContractBondEntity contractBondEntity : contractFormInfo.getContractBond()) {
				BeanUtil.copy(contractBondEntity, contractBondPlan);
				if (Func.isEmpty(contractBondEntity.getId())) {
					contractBondService.save(contractBondEntity);
				} else {
					contractBondEntity.setId(null);
					contractBondService.save(contractBondEntity);
				}
				//保存保证金履约计划
				contractBondPlan.setContractId(contractFormInfo.getId());
				contractBondPlan.setId(null);
				contractBondPlanService.save(contractBondPlan);
				list.add(contractBondEntity.getId());
			}
			contractBondService.saveBond(list, contractFormInfo.getId());
		}
		/*保存依据信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getAccording())) {
			contractFormInfoService.saveAccording(contractFormInfo);
		}
		/*保存履约信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())) {
			//删除履约信息脏数据
			performanceService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceList().forEach(performance -> {
				performance.setContractId(contractFormInfo.getId());
				performanceService.save(performance);
			});
		}
		/*保存履约计划收付款*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())) {
			//删除收付款脏数据
			contractPerformanceColPayService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceColPayList().forEach(performanceColPay -> {
				performanceColPay.setContractId(contractFormInfo.getId());
				contractPerformanceColPayService.save(performanceColPay);
			});
		}
		//开始接口处理
		if (ContractStatusEnum.APPROVAL.getKey().toString().equals(entity.getContractStatus())) {
			//处理电子签章和oa流程
			if ("1".equals(entity.getContractForm()) || "2".equals(entity.getContractForm())) {
				r = contractFormInfoService.SingleSign(R.data(entity));
			} else {
				r = contractFormInfoService.SingleSignE(R.data(entity));
			}
			if (r.getCode() != HttpStatus.OK.value()) {
				entity.setContractStatus(CHANGE_REVIEW_STATUS);
				contractFormInfoService.updateById(entity);
				r.setData(ContractFormInfoWrapper.build().entityPV(entity));
				return r;
			}
			r.getData().setCreateTime(new Date());
			contractFormInfoService.updateById(r.getData());
		}
		return R.data(ContractFormInfoWrapper.build().entityPV(entity));
	}
	/**
	 * 独立起草新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:add')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> save(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		log.info("独立起草新增开始:{}", JsonUtil.toJson(contractFormInfo));
		R<ContractFormInfoEntity> r = null;
		contractFormInfo.setContractSoure("10");
		ContractFormInfoEntity entity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, entity);
		if (Func.isEmpty(contractFormInfo.getId())) {
			contractFormInfoService.save(entity);
			//增加履约计划信息
			perServiceContentService.addPerData(Func.isEmpty(contractFormInfo.getPerServiceContentList()) ? null :
				contractFormInfo.getPerServiceContentList().get(0), entity.getId());
			//增加履约收付款信息
			perCollectPayService.addListData(contractFormInfo.getPerCollectPayList(), entity.getId());
		} else {
			contractFormInfoService.updateById(entity);
		}
		//增加履约计划信息
		//perServiceContentService.addPerData(contractFormInfo.getPerServiceContentList().get(0),entity.getId());
		contractFormInfo.setId(entity.getId());
		/*保存相对方信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())) {
			contractFormInfoService.saveCounterpart(contractFormInfo);
		}
		/*保存保证金信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())) {
			List<Long> list = new ArrayList<>();
			ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
			//删除保证金库脏数据
			contractBondService.deleteByContractId(contractFormInfo.getId());
			//删除保证金履约计划脏数据
			contractBondPlanService.deleteByContractId(contractFormInfo.getId());
			for (ContractBondEntity contractBondEntity : contractFormInfo.getContractBond()) {
				BeanUtil.copy(contractBondEntity, contractBondPlan);
				if (Func.isEmpty(contractBondEntity.getId())) {
					contractBondService.save(contractBondEntity);
				}
				//保存保证金履约计划
				contractBondPlan.setContractId(contractFormInfo.getId());
				contractBondPlan.setId(null);
				contractBondPlanService.save(contractBondPlan);
				list.add(contractBondEntity.getId());
			}
			contractBondService.saveBond(list, contractFormInfo.getId());
		}
		/*保存依据信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getAccording())) {
			contractFormInfoService.saveAccording(contractFormInfo);
		}
		/*保存履约信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())) {
			//删除履约信息脏数据
			performanceService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceList().forEach(performance -> {
				performance.setContractId(contractFormInfo.getId());
				performanceService.save(performance);
			});
		}
		/*保存履约计划收付款*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())) {
			//删除收付款脏数据
			contractPerformanceColPayService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceColPayList().forEach(performanceColPay -> {
				performanceColPay.setContractId(contractFormInfo.getId());
				contractPerformanceColPayService.save(performanceColPay);
			});
		}
		//开始接口处理
		log.info("独立起草新增-处理电子签章和oa流程：{}", entity.getContractStatus());
		if ("20".equals(entity.getContractStatus())) {
			//处理电子签章和oa流程
			if ("1".equals(entity.getContractForm()) || "2".equals(entity.getContractForm())) {
				r = contractFormInfoService.SingleSign(R.data(entity));
			} else {
				r = contractFormInfoService.SingleSignE(R.data(entity));
			}
			log.info("独立起草新增-处理电子签章和oa流程结果:{}", JsonUtil.toJson(r));
			if (r.getCode() != HttpStatus.OK.value()) {
				entity.setContractStatus(CHANGE_REVIEW_STATUS);
				contractFormInfoService.updateById(entity);
				r.setData(ContractFormInfoWrapper.build().entityPV(entity));
				return r;
			}
			r.getData().setCreateTime(new Date());
			contractFormInfoService.updateById(r.getData());
		}
		return R.data(ContractFormInfoWrapper.build().entityPV(entity));
	}


	/**
	 * 范本起草新增
	 */
	@PostMapping("/templateSave")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:templateSave')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> templateSave(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		R<ContractFormInfoEntity> r = null;
		/*List<TemplateFieldEntity> templateFieldList = JSON.parseArray(template.getJson(), TemplateFieldEntity.class);
		JSONObject j = new JSONObject();*/
		//把json串转换成一个对象
		TemplateRequestVO template = contractFormInfo.getTemplate();
		List<TemplateFieldEntity> templateFieldList = JSON.parseArray(template.getJson(), TemplateFieldEntity.class);
		JSONObject j = new JSONObject();
		for (TemplateFieldEntity templateField : templateFieldList) {
			j.put(templateField.getFieldName(), templateField.getFieldValue());
		}
		ContractFormInfoEntity contractFormInfoEntity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, contractFormInfoEntity);
		/*if(Func.isEmpty(contractFormInfoEntity.getContractTemplateId())){
			contractFormInfoEntity.setContractTemplateId(contractFormInfo.getContractListId());
		}*/
		Long id = TemplateSaveUntil.templateSave(contractFormInfoEntity, template, j);
		contractFormInfo.setId(id);
		/*保存相对方信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())) {
			contractFormInfoService.saveCounterpart(contractFormInfo);
		}
		/*保存保证金信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())) {
			List<Long> list = new ArrayList<>();
			ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
			//删除保证金库脏数据
			contractBondService.deleteByContractId(contractFormInfo.getId());
			//删除保证金履约计划脏数据
			contractBondPlanService.deleteByContractId(contractFormInfo.getId());
			for (ContractBondEntity contractBondEntity : contractFormInfo.getContractBond()) {
				BeanUtil.copy(contractBondEntity, contractBondPlan);
				if (Func.isEmpty(contractBondEntity.getId())) {
					contractBondService.save(contractBondEntity);
				}
				//保存保证金履约计划
				contractBondPlan.setContractId(contractFormInfo.getId());
				contractBondPlan.setId(null);
				contractBondPlanService.save(contractBondPlan);
				list.add(contractBondEntity.getId());
			}
			contractBondService.saveBond(list, contractFormInfo.getId());
		}
		/*保存依据信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getAccording())) {
			contractFormInfoService.saveAccording(contractFormInfo);
		}
		/*保存履约信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())) {
			//删除履约信息脏数据
			performanceService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceList().forEach(performance -> {
				performance.setContractId(contractFormInfo.getId());
				performanceService.save(performance);
			});
		}
		/*保存履约计划收付款*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())) {
			//删除收付款脏数据
			contractPerformanceColPayService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceColPayList().forEach(performanceColPay -> {
				performanceColPay.setContractId(contractFormInfo.getId());
				contractPerformanceColPayService.save(performanceColPay);
			});
		}
		//ContractFormInfoEntity contractFormInfoEntity = JSONObject.toJavaObject(j, ContractFormInfoEntity.class);
		//保存合同和关联表 这个方法有问题
		contractFormInfoEntity = contractFormInfoService.templateDraft(contractFormInfoEntity, template.getJson());
		//页面用这个字段来判断是否提交
		if ("20".equals(template.getBean())) {
			//导出pdf文件
			FileVO filevo = templateExportUntil.templateSave(contractFormInfoEntity, template, contractFormInfoEntity.getJson(), j);
			contractFormInfoEntity.setContractStatus("20");
			contractFormInfoEntity.setTextFile(filevo.getId() + ",");
			contractFormInfoEntity.setTextFilePdf(filevo.getId() + ",");
			contractFormInfoEntity.setContractStatus(template.getBean());
			contractFormInfoEntity.setFilePDF(filevo.getDomain());
			r = contractFormInfoService.SingleSign(R.data(contractFormInfoEntity));
			if (r.getCode() != HttpStatus.OK.value()) {
				r.setData(ContractFormInfoWrapper.build().entityPV(contractFormInfoEntity));
				return r;
			}
			r.getData().setCreateTime(new Date());
			contractFormInfoEntity = r.getData();
		}
		assert r != null;
		contractFormInfoService.updateById(contractFormInfoEntity);
		//增加履约计划信息
		perServiceContentService.addPerData(Func.isEmpty(contractFormInfo.getPerServiceContentList()) ? null :
			contractFormInfo.getPerServiceContentList().get(0), contractFormInfoEntity.getId());
		//增加履约收付款信息
		perCollectPayService.addListData(contractFormInfo.getPerCollectPayList(), contractFormInfoEntity.getId());
		return R.data(ContractFormInfoWrapper.build().entityPV(contractFormInfoEntity));
	}

	/**
	 * 判断电子签章和关键字是否存在
	 *
	 * @return
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
			//String json = contractFormInfoService.templateDraft(contractFormInfoEntity, template.getJson());
			//导出pdf文件
			files = templateExportUntil.templateSave(contractFormInfoEntity, template, template.getJson(), j);

		} else {
			List<FileVO> list = fileClient.getByIds(contractFormInfo.getTextFile().trim()).getData();
			files = list.get(0);
		}
		return R.data(files);
	}



	@PostMapping("/importBatchDraft")
	@ApiOperationSupport(order = 12)
	@ApiOperation(value = "批量导入", notes = "传入excel、大小类、范本json")
	public R importBatchDraft(ContractImportBatchDraftRequest contractImportBatchDraftRequest){
		contractFormInfoService.batchDraftingImport(contractImportBatchDraftRequest);
		return R.success("操作成功");
	}


	/**
	 * 批量导入，更新
	 */
	@PostMapping("/importBatchDraftUp")
	@ApiOperationSupport(order = 12)
	@ApiOperation(value = "批量导入更新", notes = "传入ContractFormInfoRequestVO")
	@Transactional(rollbackFor = Exception.class)
	public R importBatchDraftUp(@RequestBody ContractFormInfoRequestVO contractFormInfo) {
		contractFormInfoService.batchDraftingImportUp(contractFormInfo);
		return R.success("操作成功");
	}


	/**
	 * 批量导入
	 */
	//@PostMapping("/importBatchDraft")
	@ApiOperationSupport(order = 12)
	@ApiOperation(value = "导入合同", notes = "传入excel")
	@Transactional(rollbackFor = Exception.class)
	public R importUser(MultipartFile file, String json, String contractTemplateId, String contractBigCategory, String contractSmallCategory) {

//		//读取Excal 两个sheet数据
//		List<ContractFormInfoImporter> read = ExcelUtil.read(file, 0, 5, ContractFormInfoImporter.class);
//		List<ContractFormInfoImporterEx> read2 = ExcelUtil.read(file, 1, 1, ContractFormInfoImporterEx.class);
//		read.forEach(readEx -> {
//			if (("≤3年").equals(readEx.getContractPeriod())) {
//				readEx.setContractPeriod("小于等于3年");
//			} else if ((">3年").equals(readEx.getContractPeriod())) {
//				readEx.setContractPeriod("大于3年");
//			}
//			if (("票期<10天").equals(readEx.getColPayTerm())) {
//				readEx.setColPayTerm("票期小于10天");
//			} else if (("10天≤票期<45天").equals(readEx.getColPayTerm())) {
//				readEx.setColPayTerm("票期大于等于10天小于45天");
//			} else if (("45天≤票期").equals(readEx.getColPayTerm())) {
//				readEx.setColPayTerm("票期大于等于45天");
//			}
//			//contract_form合同形式
//			R<List<DictBiz>> contract_form = bizClient.getList("contract_form");
//			List<DictBiz> dataBiz = contract_form.getData();
//			dataBiz.forEach(contractForm -> {
//				if (readEx.getContractForm().equals(contractForm.getDictValue())) {
//					readEx.setContractForm(contractForm.getDictKey());
//				}
//			});
//			//收付款-收付款条件
//			R<List<DictBiz>> col_pay_term = bizClient.getList("col_pay_term");
//			List<DictBiz> dataBiz1 = col_pay_term.getData();
//			dataBiz1.forEach(colPayTerm -> {
//				if (readEx.getColPayType().equals(colPayTerm.getDictValue())) {
//					readEx.setColPayType(colPayTerm.getId().toString());
//				} else if (readEx.getColPayTerm().equals(colPayTerm.getDictValue())) {
//					readEx.setColPayTerm(colPayTerm.getId().toString());
//				}
//			});
//			//contract_period  合同期限
//			R<List<DictBiz>> contract_period = bizClient.getList("contract_period");
//			List<DictBiz> dataBiz2 = contract_period.getData();
//			dataBiz2.forEach(contractPeriod -> {
//				if (readEx.getContractPeriod().equals(contractPeriod.getDictValue())) {
//					readEx.setContractPeriod(contractPeriod.getDictKey());
//				}
//			});
//		});
//		contractFormInfoService.importContractFormInfo(read, file, json, contractTemplateId, contractBigCategory, contractSmallCategory);
		return R.success("操作成功");
	}

	/**
	 * 批量送审
	 */
	@PostMapping("/submitBatch")
	@ApiOperationSupport(order = 12)
	@ApiOperation(value = "批量送审", notes = "传入依据和合同ids")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> submitBatch(@Valid @RequestBody ContractAccordingRequestVO according) {
		ContractAccordingEntity entity = new ContractAccordingEntity();
		ContractFormInfoEntity infoEntity = new ContractFormInfoEntity();
		//保存依据信息，把依据信息替换到json串中
		for (String id : according.getContractIds()) {
			BeanUtil.copy(according, entity);
			entity.setContractId(Long.parseLong(id));
			accordingService.save(entity);
			JSONObject jsonObj = JSON.parseObject(JSON.toJSONString(entity));
			infoEntity = contractFormInfoService.getById(id);
			String json = infoEntity.getJson();
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
			json = objects.toJSONString();
			infoEntity.setJson(json);
			infoEntity.setContractStatus("20");
			contractFormInfoService.saveOrUpdate(infoEntity);
		}
		return R.data(ContractFormInfoWrapper.build().entityPV(infoEntity));
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
			changeService.updateExportStatus(ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS, Long.parseLong(entity.getChangeContractId()));
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
		contractFormInfoService.textExportCount(id, fileExportCount, FILE_EXPORT_CATEGORY);
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		//附加条件解决BUG
		if (CONTRACT_AUDIT_QUALITY.equals(infoEntity.getContractStatus())) {
			infoEntity.setContractStatus(CONTRACT_EXPORT_STATUS);
		}
		infoEntity.setFileExportCount(fileExportCount);
		infoEntity.setFileExportCategory(FILE_EXPORT_CATEGORY);
		boolean status=contractFormInfoService.updateById(infoEntity);
		if (status){
			BladeUser user = AuthUtil.getUser();
			ContractFileDownloadLogEntity fileDownloadLogEntity=new ContractFileDownloadLogEntity();
			R<User> info=userClient.userInfoById(user.getUserId());
			if (info.getCode()== HttpStatus.OK.value() ){
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
		contractFormInfoService.textExportCount(id, fileExportCount, FILE_EXPORT_CATEGORY);
		infoEntity.setFileExportCount(fileExportCount);
		//附加条件解决BUG
		if (CONTRACT_AUDIT_QUALITY.equals(infoEntity.getContractStatus())) {
			infoEntity.setContractStatus(CONTRACT_EXPORT_STATUS);
		}
		contractFormInfoService.updateById(infoEntity);
		ContractFormInfoResponseVO formInfoResponseVO = ContractFormInfoWrapper.build().entityPV(infoEntity);
		return R.data(formInfoResponseVO);
	}


	/**
	 * 审核后修改状态为待导出
	 * 20>30
	 */
	@PostMapping("/updateAuditStatus")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:updateAuditStatus')")
	public R auditStatus(@RequestParam Long id) {
		ContractFormInfoEntity infoEntity = contractFormInfoService.getById(id);
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		infoEntity.setContractStatus(CONTRACT_AUDIT_QUALITY);
		return R.status(contractFormInfoService.updateById(infoEntity));
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
	 * 签订后修改状态待归档
	 * 50>60
	 */
	@PostMapping("/updateSigningStatus")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:updateSigningStatus')")
	public R signingStatus(@RequestParam Long id) {
		ContractFormInfoEntity infoEntity = contractFormInfoService.getById(id);
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		infoEntity.setContractStatus(CONTRACT_SIGNING_STATUS);
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

	/**
	 * 合同统计分析分页
	 */
	@GetMapping("/listStatistics")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:listStatistics')")
	public R<IPage<ContractFormInfoResponseVO>> listStatistics(ContractFormInfoRequestVO contractFormInfo, Query query) {
		IPage<ContractFormInfoResponseVO> pages = contractFormInfoService.pageListStatistics(Condition.getPage(query), contractFormInfo);
		return R.data(pages);
	}

	/**
	 * 导出excel
	 *
	 * @param formInfoEntityList
	 * @param response
	 */
	@PostMapping("/exportTargetDataResultStatistics")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "导出", notes = "")
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

	/**
	 * 导出导出相对方数据excel
	 * @param response
	 */
	@PostMapping("/exportCounterpart")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "导出相对方数据", notes = "")
	public void exportCounterpart(HttpServletResponse response) {
		List<ContractCounterpartEntity> ce=counterpartService.list();
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
				cloumns.add(1==counterpartEntity.getStatus()?"使用中":"未使用");
				/*是否删除*/
				cloumns.add(counterpartEntity.getIsDeleted()==0?"正常":"已删除");
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
	/**
	 * 独立起草变更
	 */
	@PostMapping("/addChange")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "变更", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:addChange')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> saveChange(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		R<ContractFormInfoEntity> r;
		ContractFormInfoEntity entity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, entity);
		// 因为合同变更为一对一版本  所以根据ID唯一性查询是否有变更合同信息 结果为空对象说明没有变更信息即**新增**
		// 否则已有变更信息即**修改**
		if (CONTRACT_PERFORMANCE_STATUS.equals(entity.getContractStatus())) {
			//判断变更是否已经暂存，未暂存过直接保存信息
			entity.setId(null);
			//存入原合同ID 进行关联
			entity.setChangeContractId(contractFormInfo.getId().toString());
			//清空合同的文本导出次数记录
			entity.setFileExportCount(0);
			entity.setFileExportCategory(0);
			if (APPROVE_REVIEW_STATUS.equals(entity.getSubmitStatus())) {
				entity.setContractStatus(CONTRACT_REVIEW_STATUS);
			} else {
				entity.setContractStatus(CHANGE_REVIEW_STATUS);
			}
			contractFormInfoService.save(entity);
		} else if (CHANGE_REVIEW_STATUS.equals(entity.getContractStatus())) {
			entity.setChangeCategory(CHANGE_REVIEW_STATUS);
			if (APPROVE_REVIEW_STATUS.equals(entity.getSubmitStatus())) {
				entity.setContractStatus(CONTRACT_REVIEW_STATUS);
			} else {
				entity.setContractStatus(CHANGE_REVIEW_STATUS);
			}
			contractFormInfoService.updateById(entity);
		}
		//保存变更合同ID 并关联保存一下关联信息
		contractFormInfo.setId(entity.getId());
		/*保存相对方信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())) {
			contractFormInfoService.saveCounterpart(contractFormInfo);
		}
		/*保存保证金信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())) {
			List<Long> list = new ArrayList<>();
			ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
			//删除保证金库脏数据
			contractBondService.deleteByContractId(contractFormInfo.getId());
			//删除保证金履约计划脏数据
			contractBondPlanService.deleteByContractId(contractFormInfo.getId());
			for (ContractBondEntity contractBondEntity : contractFormInfo.getContractBond()) {
				BeanUtil.copy(contractBondEntity, contractBondPlan);
				if (Func.isEmpty(contractBondEntity.getId())) {
					contractBondService.save(contractBondEntity);
				} else {
					contractBondPlan.setId(null);
				}
				//保存保证金履约计划
				contractBondPlan.setContractId(contractFormInfo.getId());
				contractBondPlanService.save(contractBondPlan);
				list.add(contractBondEntity.getId());
			}
			contractBondService.saveBond(list, contractFormInfo.getId());
		}
		/*保存依据信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getAccording())) {
			contractFormInfoService.saveAccording(contractFormInfo);
		}
		/*保存履约信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())) {
			//删除履约信息脏数据
			performanceService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceList().forEach(performance -> {
				if (Func.isNotEmpty(performance.getId())) {
					performance.setId(null);
				}
				performance.setContractId(contractFormInfo.getId());
				performanceService.save(performance);
			});
		}
		/*保存履约计划收付款*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())) {
			//删除收付款脏数据
			contractPerformanceColPayService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceColPayList().forEach(performanceColPay -> {
				if (Func.isNotEmpty(performanceColPay.getId())) {
					performanceColPay.setId(null);
				}
				performanceColPay.setContractId(contractFormInfo.getId());
				contractPerformanceColPayService.save(performanceColPay);
			});
		}
		if (CONTRACT_REVIEW_STATUS.equals(entity.getContractStatus())) {
			//处理电子签章和oa流程
			r = contractFormInfoService.SingleSign(R.data(entity));
			if (r.getCode() != HttpStatus.OK.value()) {
				r.setData(ContractFormInfoWrapper.build().entityPV(entity));
				return r;
			}
			r.getData().setCreateTime(new Date());
			entity = r.getData();
			entity.setChangeCategory(CHANGE_REVIEW_STATUS);
			//注意**因为合同送审时生成合同编号，变更合同编号需要与原合同编号有关联性 即需要覆盖自动生成的合同编号 使用原合同原合同编号加后缀编号(-1+n)
			entity.setContractNumber(
				contractFormInfo.getContractNumber().contains("-") ?
					contractFormInfo.getContractNumber().substring(
						0, contractFormInfo.getContractNumber().indexOf("-") + 1) + Integer.parseInt(
						contractFormInfo.getContractNumber().substring(
							contractFormInfo.getContractNumber().lastIndexOf("-"))) + 1 : contractFormInfo.getContractNumber() + "-1");
			contractFormInfoService.updateById(entity);
		}
		//判断满足已变更新合同的条件 修改原合同 变更状为变更申请中 送审状态为送审中 合同状态变更申请中
		if (CHANGE_REVIEW_STATUS.equals(entity.getChangeCategory()) && APPROVE_REVIEW_STATUS.equals(entity.getSubmitStatus())
			&& CONTRACT_REVIEW_STATUS.equals(entity.getContractStatus())) {
			changeService.updateExportStatus(ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS, Long.parseLong(entity.getChangeContractId()));
		}
		return R.data(ContractFormInfoWrapper.build().entityPV(entity));
	}


	/**
	 * 范本起草变更新增
	 */
	@PostMapping("/templateChangeSave")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "范本变更", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:templateChangeSave')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> templateChangeSave(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		R<ContractFormInfoEntity> r;
		//把json串转换成一个对象
		//获取表单模板对象
		TemplateRequestVO template = contractFormInfo.getTemplate();
		//将范本模板JSON串转化成范本模板对象
		List<TemplateFieldEntity> templateFieldList = JSON.parseArray(template.getJson(), TemplateFieldEntity.class);
		JSONObject j = new JSONObject();
		for (TemplateFieldEntity templateField : templateFieldList) {
			j.put(templateField.getFieldName(), templateField.getFieldValue());
		}
		ContractFormInfoEntity contractFormInfoEntity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, contractFormInfoEntity);
		//****************************************变更新增代码START***************************************//
		if (CONTRACT_PERFORMANCE_STATUS.equals(contractFormInfo.getContractStatus())) {
			contractFormInfoEntity.setId(null);
			//存入原合同ID 进行关联
			contractFormInfoEntity.setChangeContractId(contractFormInfo.getId().toString());
			//清空合同的文本导出次数记录
			contractFormInfoEntity.setFileExportCount(0);
			contractFormInfoEntity.setFileExportCategory(0);
			//清空合同推送的合同正文ID  TextFile为本地合同ID  TextFilePDF为推送到他们的平台的文件ID
			contractFormInfoEntity.setTextFilePdf("");
			contractFormInfoEntity.setTextFile("");
		}
		//*****************************************变更新增代码END***************************************//
		Long id = TemplateSaveUntil.templateSave(contractFormInfoEntity, template, j);
		contractFormInfo.setId(id);
		/*保存相对方信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())) {
			contractFormInfoService.saveCounterpart(contractFormInfo);
		}
		/*保存保证金信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())) {
			List<Long> list = new ArrayList<>();
			ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
			//删除保证金库脏数据
			contractBondService.deleteByContractId(contractFormInfo.getId());
			//删除保证金履约计划脏数据
			contractBondPlanService.deleteByContractId(contractFormInfo.getId());
			for (ContractBondEntity contractBondEntity : contractFormInfo.getContractBond()) {
				BeanUtil.copy(contractBondEntity, contractBondPlan);
				if (Func.isEmpty(contractBondEntity.getId())) {
					contractBondService.save(contractBondEntity);
				}
				//保存保证金履约计划
				contractBondPlan.setContractId(contractFormInfo.getId());
				contractBondPlan.setId(null);
				contractBondPlanService.save(contractBondPlan);
				list.add(contractBondEntity.getId());
			}
			contractBondService.saveBond(list, contractFormInfo.getId());
		}
		/*保存依据信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getAccording())) {
			contractFormInfoService.saveAccording(contractFormInfo);
		}
		/*保存履约信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())) {
			//删除履约信息脏数据
			performanceService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceList().forEach(performance -> {
				performance.setContractId(contractFormInfo.getId());
				performanceService.save(performance);
			});
		}
		/*保存履约计划收付款*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())) {
			//删除收付款脏数据
			contractPerformanceColPayService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceColPayList().forEach(performanceColPay -> {
				performanceColPay.setContractId(contractFormInfo.getId());
				contractPerformanceColPayService.save(performanceColPay);
			});
		}
		//保存合同和关联表 这个方法有问题
		contractFormInfoEntity = contractFormInfoService.templateDraft(contractFormInfoEntity, template.getJson());
		//页面用这个字段来判断是否提交
		if ("20".equals(template.getBean())) {
			//导出pdf文件
			FileVO filevo = templateExportUntil.templateSave(contractFormInfoEntity, template, contractFormInfoEntity.getJson(), j);
			contractFormInfoEntity.setContractStatus(CONTRACT_REVIEW_STATUS);
			contractFormInfoEntity.setChangeCategory(CHANGE_REVIEW_STATUS);
			contractFormInfoEntity.setSubmitStatus(APPROVE_REVIEW_STATUS);
			contractFormInfoEntity.setTextFile(filevo.getId() + ",");
			contractFormInfoEntity.setTextFilePdf(filevo.getId() + ",");
			contractFormInfoEntity.setContractStatus(template.getBean());
			contractFormInfoEntity.setFilePDF(filevo.getDomain());
			//处理电子签章和oa流程
			r = contractFormInfoService.SingleSign(R.data(contractFormInfoEntity));
			if (r.getCode() != HttpStatus.OK.value()) {
				r.setData(ContractFormInfoWrapper.build().entityPV(contractFormInfoEntity));
				return r;
			}
			r.getData().setCreateTime(new Date());
			contractFormInfoEntity = r.getData();
			//覆盖自动生成的合同编号
			contractFormInfoEntity.setContractNumber(
				contractFormInfo.getContractNumber().contains("-") ?
					contractFormInfo.getContractNumber().substring(
						0, contractFormInfo.getContractNumber().indexOf("-") + 1) + Integer.parseInt(
						contractFormInfo.getContractNumber().substring(
							contractFormInfo.getContractNumber().lastIndexOf("-"))) + 1 : contractFormInfo.getContractNumber() + "-1");
		}
		//****************************************变更新增代码START**************************************//
		//判断满足已变更新合同的条件 修改原合同状态
		if (CHANGE_REVIEW_STATUS.equals(contractFormInfoEntity.getChangeCategory()) && APPROVE_REVIEW_STATUS.equals(contractFormInfoEntity.getSubmitStatus())
			&& CONTRACT_REVIEW_STATUS.equals(contractFormInfoEntity.getContractStatus())) {
			changeService.updateExportStatus(ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS, Long.parseLong(contractFormInfoEntity.getChangeContractId()));
		}
		//****************************************变更新增代码END***************************************//
		contractFormInfoService.updateById(contractFormInfoEntity);
		return R.data(ContractFormInfoWrapper.build().entityPV(contractFormInfoEntity));
	}

	/**
	 * 多方起草变更新增
	 */
	@PostMapping("/multiAddChange")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:multiAddChange')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> multiAddChange(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		R<ContractFormInfoEntity> r;
		ContractFormInfoEntity entity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, entity);
		if (Func.isEmpty(contractFormInfo.getId())) {
			contractFormInfoService.save(entity);
		} else if (Func.isBlank(contractFormInfo.getChangeContractId())) {
			entity.setId(null);
			entity.setChangeContractId(contractFormInfo.getId().toString());
			//清空合同的文本导出次数记录
			entity.setFileExportCount(0);
			entity.setFileExportCategory(0);
			contractFormInfoService.save(entity);
		} else {
			contractFormInfoService.updateById(entity);
		}
		contractFormInfo.setId(entity.getId());
		/*保存多方向对方身份信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getDraftContractCounterpartList())) {
			draftContractCounterpartMapper.deleteDraftCounterpart(contractFormInfo.getId());
			contractFormInfo.getDraftContractCounterpartList().forEach(dcl -> {
				dcl.setContractId(contractFormInfo.getId().toString());
				draftContractCounterparService.save(dcl);
			});
		}
		/*保存相对方收付款信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getMultPaymenEntityList())) {
			contractMultPaymenMapper.deleteMult(contractFormInfo.getId());
			contractFormInfo.getMultPaymenEntityList().forEach(mult -> {
				mult.setCurrencyCategory(contractFormInfo.getCurrencyCategory());
				mult.setContractId(contractFormInfo.getId().toString());
				contractMultPaymenService.save(mult);
			});
		}
		/*保存相对方信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())) {
			contractFormInfoService.saveCounterpart(contractFormInfo);
		}
		/*保存保证金信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())) {
			List<Long> list = new ArrayList<>();
			ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
			//删除保证金库脏数据
			contractBondService.deleteByContractId(contractFormInfo.getId());
			//删除保证金履约计划脏数据
			contractBondPlanService.deleteByContractId(contractFormInfo.getId());
			for (ContractBondEntity contractBondEntity : contractFormInfo.getContractBond()) {
				BeanUtil.copy(contractBondEntity, contractBondPlan);
				//判断是否为保证金库里面的保证金、或变更合同原合同的保证金
				if (Func.isEmpty(contractBondEntity.getId())) {
					contractBondService.save(contractBondEntity);
				} else {
					contractBondPlan.setId(null);
				}
				//保存保证金履约计划
				contractBondPlan.setContractId(contractFormInfo.getId());
				contractBondPlanService.save(contractBondPlan);
				list.add(contractBondEntity.getId());
			}
			//保存保证ID与合同ID进行关联
			contractBondService.saveBond(list, contractFormInfo.getId());
		}
		/*保存依据信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getAccording())) {
			contractFormInfoService.saveAccording(contractFormInfo);
		}
		/*保存履约信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())) {
			//删除履约信息脏数据
			performanceService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceList().forEach(performance -> {
				if (Func.isNotEmpty(performance.getId())) {
					performance.setId(null);
				}
				performance.setContractId(contractFormInfo.getId());
				performanceService.save(performance);
			});
		}
		/*保存履约计划收付款*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())) {
			//删除收付款脏数据
			contractPerformanceColPayService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceColPayList().forEach(performanceColPay -> {
				if (Func.isNotEmpty(performanceColPay.getId())) {
					performanceColPay.setId(null);
				}
				performanceColPay.setContractId(contractFormInfo.getId());
				contractPerformanceColPayService.save(performanceColPay);
			});
		}
		r = contractFormInfoService.SingleSign(R.data(entity));
		if (r.getCode() != HttpStatus.OK.value()) {
			r.setData(ContractFormInfoWrapper.build().entityPV(entity));
			return r;
		}
		r.getData().setCreateTime(new Date());
		entity = r.getData();
		//判断满足已变更新合同的条件 修改原合同状态
		if (CHANGE_REVIEW_STATUS.equals(contractFormInfo.getChangeCategory()) && APPROVE_REVIEW_STATUS.equals(contractFormInfo.getSubmitStatus())
			&& CONTRACT_REVIEW_STATUS.equals(contractFormInfo.getContractStatus())) {
			changeService.updateExportStatus(ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS, Long.parseLong(entity.getChangeContractId()));
		}
		return R.data(contractFormInfo);
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
}
