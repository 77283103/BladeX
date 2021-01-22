package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.MtbMarketResearchContract1Entity;
import org.springblade.contract.service.IMtbMarketResearchContract1Service;
import org.springblade.contract.vo.MtbMarketResearchContract1RequestVO;
import org.springblade.contract.vo.MtbMarketResearchContract1ResponseVO;
import org.springblade.contract.wrapper.MtbMarketResearchContract1Wrapper;
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
 * 市调合同 控制器
 *
 * @author : 刘是罕
 * @date : 2021-01-21 11:07:49
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtbMarketResearchContract1")
@Api(value = "市调合同", tags = "市调合同")
public class MtbMarketResearchContract1Controller extends BladeController {

	private IMtbMarketResearchContract1Service mtbMarketResearchContract1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtbMarketResearchContract1")
	@PreAuth("hasPermission('mtbMarketResearchContract1:mtbMarketResearchContract1:detail')")
	public R<MtbMarketResearchContract1ResponseVO> detail(@RequestParam Long id) {
		MtbMarketResearchContract1Entity detail = mtbMarketResearchContract1Service.getById(id);
		return R.data(MtbMarketResearchContract1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtbMarketResearchContract1")
	@PreAuth("hasPermission('mtbMarketResearchContract1:mtbMarketResearchContract1:page')")
	public R<IPage<MtbMarketResearchContract1ResponseVO>> list(MtbMarketResearchContract1Entity mtbMarketResearchContract1, Query query) {
		IPage<MtbMarketResearchContract1Entity> pages = mtbMarketResearchContract1Service.pageList(Condition.getPage(query), mtbMarketResearchContract1);
		return R.data(MtbMarketResearchContract1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入mtbMarketResearchContract1")
	@PreAuth("hasPermission('mtbMarketResearchContract1:mtbMarketResearchContract1:add')")
	public R save(@Valid @RequestBody MtbMarketResearchContract1RequestVO mtbMarketResearchContract1) {
		return R.status(mtbMarketResearchContract1Service.save(MtbMarketResearchContract1Wrapper.build().QVEntity(mtbMarketResearchContract1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入mtbMarketResearchContract1")
	@PreAuth("hasPermission('mtbMarketResearchContract1:mtbMarketResearchContract1:update')")
	public R update(@Valid @RequestBody MtbMarketResearchContract1RequestVO mtbMarketResearchContract1) {
	    if (Func.isEmpty(mtbMarketResearchContract1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtbMarketResearchContract1Service.updateById(MtbMarketResearchContract1Wrapper.build().QVEntity(mtbMarketResearchContract1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtbMarketResearchContract1:mtbMarketResearchContract1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtbMarketResearchContract1Service.deleteLogic(Func.toLongList(ids)));
	}

}
