package org.springblade.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.constant.ContractFormInfoTemplateContract;
import org.springblade.system.entity.DictBiz;
import org.springblade.system.entity.TemplateEntity;
import org.springblade.system.entity.TemplateFieldEntity;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.mapper.TemplateFieldMapper;
import org.springblade.system.service.IDictBizService;
import org.springblade.system.service.ITemplateFieldRelationService;
import org.springblade.system.service.ITemplateFieldService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
	private ISysClient sysClient;
	@Override
	public IPage<TemplateFieldEntity> pageList(IPage<TemplateFieldEntity> page, TemplateFieldEntity templateField) {
		return baseMapper.pageList(page, templateField);
	}

	@Override
	public String json(List<TemplateFieldEntity> list) {
		//字段属性json对象
		JSONArray json = new JSONArray();
		List<DictBiz> tree=new ArrayList<DictBiz>();
		JSONObject id = new JSONObject();
		id.put("fieldValue", "");
		id.put("componentType", "id");
		json.add(id);
		for(TemplateFieldEntity templateField : list){
			JSONObject jo = new JSONObject();
			jo.put("fieldName", templateField.getFieldName());
			jo.put("fieldValue", templateField.getFieldValue());
			jo.put("fieldTitle", templateField.getFieldTitle());
			jo.put("componentType", templateField.getComponentType());
			//判断字典code是否为空
			if("select".equals(templateField.getComponentType())||"radio".equals(templateField.getComponentType())){
				tree = dictBizService.getList(templateField.getDicCode());
				JSONArray array = new JSONArray();
				//组装字典data
				for (DictBiz dictBiz:tree){
					JSONObject dic = new JSONObject();
					dic.put("fieldValue", dictBiz.getDictKey());
					dic.put("fieldTitle", dictBiz.getDictValue());
					array.add(dic);
				}
				jo.put("dicData", array);
				//组装可编辑列表data
			}else if("cascader".equals(templateField.getComponentType())){
				BladeUser bladeUser=new BladeUser() ;
				sysClient.getDeptTree("000000", bladeUser);
			}else if("editList".equals(templateField.getComponentType())||"relationList".equals(templateField.getComponentType())||"formList".equals(templateField.getComponentType())){
				if("ContractCounterpart".equals(templateField.getRelationCode())){
					JSONArray datas = new JSONArray();
					JSONObject data = new JSONObject();
					data.put("counterpart",datas);
					data.put("contractBond",datas);
					jo.put("tableDataObject", data);
				}else{
					JSONArray data = new JSONArray();
					jo.put("tableData", data);
				}
			}else if("secondSelect".equals(templateField.getComponentType())){
				if("Classification".equals(templateField.getRelationCode())){
					JSONObject data = new JSONObject();
					data.put("first","");
					data.put("second","");
					data.put("template","");
					jo.put("secondSelectData", data);
				}if(ContractFormInfoTemplateContract.CONTRACT_COL_PAY.equals(templateField.getRelationCode())){
					JSONObject data = new JSONObject();
					data.put("first","");
					data.put("second","");
					data.put("days","");
					jo.put("secondSelectData", data);
				}else{
					JSONObject data = new JSONObject();
					data.put("first","");
					data.put("second","");
					jo.put("secondSelectData", data);
				}
			}else if("upload".equals(templateField.getComponentType())){
				//处理文件类型
				JSONArray data = new JSONArray();
				jo.put("fileData", data);
			}else{
				jo.put("dicData", "");
			}
			jo.put("fieldType", templateField.getFieldType());
			jo.put("required", templateField.getRequired());
			JSONArray requiredArray = new JSONArray();
			//组装字段规则
			if("true".equals(templateField.getRequired())){
				if("input".equals(templateField.getComponentType())){
					JSONObject required = new JSONObject();
					required.put("required", true);
					required.put("message", templateField.getTips());
					required.put("trigger", "blur");
					requiredArray.add(required);
				}
				if("datePicker".equals(templateField.getComponentType())){
					JSONObject required = new JSONObject();
					required.put("type", "date");
					required.put("required", true);
					required.put("message", templateField.getTips());
					required.put("trigger", "change");
					requiredArray.add(required);
				}
				if("select".equals(templateField.getComponentType())||"radio".equals(templateField.getComponentType())){
					JSONObject required = new JSONObject();
					required.put("required", true);
					required.put("message", templateField.getTips());
					required.put("trigger", "change");
					requiredArray.add(required);
				}
			}
			jo.put("requiredData", requiredArray);
			jo.put("layout", templateField.getLayout());
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
			//默认先生成所有的合同主表
			Class<?> clazz = Class.forName("org.springblade.contract.entity.ContractFormInfoEntity");
			Field[] fieldFather = clazz.getDeclaredFields();
			List<Field> fieldFatherList= new ArrayList(Arrays.asList(fieldFather));
			//生成传过来的合同子表
			Class<?> clazzz = Class.forName(bean.getBean());
			Field[] fieldSon = clazzz.getDeclaredFields();
			List<Field> fieldSonList1= new ArrayList(Arrays.asList(fieldSon));
			List<Field> fieldSonList= new  ArrayList<Field>(fieldSonList1); //转换为ArrayLsit调用相关的remove方法
			for(Field field:fieldSonList1){
				if("serialVersionUID".equals(field.getName())){
					fieldSonList.remove(field);
				}
				if("createUser".equals(field.getName())){
					fieldSonList.remove(field);
				}
				if("createDept".equals(field.getName())){
					fieldSonList.remove(field);
				}
				if("createTime".equals(field.getName())){
					fieldSonList.remove(field);
				}
				if("updateUser".equals(field.getName())){
					fieldSonList.remove(field);
				}
				if("updateTime".equals(field.getName())){
					fieldSonList.remove(field);
				}
				if("status".equals(field.getName())){
					fieldSonList.remove(field);
				}
				if("isDeleted".equals(field.getName())){
					fieldSonList.remove(field);
				}
			}
			fieldFatherList.addAll(fieldSonList);
			// 获取类名
			String strName01 = clazz.getName();
			// 返回所有的属性
			for(Field field:fieldFatherList){
				TemplateFieldEntity templateFieldEntity=new TemplateFieldEntity();
				if("serialVersionUID".equals(field.getName())){
					templateFieldEntity.setFieldName("id");
				}else{
					templateFieldEntity.setFieldName(field.getName());
				}
				templateFieldEntity.setFieldType(field.getType().getName().substring(field.getType().getName().lastIndexOf(".")+1));
				templateFieldEntity.setBean(strName01);
				templateFieldEntity.setBeanName(bean.getBeanName());
				//反射获取ApiModelProperty注解上的注释
				ApiModelProperty api = field.getAnnotation(ApiModelProperty.class);
				if(null != api) {
					templateFieldEntity.setFieldTitle(api.value());
				}
				templateFieldEntity.setCode(bean.getFormCode());
				templateFieldEntity.setSort(200);
				templateFieldEntity.setIsShow("false");
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
