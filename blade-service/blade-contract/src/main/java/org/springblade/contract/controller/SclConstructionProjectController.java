package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.SclConstructionProjectEntity;
import org.springblade.contract.service.ISclConstructionProjectService;
import org.springblade.contract.vo.SclConstructionProjectRequestVO;
import org.springblade.contract.vo.SclConstructionProjectResponseVO;
import org.springblade.contract.wrapper.SclConstructionProjectWrapper;
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
 * 生产类：加工承揽合同（代工合同） 控制器
 *
 * @author : 生产类：加工承揽合同（代工合同）
 * @date : 2020-12-11 09:52:17
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sclConstructionProject")
@Api(value = "生产类：加工承揽合同（代工合同）", tags = "生产类：加工承揽合同（代工合同）")
public class SclConstructionProjectController extends BladeController {

	private ISclConstructionProjectService sclConstructionProjectService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入sclConstructionProject")
	@PreAuth("hasPermission('sclConstructionProject:sclConstructionProject:detail')")
	public R<SclConstructionProjectResponseVO> detail(@RequestParam Long id) {
		SclConstructionProjectEntity detail = sclConstructionProjectService.getById(id);
		return R.data(SclConstructionProjectWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入sclConstructionProject")
	@PreAuth("hasPermission('sclConstructionProject:sclConstructionProject:page')")
	public R<IPage<SclConstructionProjectResponseVO>> list(SclConstructionProjectRequestVO sclConstructionProject, Query query) {
		IPage<SclConstructionProjectEntity> pages = sclConstructionProjectService.pageList(Condition.getPage(query), sclConstructionProject);
		return R.data(SclConstructionProjectWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入sclConstructionProject")
	@PreAuth("hasPermission('sclConstructionProject:sclConstructionProject:add')")
	public R save(@Valid @RequestBody SclConstructionProjectResponseVO sclConstructionProject) {
		return R.status(sclConstructionProjectService.save(SclConstructionProjectWrapper.build().PVEntity(sclConstructionProject)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入sclConstructionProject")
	@PreAuth("hasPermission('sclConstructionProject:sclConstructionProject:update')")
	public R update(@Valid @RequestBody SclConstructionProjectResponseVO sclConstructionProject) {
	    if (Func.isEmpty(sclConstructionProject.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(sclConstructionProjectService.updateById(SclConstructionProjectWrapper.build().PVEntity(sclConstructionProject)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('sclConstructionProject:sclConstructionProject:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(sclConstructionProjectService.deleteLogic(Func.toLongList(ids)));
	}

}
