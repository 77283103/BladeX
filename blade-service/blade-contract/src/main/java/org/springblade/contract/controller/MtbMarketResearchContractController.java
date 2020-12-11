package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.MtbMarketResearchContractRequestVO;
import org.springblade.contract.vo.MtbMarketResearchContractResponseVO;
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

import org.springblade.contract.entity.MtbMarketResearchContractEntity;
import org.springblade.contract.wrapper.MtbMarketResearchContractWrapper;
import org.springblade.contract.service.IMtbMarketResearchContractService;


/**
 * 媒体类：市调合同（定性+定量) 控制器
 *
 * @author : 王策
 * @date : 2020-12-10 19:37:13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtbMarketResearchContract")
@Api(value = "媒体类：市调合同（定性+定量)", tags = "媒体类：市调合同（定性+定量)")
public class MtbMarketResearchContractController extends BladeController {

	private IMtbMarketResearchContractService mtbMarketResearchContractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtbMarketResearchContract")
	@PreAuth("hasPermission('mtbMarketResearchContract:mtbMarketResearchContract:detail')")
	public R<MtbMarketResearchContractResponseVO> detail(@RequestParam Long id) {
		MtbMarketResearchContractEntity detail = mtbMarketResearchContractService.getById(id);
		return R.data(MtbMarketResearchContractWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtbMarketResearchContract")
	@PreAuth("hasPermission('mtbMarketResearchContract:mtbMarketResearchContract:page')")
	public R<IPage<MtbMarketResearchContractResponseVO>> list(MtbMarketResearchContractRequestVO mtbMarketResearchContract, Query query) {
		IPage<MtbMarketResearchContractEntity> pages = mtbMarketResearchContractService.pageList(Condition.getPage(query), mtbMarketResearchContract);
		return R.data(MtbMarketResearchContractWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtbMarketResearchContract")
	@PreAuth("hasPermission('mtbMarketResearchContract:mtbMarketResearchContract:add')")
	public R save(@Valid @RequestBody MtbMarketResearchContractResponseVO mtbMarketResearchContract) {
		return R.status(mtbMarketResearchContractService.save(MtbMarketResearchContractWrapper.build().PVEntity(mtbMarketResearchContract)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtbMarketResearchContract")
	@PreAuth("hasPermission('mtbMarketResearchContract:mtbMarketResearchContract:update')")
	public R update(@Valid @RequestBody MtbMarketResearchContractResponseVO mtbMarketResearchContract) {
	    if (Func.isEmpty(mtbMarketResearchContract.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtbMarketResearchContractService.updateById(MtbMarketResearchContractWrapper.build().PVEntity(mtbMarketResearchContract)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtbMarketResearchContract:mtbMarketResearchContract:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtbMarketResearchContractService.deleteLogic(Func.toLongList(ids)));
	}

}
