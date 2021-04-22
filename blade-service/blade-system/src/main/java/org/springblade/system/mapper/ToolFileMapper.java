package org.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.system.entity.ToolFileEntity;
import org.springblade.system.vo.ToolFileRequestVO;

/**
 * 工具 Mapper 接口
 *
 * @author xhb
 * @date : 2021-04-22 10:09:29
 */
public interface ToolFileMapper extends BaseMapper<ToolFileEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param toolFile
	 * @return
	 */
	IPage<ToolFileEntity> pageList(IPage<ToolFileEntity> page, ToolFileRequestVO toolFile);

}
