package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.PerCollectPayListResponseVO;
import org.springblade.contract.vo.PerCollectPayRequestVO;
import org.springblade.contract.vo.PerCollectPayResponseVO;
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

import org.springblade.contract.entity.PerCollectPayEntity;
import org.springblade.contract.wrapper.PerCollectPayWrapper;
import org.springblade.contract.service.IPerCollectPayService;


/**
 * 履约收付款 控制器
 *
 * @author : chenzy
 * @date : 2021-04-25 10:32:27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/perCollectPay")
@Api(value = "履约收付款", tags = "履约收付款")
public class PerCollectPayController extends BladeController {

	private IPerCollectPayService perCollectPayService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入perCollectPay")
	@PreAuth("hasPermission('asdsd:perCollectPay:detail')")
	public R<PerCollectPayResponseVO> detail(@RequestParam Long id) {
		PerCollectPayEntity detail = perCollectPayService.getById(id);
		return R.data(PerCollectPayWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/perCollectPayList")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入perCollectPay")
	@PreAuth("hasPermission('perCollectPay:perCollectPay:page')")
	public R<IPage<PerCollectPayListResponseVO>> perCollectPayList(PerCollectPayRequestVO perCollectPay, Query query) {
		return R.data(perCollectPayService.perCollectPayList(Condition.getPage(query), perCollectPay));
	}



}
