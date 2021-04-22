package org.springblade.system.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.ToolFileEntity;
import org.springblade.system.service.IToolFileService;
import org.springblade.system.vo.ToolFileRequestVO;
import org.springblade.system.vo.ToolFileResponseVO;
import org.springblade.system.wrapper.ToolFileWrapper;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;



/**
 * 工具 控制器
 *
 * @author : xhb
 * @date : 2021-04-22 10:09:27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/toolFile")
@Api(value = "工具", tags = "工具")
public class ToolFileController extends BladeController {

	private IToolFileService toolFileService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入toolFile")
	public R<ToolFileResponseVO> detail(@RequestParam Long id) {
		ToolFileEntity detail = toolFileService.getById(id);
		return R.data(ToolFileWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入toolFile")
	public R<IPage<ToolFileResponseVO>> list(ToolFileRequestVO toolFile, Query query) {
		IPage<ToolFileEntity> pages = toolFileService.pageList(Condition.getPage(query), toolFile);
		return R.data(ToolFileWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入toolFile")
	public R save(@Valid @RequestBody ToolFileRequestVO toolFile) {
		return R.status(toolFileService.save(ToolFileWrapper.build().QVEntity(toolFile)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入toolFile")
	public R update(@Valid @RequestBody ToolFileRequestVO toolFile) {
	    if (Func.isEmpty(toolFile.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(toolFileService.updateById(ToolFileWrapper.build().QVEntity(toolFile)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('tootFile:toolFile:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(toolFileService.deleteLogic(Func.toLongList(ids)));
	}

}
