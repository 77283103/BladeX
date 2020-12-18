package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ShjbOnlinePaymentOffsetEntity;
import org.springblade.contract.service.IShjbOnlinePaymentOffsetService;
import org.springblade.contract.vo.ShjbOnlinePaymentOffsetRequestVO;
import org.springblade.contract.vo.ShjbOnlinePaymentOffsetResponseVO;
import org.springblade.contract.wrapper.ShjbOnlinePaymentOffsetWrapper;
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
 * 售货机类：0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014） 控制器
 *
 * @author : 售货机类：0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014）
 * @date : 2020-12-18 16:04:40
 */
@RestController
@AllArgsConstructor
@RequestMapping("/shjbOnlinePaymentOffset")
@Api(value = "售货机类：0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014）", tags = "售货机类：0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014）")
public class ShjbOnlinePaymentOffsetController extends BladeController {

	private IShjbOnlinePaymentOffsetService shjbOnlinePaymentOffsetService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入shjbOnlinePaymentOffset")
	@PreAuth("hasPermission('shjbOnlinePaymentOffset:shjbOnlinePaymentOffset:detail')")
	public R<ShjbOnlinePaymentOffsetResponseVO> detail(@RequestParam Long id) {
		ShjbOnlinePaymentOffsetEntity detail = shjbOnlinePaymentOffsetService.getById(id);
		return R.data(ShjbOnlinePaymentOffsetWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入shjbOnlinePaymentOffset")
	@PreAuth("hasPermission('shjbOnlinePaymentOffset:shjbOnlinePaymentOffset:page')")
	public R<IPage<ShjbOnlinePaymentOffsetResponseVO>> list(ShjbOnlinePaymentOffsetRequestVO shjbOnlinePaymentOffset, Query query) {
		IPage<ShjbOnlinePaymentOffsetEntity> pages = shjbOnlinePaymentOffsetService.pageList(Condition.getPage(query), shjbOnlinePaymentOffset);
		return R.data(ShjbOnlinePaymentOffsetWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入shjbOnlinePaymentOffset")
	@PreAuth("hasPermission('shjbOnlinePaymentOffset:shjbOnlinePaymentOffset:add')")
	public R save(@Valid @RequestBody ShjbOnlinePaymentOffsetResponseVO shjbOnlinePaymentOffset) {
		return R.status(shjbOnlinePaymentOffsetService.save(ShjbOnlinePaymentOffsetWrapper.build().PVEntity(shjbOnlinePaymentOffset)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入shjbOnlinePaymentOffset")
	@PreAuth("hasPermission('shjbOnlinePaymentOffset:shjbOnlinePaymentOffset:update')")
	public R update(@Valid @RequestBody ShjbOnlinePaymentOffsetResponseVO shjbOnlinePaymentOffset) {
	    if (Func.isEmpty(shjbOnlinePaymentOffset.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(shjbOnlinePaymentOffsetService.updateById(ShjbOnlinePaymentOffsetWrapper.build().PVEntity(shjbOnlinePaymentOffset)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('shjbOnlinePaymentOffset:shjbOnlinePaymentOffset:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(shjbOnlinePaymentOffsetService.deleteLogic(Func.toLongList(ids)));
	}

}
