package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.SclContractTemplateEntity;
import org.springblade.contract.service.ISclContractTemplateService;
import org.springblade.contract.vo.SclContractTemplateRequestVO;
import org.springblade.contract.vo.SclContractTemplateResponseVO;
import org.springblade.contract.wrapper.SclContractTemplateWrapper;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 生产类：下脚品买卖合同模版 控制器
 *
 * @author : 张文武
 * @date : 2021-01-04 15:17:26
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sclContractTemplate")
@Api(value = "生产类：下脚品买卖合同模版", tags = "生产类：下脚品买卖合同模版")
public class SclContractTemplateController extends BladeController {

	private ISclContractTemplateService sclContractTemplateService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入sclContractTemplate")
	@PreAuth("hasPermission('sclContractTemplate:sclContractTemplate:detail')")
	public R<SclContractTemplateResponseVO> detail(@RequestParam Long id) {
		SclContractTemplateEntity detail = sclContractTemplateService.getById(id);
		return R.data(SclContractTemplateWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入sclContractTemplate")
	@PreAuth("hasPermission('sclContractTemplate:sclContractTemplate:page')")
	public R<IPage<SclContractTemplateResponseVO>> list(SclContractTemplateRequestVO sclContractTemplate, Query query) {
		IPage<SclContractTemplateEntity> pages = sclContractTemplateService.pageList(Condition.getPage(query), sclContractTemplate);
		return R.data(SclContractTemplateWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入sclContractTemplate")
	@PreAuth("hasPermission('sclContractTemplate:sclContractTemplate:add')")
	public R save(@Valid @RequestBody SclContractTemplateResponseVO sclContractTemplate) {
		return R.status(sclContractTemplateService.save(SclContractTemplateWrapper.build().PVEntity(sclContractTemplate)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入sclContractTemplate")
	@PreAuth("hasPermission('sclContractTemplate:sclContractTemplate:update')")
	public R update(@Valid @RequestBody SclContractTemplateResponseVO sclContractTemplate) {
	    if (Func.isEmpty(sclContractTemplate.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(sclContractTemplateService.updateById(SclContractTemplateWrapper.build().PVEntity(sclContractTemplate)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('sclContractTemplate:sclContractTemplate:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(sclContractTemplateService.deleteLogic(Func.toLongList(ids)));
	}

}
