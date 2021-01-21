package org.springblade.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.feign.IContractClient;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.TemplateEntity;
import org.springblade.system.service.ITemplateService;
import org.springblade.system.vo.TemplateFieldRequestVO;
import org.springblade.system.vo.TemplateFieldResponseVO;
import org.springblade.system.vo.TemplateRequestVO;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.system.entity.TemplateFieldEntity;
import org.springblade.system.wrapper.TemplateFieldWrapper;
import org.springblade.system.service.ITemplateFieldService;

import java.util.List;


/**
 *  控制器
 *
 * @author : szw
 * @date : 2020-10-20 14:45:04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/templateField")
@Api(value = "", tags = "")
public class TemplateFieldController extends BladeController {

	private ITemplateFieldService templateFieldService;
	private ITemplateService templateService;
	private IContractClient contractClient;
	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入TemplateField")
	@PreAuth("hasPermission('templateField:TemplateField:detail')")
	public R<TemplateFieldResponseVO> detail(@RequestParam Long id) {
		TemplateFieldEntity detail = templateFieldService.getById(id);
		return R.data(TemplateFieldWrapper.build().entityVO(detail));
	}

	/**
	 * 配置
	 */
	@PostMapping("/configure")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "配置", notes = "configure")
	@PreAuth("hasPermission('templateField:templateField:configure')")
	public R<List<TemplateFieldEntity>> configure(@Valid @RequestBody TemplateRequestVO template) {
		TemplateEntity detail = templateService.getById(template.getId());
		TemplateFieldEntity templateFieldEntity=new TemplateFieldEntity();
		QueryWrapper<TemplateFieldEntity> queryWrapper = Condition.getQueryWrapper(templateFieldEntity)
			.eq("code",template.getFormCode())
			.eq("is_show","true")
			//.ne("field_title","")
			.orderByAsc("sort");
		List<TemplateFieldEntity> list = templateFieldService.list(queryWrapper);
		list=templateFieldService.selectField(list,detail);
		return R.data(list);
	}

	/**
	 * 生成Json
	 */
	@PostMapping("/configureJson")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "生成Json", notes = "configureJson")
	@PreAuth("hasPermission('templateField:templateField:configureJson')")
	public R<List<TemplateFieldEntity>> configureJson(@Valid @RequestBody TemplateRequestVO template) {
		TemplateFieldEntity templateFieldEntity=new TemplateFieldEntity();
		QueryWrapper<TemplateFieldEntity> queryWrapper = Condition.getQueryWrapper(templateFieldEntity)
			.eq("code",template.getFormCode())
			.eq("is_show","true")
			.orderByAsc("sort");
		List<TemplateFieldEntity> listT = templateFieldService.list(queryWrapper);
		TemplateEntity entity = new TemplateEntity();
		BeanUtil.copy(template,entity);
		entity.setJson(templateFieldService.json(listT));
		contractClient.templateUpdate(entity);
		return R.status(templateService.updateById(entity));

	}
	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入TemplateField")
	@PreAuth("hasPermission('templateField:TemplateField:list')")
	public R<IPage<TemplateFieldResponseVO>> list(TemplateFieldEntity TemplateField, Query query) {
		IPage<TemplateFieldEntity> pages = templateFieldService.pageList(Condition.getPage(query), TemplateField);
		return R.data(TemplateFieldWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入TemplateField")
	@PreAuth("hasPermission('templateField:TemplateField:add')")
	public R save(@Valid @RequestBody TemplateFieldRequestVO TemplateField) {
		TemplateFieldEntity entity = new TemplateFieldEntity();
		BeanUtil.copy(TemplateField,entity);
		return R.status(templateFieldService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入TemplateField")
	@PreAuth("hasPermission('templateField:TemplateField:update')")
	public R update(@Valid @RequestBody List<TemplateFieldRequestVO> templateFieldList) {
		TemplateFieldEntity entity = new TemplateFieldEntity();
		for (TemplateFieldRequestVO templateField:templateFieldList){
			if(templateField.getComponentType()!=null){
				BeanUtil.copy(templateField,entity);
				if(Func.isEmpty(entity.getId())){

					templateFieldService.save(entity);
				}else{
					templateFieldService.updateById(entity);
				}
			}
		}
		/*templateFieldList.forEach(templateField ->{

		});*/
		/*JSONArray json = new JSONArray();
		for(TemplateFieldEntity templateField : templateFieldList){
			JSONObject jo = new JSONObject();
			jo.put(templateField.getFieldValue(), templateField.getFieldValue());
			json.add(jo);
		}
		System.out.println(json);*/
	   /* if (Func.isEmpty(TemplateField.getId())){
            throw new ServiceException("id不能为空");
        }
	    TemplateFieldEntity entity = new TemplateFieldEntity();
        BeanUtil.copy(TemplateField,entity);
        templateFieldService.updateById(entity)*/
		return R.success(BladeConstant.DEFAULT_SUCCESS_MESSAGE);
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('templateField:TemplateField:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(templateFieldService.deleteLogic(Func.toLongList(ids)));
	}

}
