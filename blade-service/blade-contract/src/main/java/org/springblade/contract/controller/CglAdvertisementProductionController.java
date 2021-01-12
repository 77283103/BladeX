package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.CglAdvertisementProductionEntity;
import org.springblade.contract.service.ICglAdvertisementProductionService;
import org.springblade.contract.vo.CglAdvertisementProductionRequestVO;
import org.springblade.contract.vo.CglAdvertisementProductionResponseVO;
import org.springblade.contract.wrapper.CglAdvertisementProductionWrapper;
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
 * 采购类：广告制作安装合同模板 控制器
 *
 * @author : 采购类：广告制作安装合同模板
 * @date : 2021-01-11 15:05:51
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cglAdvertisementProduction")
@Api(value = "采购类：广告制作安装合同模板", tags = "采购类：广告制作安装合同模板")
public class CglAdvertisementProductionController extends BladeController {

	private ICglAdvertisementProductionService cglAdvertisementProductionService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入cglAdvertisementProduction")
	@PreAuth("hasPermission('cglAdvertisementProduction:cglAdvertisementProduction:detail')")
	public R<CglAdvertisementProductionResponseVO> detail(@RequestParam Long id) {
		CglAdvertisementProductionEntity detail = cglAdvertisementProductionService.getById(id);
		return R.data(CglAdvertisementProductionWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入cglAdvertisementProduction")
	@PreAuth("hasPermission('cglAdvertisementProduction:cglAdvertisementProduction:page')")
	public R<IPage<CglAdvertisementProductionResponseVO>> list(CglAdvertisementProductionEntity cglAdvertisementProduction, Query query) {
		IPage<CglAdvertisementProductionEntity> pages = cglAdvertisementProductionService.pageList(Condition.getPage(query), cglAdvertisementProduction);
		return R.data(CglAdvertisementProductionWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入cglAdvertisementProduction")
	@PreAuth("hasPermission('cglAdvertisementProduction:cglAdvertisementProduction:add')")
	public R save(@Valid @RequestBody CglAdvertisementProductionRequestVO cglAdvertisementProduction) {
		return R.status(cglAdvertisementProductionService.save(CglAdvertisementProductionWrapper.build().QVEntity(cglAdvertisementProduction)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入cglAdvertisementProduction")
	@PreAuth("hasPermission('cglAdvertisementProduction:cglAdvertisementProduction:update')")
	public R update(@Valid @RequestBody CglAdvertisementProductionRequestVO cglAdvertisementProduction) {
	    if (Func.isEmpty(cglAdvertisementProduction.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(cglAdvertisementProductionService.updateById(CglAdvertisementProductionWrapper.build().QVEntity(cglAdvertisementProduction)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('cglAdvertisementProduction:cglAdvertisementProduction:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(cglAdvertisementProductionService.deleteLogic(Func.toLongList(ids)));
	}

}
