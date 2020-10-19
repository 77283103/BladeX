package org.springblade.flow.business.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.flowable.engine.TaskService;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;

import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.flow.business.service.BorrowFlowService;
import org.springblade.flow.core.entity.BorrowFlowEntity;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;




/**
 * 传阅 控制器
 *
 * @author Liu Meng
 * @date 2020-8-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/borrow_flow")
@Api(value = "传阅", tags = "传阅")
public class BorrowFlowController extends BladeController {
	private final BorrowFlowService borrowFlowService;
	private TaskService taskService;

	/**
	 * 传阅查询列表
	 */
	@GetMapping(value = "borrow_flow_list")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "传入传阅信息", notes = "")
	public R<IPage<BorrowFlowEntity>> doneList(@ApiParam("传阅信息") BorrowFlowEntity borrowFlowEntity, Query query) {
		/* 获取当前登录人的ID */
		BladeUser user = AuthUtil.getUser();
		borrowFlowEntity.setGetPersonId(user.getUserId());
		IPage<BorrowFlowEntity> pages = borrowFlowService.selectBorrowPage(Condition.getPage(query), borrowFlowEntity);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "新增", notes = "传入borrow_flow")
	public R save(@Valid @RequestBody BorrowFlowEntity borrowFlow) {
		/* 获取当前登录人的ID */
		BladeUser user = AuthUtil.getUser();
		borrowFlow.setSendPersonId(user.getUserId());
		borrowFlowService.save(borrowFlow);
		String message ="传阅至";
		taskService.addComment(borrowFlow.getTaskId(), borrowFlow.getProcessInstanceId(), "传阅信息", message);
		return R.status(true);
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "修改", notes = "传入borrow_flow")
	public R update(@Valid @RequestBody BorrowFlowEntity borrowFlow) {
		return R.status(borrowFlowService.updateById(borrowFlow));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(borrowFlowService.deleteLogic(Func.toLongList(ids)));
	}
}
