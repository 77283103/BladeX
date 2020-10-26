package org.springblade.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.vo.TemplateRelationRequestVO;
import org.springblade.system.vo.TemplateRelationResponseVO;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.system.entity.TemplateRelationEntity;
import org.springblade.system.wrapper.TemplateRelationWrapper;
import org.springblade.system.service.ITemplateRelationService;

import java.util.List;


/**
 * 关联表单 控制器
 *
 * @author : szw
 * @date : 2020-10-23 17:24:26
 */
@RestController
@AllArgsConstructor
@RequestMapping("/templateRelation")
@Api(value = "关联表单", tags = "关联表单")
public class TemplateRelationController extends BladeController {

	private ITemplateRelationService templateRelationService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入templateRelation")
	@PreAuth("hasPermission('templateRelation:templateRelation:detail')")
	public R<TemplateRelationResponseVO> detail(@RequestParam Long id) {
		TemplateRelationEntity detail = templateRelationService.getById(id);
		return R.data(TemplateRelationWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入templateRelation")
	@PreAuth("hasPermission('templateRelation:templateRelation:list')")
	public R<IPage<TemplateRelationResponseVO>> list(TemplateRelationEntity templateRelation, Query query) {
		IPage<TemplateRelationEntity> pages = templateRelationService.pageList(Condition.getPage(query), templateRelation);
		return R.data(TemplateRelationWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入templateRelation")
	@PreAuth("hasPermission('templateRelation:templateRelation:add')")
	public R save(@Valid @RequestBody TemplateRelationRequestVO templateRelation) {
        TemplateRelationEntity entity = new TemplateRelationEntity();
        BeanUtil.copy(templateRelation,entity);
		TemplateRelationEntity templateRelationEntity=new TemplateRelationEntity();
		QueryWrapper<TemplateRelationEntity> queryWrapper = Condition.getQueryWrapper(templateRelationEntity)
			.eq("form_code",entity.getFormCode());
		List<TemplateRelationEntity> list = templateRelationService.list(queryWrapper);
		if (list.size()>0){
			return R.fail(BladeConstant.DEFAULT_FAILURE_MESSAGE);
		}else{
			templateRelationService.save(entity);
			return R.data(entity);
		}
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入templateRelation")
	@PreAuth("hasPermission('templateRelation:templateRelation:update')")
	public R update(@Valid @RequestBody TemplateRelationRequestVO templateRelation) {
	    if (Func.isEmpty(templateRelation.getId())){
            throw new ServiceException("id不能为空");
        }
	    TemplateRelationEntity entity = new TemplateRelationEntity();
        BeanUtil.copy(templateRelation,entity);
		return R.status(templateRelationService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('templateRelation:templateRelation:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(templateRelationService.deleteLogic(Func.toLongList(ids)));
	}

}
