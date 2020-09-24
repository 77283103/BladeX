package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ChangeEntity;
import org.springblade.contract.service.IChangeService;
import org.springblade.contract.vo.ChangeRequestVO;
import org.springblade.contract.vo.ChangeResponseVO;
import org.springblade.contract.wrapper.ChangeWrapper;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 合同变更 控制器
 *
 * @author : szw
 * @date : 2020-09-23 19:24:49
 */
@RestController
@AllArgsConstructor
@RequestMapping("/change")
@Api(value = "合同变更", tags = "合同变更")
public class ChangeController extends BladeController {

	private IChangeService changeService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入change")
	@PreAuth("hasPermission('contractChange:change:detail')")
	public R<ChangeResponseVO> detail(@RequestParam Long id) {
		ChangeEntity detail = changeService.getById(id);
		return R.data(ChangeWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入change")
	@PreAuth("hasPermission('contractChange:change:list')")
	public R<IPage<ChangeResponseVO>> list(ChangeEntity change, Query query) {
		IPage<ChangeEntity> pages = changeService.pageList(Condition.getPage(query), change);
		return R.data(ChangeWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入change")
	@PreAuth("hasPermission('contractChange:change:add')")
	public R save(@Valid @RequestBody ChangeRequestVO change) {
        ChangeEntity entity = new ChangeEntity();
        BeanUtil.copy(change,entity);
		return R.status(changeService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入change")
	@PreAuth("hasPermission('contractChange:change:update')")
	public R update(@Valid @RequestBody ChangeRequestVO change) {
	    if (Func.isEmpty(change.getId())){
            throw new ServiceException("id不能为空");
        }
	    ChangeEntity entity = new ChangeEntity();
        BeanUtil.copy(change,entity);
		return R.status(changeService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractChange:change:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(changeService.deleteLogic(Func.toLongList(ids)));
	}

}
