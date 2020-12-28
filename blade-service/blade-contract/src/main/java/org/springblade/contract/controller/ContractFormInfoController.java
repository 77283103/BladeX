package org.springblade.contract.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
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
import lombok.AllArgsConstructor;
import org.springblade.contract.constant.ContractFormInfoTemplateContract;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.entity.ContractBondEntity;
import org.springblade.contract.entity.ContractBondPlanEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.excel.ContractFormInfoImporter;
import org.springblade.contract.excel.ContractFormInfoImporterEx;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.service.*;
import org.springblade.contract.util.TemplateExportUntil;
import org.springblade.contract.util.TemplateSaveUntil;
import org.springblade.contract.vo.ContractAccordingRequestVO;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.wrapper.ContractFormInfoWrapper;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.*;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Charsets;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.entity.DictBiz;
import org.springblade.system.entity.TemplateFieldEntity;
import org.springblade.system.feign.IDictBizClient;
import org.springblade.system.vo.TemplateRequestVO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;


/**
 * 控制器
 *
 * @author : 史智伟
 * @date : 2020-09-23 18:04:37
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractFormInfo")
@Api(value = "", tags = "")
public class ContractFormInfoController extends BladeController {

	private IContractFormInfoService contractFormInfoService;
	private IContractPerformanceService performanceService;
	private IContractAccordingService accordingService;
	private IContractBondService contractBondService;
	private IContractPerformanceColPayService contractPerformanceColPayService;
	private IContractBondPlanService contractBondPlanService;
	private ContractFormInfoMapper formInfoMapper;
	private IDictBizClient bizClient;

	private static final Integer CHANGE_CONTRACT_ID = -1;
	private static final String CHANGE_REVIEW_STATUS = "10";
	private static final String APPROVE_REVIEW_STATUS = "10";
	private static final String CONTRACT_REVIEW_STATUS = "20";
	private static final String FILE_EXPORT_CATEGORY = "1";
	private static final String CONTRACT_AUDIT_QUALITY = "30";
	private static final String CONTRACT_EXPORT_STATUS = "40";
	private static final String CONTRACT_SEAL_USING_INFO_STATUS = "50";
	private static final String CONTRACT_SIGNING_STATUS = "60";
	private static final String CONTRACT_ARCHIVE_STATUS = "110";
	private static final String CONTRACT_ASSESSMENT_STATUS = "100";
	private static final String ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS = "130";

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
		IPage<ContractFormInfoResponseVO> pages = contractFormInfoService.pageList(Condition.getPage(query), contractFormInfo);
		return R.data(pages);
	}

	/**
	 * 統計分析查詢列表
	 */
	@GetMapping("/statistics")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:statistics')")
	public R<IPage<ContractFormInfoEntity>>statistics(ContractFormInfoRequestVO contractFormInfo, Query query) {
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
		contractFormInfo.setContractSoure("20");
		//String sealName = StringUtils.join(contractFormInfo.getSealNameList(), ",");
		//contractFormInfo.setSealName(sealName);
		ContractFormInfoEntity entity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, entity);
		if (Func.isEmpty(contractFormInfo.getId())) {
			contractFormInfoService.save(entity);
		}else {
			contractFormInfoService.updateById(entity);
		}
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
				contractBondPlanService.save(contractBondPlan);
				list.add(contractBondEntity.getId());
			}
			contractBondService.saveBond(list, contractFormInfo.getId());
		}
		/*保存依据信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getAccording())) {
			ContractAccordingEntity contractAccording = contractFormInfo.getAccording().get(0);
			contractAccording.setContractId(contractFormInfo.getId());
			accordingService.updateById(contractAccording);
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
		return R.data(ContractFormInfoWrapper.build().entityPV(entity));
	}

	/**
	 * 判断电子签章是否存在
	 */
	@PostMapping("/singleSignIsNot")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "新增", notes = "传入contractFormInfo")
	@Transactional(rollbackFor = Exception.class)
	public String singleSignIsNot(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		String url = "";
		return url;
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
		contractFormInfo.setContractSoure("10");
		//String sealName = StringUtils.join(contractFormInfo.getSealNameList(), ",");
		//contractFormInfo.setSealName(sealName);
		ContractFormInfoEntity entity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, entity);
		if (Func.isEmpty(contractFormInfo.getId())) {
			contractFormInfoService.save(entity);
		}else {
			contractFormInfoService.updateById(entity);
		}
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
			ContractAccordingEntity contractAccording = contractFormInfo.getAccording().get(0);
			contractAccording.setContractId(contractFormInfo.getId());
			accordingService.updateById(contractAccording);
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
		//String paramStr = "{\"contractId\":\"1343427293518774274\",\"submitStatus\":\"30\"}";
		//cn.hutool.json.JSONObject docInfoJson = JSONUtil.parseObj(HttpUtil.createPost("http://localhost:18080/ekp/submit").body(paramStr,"application/json").execute().body());
		//开始接口处理
		if("30".equals(entity.getContractStatus())){
			//处理电子签章和oa流程
			entity=contractFormInfoService.SingleSign(entity);
			contractFormInfoService.updateById(entity);
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
	public R<String> templateSave(@Valid @RequestBody TemplateRequestVO template) {
		List<TemplateFieldEntity> templateFieldList = JSON.parseArray(template.getJson(), TemplateFieldEntity.class);
		JSONObject j = new JSONObject();
		//处理合同的二级联动保存
		for (TemplateFieldEntity templateField : templateFieldList) {
			if (ContractFormInfoTemplateContract.CONTRACT_BIG_CATEGORY.equals(templateField.getRelationCode())) {
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				JSONObject json = JSON.parseObject(jsonObj.get("template").toString());
				j.put("contractBigCategory", jsonObj.get("first"));
				j.put("contractSmallCategory", jsonObj.get("second"));
				if(null!=json){
					j.put("contractTemplateId", json.get("id"));
				}
			} else if (ContractFormInfoTemplateContract.CONTRACT_COL_PAY.equals(templateField.getRelationCode())) {
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				j.put("colPayType", jsonObj.get("first"));
				j.put("colPayTerm", jsonObj.get("second"));
				j.put("days", jsonObj.get("days"));
			} else if ("id".equals(templateField.getComponentType())) {
				j.put("id", templateField.getFieldValue());
			} else if ("upload".equals(templateField.getComponentType())) {
				//j.put("id", templateField.getFieldValue());
			} else {
				j.put(templateField.getFieldName(), templateField.getFieldValue());
			}
		}
		//把json串转换成一个对象
		ContractFormInfoEntity contractFormInfoEntity = JSONObject.toJavaObject(j, ContractFormInfoEntity.class);
		//保存合同和关联表 这个方法有问题
		TemplateSaveUntil.templateSave(contractFormInfoEntity,template,j);
		/*if (Func.isEmpty(contractFormInfoEntity.getId())) {
			contractFormInfoEntity.setContractSoure("30");
			contractFormInfoEntity.setContractStatus("10");
			contractFormInfoService.save(contractFormInfoEntity);
		} else {
			contractFormInfoService.updateById(contractFormInfoEntity);
		}*/
		contractFormInfoEntity= contractFormInfoService.templateDraft(contractFormInfoEntity, template.getJson());
		//页面用这个字段来判断是否提交
		if("30".equals(template.getBean())){
			//导出pdf文件
			TemplateExportUntil templateExportUntil=new TemplateExportUntil();
			FileVO filevo=templateExportUntil.templateSave(contractFormInfoEntity,template,contractFormInfoEntity.getJson(),j);
			contractFormInfoEntity.setTextFilePdf(filevo.getId()+",");
			contractFormInfoEntity.setContractStatus(template.getBean());
			contractFormInfoEntity.setFilePDF(filevo.getLink());
			contractFormInfoEntity=contractFormInfoService.SingleSign(contractFormInfoEntity);
		}
		contractFormInfoService.updateById(contractFormInfoEntity);
		return R.data(contractFormInfoEntity.getJson());
	}

	/**
	 * 合同预览
	 */
	@PostMapping("/contractBrowse")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "合同预览", notes = "template")
	@Transactional(rollbackFor = Exception.class)
	public R<String> contractBrowse(@Valid @RequestBody TemplateRequestVO template){
		List<TemplateFieldEntity> templateFieldList = JSON.parseArray(template.getJson(), TemplateFieldEntity.class);
		JSONObject j = new JSONObject();
		//处理合同的二级联动保存
		for (TemplateFieldEntity templateField : templateFieldList) {
			if (ContractFormInfoTemplateContract.CONTRACT_BIG_CATEGORY.equals(templateField.getRelationCode())) {
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				JSONObject json = JSON.parseObject(jsonObj.get("template").toString());
				j.put("contractBigCategory", jsonObj.get("first"));
				j.put("contractSmallCategory", jsonObj.get("second"));
				if(null!=json){
					j.put("contractTemplateId", json.get("id"));
				}
			} else if (ContractFormInfoTemplateContract.CONTRACT_COL_PAY.equals(templateField.getRelationCode())) {
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				j.put("colPayType", jsonObj.get("first"));
				j.put("colPayTerm", jsonObj.get("second"));
				j.put("days", jsonObj.get("days"));
			} else if ("id".equals(templateField.getComponentType())) {
				j.put("id", templateField.getFieldValue());
			} else if ("upload".equals(templateField.getComponentType())) {
			} else {
				j.put(templateField.getFieldName(), templateField.getFieldValue());
			}
		}
		//把json串转换成一个对象
		ContractFormInfoEntity contractFormInfoEntity = JSONObject.toJavaObject(j, ContractFormInfoEntity.class);
		//String json = contractFormInfoService.templateDraft(contractFormInfoEntity, template.getJson());
		//导出pdf文件
		TemplateExportUntil templateExportUntil=new TemplateExportUntil();
		FileVO files=templateExportUntil.templateSave(contractFormInfoEntity,template,template.getJson(),j);
		return R.data(files.getLink());
	}


	/**
	 * 批量导入
	 */
	@PostMapping("/importBatchDraft")
	@ApiOperationSupport(order = 12)
	@ApiOperation(value = "导入合同", notes = "传入excel")
	@Transactional(rollbackFor = Exception.class)
	public R importUser(MultipartFile file, String json, String contractTemplateId,String contractBigCategory,String contractSmallCategory) {
		//读取Excal 两个sheet数据
		List<ContractFormInfoImporter> read = ExcelUtil.read(file, 0, 5, ContractFormInfoImporter.class);
		List<ContractFormInfoImporterEx> read2 = ExcelUtil.read(file, 1, 1, ContractFormInfoImporterEx.class);
		read.forEach(readEx -> {
			if(("≤3年").equals(readEx.getContractPeriod())){
				readEx.setContractPeriod("小于等于3年");
			}else if((">3年").equals(readEx.getContractPeriod())){
				readEx.setContractPeriod("大于3年");
			}
			if(("票期<10天").equals(readEx.getColPayTerm())){
				readEx.setColPayTerm("票期小于10天");
			}else if(("10天≤票期<45天").equals(readEx.getColPayTerm())){
				readEx.setColPayTerm("票期大于等于10天小于45天");
			}else if(("45天≤票期").equals(readEx.getColPayTerm())){
				readEx.setColPayTerm("票期大于等于45天");
			}
			//contract_form合同形式
			R<List<DictBiz>> contract_form = bizClient.getList("contract_form");
			List<DictBiz> dataBiz = contract_form.getData();
			dataBiz.forEach(contractForm -> {
				if (readEx.getContractForm().equals(contractForm.getDictValue())) {
					readEx.setContractForm(contractForm.getDictKey());
				}
			});
			//收付款-收付款条件
			R<List<DictBiz>> col_pay_term = bizClient.getList("col_pay_term");
			List<DictBiz> dataBiz1 = col_pay_term.getData();
			dataBiz1.forEach(colPayTerm -> {
				if (readEx.getColPayType().equals(colPayTerm.getDictValue())) {
					readEx.setColPayType(colPayTerm.getId().toString());
				} else if (readEx.getColPayTerm().equals(colPayTerm.getDictValue())) {
					readEx.setColPayTerm(colPayTerm.getId().toString());
				}
			});
			//contract_period  合同期限
			R<List<DictBiz>> contract_period = bizClient.getList("contract_period");
			List<DictBiz> dataBiz2 = contract_period.getData();
			dataBiz2.forEach(contractPeriod -> {
				if (readEx.getContractPeriod().equals(contractPeriod.getDictValue())) {
					readEx.setContractPeriod(contractPeriod.getDictKey());
				}
			});
		});
		contractFormInfoService.importContractFormInfo(read,file,json,contractTemplateId,contractBigCategory,contractSmallCategory);
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
		ContractFormInfoEntity infoEntity=new ContractFormInfoEntity();
		//保存依据信息，把依据信息替换到json串中
		for (String id : according.getContractIds()) {
			BeanUtil.copy(according, entity);
			entity.setContractId(Long.parseLong(id));
			accordingService.save(entity);
			JSONObject jsonObj = JSON.parseObject(JSON.toJSONString(entity));
			infoEntity = contractFormInfoService.getById(id);
			String json=infoEntity.getJson();
			com.alibaba.fastjson.JSONArray objects = new com.alibaba.fastjson.JSONArray();
			try {
				objects = JSONArray.parseArray(json);
				for (int i = 0; i < objects.size(); i++) {
					JSONObject temp = objects.getJSONObject(i);
					String relationCode = temp.getString("relationCode");
					if("ContractAccording".equals(relationCode)){
						temp.put("tableData",jsonObj);
						objects.set(i, temp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			json = objects.toJSONString();
			infoEntity.setJson(json);
			infoEntity.setContractStatus("30");
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
	public R<ContractFormInfoEntity> multiplex(@RequestParam Long id) {
		ContractFormInfoEntity entity = contractFormInfoService.getById(id);
		ContractFormInfoEntity contractFormInfo = new ContractFormInfoEntity();
		BeanUtil.copy(entity, contractFormInfo);
		String json=contractFormInfo.getJson();
		if (!Func.isEmpty(json)) {
			com.alibaba.fastjson.JSONArray objects = new com.alibaba.fastjson.JSONArray();
			try {
				objects = JSONArray.parseArray(json);
				for (int i = 0; i < objects.size(); i++) {
					JSONObject temp = objects.getJSONObject(i);
					String componentType = temp.getString("componentType");
					if("id".equals(componentType)){
						temp.put("fieldValue","");
						objects.set(i, temp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			json = objects.toJSONString();
			contractFormInfo.setJson(json);
		}
		return R.data(contractFormInfo);
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
			formInfoMapper.updateExportStatus(ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS,entity.getChangeContractId());
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
		contractFormInfoService.textExportCount(id, fileExportCount, FILE_EXPORT_CATEGORY);
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		return R.status(contractFormInfoService.updateExportStatus(CONTRACT_EXPORT_STATUS, id));
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
		contractFormInfoService.textExportCount(id, fileExportCount, FILE_EXPORT_CATEGORY);
		infoEntity.setFileExportCount(fileExportCount);
		ContractFormInfoResponseVO formInfoResponseVO=ContractFormInfoWrapper.build().entityPV(infoEntity);
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
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		String contractStatus = CONTRACT_AUDIT_QUALITY;
		return R.status(contractFormInfoService.updateExportStatus(contractStatus, id));
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
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		String contractStatus = CONTRACT_SEAL_USING_INFO_STATUS;
		return R.status(contractFormInfoService.updateExportStatus(contractStatus, id));
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
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		String contractStatus = CONTRACT_SIGNING_STATUS;
		return R.status(contractFormInfoService.updateExportStatus(contractStatus, id));
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
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		String contractStatus = CONTRACT_ARCHIVE_STATUS;
		return R.status(contractFormInfoService.updateExportStatus(contractStatus, id));
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
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		String contractStatus = CONTRACT_ASSESSMENT_STATUS;
		return R.status(contractFormInfoService.updateExportStatus(contractStatus, id));
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
	 * @param formInfoEntityList
	 * @param response
	 */
	@PostMapping("/exportTargetDataResultStatistics")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "导出", notes = "")
	public void exportTargetDataResult(@RequestBody List<ContractFormInfoResponseVO> formInfoEntityList, HttpServletResponse response) {
		if(CollectionUtil.isNotEmpty(formInfoEntityList)){
			/* 导出文件名称 */
			String  fileName = "合同统计分析信息导出";
			WriteSheet sheet1 = new WriteSheet();
			/* 导出的sheet的名称 */
			sheet1.setSheetName("合同统计分析信息导出");
			sheet1.setSheetNo(0);
			/* 需要存入的数据 */
			List<List<Object>> data = new ArrayList<>();
			/* formInfoEntityList 表示要写入的数据 因为是前台显示列表 由前台进行传值，后期可以根据自己的需求进行改变 */
			for(ContractFormInfoResponseVO contractFormInfoEntity:formInfoEntityList){
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
				cloumns.add(bizClient.getValue("contract_status",contractFormInfoEntity.getContractStatus()).getData());
				/*签订单位*/
				cloumns.add(contractFormInfoEntity.getSigningEntity().getManageUnit());
				data.add(cloumns);
			}
			/* 表头名称，excel的表头 一个list对象为一行里的一个表头名称 */
			List<List<String>> headList = new ArrayList<List<String>>();
			/* 此处表头为一行要显示的所有表头，要和数据的顺序对应上  需要转换为list */
			List<String> head = Arrays.asList("合同类别","合同金额","签订数量","占比金额比例","收支类型","合同状态","签订单位");
			/* 为了生成一个独立的list对象，所进行的初始化 */
			List<String>  head2 =null;
			for( String head1:head){
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
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 独立起草变更新增
	 */
	@PostMapping("/addChange")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:addChange')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> saveChange(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
//		contractFormInfo.setContractSoure("10");
		ContractFormInfoEntity entity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, entity);
		if (Func.isEmpty(contractFormInfo.getId())) {
			contractFormInfoService.save(entity);
		}else if(Long.valueOf(CHANGE_CONTRACT_ID).equals(contractFormInfo.getChangeContractId())){
			entity.setId(null);
			entity.setChangeContractId(contractFormInfo.getId());
			//清空合同的文本导出次数记录
			entity.setFileExportCount(0);
			entity.setFileExportCategory(0);
			contractFormInfoService.save(entity);
		}else {
			contractFormInfoService.updateById(entity);
		}
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
				}else {
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
			ContractAccordingEntity contractAccording = contractFormInfo.getAccording().get(0);
			contractAccording.setContractId(contractFormInfo.getId());
			accordingService.updateById(contractAccording);
			contractFormInfoService.saveAccording(contractFormInfo);
		}
		/*保存履约信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())) {
			//删除履约信息脏数据
			performanceService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceList().forEach(performance -> {
				if (Func.isNotEmpty(performance.getId())){
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
				if (Func.isNotEmpty(performanceColPay.getId())){
					performanceColPay.setId(null);
				}
				performanceColPay.setContractId(contractFormInfo.getId());
				contractPerformanceColPayService.save(performanceColPay);
			});
		}
		//判断满足已变更新合同的条件 修改原合同状态
		if (CHANGE_REVIEW_STATUS.equals(contractFormInfo.getChangeCategory())&& APPROVE_REVIEW_STATUS.equals(contractFormInfo.getSubmitStatus())
				&& CONTRACT_REVIEW_STATUS.equals(contractFormInfo.getContractStatus())) {
			formInfoMapper.updateExportStatus(ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS,contractFormInfo.getChangeContractId());
		}
		return R.data(ContractFormInfoWrapper.build().entityPV(entity));
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
//		contractFormInfo.setContractSoure("20");
		//String sealName = StringUtils.join(contractFormInfo.getSealNameList(), ",");
		//contractFormInfo.setSealName(sealName);
		ContractFormInfoEntity entity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, entity);
		if (Func.isEmpty(contractFormInfo.getId())) {
			contractFormInfoService.save(entity);
		} else if (Long.valueOf(CHANGE_CONTRACT_ID).equals(contractFormInfo.getChangeContractId())) {
			entity.setId(null);
			entity.setChangeContractId(contractFormInfo.getId());
			//清空合同的文本导出次数记录
			entity.setFileExportCount(0);
			entity.setFileExportCategory(0);
			contractFormInfoService.save(entity);
		} else {
			contractFormInfoService.updateById(entity);
		}
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
			ContractAccordingEntity contractAccording = contractFormInfo.getAccording().get(0);
			contractAccording.setContractId(contractFormInfo.getId());
			accordingService.updateById(contractAccording);
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
		//判断满足已变更新合同的条件 修改原合同状态
		if (CHANGE_REVIEW_STATUS.equals(contractFormInfo.getChangeCategory()) && APPROVE_REVIEW_STATUS.equals(contractFormInfo.getSubmitStatus())
				&& CONTRACT_REVIEW_STATUS.equals(contractFormInfo.getContractStatus())) {
			formInfoMapper.updateExportStatus(ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS, contractFormInfo.getChangeContractId());
		}
		return R.data(contractFormInfo);
	}

}
