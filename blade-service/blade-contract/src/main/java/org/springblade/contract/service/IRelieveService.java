package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.RelieveEntity;

/**
 *  服务类
 *
 * @author szw
 * @date : 2020-09-23 20:10:29
 */
public interface IRelieveService extends BaseService<RelieveEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param relieve
	 * @return
	 */
	IPage<RelieveEntity> pageList(IPage<RelieveEntity> page, RelieveEntity relieve);
}
