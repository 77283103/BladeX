package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.SclConstructionProject3Entity;
import org.springblade.contract.service.ISclConstructionProject3Service;
import org.springblade.contract.vo.SclConstructionProject3RequestVO;
import org.springblade.contract.vo.SclConstructionProject3ResponseVO;
import org.springblade.contract.wrapper.SclConstructionProject3Wrapper;
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
 * 生产类：加工承揽合同（代工合同）关联表3 控制器
 *
 * @author : 生产类：加工承揽合同（代工合同）关联表3
 * @date : 2020-12-11 10:36:41
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sclConstructionProject3")
@Api(value = "生产类：加工承揽合同（代工合同）关联表3", tags = "生产类：加工承揽合同（代工合同）关联表3")
public class SclConstructionProject3Controller extends BladeController {

	private ISclConstructionProject3Service sclConstructionProject3Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入sclConstructionProject3")
	@PreAuth("hasPermission('sclConstructionProject3:sclConstructionProject3:detail')")
	public R<SclConstructionProject3ResponseVO> detail(@RequestParam Long id) {
		SclConstructionProject3Entity detail = sclConstructionProject3Service.getById(id);
		return R.data(SclConstructionProject3Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入sclConstructionProject3")
	@PreAuth("hasPermission('sclConstructionProject3:sclConstructionProject3:page')")
	public R<IPage<SclConstructionProject3ResponseVO>> list(SclConstructionProject3RequestVO sclConstructionProject3, Query query) {
		IPage<SclConstructionProject3Entity> pages = sclConstructionProject3Service.pageList(Condition.getPage(query), sclConstructionProject3);
		return R.data(SclConstructionProject3Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入sclConstructionProject3")
	@PreAuth("hasPermission('sclConstructionProject3:sclConstructionProject3:add')")
	public R save(@Valid @RequestBody SclConstructionProject3ResponseVO sclConstructionProject3) {
		return R.status(sclConstructionProject3Service.save(SclConstructionProject3Wrapper.build().PVEntity(sclConstructionProject3)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入sclConstructionProject3")
	@PreAuth("hasPermission('sclConstructionProject3:sclConstructionProject3:update')")
	public R update(@Valid @RequestBody SclConstructionProject3ResponseVO sclConstructionProject3) {
	    if (Func.isEmpty(sclConstructionProject3.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(sclConstructionProject3Service.updateById(SclConstructionProject3Wrapper.build().PVEntity(sclConstructionProject3)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('sclConstructionProject3:sclConstructionProject3:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(sclConstructionProject3Service.deleteLogic(Func.toLongList(ids)));
	}

}
