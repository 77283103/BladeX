package org.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.system.entity.TemplateFieldEntity;

/**
 *  Mapper 接口
 *
 * @author szw
 * @date : 2020-10-20 14:45:05
 */
public interface TemplateFieldMapper extends BaseMapper<TemplateFieldEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param TemplateField
	 * @return
	 */
	IPage<TemplateFieldEntity> pageList(IPage<TemplateFieldEntity> page, TemplateFieldEntity TemplateField);

}
