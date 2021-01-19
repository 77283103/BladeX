package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ProductOutServiceContract3Entity;
import org.springblade.contract.service.IProductOutServiceContract3Service;
import org.springblade.contract.vo.ProductOutServiceContract3RequestVO;
import org.springblade.contract.vo.ProductOutServiceContract3ResponseVO;
import org.springblade.contract.wrapper.ProductOutServiceContract3Wrapper;
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
 * 生产项目外包服务合同子表3 控制器
 *
 * @author : Wang Pengfei
 * @date : 2021-01-19 10:24:09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/productOutServiceContract3")
@Api(value = "生产项目外包服务合同子表3", tags = "生产项目外包服务合同子表3")
public class ProductOutServiceContract3Controller extends BladeController {

	private IProductOutServiceContract3Service productOutServiceContract3Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入productOutServiceContract3")
	@PreAuth("hasPermission('productOutServiceContract3:productOutServiceContract3:detail')")
	public R<ProductOutServiceContract3ResponseVO> detail(@RequestParam Long id) {
		ProductOutServiceContract3Entity detail = productOutServiceContract3Service.getById(id);
		return R.data(ProductOutServiceContract3Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入productOutServiceContract3")
	@PreAuth("hasPermission('productOutServiceContract3:productOutServiceContract3:page')")
	public R<IPage<ProductOutServiceContract3ResponseVO>> list(ProductOutServiceContract3Entity productOutServiceContract3, Query query) {
		IPage<ProductOutServiceContract3Entity> pages = productOutServiceContract3Service.pageList(Condition.getPage(query), productOutServiceContract3);
		return R.data(ProductOutServiceContract3Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入productOutServiceContract3")
	@PreAuth("hasPermission('productOutServiceContract3:productOutServiceContract3:add')")
	public R save(@Valid @RequestBody ProductOutServiceContract3RequestVO productOutServiceContract3) {
		return R.status(productOutServiceContract3Service.save(ProductOutServiceContract3Wrapper.build().QVEntity(productOutServiceContract3)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入productOutServiceContract3")
	@PreAuth("hasPermission('productOutServiceContract3:productOutServiceContract3:update')")
	public R update(@Valid @RequestBody ProductOutServiceContract3RequestVO productOutServiceContract3) {
	    if (Func.isEmpty(productOutServiceContract3.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(productOutServiceContract3Service.updateById(ProductOutServiceContract3Wrapper.build().QVEntity(productOutServiceContract3)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('productOutServiceContract3:productOutServiceContract3:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(productOutServiceContract3Service.deleteLogic(Func.toLongList(ids)));
	}

}
