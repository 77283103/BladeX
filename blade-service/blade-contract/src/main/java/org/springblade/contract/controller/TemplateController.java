package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.log.exception.ServiceException;
import org.springblade.contract.vo.TemplateRequestVO;
import org.springblade.contract.vo.TemplateResponseVO;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.TemplateEntity;
import org.springblade.contract.wrapper.TemplateWrapper;
import org.springblade.contract.service.ITemplateService;


/**
 * 范本管理 控制器
 *
 * @author : XHB
 * @date : 2020-09-23 20:17:06
 */
@RestController
@AllArgsConstructor
@RequestMapping("/template")
@Api(value = "范本管理", tags = "范本管理")
public class TemplateController extends BladeController {

	private ITemplateService templateService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入template")
	@PreAuth("hasPermission('template:template:detail')")
	public R<TemplateResponseVO> detail(@RequestParam Long id) {
		TemplateEntity detail = templateService.getById(id);
		return R.data(TemplateWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入template")
	@PreAuth("hasPermission('template:template:list')")
	public R<IPage<TemplateResponseVO>> list(TemplateEntity template, Query query) {
		IPage<TemplateEntity> pages = templateService.pageList(Condition.getPage(query), template);
		return R.data(TemplateWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入template")
	@PreAuth("hasPermission('template:template:add')")
	public R save(@Valid @RequestBody TemplateRequestVO template) {
        TemplateEntity entity = new TemplateEntity();
        BeanUtil.copy(template,entity);
		return R.status(templateService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入template")
	@PreAuth("hasPermission('template:template:update')")
	public R update(@Valid @RequestBody TemplateRequestVO template) {
	    if (Func.isEmpty(template.getId())){
            throw new ServiceException("id不能为空");
        }
	    TemplateEntity entity = new TemplateEntity();
        BeanUtil.copy(template,entity);
		return R.status(templateService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('template:template:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(templateService.deleteLogic(Func.toLongList(ids)));
	}

}
