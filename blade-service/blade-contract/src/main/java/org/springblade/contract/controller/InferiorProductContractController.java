package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.InferiorProductContractEntity;
import org.springblade.contract.service.IMinferiorProductContractService;
import org.springblade.contract.vo.InferiorProductContractRequestVO;
import org.springblade.contract.vo.InferiorProductContractResponseVO;
import org.springblade.contract.wrapper.InferiorProductContractWrapper;
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
 * 下脚品买卖合同模板 控制器
 *
 * @author : Wang Pengfei
 * @date : 2021-01-15 15:54:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/inferiorProductContract")
@Api(value = "下脚品买卖合同模板", tags = "下脚品买卖合同模板")
public class InferiorProductContractController extends BladeController {

	private IMinferiorProductContractService inferiorProductContractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入inferiorProductContract")
	@PreAuth("hasPermission('inferiorProductContract:inferiorProductContract:detail')")
	public R<InferiorProductContractResponseVO> detail(@RequestParam Long id) {
		InferiorProductContractEntity detail = inferiorProductContractService.getById(id);
		return R.data(InferiorProductContractWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入inferiorProductContract")
	@PreAuth("hasPermission('inferiorProductContract:inferiorProductContract:page')")
	public R<IPage<InferiorProductContractResponseVO>> list(InferiorProductContractEntity inferiorProductContract, Query query) {
		IPage<InferiorProductContractEntity> pages = inferiorProductContractService.pageList(Condition.getPage(query), inferiorProductContract);
		return R.data(InferiorProductContractWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入inferiorProductContract")
	@PreAuth("hasPermission('inferiorProductContract:inferiorProductContract:add')")
	public R save(@Valid @RequestBody InferiorProductContractRequestVO inferiorProductContract) {
		return R.status(inferiorProductContractService.save(InferiorProductContractWrapper.build().QVEntity(inferiorProductContract)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入inferiorProductContract")
	@PreAuth("hasPermission('inferiorProductContract:inferiorProductContract:update')")
	public R update(@Valid @RequestBody InferiorProductContractRequestVO inferiorProductContract) {
	    if (Func.isEmpty(inferiorProductContract.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(inferiorProductContractService.updateById(InferiorProductContractWrapper.build().QVEntity(inferiorProductContract)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('inferiorProductContract:inferiorProductContract:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(inferiorProductContractService.deleteLogic(Func.toLongList(ids)));
	}

}
