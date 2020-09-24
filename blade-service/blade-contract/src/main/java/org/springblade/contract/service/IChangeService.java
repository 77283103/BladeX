package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ChangeEntity;
import org.springblade.core.mp.base.BaseService;

/**
 * 合同变更 服务类
 *
 * @author szw
 * @date : 2020-09-23 19:24:50
 */
public interface IChangeService extends BaseService<ChangeEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param change
	 * @return
	 */
	IPage<ChangeEntity> pageList(IPage<ChangeEntity> page, ChangeEntity change);
}
