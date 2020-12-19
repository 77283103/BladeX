package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ShjbPointOperationEntity;
import org.springblade.contract.service.IShjbPointOperationService;
import org.springblade.contract.vo.ShjbPointOperationRequestVO;
import org.springblade.contract.vo.ShjbPointOperationResponseVO;
import org.springblade.contract.wrapper.ShjbPointOperationWrapper;
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
 * 售货机类：0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014） 控制器
 *
 * @author : 售货机类：0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014）
 * @date : 2020-12-18 16:05:22
 */
@RestController
@AllArgsConstructor
@RequestMapping("/shjbPointOperation")
@Api(value = "售货机类：0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014）", tags = "售货机类：0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014）")
public class ShjbPointOperationController extends BladeController {

	private IShjbPointOperationService shjbPointOperationService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入shjbPointOperation")
	@PreAuth("hasPermission('shjbPointOperation:shjbPointOperation:detail')")
	public R<ShjbPointOperationResponseVO> detail(@RequestParam Long id) {
		ShjbPointOperationEntity detail = shjbPointOperationService.getById(id);
		return R.data(ShjbPointOperationWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入shjbPointOperation")
	@PreAuth("hasPermission('shjbPointOperation:shjbPointOperation:page')")
	public R<IPage<ShjbPointOperationResponseVO>> list(ShjbPointOperationRequestVO shjbPointOperation, Query query) {
		IPage<ShjbPointOperationEntity> pages = shjbPointOperationService.pageList(Condition.getPage(query), shjbPointOperation);
		return R.data(ShjbPointOperationWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入shjbPointOperation")
	@PreAuth("hasPermission('shjbPointOperation:shjbPointOperation:add')")
	public R save(@Valid @RequestBody ShjbPointOperationResponseVO shjbPointOperation) {
		return R.status(shjbPointOperationService.save(ShjbPointOperationWrapper.build().PVEntity(shjbPointOperation)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入shjbPointOperation")
	@PreAuth("hasPermission('shjbPointOperation:shjbPointOperation:update')")
	public R update(@Valid @RequestBody ShjbPointOperationResponseVO shjbPointOperation) {
	    if (Func.isEmpty(shjbPointOperation.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(shjbPointOperationService.updateById(ShjbPointOperationWrapper.build().PVEntity(shjbPointOperation)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('shjbPointOperation:shjbPointOperation:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(shjbPointOperationService.deleteLogic(Func.toLongList(ids)));
	}

}
