package org.springblade.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.json.simple.JSONArray;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.DictBiz;
import org.springblade.system.entity.TemplateEntity;
import org.springblade.system.entity.TemplateFieldEntity;
import org.springblade.system.entity.TemplateFieldRelationEntity;
import org.springblade.system.mapper.TemplateFieldMapper;
import org.springblade.system.service.IDictBizService;
import org.springblade.system.service.ITemplateFieldRelationService;
import org.springblade.system.service.ITemplateFieldService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import springfox.documentation.spring.web.json.Json;

import java.lang.reflect.Field;
import java.util.*;

/**
 *  服务实现类
 *
 * @author szw
 * @date : 2020-10-20 14:45:05
 */
@Service
@Validated
@AllArgsConstructor
public class TemplateFieldServiceImpl extends BaseServiceImpl<TemplateFieldMapper, TemplateFieldEntity> implements ITemplateFieldService {
	private IDictBizService dictBizService;
	private ITemplateFieldRelationService templateFieldRelationService;
	@Override
	public IPage<TemplateFieldEntity> pageList(IPage<TemplateFieldEntity> page, TemplateFieldEntity templateField) {
		return baseMapper.pageList(page, templateField);
	}

	@Override
	public String json(List<TemplateFieldEntity> list) {
		JSONArray json = new JSONArray();
		List<DictBiz> tree=new ArrayList<DictBiz>();
		for(TemplateFieldEntity templateField : list){
			JSONObject jo = new JSONObject();
			jo.put("fieldName", templateField.getFieldName());
			jo.put("fieldValue", templateField.getFieldValue());
			jo.put("fieldTitle", templateField.getFieldTitle());
			jo.put("componentType", templateField.getComponentType());
			//判断字典code是否为空
			if("select".equals(templateField.getComponentType())||"cascader".equals(templateField.getComponentType())||"radio".equals(templateField.getComponentType())){
				tree = dictBizService.getList(templateField.getDicCode());
				JSONArray array = new JSONArray();
				for (DictBiz dictBiz:tree){
					JSONObject dic = new JSONObject();
					dic.put("fieldValue", dictBiz.getDictKey());
					dic.put("fieldTitle", dictBiz.getDictValue());
					array.add(dic);
				}
				jo.put("dicData", array);
			}else if("editList".equals(templateField.getComponentType())||"relationList".equals(templateField.getComponentType())){
				TemplateFieldRelationEntity templateFieldRelationEntity=new TemplateFieldRelationEntity();
				QueryWrapper<TemplateFieldRelationEntity> queryWrapper = Condition.getQueryWrapper(templateFieldRelationEntity)
					.eq("code",templateField.getRelationCode())
					.ne("field_title","")
					.orderByAsc("sort");
				List<TemplateFieldRelationEntity> listTemplateFieldRelation = templateFieldRelationService.list(queryWrapper);
				JSONArray jsonRelation = new JSONArray();
				for(TemplateFieldRelationEntity templateFieldRelation : listTemplateFieldRelation){
					JSONObject joRelation = new JSONObject();
					joRelation.put("fieldName", templateFieldRelation.getFieldName());
					joRelation.put("fieldValue", templateFieldRelation.getFieldValue());
					joRelation.put("fieldTitle", templateFieldRelation.getFieldTitle());
					joRelation.put("componentType", templateFieldRelation.getComponentType());
					//判断字典code是否为空
					if("select".equals(templateFieldRelation.getComponentType())||"cascader".equals(templateFieldRelation.getComponentType())||"radio".equals(templateFieldRelation.getComponentType())){
						tree = dictBizService.getList(templateFieldRelation.getDicCode());
						JSONArray array = new JSONArray();
						for (DictBiz dictBiz:tree){
							JSONObject dic = new JSONObject();
							dic.put("fieldValue", dictBiz.getDictKey());
							dic.put("fieldTitle", dictBiz.getDictValue());
							array.add(dic);
						}
						joRelation.put("dicData", array);
					}else{
						joRelation.put("dicData", "");
					}
					joRelation.put("fieldType", templateFieldRelation.getFieldType());
					joRelation.put("required", templateFieldRelation.getRequired());
					joRelation.put("disabled", templateFieldRelation.getDisabled());
					joRelation.put("relationCode", templateFieldRelation.getRelationCode());
					joRelation.put("sort", templateFieldRelation.getSort());
					jsonRelation.add(joRelation);
				}
				jo.put("dicData", jsonRelation);
				JSONArray data = new JSONArray();
				jo.put("tableData", data);
			}else{
				jo.put("dicData", "");
			}
			jo.put("fieldType", templateField.getFieldType());
			jo.put("required", templateField.getRequired());
			jo.put("disabled", templateField.getDisabled());
			jo.put("relationCode", templateField.getRelationCode());
			jo.put("sort", templateField.getSort());
			json.add(jo);
		}
		return json.toString();
	}

	@Override
	public List<TemplateFieldEntity> selectField(List<TemplateFieldEntity> list, TemplateEntity bean) {
		List<TemplateFieldEntity> listT=new ArrayList<TemplateFieldEntity>();
		try {
			Class<?> clazz = Class.forName(bean.getBean());
			// 获取类名
			String strName01 = clazz.getName();
			// 返回所有的属性
			Field[] field02 = clazz.getDeclaredFields();
			for(Field field:field02){
				TemplateFieldEntity templateFieldEntity=new TemplateFieldEntity();
				if("serialVersionUID".equals(field.getName())){
					templateFieldEntity.setFieldName("id");
				}else{
					templateFieldEntity.setFieldName(field.getName());
				}
				templateFieldEntity.setFieldType(field.getType().getName().substring(field.getType().getName().lastIndexOf(".")+1));
				templateFieldEntity.setBean(strName01);
				templateFieldEntity.setBeanName(bean.getBeanName());
				templateFieldEntity.setCode(bean.getFormCode());
				templateFieldEntity.setSort(99);
				listT.add(templateFieldEntity);
			}
			for(TemplateFieldEntity templateField:listT){
				for(TemplateFieldEntity tem:list){
					if(tem.getFieldName().equals(templateField.getFieldName())){
						BeanUtil.copy(tem,templateField);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		listT.sort(Comparator.comparing(TemplateFieldEntity::getSort));
		return listT;
	}
}
