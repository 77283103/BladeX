package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractChangeEntity;
import org.springblade.contract.service.IContractChangeService;
import org.springblade.contract.vo.ContractChangeRequestVO;
import org.springblade.contract.vo.ContractChangeResponseVO;
import org.springblade.contract.wrapper.ContractChangeWrapper;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 合同变更 控制器
 *
 * @author : szw
 * @date : 2020-09-23 19:24:49
 */
@RestController
@AllArgsConstructor
@RequestMapping("/change")
@Api(value = "合同变更", tags = "合同变更")
public class ContractChangeController extends BladeController {

	private IContractChangeService changeService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入change")
	@PreAuth("hasPermission('contractChange:change:detail')")
	public R<ContractChangeResponseVO> detail(@RequestParam Long id) {
		ContractChangeEntity detail = changeService.getById(id);
		return R.data(ContractChangeWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入change")
	@PreAuth("hasPermission('contractChange:change:list')")
	public R<IPage<ContractChangeResponseVO>> list(ContractChangeEntity change, Query query) {
		IPage<ContractChangeEntity> pages = changeService.pageList(Condition.getPage(query), change);
		return R.data(ContractChangeWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入change")
	@PreAuth("hasPermission('contractChange:change:add')")
	public R save(@Valid @RequestBody ContractChangeRequestVO change) {
        ContractChangeEntity entity = new ContractChangeEntity();
        BeanUtil.copy(change,entity);
		return R.status(changeService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入change")
	@PreAuth("hasPermission('contractChange:change:update')")
	public R update(@Valid @RequestBody ContractChangeRequestVO change) {
	    if (Func.isEmpty(change.getId())){
            throw new ServiceException("id不能为空");
        }
	    ContractChangeEntity entity = new ContractChangeEntity();
        BeanUtil.copy(change,entity);
		return R.status(changeService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractChange:change:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(changeService.deleteLogic(Func.toLongList(ids)));
	}

}
