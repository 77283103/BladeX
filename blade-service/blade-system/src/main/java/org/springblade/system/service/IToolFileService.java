package org.springblade.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.system.entity.ToolFileEntity;
import org.springblade.system.vo.ToolFileRequestVO;
import org.springblade.system.vo.ToolFileResponseVO;

/**
 * 工具 服务类
 *
 * @author xhb
 * @date : 2021-04-22 10:09:29
 */
public interface IToolFileService extends BaseService<ToolFileEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param toolFile
	 * @return
	 */
	IPage<ToolFileEntity> pageList(IPage<ToolFileEntity> page, ToolFileRequestVO toolFile);

	ToolFileResponseVO getById(Long id);

}
