package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.YwiShopRecruitmentEntity;
import org.springblade.contract.service.IYwiShopRecruitmentService;
import org.springblade.contract.vo.YwiShopRecruitmentResponseVO;
import org.springblade.contract.vo.YwlShopRecruitmentRequestVO;
import org.springblade.contract.wrapper.YwlShopRecruitmentWrapper;
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
 * 业务类：14.店招合同 控制器
 *
 * @author : szw
 * @date : 2020-12-04 19:04:54
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ywlShopRecruitment")
@Api(value = "业务类：14.店招合同", tags = "业务类：14.店招合同")
public class YwlShopRecruitmentController extends BladeController {

	private IYwiShopRecruitmentService ywlShopRecruitmentService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入ywlShopRecruitment")
	@PreAuth("hasPermission('ywlShopRecruitment:ywlShopRecruitment:detail')")
	public R<YwiShopRecruitmentResponseVO> detail(@RequestParam Long id) {
		YwiShopRecruitmentEntity detail = ywlShopRecruitmentService.getById(id);
		return R.data(YwlShopRecruitmentWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入ywlShopRecruitment")
	@PreAuth("hasPermission('ywlShopRecruitment:ywlShopRecruitment:page')")
	public R<IPage<YwiShopRecruitmentResponseVO>> list(YwlShopRecruitmentRequestVO ywlShopRecruitment, Query query) {
		IPage<YwiShopRecruitmentEntity> pages = ywlShopRecruitmentService.pageList(Condition.getPage(query), ywlShopRecruitment);
		return R.data(YwlShopRecruitmentWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入ywlShopRecruitment")
	@PreAuth("hasPermission('ywlShopRecruitment:ywlShopRecruitment:add')")
	public R save(@Valid @RequestBody YwiShopRecruitmentResponseVO ywlShopRecruitment) {
		return R.status(ywlShopRecruitmentService.save(YwlShopRecruitmentWrapper.build().PVEntity(ywlShopRecruitment)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入ywlShopRecruitment")
	@PreAuth("hasPermission('ywlShopRecruitment:ywlShopRecruitment:update')")
	public R update(@Valid @RequestBody YwiShopRecruitmentResponseVO ywlShopRecruitment) {
	    if (Func.isEmpty(ywlShopRecruitment.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(ywlShopRecruitmentService.updateById(YwlShopRecruitmentWrapper.build().PVEntity(ywlShopRecruitment)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('ywlShopRecruitment:ywlShopRecruitment:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(ywlShopRecruitmentService.deleteLogic(Func.toLongList(ids)));
	}

}
