package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.CglProofingContractEntity;
import org.springblade.contract.service.ICglProofingContractService;
import org.springblade.contract.vo.CglProofingContractRequestVO;
import org.springblade.contract.vo.CglProofingContractResponseVO;
import org.springblade.contract.wrapper.CglProofingContractWrapper;
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
 * 采购类_打样合同书 控制器
 *
 * @author : 采购类_打样合同书
 * @date : 2021-01-12 13:24:33
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cglProofingContract")
@Api(value = "采购类_打样合同书", tags = "采购类_打样合同书")
public class CglProofingContractController extends BladeController {

	private ICglProofingContractService cglProofingContractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入cglProofingContract")
	@PreAuth("hasPermission('cglProofingContract:cglProofingContract:detail')")
	public R<CglProofingContractResponseVO> detail(@RequestParam Long id) {
		CglProofingContractEntity detail = cglProofingContractService.getById(id);
		return R.data(CglProofingContractWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入cglProofingContract")
	@PreAuth("hasPermission('cglProofingContract:cglProofingContract:page')")
	public R<IPage<CglProofingContractResponseVO>> list(CglProofingContractEntity cglProofingContract, Query query) {
		IPage<CglProofingContractEntity> pages = cglProofingContractService.pageList(Condition.getPage(query), cglProofingContract);
		return R.data(CglProofingContractWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入cglProofingContract")
	@PreAuth("hasPermission('cglProofingContract:cglProofingContract:add')")
	public R save(@Valid @RequestBody CglProofingContractRequestVO cglProofingContract) {
		return R.status(cglProofingContractService.save(CglProofingContractWrapper.build().QVEntity(cglProofingContract)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入cglProofingContract")
	@PreAuth("hasPermission('cglProofingContract:cglProofingContract:update')")
	public R update(@Valid @RequestBody CglProofingContractRequestVO cglProofingContract) {
	    if (Func.isEmpty(cglProofingContract.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(cglProofingContractService.updateById(CglProofingContractWrapper.build().QVEntity(cglProofingContract)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('cglProofingContract:cglProofingContract:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(cglProofingContractService.deleteLogic(Func.toLongList(ids)));
	}

}
