package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.TemplateEntity;

/**
 * 范本管理 Mapper 接口
 *
 * @author XHB
 * @date : 2020-09-23 20:17:10
 */
public interface TemplateMapper extends BaseMapper<TemplateEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param template
	 * @return
	 */
	IPage<TemplateEntity> pageList(IPage<TemplateEntity> page, TemplateEntity template);

}
