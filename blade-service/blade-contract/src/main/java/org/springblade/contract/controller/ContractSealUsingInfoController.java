package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.ContractSealUsingInfoEntity;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.service.IContractSealUsingInfoService;
import org.springblade.contract.vo.ContractSealUsingInfoRequestVO;
import org.springblade.contract.vo.ContractSealUsingInfoResponseVO;
import org.springblade.contract.wrapper.ContractSealUsingInfoWrapper;
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
 * 用印名称 控制器
 *
 * @author : szw
 * @date : 2020-09-24 01:27:14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sealInfo")
@Api(value = "用印名称", tags = "用印名称")
public class ContractSealUsingInfoController extends BladeController {

	private IContractSealUsingInfoService sealInfoService;
	private IContractFormInfoService contractFormInfoService;
	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入sealInfo")
	@PreAuth("hasPermission('signInfo:sealInfo:detail')")
	public R<ContractSealUsingInfoResponseVO> detail(@RequestParam Long id) {
		ContractSealUsingInfoEntity detail = sealInfoService.getById(id);
		return R.data(ContractSealUsingInfoWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入sealInfo")
	@PreAuth("hasPermission('signInfo:sealInfo:list')")
	public R<IPage<ContractSealUsingInfoResponseVO>> list(ContractSealUsingInfoEntity sealInfo, Query query) {
		IPage<ContractSealUsingInfoEntity> pages = sealInfoService.pageList(Condition.getPage(query), sealInfo);
		return R.data(ContractSealUsingInfoWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入sealInfo")
	@PreAuth("hasPermission('signInfo:sealInfo:add')")
	public R save(@Valid @RequestBody ContractSealUsingInfoRequestVO sealInfo) {
        ContractSealUsingInfoEntity entity = new ContractSealUsingInfoEntity();
        BeanUtil.copy(sealInfo,entity);
		sealInfoService.save(entity);
		sealInfo.setId(entity.getId());
		sealInfoService.saveSeal(sealInfo);
		return R.data(entity);
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入sealInfo")
	@PreAuth("hasPermission('signInfo:sealInfo:update')")
	public R update(@Valid @RequestBody ContractSealUsingInfoRequestVO sealInfo) {
	    if (Func.isEmpty(sealInfo.getId())){
            throw new ServiceException("id不能为空");
        }
	    ContractSealUsingInfoEntity entity = new ContractSealUsingInfoEntity();
        BeanUtil.copy(sealInfo,entity);
		return R.status(sealInfoService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('signInfo:sealInfo:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(sealInfoService.deleteLogic(Func.toLongList(ids)));
	}

}
