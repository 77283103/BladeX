package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.YwlAnewDisplayEntity;
import org.springblade.contract.service.IYwlANewDisplayService;
import org.springblade.contract.vo.YwlANewDisplayRequestVO;
import org.springblade.contract.vo.YwlAnewDisplayResponseVO;
import org.springblade.contract.wrapper.YwlANewDisplayWrapper;
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
 * 业务类：21.新陈列协议书 控制器
 *
 * @author : szw
 * @date : 2020-12-07 15:37:41
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ywlANewDisplay")
@Api(value = "业务类：21.新陈列协议书", tags = "业务类：21.新陈列协议书")
public class YwlANewDisplayController extends BladeController {

	private IYwlANewDisplayService ywlANewDisplayService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入ywlANewDisplay")
	@PreAuth("hasPermission('ywlANewDisplay:ywlANewDisplay:detail')")
	public R<YwlAnewDisplayResponseVO> detail(@RequestParam Long id) {
		YwlAnewDisplayEntity detail = ywlANewDisplayService.getById(id);
		return R.data(YwlANewDisplayWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入ywlANewDisplay")
	@PreAuth("hasPermission('ywlANewDisplay:ywlANewDisplay:page')")
	public R<IPage<YwlAnewDisplayResponseVO>> list(YwlANewDisplayRequestVO ywlANewDisplay, Query query) {
		IPage<YwlAnewDisplayEntity> pages = ywlANewDisplayService.pageList(Condition.getPage(query), ywlANewDisplay);
		return R.data(YwlANewDisplayWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入ywlANewDisplay")
	@PreAuth("hasPermission('ywlANewDisplay:ywlANewDisplay:add')")
	public R save(@Valid @RequestBody YwlAnewDisplayResponseVO ywlANewDisplay) {
		return R.status(ywlANewDisplayService.save(YwlANewDisplayWrapper.build().PVEntity(ywlANewDisplay)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入ywlANewDisplay")
	@PreAuth("hasPermission('ywlANewDisplay:ywlANewDisplay:update')")
	public R update(@Valid @RequestBody YwlAnewDisplayResponseVO ywlANewDisplay) {
	    if (Func.isEmpty(ywlANewDisplay.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(ywlANewDisplayService.updateById(YwlANewDisplayWrapper.build().PVEntity(ywlANewDisplay)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('ywlANewDisplay:ywlANewDisplay:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(ywlANewDisplayService.deleteLogic(Func.toLongList(ids)));
	}

}
