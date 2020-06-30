package org.springblade.system.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import org.springblade.system.entity.UserDepartEntity;
import org.springblade.system.service.IUserDepartService;

import java.util.ArrayList;
import java.util.List;


/**
 *  控制器
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping("/depart")
@Api(value = "身份", tags = "身份")
public class UserDepartController extends BladeController {

	private final IUserDepartService userDepartService;

//	/**
//	 * 详情
//	 */
//	@GetMapping("/detail")
//	@ApiOperationSupport(order = 1)
//	@ApiOperation(value = "详情", notes = "传入user_depart")
//	public R<UserDepartEntity> detail(UserDepartEntity userDepart) {
//		UserDepartEntity detail = userDepartService.getOne(Condition.getQueryWrapper(userDepart));
//		return R.data(detail);
//	}
//
//	/**
//	 * 分页 代码自定义代号
//	 */
//	@GetMapping("/list")
//	@ApiOperationSupport(order = 2)
//	@ApiOperation(value = "分页", notes = "传入user_depart")
//	public R<IPage<UserDepartEntity>> list(UserDepartEntity user_depart, Query query) {
//		IPage<UserDepartEntity> pages = userDepartService.page(Condition.getPage(query), Condition.getQueryWrapper(user_depart));
//		return R.data(pages);
//	}
//
//	/**
//	 * 新增 代码自定义代号
//	 */
//	@PostMapping("/save")
//	@ApiOperationSupport(order = 4)
//	@ApiOperation(value = "新增", notes = "传入user_depart")
//	public R save(@Valid @RequestBody UserDepartEntity user_depart) {
//		return R.status(userDepartService.save(user_depart));
//	}
//
//	/**
//	 * 修改 代码自定义代号
//	 */
//	@PostMapping("/update")
//	@ApiOperationSupport(order = 5)
//	@ApiOperation(value = "修改", notes = "传入user_depart")
//	public R update(@Valid @RequestBody UserDepartEntity user_depart) {
//		return R.status(userDepartService.updateById(user_depart));
//	}
//
//	/**
//	 * 新增或修改 代码自定义代号
//	 */
//	@PostMapping("/submit")
//	@ApiOperationSupport(order = 6)
//	@ApiOperation(value = "新增或修改", notes = "传入user_depart")
//	public R submit(@Valid @RequestBody UserDepartEntity user_depart) {
//		return R.status(userDepartService.saveOrUpdate(user_depart));
//	}
//
//
//	/**
//	 * 删除 代码自定义代号
//	 */
//	@PostMapping("/remove")
//	@ApiOperationSupport(order = 7)
//	@ApiOperation(value = "逻辑删除", notes = "传入ids")
//	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
//		return R.status(userDepartService.deleteLogic(Func.toLongList(ids)));
//	}

	/**
	 * 根据当前登录用户查询身份信息
	 */
	@GetMapping("/currentUserDepart")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入user_depart")
	public R<List<UserDepartEntity>> currentUserDepart(BladeUser user) {
		List<UserDepartEntity> list = userDepartService.currentUserDepart(user.getUserId());
		return R.data(list);
	}

	/**
	 * 身份选择
	 * @return
	 */
	@GetMapping("/selectDepart")
	public void selectDepart(String departId) {
		System.out.println(departId);
	}
}
