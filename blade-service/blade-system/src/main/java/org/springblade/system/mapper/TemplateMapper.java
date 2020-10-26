package org.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.system.entity.TemplateEntity;

import java.util.HashMap;
import java.util.List;

/**
 * 表单模板 Mapper 接口
 *
 * @author szw
 * @date : 2020-10-19 20:00:56
 */
public interface TemplateMapper extends BaseMapper<TemplateEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param template
	 * @return
	 */
	IPage<TemplateEntity> pageList(IPage<TemplateEntity> page, TemplateEntity template);


	List<TemplateEntity> selectByCode(String code);
}
