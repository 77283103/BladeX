package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.CglProofingContract1Entity;
import org.springblade.contract.service.ICglProofingContract1Service;
import org.springblade.contract.vo.CglProofingContract1RequestVO;
import org.springblade.contract.vo.CglProofingContract1ResponseVO;
import org.springblade.contract.wrapper.CglProofingContract1Wrapper;
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
 * cgl_proofing_contract1 控制器
 *
 * @author : cglProofingContract1
 * @date : 2021-01-12 13:48:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cglProofingContract1")
@Api(value = "cgl_proofing_contract1", tags = "cgl_proofing_contract1")
public class CglProofingContract1Controller extends BladeController {

	private ICglProofingContract1Service cglProofingContract1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入cglProofingContract1")
	@PreAuth("hasPermission('cglProofingContract1:cglProofingContract1:detail')")
	public R<CglProofingContract1ResponseVO> detail(@RequestParam Long id) {
		CglProofingContract1Entity detail = cglProofingContract1Service.getById(id);
		return R.data(CglProofingContract1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入cglProofingContract1")
	@PreAuth("hasPermission('cglProofingContract1:cglProofingContract1:page')")
	public R<IPage<CglProofingContract1ResponseVO>> list(CglProofingContract1Entity cglProofingContract1, Query query) {
		IPage<CglProofingContract1Entity> pages = cglProofingContract1Service.pageList(Condition.getPage(query), cglProofingContract1);
		return R.data(CglProofingContract1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入cglProofingContract1")
	@PreAuth("hasPermission('cglProofingContract1:cglProofingContract1:add')")
	public R save(@Valid @RequestBody CglProofingContract1RequestVO cglProofingContract1) {
		return R.status(cglProofingContract1Service.save(CglProofingContract1Wrapper.build().QVEntity(cglProofingContract1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入cglProofingContract1")
	@PreAuth("hasPermission('cglProofingContract1:cglProofingContract1:update')")
	public R update(@Valid @RequestBody CglProofingContract1RequestVO cglProofingContract1) {
	    if (Func.isEmpty(cglProofingContract1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(cglProofingContract1Service.updateById(CglProofingContract1Wrapper.build().QVEntity(cglProofingContract1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('cglProofingContract1:cglProofingContract1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(cglProofingContract1Service.deleteLogic(Func.toLongList(ids)));
	}

}
