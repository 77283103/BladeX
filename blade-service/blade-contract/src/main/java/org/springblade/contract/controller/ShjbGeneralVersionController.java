package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ShjbGeneralVersionEntity;
import org.springblade.contract.service.IShjbGeneralVersionService;
import org.springblade.contract.vo.ShjbGeneralVersionRequestVO;
import org.springblade.contract.vo.ShjbGeneralVersionResponseVO;
import org.springblade.contract.wrapper.ShjbGeneralVersionWrapper;
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
 * 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本）） 控制器
 *
 * @author : 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本））
 * @date : 2020-12-18 16:02:23
 */
@RestController
@AllArgsConstructor
@RequestMapping("/shjbGeneralVersion")
@Api(value = "售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本））", tags = "售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本））")
public class ShjbGeneralVersionController extends BladeController {

	private IShjbGeneralVersionService shjbGeneralVersionService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入shjbGeneralVersion")
	@PreAuth("hasPermission('shjbGeneralVersion:shjbGeneralVersion:detail')")
	public R<ShjbGeneralVersionResponseVO> detail(@RequestParam Long id) {
		ShjbGeneralVersionEntity detail = shjbGeneralVersionService.getById(id);
		return R.data(ShjbGeneralVersionWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入shjbGeneralVersion")
	@PreAuth("hasPermission('shjbGeneralVersion:shjbGeneralVersion:page')")
	public R<IPage<ShjbGeneralVersionResponseVO>> list(ShjbGeneralVersionRequestVO shjbGeneralVersion, Query query) {
		IPage<ShjbGeneralVersionEntity> pages = shjbGeneralVersionService.pageList(Condition.getPage(query), shjbGeneralVersion);
		return R.data(ShjbGeneralVersionWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入shjbGeneralVersion")
	@PreAuth("hasPermission('shjbGeneralVersion:shjbGeneralVersion:add')")
	public R save(@Valid @RequestBody ShjbGeneralVersionResponseVO shjbGeneralVersion) {
		return R.status(shjbGeneralVersionService.save(ShjbGeneralVersionWrapper.build().PVEntity(shjbGeneralVersion)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入shjbGeneralVersion")
	@PreAuth("hasPermission('shjbGeneralVersion:shjbGeneralVersion:update')")
	public R update(@Valid @RequestBody ShjbGeneralVersionResponseVO shjbGeneralVersion) {
	    if (Func.isEmpty(shjbGeneralVersion.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(shjbGeneralVersionService.updateById(ShjbGeneralVersionWrapper.build().PVEntity(shjbGeneralVersion)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('shjbGeneralVersion:shjbGeneralVersion:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(shjbGeneralVersionService.deleteLogic(Func.toLongList(ids)));
	}

}
