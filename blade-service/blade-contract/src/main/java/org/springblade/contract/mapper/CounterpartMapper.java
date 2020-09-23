package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CounterpartEntity;

/**
 * 相对方管理 Mapper 接口
 *
 * @author XHB
 * @date : 2020-09-23 19:35:04
 */
public interface CounterpartMapper extends BaseMapper<CounterpartEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param counterpart
	 * @return
	 */
	IPage<CounterpartEntity> pageList(IPage<CounterpartEntity> page, CounterpartEntity counterpart);

}
