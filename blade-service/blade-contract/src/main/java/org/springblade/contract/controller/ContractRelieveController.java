package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractRelieveEntity;
import org.springblade.contract.service.IContractRelieveService;
import org.springblade.contract.vo.ContractRelieveRequestVO;
import org.springblade.contract.vo.ContractRelieveResponseVO;
import org.springblade.contract.wrapper.ContractRelieveWrapper;
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
 *  控制器
 *
 * @author : szw
 * @date : 2020-09-23 20:10:29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/relieve")
@Api(value = "", tags = "")
public class ContractRelieveController extends BladeController {

	private IContractRelieveService relieveService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入relieve")
	@PreAuth("hasPermission('contractRelieve:relieve:detail')")
	public R<ContractRelieveResponseVO> detail(@RequestParam Long id) {
		ContractRelieveEntity detail = relieveService.getById(id);
		return R.data(ContractRelieveWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入relieve")
	@PreAuth("hasPermission('contractRelieve:relieve:list')")
	public R<IPage<ContractRelieveResponseVO>> list(ContractRelieveEntity relieve, Query query) {
		IPage<ContractRelieveEntity> pages = relieveService.pageList(Condition.getPage(query), relieve);
		return R.data(ContractRelieveWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入relieve")
	@PreAuth("hasPermission('contractRelieve:relieve:add')")
	public R save(@Valid @RequestBody ContractRelieveRequestVO relieve) {
        ContractRelieveEntity entity = new ContractRelieveEntity();
        BeanUtil.copy(relieve,entity);
		return R.status(relieveService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入relieve")
	@PreAuth("hasPermission('contractRelieve:relieve:update')")
	public R update(@Valid @RequestBody ContractRelieveRequestVO relieve) {
	    if (Func.isEmpty(relieve.getId())){
            throw new ServiceException("id不能为空");
        }
	    ContractRelieveEntity entity = new ContractRelieveEntity();
        BeanUtil.copy(relieve,entity);
		return R.status(relieveService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractRelieve:relieve:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(relieveService.deleteLogic(Func.toLongList(ids)));
	}

}
