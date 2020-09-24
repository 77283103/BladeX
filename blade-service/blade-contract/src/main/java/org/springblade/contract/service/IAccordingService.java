package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.AccordingEntity;

/**
 * 合同依据管理 服务类
 *
 * @author XHB
 * @date : 2020-09-24 14:20:32
 */
public interface IAccordingService extends BaseService<AccordingEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param according
	 * @return
	 */
	IPage<AccordingEntity> pageList(IPage<AccordingEntity> page, AccordingEntity according);
}
