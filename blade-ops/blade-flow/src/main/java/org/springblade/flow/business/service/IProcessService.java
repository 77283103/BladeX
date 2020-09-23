package org.springblade.flow.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.flow.core.entity.ProcessEntity;

import java.util.List;
import java.util.Map;

/**
 * 流程定义信息表 服务类
 *
 * @author tianah
 * @date 2020-8-27
 */
public interface IProcessService extends BaseService<ProcessEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param process
	 * @return
	 */
	IPage<ProcessEntity> pageList(IPage<ProcessEntity> page, ProcessEntity process);

	/**
	 * 根据业务类型返回流程定义List
	 *
	 * @param businessType 业务类型
	 * @return 流程定义List
	 */
	List<ProcessEntity> getProcessByBusinessType(String businessType);

	/**
	 * 根据业务类型获取bean的字段
	 *
	 * @param businessType 业务类型
	 * @exception ClassNotFoundException 反射时找不到类
	 * @return 字段List
	 * @throws ClassNotFoundException 找不到类
	 */
	List<Map<String,String>> getBeanFields(String businessType);
}
