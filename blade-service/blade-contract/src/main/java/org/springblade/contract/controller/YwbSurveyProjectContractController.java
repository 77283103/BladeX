package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.YwbSurveyProjectContractEntity;
import org.springblade.contract.service.IYwbSurveyProjectContractService;
import org.springblade.contract.vo.YwbSurveyProjectContractRequestVO;
import org.springblade.contract.vo.YwbSurveyProjectContractResponseVO;
import org.springblade.contract.wrapper.YwbSurveyProjectContractWrapper;
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
 * 业务类：20.售点普查项目合同 控制器
 *
 * @author : 业务类：20.售点普查项目合同
 * @date : 2020-12-18 16:06:55
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ywbSurveyProjectContract")
@Api(value = "业务类：20.售点普查项目合同", tags = "业务类：20.售点普查项目合同")
public class YwbSurveyProjectContractController extends BladeController {

	private IYwbSurveyProjectContractService ywbSurveyProjectContractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入ywbSurveyProjectContract")
	@PreAuth("hasPermission('ywbSurveyProjectContract:ywbSurveyProjectContract:detail')")
	public R<YwbSurveyProjectContractResponseVO> detail(@RequestParam Long id) {
		YwbSurveyProjectContractEntity detail = ywbSurveyProjectContractService.getById(id);
		return R.data(YwbSurveyProjectContractWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入ywbSurveyProjectContract")
	@PreAuth("hasPermission('ywbSurveyProjectContract:ywbSurveyProjectContract:page')")
	public R<IPage<YwbSurveyProjectContractResponseVO>> list(YwbSurveyProjectContractRequestVO ywbSurveyProjectContract, Query query) {
		IPage<YwbSurveyProjectContractEntity> pages = ywbSurveyProjectContractService.pageList(Condition.getPage(query), ywbSurveyProjectContract);
		return R.data(YwbSurveyProjectContractWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入ywbSurveyProjectContract")
	@PreAuth("hasPermission('ywbSurveyProjectContract:ywbSurveyProjectContract:add')")
	public R save(@Valid @RequestBody YwbSurveyProjectContractResponseVO ywbSurveyProjectContract) {
		return R.status(ywbSurveyProjectContractService.save(YwbSurveyProjectContractWrapper.build().PVEntity(ywbSurveyProjectContract)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入ywbSurveyProjectContract")
	@PreAuth("hasPermission('ywbSurveyProjectContract:ywbSurveyProjectContract:update')")
	public R update(@Valid @RequestBody YwbSurveyProjectContractResponseVO ywbSurveyProjectContract) {
	    if (Func.isEmpty(ywbSurveyProjectContract.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(ywbSurveyProjectContractService.updateById(YwbSurveyProjectContractWrapper.build().PVEntity(ywbSurveyProjectContract)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('ywbSurveyProjectContract:ywbSurveyProjectContract:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(ywbSurveyProjectContractService.deleteLogic(Func.toLongList(ids)));
	}

}
