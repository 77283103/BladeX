package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.CglActivityExecutionContractRequestVO;
import org.springblade.contract.vo.CglActivityExecutionContractResponseVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.CglActivityExecutionContractEntity;
import org.springblade.contract.wrapper.CglActivityExecutionContractWrapper;
import org.springblade.contract.service.ICglActivityExecutionContractService;


/**
 * 采购类：活动执行合同 控制器
 *
 * @author : 采购类：活动执行合同
 * @date : 2020-12-10 18:29:51
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cglActivityExecutionContract")
@Api(value = "采购类：活动执行合同", tags = "采购类：活动执行合同")
public class CglActivityExecutionContractController extends BladeController {

	private ICglActivityExecutionContractService cglActivityExecutionContractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入cglActivityExecutionContract")
	@PreAuth("hasPermission('cglActivityExecutionContract:cglActivityExecutionContract:detail')")
	public R<CglActivityExecutionContractResponseVO> detail(@RequestParam Long id) {
		CglActivityExecutionContractEntity detail = cglActivityExecutionContractService.getById(id);
		return R.data(CglActivityExecutionContractWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入cglActivityExecutionContract")
	@PreAuth("hasPermission('cglActivityExecutionContract:cglActivityExecutionContract:page')")
	public R<IPage<CglActivityExecutionContractResponseVO>> list(CglActivityExecutionContractRequestVO cglActivityExecutionContract, Query query) {
		IPage<CglActivityExecutionContractEntity> pages = cglActivityExecutionContractService.pageList(Condition.getPage(query), cglActivityExecutionContract);
		return R.data(CglActivityExecutionContractWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入cglActivityExecutionContract")
	@PreAuth("hasPermission('cglActivityExecutionContract:cglActivityExecutionContract:add')")
	public R save(@Valid @RequestBody CglActivityExecutionContractResponseVO cglActivityExecutionContract) {
		return R.status(cglActivityExecutionContractService.save(CglActivityExecutionContractWrapper.build().PVEntity(cglActivityExecutionContract)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入cglActivityExecutionContract")
	@PreAuth("hasPermission('cglActivityExecutionContract:cglActivityExecutionContract:update')")
	public R update(@Valid @RequestBody CglActivityExecutionContractResponseVO cglActivityExecutionContract) {
	    if (Func.isEmpty(cglActivityExecutionContract.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(cglActivityExecutionContractService.updateById(CglActivityExecutionContractWrapper.build().PVEntity(cglActivityExecutionContract)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('cglActivityExecutionContract:cglActivityExecutionContract:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(cglActivityExecutionContractService.deleteLogic(Func.toLongList(ids)));
	}

}
