package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.PerServiceContentListResponseVO;
import org.springblade.contract.vo.PerServiceContentRequestVO;
import org.springblade.contract.vo.PerServiceContentResponseVO;
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

import org.springblade.contract.entity.PerServiceContentEntity;
import org.springblade.contract.wrapper.PerServiceContentWrapper;
import org.springblade.contract.service.IPerServiceContentService;


/**
 * 履约服务内容-接受/提供服务控制器
 *
 * @author : chenzy
 * @date : 2021-04-20 16:02:05
 */
@RestController
@AllArgsConstructor
@RequestMapping("/perServiceContent")
@Api(value = "履约服务内容", tags = "履约服务内容")
public class PerServiceContentController extends BladeController {

	private IPerServiceContentService perServiceContentService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入perServiceContent")
	@PreAuth("hasPermission('asdsd:perServiceContent:detail')")
	public R<PerServiceContentResponseVO> detail(@RequestParam Long id) {
		PerServiceContentEntity detail = perServiceContentService.getById(id);
		return R.data(PerServiceContentWrapper.build().entityPV(detail));
	}

	/**
	 * 接受/提供服务清单
	 */
	@GetMapping("/serviceContentList")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入perServiceContent")
	@PreAuth("hasPermission('perServiceContent:perServiceContent:page')")
	public R<IPage<PerServiceContentListResponseVO>> serviceContentList(PerServiceContentRequestVO perServiceContent, Query query) {
		IPage<PerServiceContentListResponseVO> pages = perServiceContentService.serviceContentList(Condition.getPage(query), perServiceContent);
		return R.data(pages);
	}


	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入perServiceContent")
	@PreAuth("hasPermission('asdsd:perServiceContent:update')")
	public R update(@Valid @RequestBody PerServiceContentResponseVO perServiceContent) {
	    if (Func.isEmpty(perServiceContent.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(perServiceContentService.updateById(PerServiceContentWrapper.build().PVEntity(perServiceContent)));
	}


}
