package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.TemplateEntity;
import org.springblade.system.entity.TemplateFieldEntity;
import org.springblade.system.mapper.TemplateMapper;
import org.springblade.system.service.ITemplateFieldService;
import org.springblade.system.service.ITemplateService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 表单模板 服务实现类
 *
 * @author szw
 * @date : 2020-10-19 20:00:56
 */
@Service
@Validated
@AllArgsConstructor
public class TemplateServiceImpl extends BaseServiceImpl<TemplateMapper, TemplateEntity> implements ITemplateService {

	private ITemplateFieldService templateFieldService;

	@Override
	public IPage<TemplateEntity> pageList(IPage<TemplateEntity> page, TemplateEntity template) {
		return baseMapper.pageList(page, template);
	}

	@Override
	public void saveBean(TemplateEntity bean) {
		try {
			Class<?> clazz = Class.forName(bean.getBean());
			// 获取类名
			String strName01 = clazz.getName();
			// 返回所有的属性
			Field[] field02 = clazz.getDeclaredFields();
			List<TemplateFieldEntity> list=new ArrayList<TemplateFieldEntity>();
			for(Field field:field02){
				TemplateFieldEntity templateFieldEntity=new TemplateFieldEntity();
				if("serialVersionUID".equals(field.getName())){
					templateFieldEntity.setFieldValue("id");
				}else{
					templateFieldEntity.setFieldName(field.getName());
				}
				templateFieldEntity.setFieldType(field.getType().getName().substring(field.getType().getName().lastIndexOf(".")+1));
				templateFieldEntity.setBean(strName01);
				templateFieldEntity.setBeanName(bean.getBeanName());
				templateFieldEntity.setCode(bean.getFormCode());
				templateFieldEntity.setSort(99);
				list.add(templateFieldEntity);
				templateFieldService.save(templateFieldEntity);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TemplateEntity> selectByCode(String code) {
		return baseMapper.selectByCode(code);
	}

	@Override
	public TemplateEntity selectTemplateByCode(String code){
		return baseMapper.selectTemplateByCode(code);
	}


}
