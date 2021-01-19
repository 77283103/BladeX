package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.DistServiceContractEntity;
import org.springblade.contract.service.IDistServiceContractService;
import org.springblade.contract.vo.DistServiceContractResponseVO;
import org.springblade.contract.vo.DistributionServiceContractRequestVO;
import org.springblade.contract.wrapper.DistributionServiceContractWrapper;
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
 * 配送服务合同 控制器
 *
 * @author : 王策
 * @date : 2021-01-18 17:24:25
 */
@RestController
@AllArgsConstructor
@RequestMapping("/distributionServiceContract")
@Api(value = "配送服务合同", tags = "配送服务合同")
public class DistributionServiceContractController extends BladeController {

	private IDistServiceContractService distributionServiceContractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入distributionServiceContract")
	@PreAuth("hasPermission('distributionServiceContract:distributionServiceContract:detail')")
	public R<DistServiceContractResponseVO> detail(@RequestParam Long id) {
		DistServiceContractEntity detail = distributionServiceContractService.getById(id);
		return R.data(DistributionServiceContractWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入distributionServiceContract")
	@PreAuth("hasPermission('distributionServiceContract:distributionServiceContract:page')")
	public R<IPage<DistServiceContractResponseVO>> list(DistServiceContractEntity distributionServiceContract, Query query) {
		IPage<DistServiceContractEntity> pages = distributionServiceContractService.pageList(Condition.getPage(query), distributionServiceContract);
		return R.data(DistributionServiceContractWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入distributionServiceContract")
	@PreAuth("hasPermission('distributionServiceContract:distributionServiceContract:add')")
	public R save(@Valid @RequestBody DistributionServiceContractRequestVO distributionServiceContract) {
		return R.status(distributionServiceContractService.save(DistributionServiceContractWrapper.build().QVEntity(distributionServiceContract)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入distributionServiceContract")
	@PreAuth("hasPermission('distributionServiceContract:distributionServiceContract:update')")
	public R update(@Valid @RequestBody DistributionServiceContractRequestVO distributionServiceContract) {
	    if (Func.isEmpty(distributionServiceContract.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(distributionServiceContractService.updateById(DistributionServiceContractWrapper.build().QVEntity(distributionServiceContract)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('distributionServiceContract:distributionServiceContract:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(distributionServiceContractService.deleteLogic(Func.toLongList(ids)));
	}

}
