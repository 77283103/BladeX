package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.MtlEditedTheContractRequestVO;
import org.springblade.contract.vo.MtlEditedTheContractResponseVO;
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

import org.springblade.contract.entity.MtlEditedTheContractEntity;
import org.springblade.contract.wrapper.MtlEditedTheContractWrapper;
import org.springblade.contract.service.IMtlEditedTheContractService;


/**
 * 媒体类：修图合同 控制器
 *
 * @author : 媒体类：修图合同
 * @date : 2020-12-10 19:24:45
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtlEditedTheContract")
@Api(value = "媒体类：修图合同", tags = "媒体类：修图合同")
public class MtlEditedTheContractController extends BladeController {

	private IMtlEditedTheContractService mtlEditedTheContractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtlEditedTheContract")
	@PreAuth("hasPermission('mtlEditedTheContract:mtlEditedTheContract:detail')")
	public R<MtlEditedTheContractResponseVO> detail(@RequestParam Long id) {
		MtlEditedTheContractEntity detail = mtlEditedTheContractService.getById(id);
		return R.data(MtlEditedTheContractWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtlEditedTheContract")
	@PreAuth("hasPermission('mtlEditedTheContract:mtlEditedTheContract:page')")
	public R<IPage<MtlEditedTheContractResponseVO>> list(MtlEditedTheContractRequestVO mtlEditedTheContract, Query query) {
		IPage<MtlEditedTheContractEntity> pages = mtlEditedTheContractService.pageList(Condition.getPage(query), mtlEditedTheContract);
		return R.data(MtlEditedTheContractWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtlEditedTheContract")
	@PreAuth("hasPermission('mtlEditedTheContract:mtlEditedTheContract:add')")
	public R save(@Valid @RequestBody MtlEditedTheContractResponseVO mtlEditedTheContract) {
		return R.status(mtlEditedTheContractService.save(MtlEditedTheContractWrapper.build().PVEntity(mtlEditedTheContract)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtlEditedTheContract")
	@PreAuth("hasPermission('mtlEditedTheContract:mtlEditedTheContract:update')")
	public R update(@Valid @RequestBody MtlEditedTheContractResponseVO mtlEditedTheContract) {
	    if (Func.isEmpty(mtlEditedTheContract.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtlEditedTheContractService.updateById(MtlEditedTheContractWrapper.build().PVEntity(mtlEditedTheContract)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtlEditedTheContract:mtlEditedTheContract:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtlEditedTheContractService.deleteLogic(Func.toLongList(ids)));
	}

}
