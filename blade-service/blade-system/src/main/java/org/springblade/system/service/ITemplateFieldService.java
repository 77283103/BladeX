package org.springblade.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.system.entity.TemplateEntity;
import org.springblade.system.entity.TemplateFieldEntity;
import java.util.List;

/**
 *  服务类
 *
 * @author szw
 * @date : 2020-10-20 14:45:05
 */
public interface ITemplateFieldService extends BaseService<TemplateFieldEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param templateField
	 * @return
	 */
	IPage<TemplateFieldEntity> pageList(IPage<TemplateFieldEntity> page, TemplateFieldEntity templateField);

	/**
	 * 封装json
	 * @param list
	 * @return
	 */
	String json(List<TemplateFieldEntity> list);

	/**
	 * 分页查询
	 * @param list
	 * @param bean
	 * @return
	 */
	List<TemplateFieldEntity> selectField(List<TemplateFieldEntity> list,TemplateEntity bean);
}
