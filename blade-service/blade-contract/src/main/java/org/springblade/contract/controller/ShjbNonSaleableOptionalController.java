package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ShjbNonSaleableOptionalEntity;
import org.springblade.contract.service.IShjbNonSaleableOptionalService;
import org.springblade.contract.vo.ShjbNonSaleableOptionalRequestVO;
import org.springblade.contract.vo.ShjbNonSaleableOptionalResponseVO;
import org.springblade.contract.wrapper.ShjbNonSaleableOptionalWrapper;
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
 * 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本） 控制器
 *
 * @author : 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本）
 * @date : 2020-12-18 16:01:14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/shjbNonSaleableOptional")
@Api(value = "售货机类：2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本）", tags = "售货机类：2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本）")
public class ShjbNonSaleableOptionalController extends BladeController {

	private IShjbNonSaleableOptionalService shjbNonSaleableOptionalService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入shjbNonSaleableOptional")
	@PreAuth("hasPermission('shjbNonSaleableOptional:shjbNonSaleableOptional:detail')")
	public R<ShjbNonSaleableOptionalResponseVO> detail(@RequestParam Long id) {
		ShjbNonSaleableOptionalEntity detail = shjbNonSaleableOptionalService.getById(id);
		return R.data(ShjbNonSaleableOptionalWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入shjbNonSaleableOptional")
	@PreAuth("hasPermission('shjbNonSaleableOptional:shjbNonSaleableOptional:page')")
	public R<IPage<ShjbNonSaleableOptionalResponseVO>> list(ShjbNonSaleableOptionalRequestVO shjbNonSaleableOptional, Query query) {
		IPage<ShjbNonSaleableOptionalEntity> pages = shjbNonSaleableOptionalService.pageList(Condition.getPage(query), shjbNonSaleableOptional);
		return R.data(ShjbNonSaleableOptionalWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入shjbNonSaleableOptional")
	@PreAuth("hasPermission('shjbNonSaleableOptional:shjbNonSaleableOptional:add')")
	public R save(@Valid @RequestBody ShjbNonSaleableOptionalResponseVO shjbNonSaleableOptional) {
		return R.status(shjbNonSaleableOptionalService.save(ShjbNonSaleableOptionalWrapper.build().PVEntity(shjbNonSaleableOptional)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入shjbNonSaleableOptional")
	@PreAuth("hasPermission('shjbNonSaleableOptional:shjbNonSaleableOptional:update')")
	public R update(@Valid @RequestBody ShjbNonSaleableOptionalResponseVO shjbNonSaleableOptional) {
	    if (Func.isEmpty(shjbNonSaleableOptional.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(shjbNonSaleableOptionalService.updateById(ShjbNonSaleableOptionalWrapper.build().PVEntity(shjbNonSaleableOptional)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('shjbNonSaleableOptional:shjbNonSaleableOptional:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(shjbNonSaleableOptionalService.deleteLogic(Func.toLongList(ids)));
	}

}
