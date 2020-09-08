package org.springblade.flow.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.flow.core.entity.ProcessEntity;

/**
 * 流程定义信息表 Mapper 接口
 *
 * @author tianah
 * @date 2020-8-27
 */
public interface ProcessMapper extends BaseMapper<ProcessEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param process
	 * @return
	 */
	IPage<ProcessEntity> pageList(IPage<ProcessEntity> page, ProcessEntity process);

}
