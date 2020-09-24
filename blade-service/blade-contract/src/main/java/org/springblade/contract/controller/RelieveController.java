package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.RelieveEntity;
import org.springblade.contract.service.IRelieveService;
import org.springblade.contract.vo.RelieveRequestVO;
import org.springblade.contract.vo.RelieveResponseVO;
import org.springblade.contract.wrapper.RelieveWrapper;
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
 *  控制器
 *
 * @author : szw
 * @date : 2020-09-23 20:10:29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/relieve")
@Api(value = "", tags = "")
public class RelieveController extends BladeController {

	private IRelieveService relieveService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入relieve")
	@PreAuth("hasPermission('contractRelieve:relieve:detail')")
	public R<RelieveResponseVO> detail(@RequestParam Long id) {
		RelieveEntity detail = relieveService.getById(id);
		return R.data(RelieveWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入relieve")
	@PreAuth("hasPermission('contractRelieve:relieve:list')")
	public R<IPage<RelieveResponseVO>> list(RelieveEntity relieve, Query query) {
		IPage<RelieveEntity> pages = relieveService.pageList(Condition.getPage(query), relieve);
		return R.data(RelieveWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入relieve")
	@PreAuth("hasPermission('contractRelieve:relieve:add')")
	public R save(@Valid @RequestBody RelieveRequestVO relieve) {
        RelieveEntity entity = new RelieveEntity();
        BeanUtil.copy(relieve,entity);
		return R.status(relieveService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入relieve")
	@PreAuth("hasPermission('contractRelieve:relieve:update')")
	public R update(@Valid @RequestBody RelieveRequestVO relieve) {
	    if (Func.isEmpty(relieve.getId())){
            throw new ServiceException("id不能为空");
        }
	    RelieveEntity entity = new RelieveEntity();
        BeanUtil.copy(relieve,entity);
		return R.status(relieveService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractRelieve:relieve:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(relieveService.deleteLogic(Func.toLongList(ids)));
	}

}
