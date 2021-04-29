package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.PerBondListVo;
import org.springblade.contract.vo.PerBondRequestVO;
import org.springblade.contract.vo.PerBondResponseVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.PerBondEntity;
import org.springblade.contract.wrapper.PerBondWrapper;
import org.springblade.contract.service.IPerBondService;


/**
 * 履约计划保证金 控制器
 *
 * @author : chenzy
 * @date : 2021-04-27 17:06:20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/perBond")
@Api(value = "履约计划保证金", tags = "履约计划保证金")
public class PerBondController extends BladeController {

	private IPerBondService perBondService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入perBond")
	@PreAuth("hasPermission('perBond:perBond:detail')")
	public R<PerBondResponseVO> detail(@RequestParam Long id) {
		PerBondEntity detail = perBondService.getById(id);
		return R.data(PerBondWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/perBondList")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入perBond")
	@PreAuth("hasPermission('perBond:perBond:page')")
	public R<IPage<PerBondListVo>> perBondList(PerBondRequestVO perBond, Query query) {
		IPage<PerBondListVo> pages = perBondService.perBondList(Condition.getPage(query), perBond);
		return R.data(pages);
	}



}
