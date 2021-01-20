package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.BusServiceContract1Entity;

/**
 * 班车服务合同子表1 Mapper 接口
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:29:13
 */
public interface BusServiceContract1Mapper extends BaseMapper<BusServiceContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param busServiceContract1
	 * @return
	 */
	IPage<BusServiceContract1Entity> pageList(IPage<BusServiceContract1Entity> page, BusServiceContract1Entity busServiceContract1);

}
