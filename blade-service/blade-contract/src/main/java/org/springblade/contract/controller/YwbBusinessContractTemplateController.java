package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.YwbBusinessContractTemplateEntity;
import org.springblade.contract.service.IYwbBusContracteTemplateService;
import org.springblade.contract.vo.YwbBusinessContractTemplateRequestVO;
import org.springblade.contract.vo.YwbBusinessContractTemplateResponseVO;
import org.springblade.contract.wrapper.YwbBusinessContractTemplateWrapper;
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
 * 业务类：15.房屋租赁合同模板 控制器
 *
 * @author : 王策
 * @date : 2021-01-12 17:30:25
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ywbBusinessContractTemplate")
@Api(value = "业务类：15.房屋租赁合同模板", tags = "业务类：15.房屋租赁合同模板")
public class YwbBusinessContractTemplateController extends BladeController {

	private IYwbBusContracteTemplateService ywbBusinessContractTemplateService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入ywbBusinessContractTemplate")
	@PreAuth("hasPermission('ywbBusinessContractTemplate:ywbBusinessContractTemplate:detail')")
	public R<YwbBusinessContractTemplateResponseVO> detail(@RequestParam Long id) {
		YwbBusinessContractTemplateEntity detail = ywbBusinessContractTemplateService.getById(id);
		return R.data(YwbBusinessContractTemplateWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入ywbBusinessContractTemplate")
	@PreAuth("hasPermission('ywbBusinessContractTemplate:ywbBusinessContractTemplate:page')")
	public R<IPage<YwbBusinessContractTemplateResponseVO>> list(YwbBusinessContractTemplateEntity ywbBusinessContractTemplate, Query query) {
		IPage<YwbBusinessContractTemplateEntity> pages = ywbBusinessContractTemplateService.pageList(Condition.getPage(query), ywbBusinessContractTemplate);
		return R.data(YwbBusinessContractTemplateWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入ywbBusinessContractTemplate")
	@PreAuth("hasPermission('ywbBusinessContractTemplate:ywbBusinessContractTemplate:add')")
	public R save(@Valid @RequestBody YwbBusinessContractTemplateRequestVO ywbBusinessContractTemplate) {
		return R.status(ywbBusinessContractTemplateService.save(YwbBusinessContractTemplateWrapper.build().QVEntity(ywbBusinessContractTemplate)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入ywbBusinessContractTemplate")
	@PreAuth("hasPermission('ywbBusinessContractTemplate:ywbBusinessContractTemplate:update')")
	public R update(@Valid @RequestBody YwbBusinessContractTemplateRequestVO ywbBusinessContractTemplate) {
	    if (Func.isEmpty(ywbBusinessContractTemplate.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(ywbBusinessContractTemplateService.updateById(YwbBusinessContractTemplateWrapper.build().QVEntity(ywbBusinessContractTemplate)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('ywbBusinessContractTemplate:ywbBusinessContractTemplate:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(ywbBusinessContractTemplateService.deleteLogic(Func.toLongList(ids)));
	}

}
