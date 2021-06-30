package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.entity.ContractMmhtxxpfEntity;
import org.springblade.contract.service.IContractMmhtxxpfService;
import org.springblade.contract.vo.ContractMmhtxxpfRequestVO;
import org.springblade.contract.vo.ContractMmhtxxpfResponseVO;
import org.springblade.contract.wrapper.ContractMmhtxxpfWrapper;
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



/**
 * 行销品买卖合同 控制器
 *
 * @author : kx
 * @date : 2021-05-10 13:37:34
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractMmhtxxpf")
@Api(value = "行销品买卖合同", tags = "行销品买卖合同")
public class ContractMmhtxxpfController extends BladeController {

	private IContractMmhtxxpfService contractMmhtxxpfService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractMmhtxxpf")
	@PreAuth("hasPermission('contractMmhtxxpf:contractMmhtxxpf:detail')")
	public R<ContractMmhtxxpfResponseVO> detail(@RequestParam Long id) {
		ContractMmhtxxpfEntity detail = contractMmhtxxpfService.getById(id);
		return R.data(ContractMmhtxxpfWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractMmhtxxpf")
	@PreAuth("hasPermission('contractMmhtxxpf:contractMmhtxxpf:page')")
	public R<IPage<ContractMmhtxxpfResponseVO>> list(ContractMmhtxxpfEntity contractMmhtxxpf, Query query) {
		IPage<ContractMmhtxxpfEntity> pages = contractMmhtxxpfService.pageList(Condition.getPage(query), contractMmhtxxpf);
		return R.data(ContractMmhtxxpfWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入contractMmhtxxpf")
	@PreAuth("hasPermission('contractMmhtxxpf:contractMmhtxxpf:add')")
	public R save(@Valid @RequestBody ContractMmhtxxpfRequestVO contractMmhtxxpf) {
		return R.status(contractMmhtxxpfService.save(ContractMmhtxxpfWrapper.build().QVEntity(contractMmhtxxpf)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入contractMmhtxxpf")
	@PreAuth("hasPermission('contractMmhtxxpf:contractMmhtxxpf:update')")
	public R update(@Valid @RequestBody ContractMmhtxxpfRequestVO contractMmhtxxpf) {
	    if (Func.isEmpty(contractMmhtxxpf.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractMmhtxxpfService.updateById(ContractMmhtxxpfWrapper.build().QVEntity(contractMmhtxxpf)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractMmhtxxpf:contractMmhtxxpf:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractMmhtxxpfService.deleteLogic(Func.toLongList(ids)));
	}

}
