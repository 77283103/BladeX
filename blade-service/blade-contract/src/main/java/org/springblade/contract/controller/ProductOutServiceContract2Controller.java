package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ProductOutServiceContract2Entity;
import org.springblade.contract.service.IProductOutServiceContract2Service;
import org.springblade.contract.vo.ProductOutServiceContract2RequestVO;
import org.springblade.contract.vo.ProductOutServiceContract2ResponseVO;
import org.springblade.contract.wrapper.ProductOutServiceContract2Wrapper;
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
 * 生产项目外包服务合同子表2 控制器
 *
 * @author : Wang Pengfei
 * @date : 2021-01-19 10:23:05
 */
@RestController
@AllArgsConstructor
@RequestMapping("/productOutServiceContract2")
@Api(value = "生产项目外包服务合同子表2", tags = "生产项目外包服务合同子表2")
public class ProductOutServiceContract2Controller extends BladeController {

	private IProductOutServiceContract2Service productOutServiceContract2Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入productOutServiceContract2")
	@PreAuth("hasPermission('productOutServiceContract2:productOutServiceContract2:detail')")
	public R<ProductOutServiceContract2ResponseVO> detail(@RequestParam Long id) {
		ProductOutServiceContract2Entity detail = productOutServiceContract2Service.getById(id);
		return R.data(ProductOutServiceContract2Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入productOutServiceContract2")
	@PreAuth("hasPermission('productOutServiceContract2:productOutServiceContract2:page')")
	public R<IPage<ProductOutServiceContract2ResponseVO>> list(ProductOutServiceContract2Entity productOutServiceContract2, Query query) {
		IPage<ProductOutServiceContract2Entity> pages = productOutServiceContract2Service.pageList(Condition.getPage(query), productOutServiceContract2);
		return R.data(ProductOutServiceContract2Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入productOutServiceContract2")
	@PreAuth("hasPermission('productOutServiceContract2:productOutServiceContract2:add')")
	public R save(@Valid @RequestBody ProductOutServiceContract2RequestVO productOutServiceContract2) {
		return R.status(productOutServiceContract2Service.save(ProductOutServiceContract2Wrapper.build().QVEntity(productOutServiceContract2)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入productOutServiceContract2")
	@PreAuth("hasPermission('productOutServiceContract2:productOutServiceContract2:update')")
	public R update(@Valid @RequestBody ProductOutServiceContract2RequestVO productOutServiceContract2) {
	    if (Func.isEmpty(productOutServiceContract2.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(productOutServiceContract2Service.updateById(ProductOutServiceContract2Wrapper.build().QVEntity(productOutServiceContract2)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('productOutServiceContract2:productOutServiceContract2:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(productOutServiceContract2Service.deleteLogic(Func.toLongList(ids)));
	}

}
