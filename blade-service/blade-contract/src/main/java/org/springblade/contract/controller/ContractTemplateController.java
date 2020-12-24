package org.springblade.contract.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractTemplateEntity;
import org.springblade.contract.service.IContractTemplateService;
import org.springblade.contract.vo.ContractTemplateRequestVO;
import org.springblade.contract.vo.ContractTemplateResponseVO;
import org.springblade.contract.wrapper.ContractTemplateWrapper;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Charsets;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.feign.IDictBizClient;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 范本管理 控制器
 *
 * @author : XHB
 * @date : 2020-09-24 13:57:34
 */
@RestController
@AllArgsConstructor
@RequestMapping("/template")
@Api(value = "范本管理", tags = "范本管理")
public class ContractTemplateController extends BladeController {
	private IUserClient userClient;
	private ISysClient sysClient;
	private IDictBizClient dictBizClient;
	private IContractTemplateService templateService;
	private static final String TEMPLATE_STATUS="10";
	private static final String TEMPLATE_SCRAP_STATUS="30";
	private static final String TEMPLATE_REVISION_STATUS="20";
	private static final String TEMPLATE_REVISION_STATUS_OLD="40";

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入template")
	@PreAuth("hasPermission('contract:template:detail')")
	public R<ContractTemplateResponseVO> detail(@RequestParam Long id) {
		ContractTemplateEntity detail = templateService.getById(id);
		return R.data(ContractTemplateWrapper.build().entityPV(detail));
	}

	/**
	 * 历史版本详情列表信息
	 */
	@GetMapping("/version")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入id")
	@PreAuth("hasPermission('contract:template:version')")
	public R<ContractTemplateResponseVO> version(@RequestParam Long id) {
		ContractTemplateEntity version = templateService.getByNewId(id);
		return R.data(ContractTemplateWrapper.build().entityPV(version));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入template")
	@PreAuth("hasPermission('contract:template:list')")
	public R<IPage<ContractTemplateResponseVO>> list(ContractTemplateRequestVO template, Query query) {
		IPage<ContractTemplateResponseVO> pages = templateService.pageList(Condition.getPage(query), template);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入template")
	@PreAuth("hasPermission('contract:template:add')")
	@Transactional(rollbackFor = Exception.class)
	public R save(@Valid @RequestBody ContractTemplateRequestVO template) {
        ContractTemplateEntity entity = new ContractTemplateEntity();
        BeanUtil.copy(template,entity);
        entity.setOriginalTemplateId(entity.getId());
        entity.setTemplateStatus(TEMPLATE_STATUS);
		return R.status(templateService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入template")
	@PreAuth("hasPermission('contract:template:update')")
	public R update(@Valid @RequestBody ContractTemplateRequestVO template) {
	    if (Func.isEmpty(template.getId())){
            throw new ServiceException("id不能为空");
        }
	    ContractTemplateEntity entity = new ContractTemplateEntity();
        BeanUtil.copy(template,entity);
		return R.status(templateService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contract:template:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(templateService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 废弃
	 */
	@PostMapping("/scrap")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "修改状态", notes = "传入id")
	@PreAuth("hasPermission('contract:template:scrap')")
	public R scrap(@RequestParam Long id) {
		String  templateStatus = TEMPLATE_SCRAP_STATUS;
		if (Func.isEmpty(id)){
			throw new ServiceException("id不能为空");
		}
		return R.status(templateService.updateTemplateStatus(templateStatus,id));
	}

	/**
	 * 恢复
	 * 修訂後的範本恢復成20
	 * 為修訂的範本回復成10
	 */
	@PostMapping("/restore")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "修改状态", notes = "传入id")
	@PreAuth("hasPermission('contract:template:restore')")
	public R restore(@RequestParam Long id) {
		if (Func.isEmpty(id)){
			throw new ServiceException("id不能为空");
		}
		if(Func.isNotEmpty(templateService.getById(id).getOriginalTemplateId())){
			templateService.updateTemplateStatus(TEMPLATE_REVISION_STATUS,id);
		}else{
			templateService.updateTemplateStatus( TEMPLATE_STATUS,id);
		}
		return R.data(true);
	}

	/**
	 * 修订
	 */
	@PostMapping("/Revision")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入template")
	@PreAuth("hasPermission('contract:template:Revision')")
	@Transactional(rollbackFor = Exception.class)
	public R Revision(@Valid @RequestBody ContractTemplateRequestVO template) {
		if (Func.isNotEmpty(template.getId())) {
			template.setId(null);
		}
		ContractTemplateEntity entity = new ContractTemplateEntity();
		BeanUtil.copy(template,entity);
		entity.setTemplateCode(template.getTemplateCode());
		entity.setTemplateStatus(TEMPLATE_REVISION_STATUS);
		templateService.save(entity);
		templateService.updateTemplateStatus(TEMPLATE_REVISION_STATUS_OLD,entity.getOriginalTemplateId());
		return R.data(entity);
	}


	/**
	 /**
	 * 导出excel
	 * @param templateEntityList
	 * @param response
	 */
	@PostMapping("/exportTargetDataResult")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "导出", notes = "")
	public void exportTargetDataResult(@RequestBody List<ContractTemplateEntity> templateEntityList, HttpServletResponse response) {

		if(CollectionUtil.isNotEmpty(templateEntityList)){
			/* 导出文件名称 */
			String  fileName = "合同范本使用分析信息导出";
			WriteSheet sheet1 = new WriteSheet();
			/* 导出的sheet的名称 */
			sheet1.setSheetName("合同范本使用分析信息导出");
			sheet1.setSheetNo(0);
			/* 需要存入的数据 */
			List<List<Object>> data = new ArrayList<>();
			/* formInfoEntityList 表示要写入的数据 因为是前台显示列表 由前台进行传值，后期可以根据自己的需求进行改变 */
			for(ContractTemplateEntity templateEntity:templateEntityList){
				/* 属性 cloumns 表示一行，cloumns包含的数据是一行的数据
				  要将一行的每个值 作为list的一个属性存进到list里 ，数据要和展示的excel表头一致*/
				List<Object> cloumns = new ArrayList<Object>();
				/*范本名称*/
				cloumns.add(templateEntity.getName());
				/*使用范围*/
				cloumns.add(dictBizClient.getValue("use_range",templateEntity.getUseRange()).getData());
				/*所属合同一级分类*/
				cloumns.add(dictBizClient.getValues("HTDL",Long.valueOf(templateEntity.getContractBigCategory())).getData());
				/*承办单位*/
				cloumns.add(sysClient.getDept(templateEntity.getCreateDept()).getData().getDeptName());
				/*使用记录*/
				cloumns.add(templateEntity.getCompletedContractCount());
				/*使用率*/
				cloumns.add(templateEntity.getUsageRate());
				/*版本*/
				cloumns.add(templateEntity.getRecordVersion());
				/*创建时间*/
				cloumns.add(templateEntity.getCreateTime());
				data.add(cloumns);
			}
			/* 表头名称，excel的表头 一个list对象为一行里的一个表头名称 */
			List<List<String>> headList = new ArrayList<List<String>>();
			/* 此处表头为一行要显示的所有表头，要和数据的顺序对应上  需要转换为list */
			List<String> head = Arrays.asList("范本名称","所属合同一级分类","使用范围","承办单位","使用记录",
					"使用率","版本","创建时间");
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

}
