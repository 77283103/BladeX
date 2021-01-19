package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ProductOutServiceContract1Entity;
import org.springblade.contract.service.IProductOutServiceContract1Service;
import org.springblade.contract.vo.ProductOutServiceContract1RequestVO;
import org.springblade.contract.vo.ProductOutServiceContract1ResponseVO;
import org.springblade.contract.wrapper.ProductOutServiceContract1Wrapper;
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
 * 生产项目外包服务合同子表1 控制器
 *
 * @author : Wang Pengfei
 * @date : 2021-01-19 10:22:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/productOutServiceContract1")
@Api(value = "生产项目外包服务合同子表1", tags = "生产项目外包服务合同子表1")
public class ProductOutServiceContract1Controller extends BladeController {

	private IProductOutServiceContract1Service productOutServiceContract1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入productOutServiceContract1")
	@PreAuth("hasPermission('productOutServiceContract1:productOutServiceContract1:detail')")
	public R<ProductOutServiceContract1ResponseVO> detail(@RequestParam Long id) {
		ProductOutServiceContract1Entity detail = productOutServiceContract1Service.getById(id);
		return R.data(ProductOutServiceContract1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入productOutServiceContract1")
	@PreAuth("hasPermission('productOutServiceContract1:productOutServiceContract1:page')")
	public R<IPage<ProductOutServiceContract1ResponseVO>> list(ProductOutServiceContract1Entity productOutServiceContract1, Query query) {
		IPage<ProductOutServiceContract1Entity> pages = productOutServiceContract1Service.pageList(Condition.getPage(query), productOutServiceContract1);
		return R.data(ProductOutServiceContract1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入productOutServiceContract1")
	@PreAuth("hasPermission('productOutServiceContract1:productOutServiceContract1:add')")
	public R save(@Valid @RequestBody ProductOutServiceContract1RequestVO productOutServiceContract1) {
		return R.status(productOutServiceContract1Service.save(ProductOutServiceContract1Wrapper.build().QVEntity(productOutServiceContract1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入productOutServiceContract1")
	@PreAuth("hasPermission('productOutServiceContract1:productOutServiceContract1:update')")
	public R update(@Valid @RequestBody ProductOutServiceContract1RequestVO productOutServiceContract1) {
	    if (Func.isEmpty(productOutServiceContract1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(productOutServiceContract1Service.updateById(ProductOutServiceContract1Wrapper.build().QVEntity(productOutServiceContract1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('productOutServiceContract1:productOutServiceContract1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(productOutServiceContract1Service.deleteLogic(Func.toLongList(ids)));
	}

}
