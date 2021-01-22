package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.LaborDispatchEntity;
import org.springblade.contract.service.ILaborDispatchService;
import org.springblade.contract.vo.LaborDispatchRequestVO;
import org.springblade.contract.vo.LaborDispatchResponseVO;
import org.springblade.contract.wrapper.LaborDispatchWrapper;
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
 * 韩素娟劳务派遣合同模板(甲方有拼接附件） 控制器
 *
 * @author : wd
 * @date : 2021-01-22 15:16:03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/laborDispatch")
@Api(value = "韩素娟劳务派遣合同模板(甲方有拼接附件）", tags = "韩素娟劳务派遣合同模板(甲方有拼接附件）")
public class LaborDispatchController extends BladeController {

	private ILaborDispatchService laborDispatchService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入laborDispatch")
	@PreAuth("hasPermission('laborDispatch:laborDispatch:detail')")
	public R<LaborDispatchResponseVO> detail(@RequestParam Long id) {
		LaborDispatchEntity detail = laborDispatchService.getById(id);
		return R.data(LaborDispatchWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入laborDispatch")
	@PreAuth("hasPermission('laborDispatch:laborDispatch:page')")
	public R<IPage<LaborDispatchResponseVO>> list(LaborDispatchEntity laborDispatch, Query query) {
		IPage<LaborDispatchEntity> pages = laborDispatchService.pageList(Condition.getPage(query), laborDispatch);
		return R.data(LaborDispatchWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入laborDispatch")
	@PreAuth("hasPermission('laborDispatch:laborDispatch:add')")
	public R save(@Valid @RequestBody LaborDispatchRequestVO laborDispatch) {
		return R.status(laborDispatchService.save(LaborDispatchWrapper.build().QVEntity(laborDispatch)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入laborDispatch")
	@PreAuth("hasPermission('laborDispatch:laborDispatch:update')")
	public R update(@Valid @RequestBody LaborDispatchRequestVO laborDispatch) {
	    if (Func.isEmpty(laborDispatch.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(laborDispatchService.updateById(LaborDispatchWrapper.build().QVEntity(laborDispatch)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('laborDispatch:laborDispatch:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(laborDispatchService.deleteLogic(Func.toLongList(ids)));
	}

}
