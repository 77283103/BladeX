package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.MtlAudioProductionContractEntity;
import org.springblade.contract.service.IMtlAudioProductionContractService;
import org.springblade.contract.vo.MtlAudioProductionContractRequestVO;
import org.springblade.contract.vo.MtlAudioProductionContractResponseVO;
import org.springblade.contract.wrapper.MtlAudioProductionContractWrapper;
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
 * 媒体类：音频制作合同 控制器
 *
 * @author : 媒体类：音频制作合同
 * @date : 2020-12-10 19:21:33
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtlAudioProductionContract")
@Api(value = "媒体类：音频制作合同", tags = "媒体类：音频制作合同")
public class MtlAudioProductionContractController extends BladeController {

	private IMtlAudioProductionContractService mtlAudioProductionContractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtlAudioProductionContract")
	@PreAuth("hasPermission('mtlAudioProductionContract:mtlAudioProductionContract:detail')")
	public R<MtlAudioProductionContractResponseVO> detail(@RequestParam Long id) {
		MtlAudioProductionContractEntity detail = mtlAudioProductionContractService.getById(id);
		return R.data(MtlAudioProductionContractWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtlAudioProductionContract")
	@PreAuth("hasPermission('mtlAudioProductionContract:mtlAudioProductionContract:page')")
	public R<IPage<MtlAudioProductionContractResponseVO>> list(MtlAudioProductionContractRequestVO mtlAudioProductionContract, Query query) {
		IPage<MtlAudioProductionContractEntity> pages = mtlAudioProductionContractService.pageList(Condition.getPage(query), mtlAudioProductionContract);
		return R.data(MtlAudioProductionContractWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtlAudioProductionContract")
	@PreAuth("hasPermission('mtlAudioProductionContract:mtlAudioProductionContract:add')")
	public R save(@Valid @RequestBody MtlAudioProductionContractResponseVO mtlAudioProductionContract) {
		return R.status(mtlAudioProductionContractService.save(MtlAudioProductionContractWrapper.build().PVEntity(mtlAudioProductionContract)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtlAudioProductionContract")
	@PreAuth("hasPermission('mtlAudioProductionContract:mtlAudioProductionContract:update')")
	public R update(@Valid @RequestBody MtlAudioProductionContractResponseVO mtlAudioProductionContract) {
	    if (Func.isEmpty(mtlAudioProductionContract.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtlAudioProductionContractService.updateById(MtlAudioProductionContractWrapper.build().PVEntity(mtlAudioProductionContract)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtlAudioProductionContract:mtlAudioProductionContract:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtlAudioProductionContractService.deleteLogic(Func.toLongList(ids)));
	}

}
