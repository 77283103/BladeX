package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.TemplateEntity;

/**
 * 范本管理 服务类
 *
 * @author XHB
 * @date : 2020-09-23 20:17:13
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
