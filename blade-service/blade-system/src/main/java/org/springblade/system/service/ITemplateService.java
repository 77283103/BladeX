package org.springblade.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.system.entity.TemplateEntity;

/**
 * 表单模板 服务类
 *
 * @author szw
 * @date : 2020-10-19 20:00:56
 */
public interface ITemplateService extends BaseService<TemplateEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param template
	 * @return
	 */
	IPage<TemplateEntity> pageList(IPage<TemplateEntity> page, TemplateEntity template);
}
