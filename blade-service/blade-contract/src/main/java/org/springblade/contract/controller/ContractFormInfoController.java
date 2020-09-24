package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.wrapper.ContractFormInfoWrapper;
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
 * @author : 史智伟
 * @date : 2020-09-23 18:04:37
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractFormInfo")
@Api(value = "", tags = "")
public class ContractFormInfoController extends BladeController {

	private IContractFormInfoService contractFormInfoService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:detail')")
	public R<ContractFormInfoResponseVO> detail(@RequestParam Long id) {
		ContractFormInfoEntity detail = contractFormInfoService.getById(id);
		return R.data(ContractFormInfoWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:list')")
	public R<IPage<ContractFormInfoResponseVO>> list(ContractFormInfoEntity contractFormInfo, Query query) {
		IPage<ContractFormInfoEntity> pages = contractFormInfoService.pageList(Condition.getPage(query), contractFormInfo);
		return R.data(ContractFormInfoWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:add')")
	public R save(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
        ContractFormInfoEntity entity = new ContractFormInfoEntity();
        BeanUtil.copy(contractFormInfo,entity);
		return R.status(contractFormInfoService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:update')")
	public R update(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
	    if (Func.isEmpty(contractFormInfo.getId())){
            throw new ServiceException("id不能为空");
        }
	    ContractFormInfoEntity entity = new ContractFormInfoEntity();
        BeanUtil.copy(contractFormInfo,entity);
		return R.status(contractFormInfoService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractFormInfoService.deleteLogic(Func.toLongList(ids)));
	}

}
