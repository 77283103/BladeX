package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.MtlAdaptationContractRequestVO;
import org.springblade.contract.vo.MtlAdaptationContractResponseVO;
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

import org.springblade.contract.entity.MtlAdaptationContractEntity;
import org.springblade.contract.wrapper.MtlAdaptationContractWrapper;
import org.springblade.contract.service.IMtlAdaptationContractService;


/**
 * 媒体类：视频广告改编合同 控制器
 *
 * @author :  媒体类：视频广告改编合同
 * @date : 2020-12-10 19:40:27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtlAdaptationContract")
@Api(value = "媒体类：视频广告改编合同", tags = "媒体类：视频广告改编合同")
public class MtlAdaptationContractController extends BladeController {

	private IMtlAdaptationContractService mtlAdaptationContractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtlAdaptationContract")
	@PreAuth("hasPermission('mtlAdaptationContract:mtlAdaptationContract:detail')")
	public R<MtlAdaptationContractResponseVO> detail(@RequestParam Long id) {
		MtlAdaptationContractEntity detail = mtlAdaptationContractService.getById(id);
		return R.data(MtlAdaptationContractWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtlAdaptationContract")
	@PreAuth("hasPermission('mtlAdaptationContract:mtlAdaptationContract:page')")
	public R<IPage<MtlAdaptationContractResponseVO>> list(MtlAdaptationContractRequestVO mtlAdaptationContract, Query query) {
		IPage<MtlAdaptationContractEntity> pages = mtlAdaptationContractService.pageList(Condition.getPage(query), mtlAdaptationContract);
		return R.data(MtlAdaptationContractWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtlAdaptationContract")
	@PreAuth("hasPermission('mtlAdaptationContract:mtlAdaptationContract:add')")
	public R save(@Valid @RequestBody MtlAdaptationContractResponseVO mtlAdaptationContract) {
		return R.status(mtlAdaptationContractService.save(MtlAdaptationContractWrapper.build().PVEntity(mtlAdaptationContract)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtlAdaptationContract")
	@PreAuth("hasPermission('mtlAdaptationContract:mtlAdaptationContract:update')")
	public R update(@Valid @RequestBody MtlAdaptationContractResponseVO mtlAdaptationContract) {
	    if (Func.isEmpty(mtlAdaptationContract.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtlAdaptationContractService.updateById(MtlAdaptationContractWrapper.build().PVEntity(mtlAdaptationContract)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtlAdaptationContract:mtlAdaptationContract:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtlAdaptationContractService.deleteLogic(Func.toLongList(ids)));
	}

}
