package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractSealEntity;
import org.springblade.contract.service.IContractSealService;
import org.springblade.contract.vo.ContractSealRequestVO;
import org.springblade.contract.vo.ContractSealResponseVO;
import org.springblade.contract.wrapper.ContractSealWrapper;
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
 * 统一子公司（签章申请单位） 控制器
 *
 * @author : xhb
 * @date : 2021-06-16 16:10:58
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractSeal")
@Api(value = "统一子公司（签章申请单位）", tags = "统一子公司（签章申请单位）")
public class ContractSealController extends BladeController {

	private final IContractSealService contractSealService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractSeal")
	public R<ContractSealResponseVO> detail(@RequestParam Long id) {
		ContractSealEntity detail = contractSealService.getById(id);
		return R.data(ContractSealWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractSeal")
	public R<IPage<ContractSealResponseVO>> list(ContractSealRequestVO contractSeal, Query query) {
		IPage<ContractSealEntity> pages = contractSealService.pageList(Condition.getPage(query), contractSeal);
		return R.data(ContractSealWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入contractSeal")
	public R save(@Valid @RequestBody ContractSealResponseVO contractSeal) {
		return R.status(contractSealService.save(ContractSealWrapper.build().PVEntity(contractSeal)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入contractSeal")
	public R update(@Valid @RequestBody ContractSealResponseVO contractSeal) {
	    if (Func.isEmpty(contractSeal.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractSealService.updateById(ContractSealWrapper.build().PVEntity(contractSeal)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractSeal:contractSeal:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractSealService.deleteLogic(Func.toLongList(ids)));
	}

}
