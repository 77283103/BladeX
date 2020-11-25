package org.springblade.contract.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.mysql.cj.xdevapi.JsonArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.springblade.contract.constant.ContractFormInfoTemplateContract;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.entity.ContractBondEntity;
import org.springblade.contract.entity.ContractBondPlanEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.service.*;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.wrapper.ContractFormInfoWrapper;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.TemplateFieldEntity;
import org.springblade.system.vo.TemplateRequestVO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


/**
 *  控制器
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
	private static final String FILE_EXPORT_CATEGORY="1";
	private static final String CONTRACT_AUDIT_QUALITY="30";
	private static final String CONTRACT_EXPORT_STATUS="40";
	private static final String CONTRACT_SEAL_USING_INFO_STATUS="50";
	private static final String CONTRACT_SIGNING_STATUS="60";
	private static final String CONTRACT_ARCHIVE_STATUS="110";
	private static final String CONTRACT_ASSESSMENT_STATUS="100";

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
		BeanUtil.copy(contractFormInfo,entity);
		if (Func.isEmpty(contractFormInfo.getId())){
			contractFormInfoService.save(entity);
		}else{
			contractFormInfoService.updateById(entity);
		}
		contractFormInfo.setId(entity.getId());
		/*保存相对方信息*/
		if(CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())){
			contractFormInfoService.saveCounterpart(contractFormInfo);
		}
		/*保存保证金信息*/
		if(CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())){
			List<Long> list=new ArrayList<>();
			ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
			//删除保证金库脏数据
			contractBondService.deleteByContractId(contractFormInfo.getId());
			//删除保证金履约计划脏数据
			contractBondPlanService.deleteByContractId(contractFormInfo.getId());
			for(ContractBondEntity contractBondEntity:contractFormInfo.getContractBond()){
				BeanUtil.copy(contractBondEntity, contractBondPlan);
				if (Func.isEmpty(contractBondEntity.getId())){
					contractBondService.save(contractBondEntity);
				}
				//保存保证金履约计划
				contractBondPlan.setContractId(entity.getId());
				contractBondPlanService.save(contractBondPlan);
				list.add(contractBondEntity.getId());
			}
			contractBondService.saveBond(list,contractFormInfo.getId());
		}
		/*保存依据信息*/
		if(CollectionUtil.isNotEmpty(contractFormInfo.getAccording())){
			ContractAccordingEntity contractAccording=contractFormInfo.getAccording().get(0);
			contractAccording.setContractId(contractFormInfo.getId());
			accordingService.updateById(contractAccording);
		}
		/*保存履约信息*/
		if(CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())){
			//删除履约信息脏数据
			performanceService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceList().forEach(performance->{
				performance.setContractId(contractFormInfo.getId());
				performanceService.save(performance);
			});
		}
		/*保存履约计划收付款*/
		if(CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())){
			//删除收付款脏数据
			contractPerformanceColPayService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceColPayList().forEach(performanceColPay->{
				performanceColPay.setContractId(contractFormInfo.getId());
				contractPerformanceColPayService.save(performanceColPay);
			});
		}
		return R.data(contractFormInfo);
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
        BeanUtil.copy(contractFormInfo,entity);
		if (Func.isEmpty(contractFormInfo.getId())){
			contractFormInfoService.save(entity);
		}else{
			contractFormInfoService.updateById(entity);
		}
		contractFormInfo.setId(entity.getId());
		/*保存相对方信息*/
		if(CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())){
			contractFormInfoService.saveCounterpart(contractFormInfo);
		}
		/*保存保证金信息*/
		if(CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())){
			List<Long> list=new ArrayList<>();
			ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
			//删除保证金库脏数据
			contractBondService.deleteByContractId(contractFormInfo.getId());
			//删除保证金履约计划脏数据
			contractBondPlanService.deleteByContractId(contractFormInfo.getId());
			for(ContractBondEntity contractBondEntity:contractFormInfo.getContractBond()){
				BeanUtil.copy(contractBondEntity, contractBondPlan);
				if (Func.isEmpty(contractBondEntity.getId())){
					contractBondService.save(contractBondEntity);
				}
				//保存保证金履约计划
				contractBondPlan.setContractId(entity.getId());
				contractBondPlanService.save(contractBondPlan);
				list.add(contractBondEntity.getId());
			}
			contractBondService.saveBond(list,contractFormInfo.getId());
		}
		/*保存依据信息*/
		if(CollectionUtil.isNotEmpty(contractFormInfo.getAccording())){
			ContractAccordingEntity contractAccording=contractFormInfo.getAccording().get(0);
			contractAccording.setContractId(contractFormInfo.getId());
			accordingService.updateById(contractAccording);
		}
		/*保存履约信息*/
		if(CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())){
			//删除履约信息脏数据
			performanceService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceList().forEach(performance->{
				performance.setContractId(contractFormInfo.getId());
				performanceService.save(performance);
			});
		}
		/*保存履约计划收付款*/
		if(CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())){
			//删除收付款脏数据
			contractPerformanceColPayService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceColPayList().forEach(performanceColPay->{
				performanceColPay.setContractId(contractFormInfo.getId());
				contractPerformanceColPayService.save(performanceColPay);
			});
		}
		return R.data(contractFormInfo);
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
		for(TemplateFieldEntity templateField : templateFieldList){
			if (ContractFormInfoTemplateContract.CONTRACT_BIG_CATEGORY.equals(templateField.getRelationCode())) {
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				JSONObject json = JSON.parseObject(jsonObj.get("template").toString());
				j.put("contractBigCategory", jsonObj.get("first"));
				j.put("contractSmallCategory", jsonObj.get("second"));
				j.put("contractTemplateId", json.get("id"));
			}else if (ContractFormInfoTemplateContract.CONTRACT_COL_PAY.equals(templateField.getRelationCode())){
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				j.put("colPayType", jsonObj.get("first"));
				j.put("colPayTerm", jsonObj.get("second"));
				j.put("days", jsonObj.get("days"));
			}else if ("id".equals(templateField.getComponentType())){
				j.put("id", templateField.getFieldValue());
			}else if ("upload".equals(templateField.getComponentType())){
				//j.put("id", templateField.getFieldValue());
			}else{
				j.put(templateField.getFieldName(), templateField.getFieldValue());
			}
		}
		//把json串转换成一个对象
		ContractFormInfoEntity contractFormInfoEntity=JSONObject.toJavaObject(j, ContractFormInfoEntity.class);
		if (Func.isEmpty(contractFormInfoEntity.getId())){
			contractFormInfoEntity.setContractSoure("30");
			contractFormInfoEntity.setContractStatus("10");
			contractFormInfoService.save(contractFormInfoEntity);
		}else{
			contractFormInfoService.updateById(contractFormInfoEntity);
		}
		String json=contractFormInfoService.templateDraft(contractFormInfoEntity,template);
		contractFormInfoEntity.setJson(json);
		contractFormInfoService.updateById(contractFormInfoEntity);
		return R.data(json);
	}


	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:update')")
	public R update(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
	    if (Func.isEmpty(contractFormInfo.getId())){
            throw new ServiceException("id不能为空");
        }
	    ContractFormInfoEntity entity = new ContractFormInfoEntity();
        BeanUtil.copy(contractFormInfo,entity);
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
		String contractStatus = CONTRACT_EXPORT_STATUS;
		String fileExportCategory= FILE_EXPORT_CATEGORY;
		ContractFormInfoEntity infoEntity=contractFormInfoService.getById(id);
		Integer fileExportCount=infoEntity.getFileExportCount();
		fileExportCount+=1;
		contractFormInfoService.textExportCount(id,fileExportCount,fileExportCategory);
		if (Func.isEmpty(id)){
			throw new ServiceException("id不能为空");
		}
		return R.status(contractFormInfoService.updateExportStatus(contractStatus,id));
	}

	/**
	 * 复用导出合同文本
	 */
	@PostMapping("/repeatExport")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:repeatExport')")
	public R repeatExport(@RequestParam Long id) {
		String fileExportCategory= FILE_EXPORT_CATEGORY;
		ContractFormInfoEntity infoEntity=contractFormInfoService.getById(id);
		Integer fileExportCount=infoEntity.getFileExportCount();
		fileExportCount+=1;
		if (Func.isEmpty(id)){
			throw new ServiceException("id不能为空");
		}
		return R.status(contractFormInfoService.textExportCount(id,fileExportCount,fileExportCategory));
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
		if (Func.isEmpty(id)){
			throw new ServiceException("id不能为空");
		}
		String contractStatus=CONTRACT_AUDIT_QUALITY;
		return R.status(contractFormInfoService.updateExportStatus(contractStatus,id));
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
		if (Func.isEmpty(id)){
			throw new ServiceException("id不能为空");
		}
		String contractStatus=CONTRACT_SEAL_USING_INFO_STATUS;
		return R.status(contractFormInfoService.updateExportStatus(contractStatus,id));
	}

	/**
	 * 	签订后修改状态待归档
	 * 	50>60
	 */
	@PostMapping("/updateSigningStatus")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:updateSigningStatus')")
	public R signingStatus(@RequestParam Long id) {
		if (Func.isEmpty(id)){
			throw new ServiceException("id不能为空");
		}
		String contractStatus=CONTRACT_SIGNING_STATUS;
		return R.status(contractFormInfoService.updateExportStatus(contractStatus,id));
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
		if (Func.isEmpty(id)){
			throw new ServiceException("id不能为空");
		}
		String contractStatus=CONTRACT_ARCHIVE_STATUS;
		return R.status(contractFormInfoService.updateExportStatus(contractStatus,id));
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
		if (Func.isEmpty(id)){
			throw new ServiceException("id不能为空");
		}
		String contractStatus=CONTRACT_ASSESSMENT_STATUS;
		return R.status(contractFormInfoService.updateExportStatus(contractStatus,id));
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
	public ArrayList<Map<String,String>>  getAmountList() {
		List<ContractFormInfoEntity> list = contractFormInfoService.getAmountList();
		ArrayList<Map<String,String>> listMap = new ArrayList<>();
		for(int i = 0;i < list.size();i++){
			HashMap<String, String> map = new HashMap<>();
			map.put("name",list.get(i).getDictValue());
			map.put("value",String.valueOf(list.get(i).getContractAmount()));
			listMap.add(map);
		}
		return listMap;
	}

	/**
	 * 合同大类数量
	 */
	@PostMapping("/getNumList")
	@ApiOperation(value = "合同大类数量", notes = "传入ids")
	public ArrayList<Map<String,String>>  getNumList() {
		List<ContractFormInfoEntity> list = contractFormInfoService.getNumList();
		ArrayList<Map<String,String>> listMap = new ArrayList<>();
		for(int i = 0;i < list.size();i++){
			HashMap<String, String> map = new HashMap<>();
			map.put("name",list.get(i).getDictValue());
			map.put("value",String.valueOf(list.get(i).getCount()));
			listMap.add(map);
		}
		return listMap;
	}

}
