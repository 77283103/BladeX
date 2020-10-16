package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.CounterpartEntity;

/**
 * 合同相对方的管理 服务类
 *
 * @author XHB
 * @date : 2020-09-18 21:13:55
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
