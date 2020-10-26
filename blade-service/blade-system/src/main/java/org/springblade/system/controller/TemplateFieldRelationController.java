package org.springblade.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.TemplateRelationEntity;
import org.springblade.system.service.ITemplateRelationService;
import org.springblade.system.vo.*;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.system.entity.TemplateFieldRelationEntity;
import org.springblade.system.wrapper.TemplateFieldRelationWrapper;
import org.springblade.system.service.ITemplateFieldRelationService;

import java.util.List;


/**
 * 关联表字段属性配置表 控制器
 *
 * @author : szw
 * @date : 2020-10-23 17:24:41
 */
@RestController
@AllArgsConstructor
@RequestMapping("/templateFieldRelation")
@Api(value = "关联表字段属性配置表", tags = "关联表字段属性配置表")
public class TemplateFieldRelationController extends BladeController {

	private ITemplateFieldRelationService templateFieldRelationService;
	private ITemplateRelationService templateRelationService;
	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入templateFieldRelation")
	@PreAuth("hasPermission('templateFieldRelation:templateFieldRelation:detail')")
	public R<TemplateFieldRelationResponseVO> detail(@RequestParam Long id) {
		TemplateFieldRelationEntity detail = templateFieldRelationService.getById(id);
		return R.data(TemplateFieldRelationWrapper.build().entityVO(detail));
	}

	/**
	 * 配置
	 */
	@PostMapping("/configure")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "配置", notes = "configure")
	@PreAuth("hasPermission('templateFieldRelation:templateFieldRelation:configure')")
	public R<List<TemplateFieldRelationEntity>> configure(@Valid @RequestBody TemplateRelationRequestVO templateRelation) {
		TemplateRelationEntity detail = templateRelationService.getById(templateRelation.getId());
		TemplateFieldRelationEntity templateFieldRelationEntity=new TemplateFieldRelationEntity();
		QueryWrapper<TemplateFieldRelationEntity> queryWrapper = Condition.getQueryWrapper(templateFieldRelationEntity)
			.eq("code",templateRelation.getFormCode())
			.ne("field_title","")
			.orderByAsc("sort");
		List<TemplateFieldRelationEntity> list = templateFieldRelationService.list(queryWrapper);
		list=templateFieldRelationService.selectField(list,detail);
		return R.data(list);
	}

	/**
	 * 生成Json
	 */
	/*@PostMapping("/configureJson")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "生成Json", notes = "configureJson")
	@PreAuth("hasPermission('templateField:templateField:configureJson')")
	public R<List<TemplateFieldRelationEntity>> configureJson(@Valid @RequestBody TemplateRelationRequestVO templateRelation) {
		TemplateFieldRelationEntity templateFieldRelationEntity=new TemplateFieldRelationEntity();
		QueryWrapper<TemplateFieldRelationEntity> queryWrapper = Condition.getQueryWrapper(templateFieldRelationEntity)
			.eq("code",templateRelation.getFormCode())
			.ne("field_title","")
			.orderByAsc("sort");
		List<TemplateFieldRelationEntity> listT = templateFieldRelationService.list(queryWrapper);
		TemplateRelationEntity entity = new TemplateRelationEntity();
		BeanUtil.copy(templateRelation,entity);
		entity.setJson(templateFieldRelationService.json(listT));
		return R.status(templateRelationService.updateById(entity));
		//json转存成对象
		JSONObject jo = new JSONObject();
		for(TemplateFieldEntity templateField : listT){
			jo.put(templateField.getFieldValue(), templateField.getFieldValue());
		}
		TemplateFieldEntity templateField= JSONObject.toJavaObject(jo, TemplateFieldEntity.class);
	}*/

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入templateFieldRelation")
	@PreAuth("hasPermission('templateFieldRelation:templateFieldRelation:list')")
	public R<IPage<TemplateFieldRelationResponseVO>> list(TemplateFieldRelationEntity templateFieldRelation, Query query) {
		IPage<TemplateFieldRelationEntity> pages = templateFieldRelationService.pageList(Condition.getPage(query), templateFieldRelation);
		return R.data(TemplateFieldRelationWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入templateFieldRelation")
	@PreAuth("hasPermission('templateFieldRelation:templateFieldRelation:add')")
	public R save(@Valid @RequestBody TemplateFieldRelationRequestVO templateFieldRelation) {
        TemplateFieldRelationEntity entity = new TemplateFieldRelationEntity();
        BeanUtil.copy(templateFieldRelation,entity);
		return R.status(templateFieldRelationService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入templateFieldRelation")
	@PreAuth("hasPermission('templateFieldRelation:templateFieldRelation:update')")
	public R update(@Valid @RequestBody List<TemplateFieldRelationRequestVO> templateFieldRelationList) {
		TemplateFieldRelationEntity entity = new TemplateFieldRelationEntity();
		for (TemplateFieldRelationRequestVO templateFieldRelation:templateFieldRelationList){
			if(!Func.isEmpty(templateFieldRelation.getFieldTitle())){
				BeanUtil.copy(templateFieldRelation,entity);
				if(entity.getId()==-1){
					entity.setId(null);
					templateFieldRelationService.save(entity);
				}else{
					templateFieldRelationService.updateById(entity);
				}
			}
		}
		return R.success(BladeConstant.DEFAULT_SUCCESS_MESSAGE);
	}




	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('templateFieldRelation:templateFieldRelation:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(templateFieldRelationService.deleteLogic(Func.toLongList(ids)));
	}

}
