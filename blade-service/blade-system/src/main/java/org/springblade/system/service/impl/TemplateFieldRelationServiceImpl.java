package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.TemplateFieldEntity;
import org.springblade.system.entity.TemplateFieldRelationEntity;
import org.springblade.system.entity.TemplateRelationEntity;
import org.springblade.system.mapper.TemplateFieldRelationMapper;
import org.springblade.system.service.ITemplateFieldRelationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 关联表字段属性配置表 服务实现类
 *
 * @author szw
 * @date : 2020-10-23 17:24:44
 */
@Service
public class TemplateFieldRelationServiceImpl extends BaseServiceImpl<TemplateFieldRelationMapper, TemplateFieldRelationEntity> implements ITemplateFieldRelationService {

	@Override
	public IPage<TemplateFieldRelationEntity> pageList(IPage<TemplateFieldRelationEntity> page, TemplateFieldRelationEntity templateFieldRelation) {
		return baseMapper.pageList(page, templateFieldRelation);
	}

	@Override
	public List<TemplateFieldRelationEntity> selectField(List<TemplateFieldRelationEntity> list, TemplateRelationEntity bean) {
		List<TemplateFieldRelationEntity> listT=new ArrayList<TemplateFieldRelationEntity>();
		try {
			Class<?> clazz = Class.forName(bean.getBean());
			// 获取类名
			String strName01 = clazz.getName();
			// 返回所有的属性
			Field[] field02 = clazz.getDeclaredFields();
			for(Field field:field02){
				TemplateFieldRelationEntity templateFieldRelationEntity=new TemplateFieldRelationEntity();
				if("serialVersionUID".equals(field.getName())){
					templateFieldRelationEntity.setFieldName("id");
				}else{
					templateFieldRelationEntity.setFieldName(field.getName());
				}
				templateFieldRelationEntity.setFieldType(field.getType().getName().substring(field.getType().getName().lastIndexOf(".")+1));
				templateFieldRelationEntity.setBean(strName01);
				templateFieldRelationEntity.setBeanName(bean.getBeanName());
				templateFieldRelationEntity.setCode(bean.getFormCode());
				templateFieldRelationEntity.setSort(99);
				listT.add(templateFieldRelationEntity);
			}
			for(TemplateFieldRelationEntity templateField:listT){
				for(TemplateFieldRelationEntity tem:list){
					if(tem.getFieldName().equals(templateField.getFieldName())){
						BeanUtil.copy(tem,templateField);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		listT.sort(Comparator.comparing(TemplateFieldRelationEntity::getSort));
		return listT;

	}


}
