package org.springblade.contract.controller;

import io.micrometer.core.instrument.binder.db.DatabaseTableMetrics;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.log.exception.ServiceException;
import org.springblade.contract.vo.ContractTemplateRequestVO;
import org.springblade.contract.vo.ContractTemplateResponseVO;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.ContractTemplateEntity;
import org.springblade.contract.wrapper.ContractTemplateWrapper;
import org.springblade.contract.service.IContractTemplateService;

import java.util.Date;


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

	private IContractTemplateService templateService;
	private static final String TEMPLATE_STATUS="10";
	private static final String TEMPLATE_SCRAP_STATUS="20";
	private static final String TEMPLATE_REVISION_STATUS="30";

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入template")
	@PreAuth("hasPermission('contract:template:detail')")
	public R<ContractTemplateResponseVO> detail(@RequestParam Long id) {
		ContractTemplateEntity detail = templateService.getById(id);
		return R.data(ContractTemplateWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入template")
	@PreAuth("hasPermission('contract:template:list')")
	public R<IPage<ContractTemplateResponseVO>> list(ContractTemplateEntity template, Query query) {
		IPage<ContractTemplateEntity> pages = templateService.pageList(Condition.getPage(query), template);
		return R.data(ContractTemplateWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入template")
	@PreAuth("hasPermission('contract:template:add')")
	public R save(@Valid @RequestBody ContractTemplateRequestVO template) {
        ContractTemplateEntity entity = new ContractTemplateEntity();
        BeanUtil.copy(template,entity);
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
	 */
	@PostMapping("/restore")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "修改状态", notes = "传入id")
	@PreAuth("hasPermission('contract:template:restore')")
	public R restore(@RequestParam Long id) {
		String  templateStatus = TEMPLATE_STATUS;
		if (Func.isEmpty(id)){
			throw new ServiceException("id不能为空");
		}
		return R.status(templateService.updateTemplateStatus(templateStatus,id));
	}

	/**
	 * 修订
	 */
	@PostMapping("/Revision")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入template")
	@PreAuth("hasPermission('contract:template:Revision')")
	public R Revision(@Valid @RequestBody ContractTemplateRequestVO template) {
		ContractTemplateEntity entity = new ContractTemplateEntity();
		BeanUtil.copy(template,entity);
		templateService.save(entity);
		return R.data(entity);
	}
}
