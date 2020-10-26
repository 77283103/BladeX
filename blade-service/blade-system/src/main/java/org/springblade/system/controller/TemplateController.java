package org.springblade.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.TemplateFieldEntity;
import org.springblade.system.vo.TemplateRequestVO;
import org.springblade.system.vo.TemplateResponseVO;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.system.entity.TemplateEntity;
import org.springblade.system.wrapper.TemplateWrapper;
import org.springblade.system.service.ITemplateService;
import java.util.List;


/**
 * 表单模板 控制器
 *
 * @author : szw
 * @date : 2020-10-19 20:00:56
 */
@RestController
@AllArgsConstructor
@RequestMapping("/template")
@Api(value = "表单模板", tags = "表单模板")
public class TemplateController extends BladeController {

	private ITemplateService templateService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入template")
	@PreAuth("hasPermission('template:template:detail')")
	public R<TemplateResponseVO> detail(@RequestParam Long id) {
		TemplateEntity detail = templateService.getById(id);
		//JSON对象转成JSON数组再转成对象集合list
		String json="[{\n" +
			"\t\"componentType\": \"input\",\n" +
			"\t\"fieldName\": \"contractName\",\n" +
			"\t\"dicData\": \"\",\n" +
			"\t\"relationCode\": \"\",\n" +
			"\t\"disabled\": \"true\",\n" +
			"\t\"sort\": 1,\n" +
			"\t\"fieldTitle\": \"合同\",\n" +
			"\t\"fieldValue\": \"测试\",\n" +
			"\t\"fieldType\": \"String\",\n" +
			"\t\"required\": \"true\"\n" +
			"}, {\n" +
			"\t\"componentType\": \"datePicker\",\n" +
			"\t\"fieldName\": \"startingTime\",\n" +
			"\t\"dicData\": \"\",\n" +
			"\t\"relationCode\": \"\",\n" +
			"\t\"disabled\": \"false\",\n" +
			"\t\"sort\": 2,\n" +
			"\t\"fieldTitle\": \"开始时间\",\n" +
			"\t\"fieldValue\": \"\",\n" +
			"\t\"fieldType\": \"Date\",\n" +
			"\t\"required\": \"true\"\n" +
			"}, {\n" +
			"\t\"componentType\": \"datePicker\",\n" +
			"\t\"fieldName\": \"endTime\",\n" +
			"\t\"dicData\": \"\",\n" +
			"\t\"relationCode\": \"\",\n" +
			"\t\"disabled\": \"false\",\n" +
			"\t\"sort\": 3,\n" +
			"\t\"fieldTitle\": \"结束时间\",\n" +
			"\t\"fieldValue\": \"\",\n" +
			"\t\"fieldType\": \"Date\",\n" +
			"\t\"required\": \"true\"\n" +
			"}, {\n" +
			"\t\"componentType\": \"input\",\n" +
			"\t\"fieldName\": \"contractAmount\",\n" +
			"\t\"dicData\": \"\",\n" +
			"\t\"relationCode\": \"\",\n" +
			"\t\"disabled\": \"false\",\n" +
			"\t\"sort\": 4,\n" +
			"\t\"fieldTitle\": \"合同金额\",\n" +
			"\t\"fieldValue\": \"213\",\n" +
			"\t\"fieldType\": \"Double\",\n" +
			"\t\"required\": \"true\"\n" +
			"}, {\n" +
			"\t\"componentType\": \"select\",\n" +
			"\t\"fieldName\": \"currencyCategory\",\n" +
			"\t\"dicData\": {\n" +
			"\t\t\"rmb\": \"人民币\",\n" +
			"\t\t\"my\": \"美元\"\n" +
			"\t},\n" +
			"\t\"relationCode\": \"\",\n" +
			"\t\"disabled\": \"false\",\n" +
			"\t\"sort\": 5,\n" +
			"\t\"fieldTitle\": \"币种\",\n" +
			"\t\"fieldValue\": \"rmb\",\n" +
			"\t\"fieldType\": \"String\",\n" +
			"\t\"required\": \"true\"\n" +
			"}, {\n" +
			"\t\"componentType\": \"editList\",\n" +
			"\t\"fieldName\": \"accordingList\",\n" +
			"\t\"dicData\": [{\n" +
			"\t\t\"componentType\": \"input\",\n" +
			"\t\t\"fieldName\": \"accordingList\",\n" +
			"\t\t\"dicData\": \"\",\n" +
			"\t\t\"relationCode\": \"\",\n" +
			"\t\t\"disabled\": \"false\",\n" +
			"\t\t\"sort\": 1,\n" +
			"\t\t\"fieldTitle\": \"依据名称\",\n" +
			"\t\t\"fieldValue\": \"\",\n" +
			"\t\t\"fieldType\": \"String\",\n" +
			"\t\t\"required\": \"false\"\n" +
			"\t}, {\n" +
			"\t\t\"componentType\": \"select\",\n" +
			"\t\t\"fieldName\": \"accordingList\",\n" +
			"\t\t\"dicData\": {},\n" +
			"\t\t\"relationCode\": \"\",\n" +
			"\t\t\"disabled\": \"false\",\n" +
			"\t\t\"sort\": 2,\n" +
			"\t\t\"fieldTitle\": \"类型\",\n" +
			"\t\t\"fieldValue\": \"\",\n" +
			"\t\t\"fieldType\": \"String\",\n" +
			"\t\t\"required\": \"false\"\n" +
			"\t}, {\n" +
			"\t\t\"componentType\": \"input\",\n" +
			"\t\t\"fieldName\": \"accordingList\",\n" +
			"\t\t\"dicData\": \"\",\n" +
			"\t\t\"relationCode\": \"\",\n" +
			"\t\t\"disabled\": \"false\",\n" +
			"\t\t\"sort\": 3,\n" +
			"\t\t\"fieldTitle\": \"备注\",\n" +
			"\t\t\"fieldValue\": \"\",\n" +
			"\t\t\"fieldType\": \"String\",\n" +
			"\t\t\"required\": \"false\"\n" +
			"\t}],\n" +
			"\t\"relationCode\": \"ContractAccording\",\n" +
			"\t\"disabled\": \"false\",\n" +
			"\t\"sort\": 6,\n" +
			"\t\"fieldTitle\": \"相对方\",\n" +
			"\t\"fieldValue\": \"\",\n" +
			"\t\"fieldType\": \"List\",\n" +
			"\t\"required\": \"false\"\n" +
			"}]";


		List<TemplateFieldEntity> templateFieldList = JSON.parseArray(json, TemplateFieldEntity.class);
		JSONObject j = new JSONObject();
		for(TemplateFieldEntity templateField : templateFieldList){
			if("editList".equals(templateField.getComponentType())||"relationList".equals(templateField.getComponentType())){
				ContractFormInfoEntity contractFormInfoEntity=JSONObject.toJavaObject(j, ContractFormInfoEntity.class);
				if("ContractAccording".equals(templateField.getRelationCode())){
					/*保存依据信息*/
					/*if(contractFormInfo.getAccording().size()>0){
						contractFormInfoService.saveAccording(contractFormInfo);
					}*/
				}
			}else{
				j.put(templateField.getFieldName(), templateField.getFieldValue());
			}
		}

		return R.data(TemplateWrapper.build().entityVO(detail));
	}



	/**
	 * 根据code查询
	 */
	@PostMapping("/findCode")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入template")
	@PreAuth("hasPermission('template:template:findCode')")
	public R<TemplateEntity> findCode(@ApiParam(value = "code", required = true) @RequestParam String code) {
		TemplateEntity templateEntity=new TemplateEntity();
		QueryWrapper<TemplateEntity> queryWrapper = Condition.getQueryWrapper(templateEntity)
			.eq("form_code",code);
		List<TemplateEntity> list = templateService.list(queryWrapper);
		return R.data(list.get(0));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入template")
	@PreAuth("hasPermission('template:template:list')")
	public R<IPage<TemplateResponseVO>> list(TemplateEntity template, Query query) {
		IPage<TemplateEntity> pages = templateService.pageList(Condition.getPage(query), template);
		return R.data(TemplateWrapper.build().pageVO(pages));
	}


	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入template")
	@PreAuth("hasPermission('template:template:add')")
	public R<TemplateEntity> save(@Valid @RequestBody TemplateRequestVO template) throws Exception {
        TemplateEntity entity = new TemplateEntity();
        BeanUtil.copy(template,entity);
		List<TemplateEntity> list=templateService.selectByCode(entity.getFormCode());
        if (list.size()>0){
			return R.fail(BladeConstant.DEFAULT_FAILURE_MESSAGE);
		}else{
			templateService.save(entity);
			//templateService.saveBean(entity);
			return R.data(entity);
		}
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入template")
	@PreAuth("hasPermission('template:template:update')")
	public R update(@Valid @RequestBody TemplateRequestVO template) {
	    if (Func.isEmpty(template.getId())){
            throw new ServiceException("id不能为空");
        }
	    TemplateEntity entity = new TemplateEntity();
        BeanUtil.copy(template,entity);
		return R.status(templateService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('template:template:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(templateService.deleteLogic(Func.toLongList(ids)));
	}

}
