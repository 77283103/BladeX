package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ProductOutServiceContractEntity;
import org.springblade.contract.service.IProductOutServiceContractService;
import org.springblade.contract.vo.ProductOutServiceContractRequestVO;
import org.springblade.contract.vo.ProductOutServiceContractResponseVO;
import org.springblade.contract.wrapper.ProductOutServiceContractWrapper;
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
 * 生产项目外包服务合同 控制器
 *
 * @author : Wang Pengfei
 * @date : 2021-01-19 10:19:59
 */
@RestController
@AllArgsConstructor
@RequestMapping("/productOutServiceContract")
@Api(value = "生产项目外包服务合同", tags = "生产项目外包服务合同")
public class ProductOutServiceContractController extends BladeController {

	private IProductOutServiceContractService productOutServiceContractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入productOutServiceContract")
	@PreAuth("hasPermission('productOutServiceContract:productOutServiceContract:detail')")
	public R<ProductOutServiceContractResponseVO> detail(@RequestParam Long id) {
		ProductOutServiceContractEntity detail = productOutServiceContractService.getById(id);
		return R.data(ProductOutServiceContractWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入productOutServiceContract")
	@PreAuth("hasPermission('productOutServiceContract:productOutServiceContract:page')")
	public R<IPage<ProductOutServiceContractResponseVO>> list(ProductOutServiceContractEntity productOutServiceContract, Query query) {
		IPage<ProductOutServiceContractEntity> pages = productOutServiceContractService.pageList(Condition.getPage(query), productOutServiceContract);
		return R.data(ProductOutServiceContractWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入productOutServiceContract")
	@PreAuth("hasPermission('productOutServiceContract:productOutServiceContract:add')")
	public R save(@Valid @RequestBody ProductOutServiceContractRequestVO productOutServiceContract) {
		return R.status(productOutServiceContractService.save(ProductOutServiceContractWrapper.build().QVEntity(productOutServiceContract)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入productOutServiceContract")
	@PreAuth("hasPermission('productOutServiceContract:productOutServiceContract:update')")
	public R update(@Valid @RequestBody ProductOutServiceContractRequestVO productOutServiceContract) {
	    if (Func.isEmpty(productOutServiceContract.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(productOutServiceContractService.updateById(ProductOutServiceContractWrapper.build().QVEntity(productOutServiceContract)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('productOutServiceContract:productOutServiceContract:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(productOutServiceContractService.deleteLogic(Func.toLongList(ids)));
	}

}
