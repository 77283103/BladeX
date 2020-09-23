package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.CounterpartEntity;

/**
 * 相对方管理 服务类
 *
 * @author XHB
 * @date : 2020-09-23 19:35:05
 */
public interface ICounterpartService extends BaseService<CounterpartEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param counterpart
	 * @return
	 */
	IPage<CounterpartEntity> pageList(IPage<CounterpartEntity> page, CounterpartEntity counterpart);
}
